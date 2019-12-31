package app.web.controller;
import app.jpa.entity.Post;
import app.jpa.service.UserService;
import app.jpa.entity.User;
import app.web.forms.PostNewForm;
import app.web.forms.UserRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sun.awt.X11.XSystemTrayPeer;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public String userRegister(Model model) {
        model.addAttribute("userRegisterForm", new UserRegisterForm());
        return "user-register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String userRegister(@ModelAttribute("userRegisterForm") @Valid UserRegisterForm userRegisterForm, BindingResult result) {
        if (result.hasErrors()) {
            return "user-register";
        }
        String name = userRegisterForm.getUserName();
        User user = new User(name);
        userService.insert(user);
        return "redirect:/users/" + user.getId();
    }

    @RequestMapping("/{userId}")
    public ModelAndView userView(@PathVariable("userId") long userId) {
        Optional<User> user = userService.find(userId);
        Map<String, Object> model = Collections.singletonMap("user", user.orElse(null));
        return new ModelAndView("user-profile", model);
    }

    @RequestMapping("/{userId}/home")
    public String userHomeView(@PathVariable("userId") long userId, Model model) {
        Optional<User> user = userService.find(userId);
        //Map<String, Object> model = Collections.singletonMap("user", user.orElse(null));
        model.addAttribute("user", user.orElse(null));
        List<Post> posts = new ArrayList<>();
        for (User friend: user.orElse(null).getFollowing()) {
            for(Post post:friend.getPosts()){
                posts.add(post);
            }
        }
        Collections.sort(posts);
        Collections.reverse(posts);
        model.addAttribute("newPosts", posts);
        return "user-home";
    }

    @RequestMapping("/{userId}/friends")
    public String userFriendsView(@PathVariable("userId") long userId, Model model) {
        Optional<User> user = userService.find(userId);
        //Map<String, Object> model = Collections.singletonMap("user", user.orElse(null));
        model.addAttribute("user", user.orElse(null));
        return "user-friends";
    }

    @RequestMapping("/{userId}/unfollow/{friendId}")
    public String unfollowUser(@PathVariable("userId") long userId,
                               Model model, @PathVariable("friendId") long friendId){
        User user = userService.find(userId).get();
        model.addAttribute("user", user);
        User friend = userService.find(friendId).get();
        Set<User> friends = user.getFollowing();
        friends.remove(friend);
        user.setFollowing(friends);
        userService.update(user);
        return "user-friends";
    }

    @RequestMapping(value="/{userId}/edit")
    public String editUser(Model model, @PathVariable("userId") long userId){
        Optional<User> user = userService.find(userId);
        model.addAttribute("userRegisterForm", new UserRegisterForm(user.get()));
        model.addAttribute("user", user.get());
        return "user-register";
    }

    @RequestMapping(value = "/{userId}/edit", method = RequestMethod.POST)
    public String editUser(
            @PathVariable("userId") long userId,
            @ModelAttribute("userRegisterForm") @Valid UserRegisterForm userRegisterForm,
            BindingResult result) {
        if (result.hasErrors()) {
            return "user-register";
        }
        User user = userService.find(userId)
                .orElseThrow(RuntimeException::new);
        user.setUserName(userRegisterForm.getUserName());
        userService.update(user);
        return "redirect:/users/" + user.getId();
    }
}

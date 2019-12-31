package app.web.controller;

import app.jpa.entity.Post;
import app.jpa.entity.User;
import app.jpa.service.PostService;
import app.jpa.service.UserService;
import app.web.forms.PostNewForm;
import app.web.forms.UserRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final UserService userService;
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @RequestMapping("/{userId}/new")
    public String newPost(Model model, @PathVariable("userId") long userId) {
        model.addAttribute("postNewForm", new PostNewForm());
        Optional<User> user = userService.find(userId);
        model.addAttribute("user", user.orElse(null));
        return "post-new";
    }

    @RequestMapping(value = "/{userId}/new", method = RequestMethod.POST)
    public String newPost(@ModelAttribute("postNewForm") @Valid PostNewForm postNewForm, BindingResult result, @PathVariable("userId") long userId) {
        if (result.hasErrors()) {
            return "post-new";
        }
        Optional<User> user = userService.find(userId);
        Post post = new Post(postNewForm.getPostName(), postNewForm.getPrice(), postNewForm.getDescription(),
                             postNewForm.getQuantityAvailable(), user.get());
        postService.insert(post);
        return "redirect:/users/" + user.get().getId();
    }

    @RequestMapping(value="/{userId}/{postId}/delete")
    public String deletePost(Model model, @PathVariable("userId") long userId, @PathVariable("postId") long postId){
        Optional<User> user = userService.find(userId);
        postService.deleteById(postId);
        return "redirect:/users/" + user.get().getId();
    }

    @RequestMapping(value="/{userId}/{postId}/edit")
    public String editPost(Model model, @PathVariable("userId") long userId,
                           @PathVariable("postId") long postId){
        Optional<User> user = userService.find(userId);
        Post post = postService.find(postId).get();
        model.addAttribute("postNewForm", new PostNewForm(post));
        model.addAttribute("user", user.get());
        return "post-new";
    }

    @RequestMapping(value = "/{userId}/{postId}/edit", method = RequestMethod.POST)
    public String editPost(
            @PathVariable("userId") long userId, @PathVariable("postId") long postId,
            @ModelAttribute("postNewForm") @Valid PostNewForm postNewForm,
            BindingResult result) {
        if (result.hasErrors()) {
            return "post-new";
        }
        User user = userService.find(userId)
                .orElseThrow(RuntimeException::new);
        Post post = postService.find(postId)
                .orElseThrow(RuntimeException::new);

        post.setDescription(postNewForm.getDescription());
        post.setPostName(postNewForm.getPostName());
        post.setPrice(postNewForm.getPrice());
        post.setQuantityAvailable(postNewForm.getQuantityAvailable());
        postService.update(post);
        return "redirect:/users/" + user.getId();
    }
}

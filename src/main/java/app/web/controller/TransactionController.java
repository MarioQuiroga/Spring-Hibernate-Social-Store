package app.web.controller;
import app.jpa.entity.Post;
import app.jpa.entity.Transaction;
import app.jpa.entity.User;
import app.jpa.service.PostService;
import app.jpa.service.TransactionService;
import app.jpa.service.UserService;
import app.web.forms.PostNewForm;
import app.web.forms.TransactionNewForm;
import app.web.forms.UserRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/transactions")
public class TransactionController {
    private final PostService postService;
    private final UserService userService;
    private final TransactionService transactionService;

    public TransactionController(PostService postService, UserService userService, TransactionService transactionService) {
        this.postService = postService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @RequestMapping("/{userId}")
    public String transactionView(Model model, @PathVariable("userId") long userId){
        User user = userService.find(userId).get();
        Set<Transaction> transactions = user.getTransactions();
        List<Transaction> sells = new ArrayList<>();
        for (Post post: user.getPosts()) {
            for (Transaction transaction : post.getTransactions()) {
                sells.add(transaction);
            }
        }
        model.addAttribute("transactions", transactions);
        model.addAttribute("user", user);
        model.addAttribute("sells", sells);
        return "transaction-home";
    }

    @RequestMapping("/{buyerId}/{postId}/{sellerId}")
    public String buy(Model model, @PathVariable("buyerId") long buyerId,
                      @PathVariable("postId") long postId, @PathVariable("sellerId") long sellerId){
        Post post = postService.find(postId).get();
        User user = userService.find(buyerId).get();
        model.addAttribute("post", post);
        model.addAttribute("user", user);
        model.addAttribute("transactionNewForm", new TransactionNewForm());
        return "transaction-new";
    }

    @RequestMapping(value = "/{buyerId}//{postId}//{sellerId}", method = RequestMethod.POST)
    public String buy(@ModelAttribute("transactionNewForm") @Valid TransactionNewForm transactionNewForm,
                               BindingResult result,
                               @PathVariable("buyerId") long buyerId,
                               @PathVariable("postId") long postId,
                               @PathVariable("sellerId") long sellerId) {
        if (result.hasErrors()) {
            return "transaction-new";
        }
        Integer quantity = transactionNewForm.getQuantity();
        Post post = postService.find(postId).get();
        User buyer = userService.find(buyerId).get();
        User seller = userService.find(sellerId).get();
        if(post.getQuantityAvailable()>=quantity){
            Transaction transaction = new Transaction(quantity, 0, post, buyer);
            transactionService.insert(transaction);
            return "redirect:/users/" + buyer.getId();
        }else{
            return "transaction-new";
        }


    }

    @RequestMapping("/score/{idTransaction}/{score}")
    public String score(Model model,
                        @PathVariable("idTransaction") long idTransaction,
                        @PathVariable("score") int score){
        Transaction transaction = transactionService.find(idTransaction);
        if (transaction!=null){
            transaction.setScoreTransaction(score);
            transactionService.update(transaction);
        }
        return "redirect:/transactions/" + idTransaction;
    }

    @RequestMapping("confirm/{transactionId}/{userId}")
    public String confirm(Model model,
                          @PathVariable("transactionId") long transactionId,
                          @PathVariable("userId") long userId){

        Transaction transaction = transactionService.find(transactionId);
        Post post = transaction.getPost();
        if(post.getQuantityAvailable()<=transaction.getQuantity()){
            post.setQuantityAvailable(post.getQuantityAvailable()-transaction.getQuantity());
        }
        postService.update(post);
        transaction.setState("confirm");
        transactionService.update(transaction);
        return "redirect:/transactions/"  + userId;

    }
}

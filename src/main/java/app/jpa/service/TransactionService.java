package app.jpa.service;

import app.jpa.entity.Post;
import app.jpa.entity.Transaction;
import app.jpa.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionService {
    List<Transaction> findByPost(Post post);

    List<Transaction> findByBuyer(User user);

    List<Transaction> findByPostAndBuyer_UserName(Post post, String userName);

    @Transactional
    void deleteById(Long id);

    @Transactional
    void insert(Transaction transaction);

    @Transactional
    void remove(Transaction transaction);

    @Transactional
    void update(Transaction transaction);
}

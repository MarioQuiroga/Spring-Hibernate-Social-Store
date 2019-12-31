package app.jpa.service;

import app.jpa.entity.Post;
import app.jpa.entity.Transaction;
import app.jpa.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionService {

    Transaction find(long id);

    @Transactional
    void insert(Transaction transaction);

    @Transactional
    void update(Transaction transaction);
}

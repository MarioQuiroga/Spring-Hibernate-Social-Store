package app.jpa.service;

import app.jpa.entity.Post;
import app.jpa.entity.Transaction;
import app.jpa.entity.User;
import app.jpa.repos.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository trepo;

    @Inject
    public TransactionServiceImpl(TransactionRepository trepo) {
        this.trepo = trepo;
    }

    @Transactional
    @Override
    public void insert(Transaction transaction) {
        trepo.save(transaction);
    }

    @Transactional
    @Override
    public void update(Transaction transaction) {
        trepo.save(transaction);
    }

    @Override
    public Transaction find(long id){
        return trepo.findById(id).orElse(null);
    }
}

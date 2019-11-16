package jpa.repos;

import jpa.entity.Post;
import jpa.entity.Transaction;
import jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByPost(Post post);

    List<Transaction> findByBuyer(User user);

    List<Transaction> findByPostAndBuyer_UserName(Post post, String userName);

    void deleteById(Long id);

}

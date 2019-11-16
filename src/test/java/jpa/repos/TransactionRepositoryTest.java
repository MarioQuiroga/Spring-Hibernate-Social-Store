package jpa.repos;

import jpa.DataSourcesConfig;
import jpa.entity.Post;
import jpa.entity.Transaction;
import jpa.entity.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataSourcesConfig.class)
@Transactional
public class TransactionRepositoryTest {

    @Rule
    public TestHelper testHelper = new TestHelper();

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Test
    public void createTest() {
        Transaction transaction = testHelper.getTransaction();
        Transaction saved = transactionRepo.save(transaction);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    public void findByPostTest(){
        User mario = testHelper.mario();
        User mariano = testHelper.mariano();
        Post post = testHelper.getPost1_mario();
        Transaction transaction = testHelper.getTransaction();

        testHelper.persist(userRepo, mario, mariano);
        testHelper.persist(postRepo, post);
        Transaction transactionSaved = transactionRepo.save(transaction);

        List<Transaction> transactions = transactionRepo.findByPost(post);
        assertThat(transactions.get(0).getId()).isEqualTo(transactionSaved.getId());
    }

    @Test
    public void findByBuyerTest(){
        User mario = testHelper.mario();
        User mariano = testHelper.mariano();
        Post post = testHelper.getPost1_mario();
        Transaction transaction = testHelper.getTransaction();

        testHelper.persist(userRepo, mario, mariano);
        testHelper.persist(postRepo, post);
        Transaction transactionSaved = transactionRepo.save(transaction);

        List<Transaction> transactions = transactionRepo.findByBuyer(mariano);
        assertThat(transactions.get(0).getId()).isEqualTo(transactionSaved.getId());
    }

    @Test
    public void findByPostAndBuyer_UserNameTest(){
        User mario = testHelper.mario();
        User mariano = testHelper.mariano();
        Post post = testHelper.getPost1_mario();
        Transaction transaction = testHelper.getTransaction();

        testHelper.persist(userRepo, mario, mariano);
        testHelper.persist(postRepo, post);
        Transaction transactionSaved = transactionRepo.save(transaction);

        List<Transaction> transactions = transactionRepo.findByPostAndBuyer_UserName(post, "Mariano Goldman");
        assertThat(transactions.get(0).getId()).isEqualTo(transactionSaved.getId());
    }
}

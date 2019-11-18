package app.jpa.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@RunWith(SpringRunner.class)
@Transactional
public class PostServiceTest {

    @Inject
    PostService postService;

    @Inject
    UserService userService;

    @Inject
    TransactionService transactionService;

    @Test
    public void findByUserTest(){
        
    }

    @Test
    public void findByDescriptionContainingTest(){

    }
}

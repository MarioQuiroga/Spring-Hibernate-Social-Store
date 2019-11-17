package app.jpa;

import app.jpa.entity.Post;
import app.jpa.entity.User;
import app.jpa.repos.PostRepository;
import app.jpa.repos.UserRepository;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class DataSourcesConfigTest {

    private static final Logger logger = LoggerFactory.getLogger(DataSourcesConfigTest.class);

    @Test
    public void dataBaseReadTest() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DataSourcesConfig.class);

        UserRepository userRepo = context.getBean(UserRepository.class);
        PostRepository postRepo = context.getBean(PostRepository.class);

        List<User> users = userRepo.findAll();
        assertThat(users).isEmpty();

        List<Post> posts = postRepo.findAll();
        assertThat(posts).isEmpty();
    }
/*
    @Test
    public void dataBaseWriteTest() {
        ApplicationContext model.context = new AnnotationConfigApplicationContext(DataSourcesConfig.class);

        UserRepository userRepo = model.context.getBean(UserRepository.class);
        PostRepository postRepo = model.context.getBean(PostRepository.class);
        PlatformTransactionManager transactionManager = model.context.getBean(PlatformTransactionManager.class);

        User userSaved = new TransactionTemplate(transactionManager).execute(status -> {
            User user = new User("J.R.R.");
            logger.debug("autor antes de guardar = {}", user);
            userRepo.save(user);
            logger.debug("autor ya guardado = {}", user);

            Post post = new Post("auto", 100, "auto",1, user);
            post.setUser(user);

            logger.debug("libro antes de guardar = {}", post.getPostName(), );

            return postRepo.save(post);
        });

        logger.debug("libro ya guardado = {}", userSaved.getUserName());
    }*/
}

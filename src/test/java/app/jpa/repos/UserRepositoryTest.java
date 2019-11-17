package app.jpa.repos;

import app.jpa.DataSourcesConfig;
import app.jpa.entity.Post;
import app.jpa.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import org.junit.Rule;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DataSourcesConfig.class)
@Transactional
public class UserRepositoryTest {

    @Rule
    public TestHelper testHelper = new TestHelper();

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    @Test
    public void createTest() {
        User user = testHelper.mario();
        User saved = userRepo.save(user);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    public void searchByPostTest() {
        User mario = testHelper.mario();
        Post post = testHelper.getPost1_mario();

        testHelper.persist(userRepo, mario);
        testHelper.persist(postRepo, post);
        User foundUser = userRepo.findByPosts(post);

        assertThat(foundUser.getUserName()).isEqualTo(mario.getUserName());
    }

    @Test
    public void searchByNameTest(){
        List<User> founds = userRepo.findByUserNameContaining("Ma");
        assertThat(founds.get(0).getUserName()).isEqualTo("Mario Quiroga");
        assertThat(founds.get(1).getUserName()).isEqualTo("Mariano Goldman");
    }

    @Test
    public void removeByUserNameTest(){
        User mario = testHelper.mario();
        testHelper.persist(userRepo, mario);
        userRepo.removeByUserName(mario.getUserName());
        assertThat(userRepo.findById(mario.getId())).isNotPresent();
    }
}

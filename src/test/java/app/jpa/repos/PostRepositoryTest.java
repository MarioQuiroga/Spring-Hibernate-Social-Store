package app.jpa.repos;

import app.jpa.DataSourcesConfig;
import app.jpa.entity.Post;
import app.jpa.entity.User;
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
public class PostRepositoryTest {

    @Rule
    public TestHelper testHelper = new TestHelper();

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PostRepository postRepo;

    @Test
    public void createTest() {
        Post post = testHelper.getPost1_mario();
        Post saved = postRepo.save(post);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    public void findByUserTest(){
        Post post = testHelper.getPost1_mario();
        Post post1 = testHelper.getPost2_mario();
        User mario = testHelper.mario();

        testHelper.persist(userRepo, mario);
        testHelper.persist(postRepo, post);
        testHelper.persist(postRepo, post1);

        List<Post> founds = postRepo.findByUser(mario);

        assertThat(founds.get(0).getPostName()).isEqualTo(post.getPostName());
        assertThat(founds.get(1).getPostName()).isEqualTo(post1.getPostName());
    }

    @Test
    public void findByDescriptionContainingTest(){
        Post post = testHelper.getPost1_mario();
        Post post1 = testHelper.getPost2_mario();
        User mario = testHelper.mario();

        testHelper.persist(userRepo, mario);
        testHelper.persist(postRepo, post);
        testHelper.persist(postRepo, post1);

        List<Post> founds = postRepo.findByDescriptionContaining("buen estado");

        assertThat(founds.get(0).getPostName()).isEqualTo(post.getPostName());
        assertThat(founds.get(1).getPostName()).isEqualTo(post1.getPostName());
    }

    @Test
    public void findByPostNameTest(){
        Post post = testHelper.getPost1_mario();
        User mario = testHelper.mario();

        testHelper.persist(userRepo, mario);
        testHelper.persist(postRepo, post);

        List<Post> founds = postRepo.findByPostName("Bicicleta");
        assertThat(founds.get(0).getPostName()).isEqualTo(post.getPostName());
    }

    @Test
    public void removeByPostNameTest(){
        User mario = testHelper.mario();
        Post post = testHelper.getPost1_mario();

        testHelper.persist(userRepo, mario);
        testHelper.persist(postRepo, post);

        postRepo.removeByPostName(post.getPostName());
        assertThat(postRepo.findById(post.getId())).isNotPresent();
    }
}

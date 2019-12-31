package app.jpa.repos;

import app.jpa.entity.Post;
import app.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPosts(Post post);

    List<User> findByUserNameContaining(String userName);

    void deleteById(Long id);

    List<User> removeByUserName(String userName);

    List<User> findByFollowers(User user);

}

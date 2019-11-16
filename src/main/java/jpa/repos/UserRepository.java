package jpa.repos;

import jpa.entity.Post;
import jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByPosts(Post post);

    List<User> findByUserNameContaining(String userName);

    void deleteById(Long id);

    List<User> removeByUserName(String userName);

}

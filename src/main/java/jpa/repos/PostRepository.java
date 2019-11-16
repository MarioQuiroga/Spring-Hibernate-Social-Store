package jpa.repos;

import jpa.entity.Post;
import jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByUser(User user);

    List<Post> findByDescriptionContaining(String description);

    List<Post> findByPostName(String postName);

    void deleteById(Long id);

    List<Post> removeByPostName(String postName);
}

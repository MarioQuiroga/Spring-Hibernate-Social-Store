package app.jpa.service;

import app.jpa.entity.Post;
import app.jpa.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findByUser(User user);

    List<Post> findByDescriptionContaining(String description);

    List<Post> findByPostName(String postName);

    @Transactional
    void deleteById(Long id);

    @Transactional
    List<Post> removeByPostName(String postName);

    @Transactional
    void insert(Post post);

    @Transactional
    void remove(Post post);

    @Transactional
    void update(Post post);

    Optional<Post> find(Long id);
}

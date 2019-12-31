package app.jpa.service;

import app.jpa.entity.Post;
import app.jpa.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PostService {

    @Transactional
    void deleteById(Long id);


    @Transactional
    void insert(Post post);

    @Transactional
    void update(Post post);

    Optional<Post> find(Long id);
}

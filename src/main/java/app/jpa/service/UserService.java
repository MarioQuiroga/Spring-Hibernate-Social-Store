package app.jpa.service;

import app.jpa.entity.Post;
import app.jpa.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {

    @Transactional
    void insert(User user);

    @Transactional
    void update(User user);

    Optional<User> find(Long id);
}

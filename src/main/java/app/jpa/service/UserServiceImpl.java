package app.jpa.service;

import app.jpa.entity.Post;
import app.jpa.entity.User;
import app.jpa.repos.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository urepo;

    @Inject
    public UserServiceImpl(UserRepository urepo) {
        this.urepo = urepo;
    }

    @Transactional
    @Override
    public void insert(User user) {
        urepo.save(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        urepo.save(user);
    }

    @Override
    public Optional<User> find(Long id) {
        return urepo.findById(id);
    }
}

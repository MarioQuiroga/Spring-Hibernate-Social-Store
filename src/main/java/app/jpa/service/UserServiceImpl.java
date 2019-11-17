package app.jpa.service;

import app.jpa.entity.Post;
import app.jpa.entity.User;
import app.jpa.repos.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository urepo;

    @Inject
    public UserServiceImpl(UserRepository urepo) {
        this.urepo = urepo;
    }

    @Override
    public User findByPosts(Post post) {
        return urepo.findByPosts(post);
    }

    @Override
    public List<User> findByUserNameContaining(String userName) {
        return urepo.findByUserNameContaining(userName);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        urepo.deleteById(id);
    }

    @Transactional
    @Override
    public List<User> removeByUserName(String userName) {
        return urepo.removeByUserName(userName);
    }

    @Transactional
    @Override
    public void insert(User user) {
        urepo.save(user);
    }

    @Transactional
    @Override
    public void remove(User user) {
        urepo.delete(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        urepo.save(user);
    }
}

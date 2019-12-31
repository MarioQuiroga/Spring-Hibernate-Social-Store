package app.jpa.service;

import app.jpa.entity.Post;
import app.jpa.entity.User;
import app.jpa.repos.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;

    @Inject
    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public List<Post> findByUser(User user) {
        return postRepo.findByUser(user);
    }

    @Override
    public List<Post> findByDescriptionContaining(String description) {
        return postRepo.findByDescriptionContaining(description);
    }

    @Override
    public List<Post> findByPostName(String postName) {
        return postRepo.findByPostName(postName);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        postRepo.deleteById(id);
    }

    @Transactional
    @Override
    public List<Post> removeByPostName(String postName) {
        return postRepo.removeByPostName(postName);
    }

    @Transactional
    @Override
    public void insert(Post post) {
        postRepo.save(post);
    }

    @Transactional
    @Override
    public void remove(Post post) {
        postRepo.delete(post);
    }

    @Transactional
    @Override
    public void update(Post post) {
        postRepo.save(post);
    }

    @Override
    public Optional<Post> find(Long id) {
        return postRepo.findById(id);
    }
}

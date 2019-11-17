package app.jpa.repos;

import app.jpa.entity.Post;
import app.jpa.entity.Transaction;
import app.jpa.entity.User;
import org.junit.rules.ExternalResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;

public class TestHelper extends ExternalResource {

    User mario = new User("Mario Quiroga");
    User mariano = new User("Mariano Goldman");
    Post post1_mario = new Post("Bicicleta", 1000, "Bicicleta playera buen estado", 1, mario);
    Post post2_mario = new Post("Escalera", 1000, "Escalera de aluminio plegable buen estado", 1, mario);
    Transaction transaction = new Transaction(1, 5, post1_mario, mariano);
    Transaction transaction1 = new Transaction(1, 5, post2_mario, mariano);

    User mario(){
        return mario;
    }
    User mariano(){
        return mariano;
    }
    Post getPost1_mario(){
        return  post1_mario;
    }
    Post getPost2_mario(){
        return  post2_mario;
    }
    Transaction getTransaction(){
        return transaction;
    }
    Transaction getTransaction1(){
        return transaction1;
    }

    <T, ID> void persist(JpaRepository<T, ID> repo, T... entities) {
        Arrays.stream(entities).forEach(repo::save);
        repo.flush();
    }

}

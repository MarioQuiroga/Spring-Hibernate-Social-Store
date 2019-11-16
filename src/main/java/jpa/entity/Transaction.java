package jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transactions")
public class Transaction implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "scoreTransaction")
    private int scoreTransaction;

    @JoinColumn(name = "id_post", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false, fetch = FetchType.EAGER)
    private Post post;

    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false, fetch = FetchType.EAGER)
    private User buyer;

    public Transaction(int quantity, int scoreTransaction, Post post, User buyer) {
        this.quantity = quantity;
        this.scoreTransaction = scoreTransaction;
        this.post = post;
        this.buyer = buyer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getScoreTransaction() {
        return scoreTransaction;
    }

    public void setScoreTransaction(int scoreTransaction) {
        this.scoreTransaction = scoreTransaction;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }
}

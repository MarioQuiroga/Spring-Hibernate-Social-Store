package jpa.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transactions")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "postName")
    private String postName;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "quantityAvailable")
    private int quantityAvailable;

    @OneToMany(mappedBy = "post")
    private Set<Transaction> transactions = new HashSet<>();

    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false, fetch = FetchType.EAGER)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

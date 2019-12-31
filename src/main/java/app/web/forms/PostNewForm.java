package app.web.forms;

import app.jpa.entity.AuditableBaseEntity;
import app.jpa.entity.Post;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

public class PostNewForm extends AuditableBaseEntity {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 200)
    private String postName;

    @NotNull
    private Double price;

    @NotEmpty
    @Size(min = 2, max = 200)
    private String description;

    @NotNull
    private Integer quantityAvailable;

    public PostNewForm(Post post) {
        this.id = post.getId();
        this.postName = post.getPostName();
        this.price = post.getPrice();
        this.description = post.getDescription();
        this.quantityAvailable = post.getQuantityAvailable();
    }

    public PostNewForm() {
    }

    public String getPostName() {
        return postName;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PostNewForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("postName='" + postName + "'")
                .toString();
    }
}

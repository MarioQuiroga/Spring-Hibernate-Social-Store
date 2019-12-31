package app.web.forms;

import app.jpa.entity.AuditableBaseEntity;
import app.jpa.entity.Transaction;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

public class TransactionNewForm extends AuditableBaseEntity {
    private Long id;

    @NotNull
    private Integer quantity;

    public TransactionNewForm(){

    }

    public TransactionNewForm(Transaction transaction){
        this.id = transaction.getId();
        this.quantity = transaction.getQuantity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TransactionNewForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("quantity='" + quantity + "'")
                .toString();
    }
}


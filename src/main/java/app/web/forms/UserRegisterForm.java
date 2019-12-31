package app.web.forms;

import app.jpa.entity.AuditableBaseEntity;
import app.jpa.entity.User;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.StringJoiner;

public class UserRegisterForm extends AuditableBaseEntity {
    public UserRegisterForm() {
    }

    public UserRegisterForm(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
    }

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 200)
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String name) {
        this.userName = name;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserRegisterForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("userName='" + userName + "'")
                .toString();
    }

}

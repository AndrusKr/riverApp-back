package by.andrus.riversappback.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public Role() {
    }

    public Role(String name) throws IllegalArgumentException {
        RoleName roleName = RoleName.valueOf(name);
        this.setId(roleName.id);
        this.setName(roleName.name());
    }

    @Override
    public String toString() {
        return "Role {" +
                "id: " + super.getId() + ", " +
                "name: " + name + "}";
    }

}

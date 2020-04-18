package by.andrus.riversappback.dto;

import by.andrus.riversappback.model.Role;
import by.andrus.riversappback.model.Status;
import by.andrus.riversappback.model.User;
import by.andrus.riversappback.service.RoleService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String status;
    private List<String> roleNames;

    public static AdminUserDto fromUser(User user) {
        AdminUserDto adminUserDto = new AdminUserDto();
        adminUserDto.setId(user.getId());
        adminUserDto.setUsername(user.getUsername());
        adminUserDto.setFirstName(user.getFirstName());
        adminUserDto.setLastName(user.getLastName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setPassword(user.getPassword());
        adminUserDto.setStatus(user.getStatus().name());
        adminUserDto.setRoleNames(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return adminUserDto;
    }

    public User toUser() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setStatus(Status.valueOf(this.status));
        user.setRoles(
                this.roleNames.stream().map(
                        role -> roleService.getByName(role)
                ).filter(Objects::nonNull).collect(Collectors.toList()));
        return user;
    }
}

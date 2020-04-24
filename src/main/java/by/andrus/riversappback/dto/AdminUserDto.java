package by.andrus.riversappback.dto;

import by.andrus.riversappback.model.Role;
import by.andrus.riversappback.model.Status;
import by.andrus.riversappback.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
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
        adminUserDto.setPassword("PASSWORD_HIDDEN");
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
        if (this.getStatus() != null)
            user.setStatus(Status.valueOf(this.status));
        if (this.roleNames != null)
            user.setRoles(this.roleNames.stream().map(Role::new).collect(Collectors.toList()));
        return user;
    }
}

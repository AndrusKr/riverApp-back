package by.andrus.riversappback.rest.admin;

import by.andrus.riversappback.dto.AdminUserDto;
import by.andrus.riversappback.model.User;
import by.andrus.riversappback.service.RoleService;
import by.andrus.riversappback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/admin/users")
public class AdminUserRestControllerV1 {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminUserRestControllerV1(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<AdminUserDto> createUser(@RequestBody @Valid AdminUserDto adminUserDto) {
        User registeredUser = this.userService.register(adminUserDto.toUser());
        return registeredUser == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(AdminUserDto.fromUser(registeredUser), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AdminUserDto>> getAllUsers() {
        List<User> users = userService.getAll();
        return users == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(
                users
                        .stream().map(AdminUserDto::fromUser)
                        .collect(Collectors.toList())
                , HttpStatus.OK
        );
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        return user == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(AdminUserDto.fromUser(user), HttpStatus.OK);
    }
}

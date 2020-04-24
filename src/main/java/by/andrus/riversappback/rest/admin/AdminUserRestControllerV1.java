package by.andrus.riversappback.rest.admin;

import by.andrus.riversappback.dto.AdminUserDto;
import by.andrus.riversappback.model.User;
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

    @Autowired
    public AdminUserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<AdminUserDto> createUser(@RequestBody @Valid AdminUserDto adminUserDto) {
        User registeredUser;
        try {
            registeredUser = this.userService.create(adminUserDto.toUser());
        } catch (Throwable throwable) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(AdminUserDto.fromUser(registeredUser), HttpStatus.OK);
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
        User user = userService.getById(id);
        return user == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(AdminUserDto.fromUser(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<HttpStatus> deleteUserById(@PathVariable(name = "id") Long id) {
        try {
            userService.deleteById(id);
        } catch (Throwable throwable) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

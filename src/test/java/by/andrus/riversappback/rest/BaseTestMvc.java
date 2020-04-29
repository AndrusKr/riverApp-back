package by.andrus.riversappback.rest;

import by.andrus.riversappback.RiversAppBackApplication;
import by.andrus.riversappback.model.RoleName;
import by.andrus.riversappback.security.jwt.JwtProvider;
import by.andrus.riversappback.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = RiversAppBackApplication.class,
        properties = {
                "logging.level.jdbc=OFF",
                "logging.level.jdbc.sqlonly=OFF"
        })
@AutoConfigureMockMvc
public abstract class BaseTestMvc {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserService userService;

    protected Long getUserId(String username) {
        return userService.getByUsername(username).getId();
    }

    protected String getTokenWithRole(RoleName roleName) {
        switch (roleName) {
            case ROLE_ADMIN:
                return "Bearer_" + jwtProvider.createToken(userService.getByUsername("andrus"));
            case ROLE_USER:
                return "Bearer_" + jwtProvider.createToken(userService.getByUsername("ld"));
        }
        return null;
    }
}

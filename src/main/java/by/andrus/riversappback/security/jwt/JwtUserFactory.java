package by.andrus.riversappback.security.jwt;

import by.andrus.riversappback.model.Role;
import by.andrus.riversappback.model.Status;
import by.andrus.riversappback.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToAuthorities(user.getRoles()),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated());
    }

    private static List<GrantedAuthority> mapToAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}

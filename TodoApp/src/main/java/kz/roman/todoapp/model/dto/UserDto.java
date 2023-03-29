package kz.roman.todoapp.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.roman.todoapp.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements UserDetails {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
    private LocalDateTime lastAccessDate;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDto build(UserEntity user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getCode().name()))
                .collect(Collectors.toList());
        return new UserDto(user.getId(), user.getEmail(), user.getFirstName(),
                            user.getLastName(), user.getPassword(), user.getLastAccessDate(),
                            authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

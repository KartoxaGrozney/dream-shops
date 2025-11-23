package com.kartoxa.dreamshops.security.user;

import com.kartoxa.dreamshops.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopUserDetails implements UserDetails {

    private Long id;
    private String email;
    private String password;
    private Collection<GrantedAuthority> authorities;

    // Статический фабричный метод для создания ShopUserDetails из User
    public static ShopUserDetails buildUserDetails(User user) {
        Collection<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new ShopUserDetails(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    // Методы интерфейса UserDetails

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
        return email; // Используем email как username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Аккаунт не истек
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Аккаунт не заблокирован
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Пароль не истек
    }

    @Override
    public boolean isEnabled() {
        return true; // Аккаунт активен
    }
}

package com.example.mumschedpoc.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.mumschedpoc.entities.enums.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class SpringSecurityUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public SpringSecurityUser() {
    }

    public SpringSecurityUser(Integer id, String email, String password, Set<UserRole> roles) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = roles.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
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
        return email;
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

    public boolean hasRole(UserRole role) {
        return getAuthorities().contains(new SimpleGrantedAuthority(role.getDescription()));
    }

    public List<Integer> getUserRoleIds() {
        return getAuthorities().stream().map(a -> UserRole.getCodeFromDescription(a.getAuthority())).collect(Collectors.toList());
    }
}



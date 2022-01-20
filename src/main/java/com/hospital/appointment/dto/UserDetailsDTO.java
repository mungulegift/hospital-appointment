package com.hospital.appointment.dto;

import java.util.*;

import com.hospital.appointment.model.RoleEntity;
import com.hospital.appointment.model.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsDTO implements UserDetails {

private UserEntity userEntity;

public UserDetailsDTO(UserEntity userEntity) {
this.userEntity = userEntity;
}

@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<RoleEntity> roleEntities = userEntity.getRoles();
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();

    for (RoleEntity roleEntity : roleEntities) {
    authorities.add(new SimpleGrantedAuthority(roleEntity.getName()));
    }

    return authorities;
    }

    @Override
    public String getPassword() {
    return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
    return userEntity.getUsername();
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
    return userEntity.isEnabled();
    }

}
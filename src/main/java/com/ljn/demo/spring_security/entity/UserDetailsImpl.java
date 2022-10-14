package com.ljn.demo.spring_security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"user", "enabled","accountNonExpired", "accountNonLocked", "credentialsNonExpired", "authorities"})
//SimpleGrantedAuthority没有无参构造函数，反序列化的时候无法创建对象，所以反序列化会报错
@JsonIgnoreProperties(value = "authorities", ignoreUnknown = true)
public class UserDetailsImpl implements UserDetails {
    private User user;
    private List<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> collection = new ArrayList<>();
        for (String permission : permissions) {
            collection.add(new SimpleGrantedAuthority(permission));
        }
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
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

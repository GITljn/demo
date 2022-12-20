package com.ljn.demo.spring_security.expression;

import com.ljn.demo.spring_security.entity.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityExpression {
    public boolean hasAuthority(String authority) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        List<String> permissions = userDetails.getPermissions();
        return permissions.contains(authority);
    }
}

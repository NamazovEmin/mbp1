/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) 2023, TopS BI LLC. All rights reserved.
 * http://www.topsbi.ru
 */

/*
 * Copyright (c) . Namazov Emin. All rights reserved.
 */

package ru.namazov.mbp1.login.presenter;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class LoginPresenter {

//    private final AuthenticationManager manager;

    public void auth(String username, String password) {
//        Authentication authenticate = manager.authenticate(
//                new UsernamePasswordAuthenticationToken(username, password));
//        SecurityContext context = SecurityContextHolder.getContext();
//        context.setAuthentication(authenticate);
    }

    public boolean getRole() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication().getAuthorities().stream()
                .filter(i -> i.getAuthority().equals("ROLE_ADMIN"))
                .count() == 1;
    }
}

package com.springboot.webapp.registration;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService {

    private final UserInterface userInterface;

    public MyUserDetailsService(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public User findUserByUsername(String username) {
        User user = userInterface.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException("User not found");

        return user;
    }
}

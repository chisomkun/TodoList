package com.chiz.todo.todoList.appuser;

import com.chiz.todo.todoList.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User not found with username: %s", email)));
    }

    public MessageResponse signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository
                .findByEmail(appUser.getUsername())
                .isPresent();
        if (userExists) {
            throw new IllegalStateException("User already exists");
        }
        String encodedpwd = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedpwd);

        appUserRepository.save(appUser);
        return new MessageResponse("User registered successfully");
    }

}

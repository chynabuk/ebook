package com.application.electronic_book.service.sec;

import com.application.electronic_book.entity.User;
import com.application.electronic_book.exception.EBookException;
import com.application.electronic_book.model.user.UserDetailModel;
import com.application.electronic_book.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserServ {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new EBookException("User not found"));
        return new UserDetailModel(user);
    }
}
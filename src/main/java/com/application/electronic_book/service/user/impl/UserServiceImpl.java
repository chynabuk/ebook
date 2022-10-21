package com.application.electronic_book.service.user.impl;

import com.application.electronic_book.entity.Group;
import com.application.electronic_book.entity.Order;
import com.application.electronic_book.entity.User;
import com.application.electronic_book.model.user.*;
import com.application.electronic_book.repository.UserRepository;
import com.application.electronic_book.service.others.GroupService;
import com.application.electronic_book.service.user.UserService;
import com.application.electronic_book.util.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder encoder;

    private final GroupService groupService;

//    @Autowired
//    private JwtUtil jwtUtil;

//    private final AuthenticationManager authenticationManager;

    @Override
    public UserModel create(UserRegistrationModel userRegistrationModel) {
        Group group = groupService.getEntityById(userRegistrationModel.getGroupId());

        User user = User.builder()
                .email(userRegistrationModel.getEmail())
//                .password(encoder.encode(userRegistrationModel.getPassword()))
                .phone(userRegistrationModel.getPhone())
                .fullName(userRegistrationModel.getFullName())
                .group(group)
                .build();
        userRepository.save(user);

        return toModel(user);
    }

    @Override
    public String delete(Long id) {
        User user = getEntityById(id);

        List<Order> orders= user.getOrders();

        if (orders != null || !orders.isEmpty()){
            for (Order order : orders){
                order.setIsDeleted(true);
            }
        }

        userRepository.save(user);
        userRepository.delete(user);
        return "User with id:" + id + " was deleted";
    }

    @Override
    public User getEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id:" + id + " was not found"));
    }

    @Override
    public UserModel getById(Long id) {
        return toModel(getEntityById(id));
    }

    @Override
    public List<UserModel> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> toModel(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserTokenModel login(UserAuthModel userAuthModel) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(userAuthModel.getEmail(), userAuthModel.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetailModel userDetailModel = (UserDetailModel) authentication.getPrincipal();
//        String jwtToken = jwtUtil.generateJwtToken(userDetailModel);
//
//        return new UserTokenModel(userDetailModel.getUsername(), jwtToken);
        return null;
    }

    @Override
    public UserModel toModel(User user) {
        return UserModel.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getAuthority())
                .phone(user.getPhone())
                .build();
    }
}

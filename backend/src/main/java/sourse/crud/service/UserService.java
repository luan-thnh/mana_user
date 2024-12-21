package sourse.crud.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sourse.crud.dto.request.UserCreationRequest;
import sourse.crud.dto.request.UserUpdationRequest;
import sourse.crud.dto.response.UserResponse;
import sourse.crud.entity.User;
import sourse.crud.exception.AppException;
import sourse.crud.exception.ErrorCode;
import sourse.crud.mapper.UserMapper;
import sourse.crud.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
//    create a constructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    public List<UserResponse> index() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public User findById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public UserResponse show(String id) {
        return userMapper.toUserResponse(findById(id));
    }

    public UserResponse store(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXITTED);
        }

        if (!request.isPasswordConfirmed()) {
            throw new IllegalArgumentException("Password and Confirm Password not match.");
        }

        User user = userMapper.toUser(request);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashedPassword);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public UserResponse update(String id, UserUpdationRequest request) {
        User user = this.findById(id);
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public String delete(String id) {
        User user = this.findById(id);
        userRepository.delete(user);
        return "Delete success";
    };
}

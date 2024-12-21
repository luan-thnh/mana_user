package sourse.crud.controller.api;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import sourse.crud.core.ApiResponse;
import sourse.crud.dto.request.AuthenticationRequest;
import sourse.crud.dto.request.UserCreationRequest;
import sourse.crud.dto.response.AuthenticationResponse;
import sourse.crud.dto.response.UserResponse;
import sourse.crud.service.AuthenticationService;
import sourse.crud.service.CompanyService;
import sourse.crud.service.UserService;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("api/auth")
public class AuthApiController {
    static final Logger log = LoggerFactory.getLogger(UserApiController.class);
    AuthenticationService authenticationService;
    UserService userService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse>authentication(@RequestBody @Valid AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder().result(authenticationService.login(request)).build();
    }

    @PostMapping("/register")
    public ApiResponse<UserResponse> create(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder().result(userService.store(request)).build();
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse> getUserDetails(@RequestHeader("Authorization") String token) {
        String cleanedToken = token.replace("Bearer ", ""); // Loại bỏ "Bearer " nếu có
        UserResponse userResponse = authenticationService.getUserDetailsFromToken(cleanedToken);
        return ApiResponse.<UserResponse>builder().result(userResponse).build();
    }
}

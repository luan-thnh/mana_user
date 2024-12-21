package sourse.crud.controller.api;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sourse.crud.core.ApiResponse;
import sourse.crud.dto.request.UserCreationRequest;
import sourse.crud.dto.request.UserUpdationRequest;
import sourse.crud.dto.response.UserResponse;
import sourse.crud.entity.Company;
import sourse.crud.entity.User;
import sourse.crud.service.CompanyService;
import sourse.crud.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
@RequestMapping("api/users")
public class UserApiController {
    static final Logger log = LoggerFactory.getLogger(UserApiController.class);
    UserService userService;
    CompanyService companyService;
    @GetMapping()
    public ApiResponse<List<UserResponse>> index() {
        return ApiResponse.<List<UserResponse>>builder().result(userService.index()).build();
    }


    @GetMapping("/{id}")
    public ApiResponse<UserResponse> detail(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder().result(userService.show(id)).build();

    }

    @PostMapping
    public ApiResponse<UserResponse> create(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder().result(userService.store(request)).build();
    }

    @PatchMapping("/{id}")
    public ApiResponse<UserResponse> update(@PathVariable String id, @RequestBody @Valid UserUpdationRequest request) {
        return ApiResponse.<UserResponse>builder().result(userService.update(id, request)).build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<UserResponse> delete(@PathVariable String id) {
        return ApiResponse.<UserResponse>builder().message(userService.delete(id)).build();
    }
}

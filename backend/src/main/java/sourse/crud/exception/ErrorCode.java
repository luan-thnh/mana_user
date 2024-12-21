package sourse.crud.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Exception"),
    USER_EXITTED(1001, "user Existed"),
    INVALID_KEY(1002, "Invalid message key"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters"),
    UNAUTHENTICATED(1005, "UNAUTHENTICATED"),
    USER_NOT_FOUND(1006, "User not found");
    int code;
    String message;
};

package sourse.crud.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults (level = AccessLevel.PRIVATE)

public class UserCreationRequest {
    @Size(min = 3, max = 20, message = "USERNAME_INVALID")
     String username;

    @Size(min = 8, max = 20, message = "PASSWORD_INVALID")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
//            message = "Password must contain at least one letter, one number, and one special character.")
    @NotBlank
    String password;
    @NotBlank
    String confirmPassword;
    String firstName;
    String lastName;
    LocalDate birthday;
    String companyId;
    public boolean isPasswordConfirmed() {
        return password != null && password.equals(confirmPassword);
    }
}

package sourse.crud.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
     String id;
     String username;
     String firstName;
     String lastName;
     LocalDate birthday;
     String companyId;
}


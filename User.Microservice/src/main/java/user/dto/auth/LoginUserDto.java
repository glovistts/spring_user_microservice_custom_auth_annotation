package user.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    String email;
    String password;
    String username;
    String phone;
}

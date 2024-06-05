package user.service;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import user.common.exception.AuthenticationException;
import user.dto.auth.LoginUserDto;
import user.dto.auth.RegisterUserDto;
import user.model.User;
import user.repository.UserRepository;

@Service
public class AuthService {

    UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginUser(LoginUserDto loginUserDto) {
        Optional<User> user=userRepository.getUserByEmailOrUsernameOrPhoneOrPassword(loginUserDto.getEmail(), loginUserDto.getPhone(), loginUserDto.getUsername(), loginUserDto.getPassword());
        return user.orElseThrow(() -> new AuthenticationException("نام کاربری یا رمز عبور اشتباه است"));
    }

    public void registerUser(RegisterUserDto registerUser) {
        try{
            User user=new User(null, registerUser.getUsername(),registerUser.getEmail(),registerUser.getPhone(),registerUser.getPassword(), null, null, null, 0, null, null);
            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new AuthenticationException("اکانت تکراری میباشد!");
        }catch(Exception exception){
            throw new AuthenticationException("خطا در ثبت نام");
        }
    }
    
}

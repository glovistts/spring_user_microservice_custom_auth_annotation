package user.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import user.common.annotation.authenication.JwtAuth;
import user.dto.ResultDTO;
import user.dto.auth.LoginUserDto;
import user.dto.auth.RegisterUserDto;
import user.model.User;
import user.service.AuthService;
import user.service.JwtService;

@RestController
@RequestMapping("${apiPrefix}/auth")
public class AuthenticationController  {
    JwtService jwtService;
    AuthService authService;
    public AuthenticationController(AuthService authService ,JwtService jwtService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResultDTO> register(@RequestBody RegisterUserDto registerUserDto) {
        authService.registerUser(registerUserDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResultDTO(200, " ثبت نام شما موفقیت آمیز بود", null, false));
    }
    @PostMapping("/login")
    public ResponseEntity<ResultDTO> login(@RequestBody LoginUserDto loginUserDto) {
        User user=authService.loginUser(loginUserDto);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("email", user.getEmail());
        String jwtToken = jwtService.generateToken(claims);
        System.out.println(jwtToken);
        Map<String, Object> data = new HashMap<>();
        data.put("token", jwtToken);
        return ResponseEntity.status(HttpStatus.OK).body(new ResultDTO(200, "احراز هویت شما موفقیت آمیرز بود", data, false));
    }
    @JwtAuth
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/authoriza")
    public ResponseEntity<ResultDTO> authoriza(@RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResultDTO(200, "احراز هویت شما موفقیت آمیرز بود", null, false));
    }
}

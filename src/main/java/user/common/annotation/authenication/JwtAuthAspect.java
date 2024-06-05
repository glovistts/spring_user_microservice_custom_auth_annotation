package user.common.annotation.authenication;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;
import user.common.exception.AuthenticationException;
import user.common.utils.JwtUtil;

@Aspect
@Component
public class JwtAuthAspect {

    JwtUtil jwtUtil;

    public JwtAuthAspect(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Before("@within(JwtAuth) || @annotation(JwtAuth)")
    public void checkAuthorization() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
        }
        HttpServletRequest request = attributes.getRequest();

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AuthenticationException("هدر مربوط به احراز هویت نمیتواند خالی باشد!");
        }

        String token = authorizationHeader.substring(7);
        boolean isValid = checkJwtToken(token);

        if (!isValid) {
            throw new AuthenticationException("خطا در احراز هویت!");
        }
    }

    private boolean checkJwtToken(String token) {
        return jwtUtil.validateToken(token);
    }
}

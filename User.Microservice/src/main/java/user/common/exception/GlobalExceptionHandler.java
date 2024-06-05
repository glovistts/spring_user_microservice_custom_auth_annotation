package user.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import user.dto.ResultDTO;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResultDTO> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        Map<String, Object> data = new HashMap<>();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResultDTO(500, ex.getMessage(), data, false));
    }
}
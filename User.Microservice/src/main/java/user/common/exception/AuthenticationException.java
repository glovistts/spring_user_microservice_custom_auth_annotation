package user.common.exception;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }
    @Override
    public String getMessage() {
        return " خطا :  " + super.getMessage();
    }
}

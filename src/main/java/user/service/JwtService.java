package user.service;

import io.jsonwebtoken.Claims;
import user.common.utils.JwtUtil;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final JwtUtil jwtUtil;

    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }
    
    public String generateToken(Map<String, Object> claims) {
        return jwtUtil.generateToken(claims);
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }

    public Claims getClaimsFromToken(String token) {
        return jwtUtil.getClaimsFromToken(token);
    }
}

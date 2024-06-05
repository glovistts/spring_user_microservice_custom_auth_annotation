package user.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

public class JwtUtil {


        private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${security.jwt.expiration-time}")
    private long EXPIRATION_TIME;
    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY; 
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    @SuppressWarnings("deprecation")
    public String generateToken(Map<String, Object> claims) {
        try {
        return Jwts.builder()
               .setClaims(claims)
               .setIssuedAt(new Date(System.currentTimeMillis()))
               .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
               .signWith( SIGNATURE_ALGORITHM,SECRET_KEY)
               .compact();
            } catch (Exception e) {
                logger.error("Error generating JWT token", e);
                throw new RuntimeException("Failed to generate JWT token", e);
            }
    }

    @SuppressWarnings("deprecation")
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("deprecation")
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}

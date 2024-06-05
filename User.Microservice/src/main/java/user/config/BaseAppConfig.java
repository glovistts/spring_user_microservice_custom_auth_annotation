package user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import user.common.utils.JwtUtil;

@Configuration
public class BaseAppConfig {

    @Bean
    JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}

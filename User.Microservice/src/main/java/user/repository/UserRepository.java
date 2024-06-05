package user.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

import user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE (u.email = :email OR u.phone = :phone OR u.username = :username) AND u.password = :password")
    Optional<User> getUserByEmailOrUsernameOrPhoneOrPassword(@Param("email") String email, @Param("phone") String phone, @Param("username") String username, @Param("password") String password);

}

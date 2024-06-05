package user.model;

import java.util.Date;

import org.springframework.data.annotation.LastModifiedDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Version;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
public class User {

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String username;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(unique = true, nullable = true)
    private String phone;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String otpPassword;

    @Column(nullable = true)
    private Date otpExpireTime;

    @Column(nullable = true)
    private Integer isRoot;

    @Version
    private int version;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

}

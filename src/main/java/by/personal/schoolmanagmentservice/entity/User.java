package by.personal.schoolmanagmentservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Login is mandatory")
    @Size(min = 3, max = 24, message = "The length of the login should be from 3 to 24 characters")
    private String login;
    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 50, message = "The length of the First Name should be from 3 to 50 characters")
    @Column
    private String firstName;
    @Size(min = 3, max = 50, message = "The length of the Last Name should be from 3 to 50 characters")
    @NotBlank(message = "Last name is mandatory")
    @Column
    private String lastName;
    @Email
    @NotEmpty(message = "User's email cannot be empty.")
    @Column
    private String email;
    @NotBlank(message = "Password is mandatory")
    @Column
    private String password;

    @Lob
    @Column(name = "avatar")
    private byte[] avatar;

    @OneToMany(mappedBy = "user")
    List<News> newsList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles"
            , joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "name")})
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "newsList=" + newsList +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }
}


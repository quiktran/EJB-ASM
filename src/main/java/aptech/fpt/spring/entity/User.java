package aptech.fpt.spring.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Tên đăng nhập không được để trống.")
    @Size(min = 6, message = "username phải dài hơn 6 kí tự.")
    private String username;
    @NotBlank(message = "Mật khẩu không được để trống.")
    private String password;

    public User() {
    }

    public User(@NotBlank(message = "Tên đăng nhập không được để trống.") @Size(min = 6, message = "username phải dài hơn 6 kí tự.") String username, @NotBlank(message = "Mật khẩu không được để trống.") String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

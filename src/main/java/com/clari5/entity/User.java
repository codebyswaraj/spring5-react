package com.clari5.entity;

import com.clari5.entity.mod.UserMod;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CL5_USER_TBL")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Long rvn;

    @NotEmpty(message = "{user.id.non.empty}")
    @Column(nullable = false, unique = true, length = 10)
    private String userId;

    @NotEmpty()
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, unique = true, length = 200)
    @NotEmpty
    @Email(message = "{errors.invalid_email}")
    private String email;

    @Column(nullable = false)
    @NotEmpty
    @Size(min = 8)
    private String password;

    @Column(name = "created_on", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;

    @Column(nullable = false)
    private Boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "CL5_USER_ROLE_TBL",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public User setUserId(String u) {
        this.userId = u;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getRvn() {
        return rvn;
    }

    public void setRvn(Long rvn) {
        this.rvn = rvn;
    }


    public static User from(UserMod userMod) {
        User user = new User();
        user.setUserId(userMod.getUserId());
        return user;
    }
}
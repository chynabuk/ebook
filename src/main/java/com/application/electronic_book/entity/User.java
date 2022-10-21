package com.application.electronic_book.entity;

import com.application.electronic_book.enums.Role;
import com.application.electronic_book.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User extends BaseEntity implements GrantedAuthority {
    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
package com.application.electronic_book.entity;

import com.application.electronic_book.enums.Role;
import com.application.electronic_book.enums.Status;
import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private String phone;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "group_id")
    private Group group;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;


    @ManyToMany
    @JoinTable(
            name = "favourites",
            joinColumns ={@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> favourites;

    @PrePersist
    public void initRole(){
        this.role = Role.READER;
    }
    @Override
    public String getAuthority() {
        return role.name();
    }
}
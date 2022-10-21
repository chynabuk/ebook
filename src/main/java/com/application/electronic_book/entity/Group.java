package com.application.electronic_book.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group extends BaseEntity{
    @Column
    private String name;

    @OneToMany(mappedBy = "group")
    private List<User> readers;
}

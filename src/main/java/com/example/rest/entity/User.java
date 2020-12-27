package com.example.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "t_user")
@NamedEntityGraph(name = "graph.Profile.roles", attributeNodes = @NamedAttributeNode("roles"))
@Getter
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<UserRole> roles;

    public User(String username, String password) {
        id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' + '}';
    }
}

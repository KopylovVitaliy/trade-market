package com.trademarket.services.entity;



import com.trademarket.model.constants.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private String email;
    private String firstName;
    private String secondName;
    private String password;
    private String phone;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.MERGE)
    private List<Advertisement> advertisements;

    @OneToMany(mappedBy = "author", cascade = CascadeType.MERGE)
    private List<Comment> comments;
}

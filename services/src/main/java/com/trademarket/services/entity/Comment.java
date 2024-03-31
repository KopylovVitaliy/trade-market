package com.trademarket.services.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Comment {
    @Id
    @Column(name = "comment_id", nullable = false)
    private UUID commentId;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Person author;


    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Advertisement ad;

    @CreationTimestamp
    @Column(name = "comment_date", length = 20, nullable = false)
    private LocalDateTime createdAt;

    @Column(length = 1000, nullable = false)
    private String text;
}

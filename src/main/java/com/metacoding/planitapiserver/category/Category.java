package com.metacoding.planitapiserver.category;

import com.metacoding.planitapiserver.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "category_tb")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    // TODO 나중에 요청에 따라 걸러야함
    private User user;

    @Builder
    public Category(Integer id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}

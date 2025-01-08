package com.metacoding.planitapiserver.todo;

import com.metacoding.planitapiserver.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "todo_tb")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column()
    private String category;

    @Column()
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dueDate;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Pattern(regexp = "매년|매월|매주|매일|없음", message = "허용되지 않은 카테고리 값입니다.")
    @Column(nullable = false,columnDefinition = "VARCHAR(2) DEFAULT '없음'")
    private String repeat;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isCompleted;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted;


    public void update(String title, String content, String category, LocalDateTime dueDate, String repeat){
        this.title = title;
        this.content = content;
        this.category = category;
        this.dueDate = dueDate;
        this.repeat = repeat;
    }

    @Builder
    public ToDo(Integer id, String title, User user, String category, String content, LocalDateTime dueDate, LocalDateTime createdAt, String repeat, Boolean isCompleted, Boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.category = category;
        this.content = content;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.repeat = repeat;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
    }
}

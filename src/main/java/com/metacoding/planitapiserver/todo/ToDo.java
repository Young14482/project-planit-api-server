package com.metacoding.planitapiserver.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metacoding.planitapiserver.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
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
    @JsonIgnore
    private User user;

    @Column()
    private String category;

    @Column()
    private String memo;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDate dueDate;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Pattern(regexp = "매년|매월|매주|매일|없음", message = "허용되지 않은 카테고리 값입니다.")
    @Column(nullable = false, columnDefinition = "VARCHAR(2) DEFAULT '없음'")
    private String repeat;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isCompleted;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean isDeleted;

    @Builder
    public ToDo(Integer id, String title, User user, String category, String memo, LocalDate dueDate, LocalDateTime createdAt, String repeat, Boolean isCompleted, Boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.category = category;
        this.memo = memo;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.repeat = repeat;
        this.isCompleted = isCompleted;
        this.isDeleted = isDeleted;
    }

    @PrePersist
    public void prePersist() {
        if(isCompleted==null){
            isCompleted=false;
        }

        if(isDeleted==null){
            isDeleted=false;
        }

        if(repeat==null){
            repeat="없음";
        }
    }

}

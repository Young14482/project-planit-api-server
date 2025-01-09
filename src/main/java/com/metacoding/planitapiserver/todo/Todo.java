package com.metacoding.planitapiserver.todo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metacoding.planitapiserver.category.Category;
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
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Category category;

    @Lob
    @Column(nullable = false)
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
    public Todo(Integer id, String title, User user, Category category, String memo, LocalDate dueDate, LocalDateTime createdAt, String repeat, Boolean isCompleted, Boolean isDeleted) {
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
        if (isCompleted == null) {
            isCompleted = false;
        }

        if (isDeleted == null) {
            isDeleted = false;
        }

        if (title == null) {
            title = "제목없음";
        }

        if (memo == null) {
            memo = "";
        }

        if (repeat == null) {
            repeat = "없음";
        }
    }

    public void update(TodoRequest.UpdateDTO dto) {
        if (dto.getTitle() != null)
            this.title = dto.getTitle();
        if (dto.getMemo() != null)
            this.memo = dto.getMemo();
        if (dto.getCategory() != null)
            this.category = Category.builder().id(dto.getCategory()).build();
        if (dto.getDueDate() != null)
            this.dueDate = dto.getDueDate();
    }
    public void clearCategory() {
        this.category = null;
    }

    public void delete() {
        isDeleted = true;
    }

}
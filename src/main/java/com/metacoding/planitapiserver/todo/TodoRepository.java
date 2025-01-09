package com.metacoding.planitapiserver.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

    @Query("select t from Todo t WHERE t.user.id = :userId and t.isDeleted = false")
    List<Todo> findAllByUserId(@Param("userId") Integer userId);

    @Query("select t from Todo t WHERE t.user.id = :userId and t.category.id = :categoryId")
    List<Todo> findAllByUserIdAndCategoryId(@Param("userId")Integer userId, @Param("categoryId") Integer categoryId);
}

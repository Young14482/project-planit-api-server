package com.metacoding.planitapiserver.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c join fetch c.user u where c.user.id = :userId order by c.id")
    List<Category> findAllByUserId(@Param("userId") int userId);
}

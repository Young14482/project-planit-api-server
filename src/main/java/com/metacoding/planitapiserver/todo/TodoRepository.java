package com.metacoding.planitapiserver.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<ToDo, Integer> {

    @Query("SELECT t FROM ToDo t WHERE DATE(t.createdAt) = :date ")
    Page<ToDo> mFindAll(Pageable pageable, LocalDate date);

    @Query("select p from ToDo p join fetch p.user u where p.id = :id")
    Optional<ToDo> mFindById(@Param("id") int id);

}

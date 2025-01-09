package com.metacoding.planitapiserver.todo;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void findAllByUserId_test() {
        // given
        int userId = 1;

        // when
        List<Todo> todoList = todoRepository.findAllByUserId(userId);

        // then
        System.out.println(todoList.size());
    }
}

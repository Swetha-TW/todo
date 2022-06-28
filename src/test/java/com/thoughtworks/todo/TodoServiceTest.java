package com.thoughtworks.todo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    void shouldReturnAllTodosWhenFindAllIsCalled() {
        Todo expectedTodo = new Todo("Learn Spring Boot", true);
        todoRepository.save(expectedTodo);

        TodoService todoService = new TodoService(todoRepository);
        List<Todo> todoList = todoService.findAll();
        Todo actualTodo = todoList.get(todoList.size() - 1);

        assertEquals(expectedTodo.getDescription(), actualTodo.getDescription());
        assertEquals(expectedTodo.isCompleted(), actualTodo.isCompleted());
    }
}

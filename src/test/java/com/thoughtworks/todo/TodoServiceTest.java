package com.thoughtworks.todo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

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

    @Test
    void shouldSaveTodoWhenCreateIsCalled() {
        Todo todo = new Todo("Learn React", false);
        TodoService todoService = new TodoService(todoRepository);

        todoService.create(todo);

        assertEquals(1.0, todoRepository.count());
    }
}

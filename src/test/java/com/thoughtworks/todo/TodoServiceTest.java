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

    @Test
    void shouldSaveUpdatedTodoWhenUpdateIsCalled() {
        Todo savedTodo = new Todo("Implement Put method", false);
        TodoService todoService = new TodoService(todoRepository);
        todoRepository.save(savedTodo);
        Todo expectedTodo = new Todo(savedTodo.getId(), "Implement Put method TDD", false);

        Todo updatedTodo = todoService.update(savedTodo.getId(), expectedTodo);

        assertEquals(savedTodo.getId(),updatedTodo.getId());
        assertEquals(expectedTodo.getDescription(), updatedTodo.getDescription());
        assertEquals(expectedTodo.isCompleted(), updatedTodo.isCompleted());

    }
}

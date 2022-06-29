package com.thoughtworks.todo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Test
    void shouldReturnSuccessWhenGetAllTodoIsCalled() throws Exception {
        List<Todo> todo = new ArrayList<Todo>();
        todo.add(new Todo("Learn Spring Boot -TDD", false));
        todo.add(new Todo("Learn Testing Exception Handling", false));

        Mockito.when(todoService.findAll()).thenReturn(todo);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/todo"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$", hasSize(2)));
    }


    @Test
    void shouldReturnCreatedStatusWhenTodoIsCreated() throws Exception {
        Todo todo = new Todo("Learn Spring Boot", false);
        ObjectMapper objectMapper = new ObjectMapper();
        String todoString = objectMapper.writeValueAsString(todo);
        Mockito.when(todoService.create(any(Todo.class))).thenReturn(todo);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/todo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoString));

        result.andExpectAll(
                status().isCreated(),
                jsonPath("$.description").value(todo.getDescription()),
                jsonPath("$.completed").value(todo.isCompleted()));
    }

    @Test
    void shouldReturnCreatedStatusWhenTodoIsUpdated() throws Exception {
        Todo todo = new Todo("Learn Spring Boot", false);
        todoService.create(todo);
        Todo updatedTodo = new Todo("Learn Spring Boot TDD", false);
        ObjectMapper objectMapper = new ObjectMapper();
        String updateTodoString = objectMapper.writeValueAsString(updatedTodo);
        System.out.println(updateTodoString);
        Mockito.when(todoService.update(anyInt(), any(Todo.class))).thenReturn(updatedTodo);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .put("/todo/" + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateTodoString));

        result.andDo(print()).andExpectAll(
                status().isCreated(),
                jsonPath("$.description").value(updatedTodo.getDescription()),
                jsonPath("$.completed").value(updatedTodo.isCompleted())
        );

    }

    @Test
    void shouldReturnSuccessWhenTodoIsDeleted() throws Exception {
        Todo todo = new Todo(1, "Start Learning React", false);
        todoService.create(todo);
        doNothing().when(todoService).delete(todo.getId());

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/todo/" + 1)
                .contentType(MediaType.APPLICATION_JSON));

        result.andDo(print()).andExpect(status().isOk());
    }
}

package com.thoughtworks.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todo")
    public ResponseEntity<List<Todo>> getAllTodo()
    {
        return new ResponseEntity<>(todoService.findAll(), OK);
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.create(todo), CREATED);
    }
}

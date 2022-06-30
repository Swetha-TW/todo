package com.thoughtworks.todo;

import com.thoughtworks.todo.exception.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
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

    @PutMapping("/todo/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo todo) throws TodoNotFoundException {
        return new ResponseEntity<>(todoService.update(id,todo), CREATED);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable int id) throws TodoNotFoundException
    {
        todoService.delete(id);
        return new ResponseEntity<>(OK);
    }
}

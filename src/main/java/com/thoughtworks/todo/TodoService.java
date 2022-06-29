package com.thoughtworks.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return (List<Todo>) todoRepository.findAll();
    }

    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

    public Todo update(int id, Todo todo) {
        if (todoRepository.existsById(id)) {
            return todoRepository.save(todo);
        }
        else
            return new Todo();
    }

    public void delete(int id) {
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
        }
    }
}

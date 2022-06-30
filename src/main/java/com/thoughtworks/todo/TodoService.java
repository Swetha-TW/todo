package com.thoughtworks.todo;

import com.thoughtworks.todo.exception.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Todo update(int id, Todo todo) throws TodoNotFoundException {
        if (todoRepository.existsById(id)) {
            return todoRepository.save(todo);
        }
        else {
            throw new TodoNotFoundException("Todo not found with id " + id);
        }
    }

    public void delete(int id) {
        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
        }
    }
}

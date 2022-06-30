package com.thoughtworks.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TodoExceptionController {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(TodoNotFoundException.class)
    public Map<String, String> handleTodoNotFoundException(TodoNotFoundException exception){
        Map<String,String> errorDetails = new HashMap<>();
        errorDetails.put("errorMessage", exception.getMessage());
        return errorDetails;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String > errorDetails = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error-> {
            errorDetails.put(error.getField(),error.getDefaultMessage());
        });
        return errorDetails;
    }
}

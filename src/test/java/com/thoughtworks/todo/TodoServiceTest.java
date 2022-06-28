package com.thoughtworks.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoServiceTest {

    @Autowired
    private TodoRepository todoRepository;


}

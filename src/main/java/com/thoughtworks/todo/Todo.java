package com.thoughtworks.todo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private boolean isCompleted;

    public Todo() {
    }

    public Todo(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}

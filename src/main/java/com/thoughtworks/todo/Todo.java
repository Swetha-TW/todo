package com.thoughtworks.todo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Column(name = "completed")
    private boolean isCompleted;

    public Todo() {
    }

    public Todo(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public Todo(int id, String description, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }
}

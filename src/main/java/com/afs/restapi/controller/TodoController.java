package com.afs.restapi.controller;

import com.afs.restapi.dto.TodoResponse;
import com.afs.restapi.entity.Todo;
import com.afs.restapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.findAll();
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable String id){
        return todoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.create(todo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable String id) {
        todoService.delete(id);
    }

    @PutMapping("/{id}")
    public Todo editTodo(@PathVariable String id, @RequestBody Todo todo){
        return todoService.edit(id,todo);
    }

}

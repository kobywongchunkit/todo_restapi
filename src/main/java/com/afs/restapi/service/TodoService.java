package com.afs.restapi.service;

import com.afs.restapi.entity.Todo;
import com.afs.restapi.exception.NoTodoFoundException;
import com.afs.restapi.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll(){return todoRepository.findAll();}

    public Todo create(Todo todo) {
        if(todo.getDone() == null){
            todo.setDone(false);
        }
        return todoRepository.insert(todo);
    }

    public void delete(String id) {
        todoRepository.findById(id).orElseThrow(NoTodoFoundException::new);
        todoRepository.deleteById(id);
    }

    public Todo edit(String id, Todo updateTodo) {
        Todo todo = todoRepository.findById(id).orElseThrow(NoTodoFoundException::new);
        if(updateTodo.getText() != null){
            todo.setText(updateTodo.getText());
        }
        if(updateTodo.getDone() != null){
            todo.setDone(updateTodo.getDone());
        }
        return todoRepository.save(todo);
    }

    public Todo findById(String id) {
        return null;
    }
}

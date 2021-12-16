package com.afs.restapi.service;

import com.afs.restapi.entity.Todo;
import com.afs.restapi.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
public class TodoServiceTest {
    @Mock
    TodoRepository todoRepository;
    @InjectMocks
    TodoService todoService;

    @Test
    void should_return_all_todo_when_find_all_given_todos() {
        //given
        List<Todo> todos = Stream.of(new Todo("1","test",true))
                .collect(Collectors.toList());
        given(todoRepository.findAll())
                .willReturn(todos);
        //When
        List<Todo> actual = todoService.findAll();
        //then
        assertEquals(todos,actual);

    }
    @Test
    void should_return_todo_when_find_by_id_given_todos() {
        //given
        Todo todo = new Todo("1","test",true);
        List<Todo> employees = Stream.of(new Todo("1","test",true)).collect(Collectors.toList());
        given(todoRepository.findById(any()))
                .willReturn(java.util.Optional.of(todo));
        //When
        Todo actual = todoService.findById(todo.getId());
        //then
        assertEquals(todo, actual);
    }
    @Test
    void should_remove_todo_when_delete_given_id() {
        //given
        Todo todo = new Todo("1","test",true);
        given(todoRepository.findById("1"))
                .willReturn(java.util.Optional.of(todo));
        //When
        todoService.delete("1");
        //then
        verify(todoRepository, times(1)).deleteById("1");
    }
}

package com.afs.restapi.controller;

import com.afs.restapi.entity.Todo;
import com.afs.restapi.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void clearRepository(){
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todos_when_perform_get_given_todos() throws Exception {
        //given
        Todo todo = new Todo("1","todo text1",false);
        todoRepository.insert(todo);
        todo = new Todo("2","todo text2",true);
        todoRepository.insert(todo);
        //When
        //then
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].text").value("todo text1"))
                .andExpect(jsonPath("$[0].done").value(false))
        ;
    }

    @Test
    void should_return_todo_when_when_perform_post_given_todo() throws Exception {
        //given
        String todo = "    {\n" +
                "        \"text\": \"test todo\",\n" +
                "        \"done\": false\n" +
                "    }";
        //When
        //then
        mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON).content(todo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.text").value("test todo"))
                .andExpect(jsonPath("$.done").value(false))
        ;
    }
    @Test
    void should_remove_todo_when_perform_delete_given_todos_and_id() throws Exception {
        //given
        Todo todo = new Todo("1","todo text1",false);
        todoRepository.insert(todo);

        //When
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/" + todo.getId()))
                .andExpect(status().isNoContent());

        assertEquals(0, todoRepository.findAll().size());
    }

    @Test
    void should_throw_exception_when_delete_invalid_todo() throws Exception {
        //given
        //When
        //then
        mockMvc.perform(delete("/todos/{id}", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value("404"))
                .andExpect(jsonPath("$.errorMsg").value("Entity not found."));
    }
    @Test
    void should_return_todo_when_perform_put_given_todo_and_id() throws Exception {
        //given
        Todo todo = new Todo("1","todo text1",false);
        todoRepository.insert(todo);
        String updatedTodo = "    {\n" +
                "        \"text\": \"updated todo\",\n" +
                "        \"done\": true\n" +
                "    }";
        //When
        //then
        mockMvc.perform(put("/todos/{id}", todo.getId())
                        .contentType(MediaType.APPLICATION_JSON).content(updatedTodo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("updated todo"))
                .andExpect(jsonPath("$.done").value(true))
        ;

    }
}

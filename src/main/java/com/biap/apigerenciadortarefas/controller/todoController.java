package com.biap.apigerenciadortarefas.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biap.apigerenciadortarefas.entity.todo;
import com.biap.apigerenciadortarefas.service.todoService;
@RestController
@RequestMapping("/tasks")
public class todoController {
    @Autowired
    private todoService todoService;

    public todoController(com.biap.apigerenciadortarefas.service.todoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    List<todo> create(@RequestBody @Valid todo todo) {
        return todoService.create(todo);
    }

    @GetMapping
    List<todo> read() {
        return todoService.read();
    }

    @PutMapping("{id}")
    List<todo> update(@PathVariable Long id, @RequestBody todo todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("{id}")
    List<todo> delete(@PathVariable("id") Long id) {
        return todoService.delete(id);
    }
}

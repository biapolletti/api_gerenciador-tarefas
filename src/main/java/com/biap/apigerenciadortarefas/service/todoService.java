package com.biap.apigerenciadortarefas.service;

import java.util.List;

import com.biap.apigerenciadortarefas.exception.BadRequestException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.biap.apigerenciadortarefas.entity.todo;
import com.biap.apigerenciadortarefas.repository.todoRepository;

@Service
public class todoService {
    private todoRepository todoRepository;

    public todoService(com.biap.apigerenciadortarefas.repository.todoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<todo> create(todo todo) {
        todoRepository.save(todo);
        return read();
    }

    public List<todo> read() {
        Sort sort = Sort.by("prioridade").descending()
                .and(Sort.by("nome").ascending().and(Sort.by("realizado").ascending()));
        return todoRepository.findAll(sort);
    }

    public List<todo> update(Long id, todo todo) {
        todoRepository.findById(id).ifPresentOrElse((existingtodo) -> {todo.setId(id);
            todoRepository.save(todo);}, () -> {throw new BadRequestException("Tarefa %d não existe! ".formatted(id)); });
        return read();
    }

    public List<todo> delete(Long id) {
        todoRepository.findById(id).ifPresentOrElse((existingtodo) -> todoRepository.delete(existingtodo), () -> {
            throw new BadRequestException("Tarefa %d não existe! ".formatted(id));});
        return read();
    }
}

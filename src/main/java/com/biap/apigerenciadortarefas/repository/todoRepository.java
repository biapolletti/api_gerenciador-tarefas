package com.biap.apigerenciadortarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biap.apigerenciadortarefas.entity.todo;

public interface todoRepository extends JpaRepository<todo, Long> {

}

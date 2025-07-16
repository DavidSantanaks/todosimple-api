package com.davidsantana.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.davidsantana.todosimple.models.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{
    
}

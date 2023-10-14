package com.davidSantana.todosimple.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.davidSantana.todosimple.models.Task;
import com.davidSantana.todosimple.services.TaskService;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {
    
    @Autowired
    TaskService taskService;

    //Encontra a task de qualquer usuario com o ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        Task task = this.taskService.findTask(id);
        return ResponseEntity.ok().body(task);
    }
    //Encontra todas as task do usuario informado no ID
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Task>> findAllByUserId(@PathVariable Long id){
        List<Task> taskList = this.taskService.findAllByUserId(id);
        return ResponseEntity.ok().body(taskList);
    }


    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task obj){
        this.taskService.creatTask(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    //DETALHE PARA FICAR ATENTO, CADA PARAMETRO RECEBE AS ANOTAÇÕES PROPRIA
    public ResponseEntity<Void> updateTask(@Valid @RequestBody Task obj, @PathVariable Long id){
        obj.setId(id);
        this.taskService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    

}

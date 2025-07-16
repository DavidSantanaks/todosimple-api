package com.davidsantana.todosimple.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidsantana.todosimple.models.Task;
import com.davidsantana.todosimple.models.User;
import com.davidsantana.todosimple.repositories.TaskRepository;
import com.davidsantana.todosimple.repositories.UserRepository;

@Service
public class TaskService {

    @Autowired
    public TaskRepository taskRepository;
    @Autowired
    public UserService userService;

    public Task findById(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
            "Task não encontrada" + id + ", Tipo: " + Task.class.getName()
        ));
    }

    public Task findAll(){
        Optional<List<Task>> allTask = Optional.ofNullable(this.taskRepository.findAll());
        return (Task) allTask.orElseThrow(() -> new RuntimeException(
            "Não tem nenhuma task cadastrada"
        ));
    }
    
    @Transactional
    public Task create(Task obj){
        User user =  this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user); 
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
        
    }
    
    public void delete(Long id){
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel deletar");
        }
    }
    
}

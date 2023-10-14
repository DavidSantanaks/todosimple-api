package com.davidSantana.todosimple.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidSantana.todosimple.models.Task;
import com.davidSantana.todosimple.models.User;
import com.davidSantana.todosimple.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private UserService userService;
    @Autowired
    private TaskRepository taskRepository;

    //Procurando task
    public Task findTask(Long id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Task não encontrada! ID: " + id + ",Tipo: " + Task.class.getName()));
    }

    public List<Task> findAllByUserId(Long userId){
        List<Task> task = this.taskRepository.findByUser_Id(userId);
        return task;
        
    }

    //Criando uma task
    @Transactional
    public Task creatTask(Task obj){
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;
    }

    //Atualizando uma task
    @Transactional
    public Task update(Task obj){
        Task newObj = findTask(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }
    
    //Deletando uma task
    @Transactional
    public void delete(Long id){
        findTask(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel excluir pois há entidades relacionadas");
        }
    }

    @Transactional
    public void deleteAllTask(Long id){
        User user = this.userService.findById(id);
        this.deleteAllTask(user.getId());
    }

    


}

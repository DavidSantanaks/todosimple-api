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
import com.davidSantana.todosimple.models.User;
import com.davidSantana.todosimple.models.User.UpdateUser;
import com.davidSantana.todosimple.models.User.createUser;
import com.davidSantana.todosimple.services.TaskService;
import com.davidSantana.todosimple.services.UserService;


@RestController
//Rota base 
@RequestMapping("/user")
//Valida as informações que chega do fronte-end
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;
    
    //Buscando usuario via controller
    @GetMapping("/{id}")
    //ResponseEntity é uma entidade de resposta Http
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = this.userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/all")
        public ResponseEntity<List<User>> findAllUser(){
            List<User> userList = this.userService.findAllUsers();
            return ResponseEntity.ok().body(userList);
        }

    
    @PostMapping 
    @Validated(createUser.class)
    public ResponseEntity<Void> create(@Valid @RequestBody User obj){
        this.userService.creatUser(obj);
         //Temos que criar para informar qual a rota em que estamos criando o usuario e passar no .created
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        
        return ResponseEntity.created(uri).build();

}
    @PutMapping("/{id}")
    @Validated(UpdateUser.class)
    public ResponseEntity<Void> update(@Valid @RequestBody User obj, @PathVariable Long id){
        obj.setId(id);
        this.userService.updateUser(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        
        this.userService.deletUser(id);
        return ResponseEntity.noContent().build();
    }

}
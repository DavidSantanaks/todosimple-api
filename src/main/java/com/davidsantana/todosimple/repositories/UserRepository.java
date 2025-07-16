package com.davidsantana.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidsantana.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}

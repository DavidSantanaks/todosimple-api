package com.davidSantana.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davidSantana.todosimple.models.User;
import java.util.List;



                                //Sempre extender a JPA REPOSITORY
@Repository                     //Informa a cpluna que tem o ID e o Tipo
public interface UserRepository extends JpaRepository<User, Long>{

    
}


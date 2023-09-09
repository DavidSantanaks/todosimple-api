package com.davidSantana.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davidSantana.todosimple.models.Task;
import java.util.List;


@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
    List<Task> findByUser_Id(Long id);

    //Utilizando JPQL
    @Query(value = "SELECT t from Task t where t.user.id = :id ") 
    List<Task> findByUseId(@Param("id") Long id);

    //Utilizando SQL
    @Query(value = "SELECT * FROM task t  where t.user_id = :id ",nativeQuery = true)
    List<Task> findByUserId(@Param("id") Long id);
}

package com.davidSantana.todosimple.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity //A classe funciona como uma tabela de banco de dados
@Table(name = Task.TABLE_NAME) //Nome da tabela no banco de dados
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Task {
    
    public static final String TABLE_NAME = "task"; //Constante para força o nome da tabela

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "id", unique = true)
    private Long id;

    @ManyToOne//Varias coisas de um unico usuario da classe
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user; //A task pertence a um usuario

    @Column(name = "description", length = 255, nullable = false)
    @NotBlank
    @Size(min = 1 , max = 255)
    private String description;


    

    
}

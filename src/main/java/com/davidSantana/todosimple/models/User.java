package com.davidSantana.todosimple.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



//INFORMA PARA O BANCO QUE ESTA CLASSE É UMA TABELA DO BANCO DE DADOS
@Entity
//NOME DA TABELA
@Table(name = User.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
//@Data substitui tudo acima
public class User {

    public interface createUser{}
    public interface UpdateUser{}

    //Constante para garantir o nome da tabela.
    public static final String TABLE_NAME = "USER";

    //Atributos
    @Id //Informa para o banco de dados que esse campo é de ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Informa para o banco de dados que é para auto incrementar os ID 
    @Column(name = "id", unique = true) //Força o nome da coluna no banco
    private Long id;

    @Column(name = "userName", length = 100, nullable = false, unique = true ) //Força o nome na coluna, define o tamanho, pode ser nulo e que a informação é unica.
    @NotNull(groups = createUser.class) //NÃO PERMITE VALOR NULO
    @NotEmpty(groups = createUser.class) //NÃO PERMITE VALOR VAZIO >> NOME = ""
    @Size(min = 2, max = 100) //Define o tamanho do campo
    private String userName;


    @NotBlank(groups = createUser.class) //Funciona como o notNull e notEmpty ao mesmo tempo.
    @Size(groups = {createUser.class, UpdateUser.class}, min = 8, max = 60) //Define o tamanho do campo
    @JsonProperty(access = Access.WRITE_ONLY)//Não retorna a senha 
    @Column(name = "password", length = 60, nullable = false)
    private String password;


    @OneToMany(mappedBy = "user") //Um usuario para varias tarefas
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Task> task = new ArrayList<Task>();
    


  
    
}

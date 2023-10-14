package com.davidSantana.todosimple.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidSantana.todosimple.models.Task;
import com.davidSantana.todosimple.models.User;
import com.davidSantana.todosimple.repositories.UserRepository;

@Service
public class UserService {
   
    @Autowired //"Instancia" a interface para criar a conexão entre as classes e interface que nos permite utilizar ações no banco
    private UserRepository userRepository;

                                        /*  Funciona como logica de negocio os metodos classe service
                                            e assim chamamos as ações no banco 
                                        */
    public User findById(Long id){
        //A variavel Optional<> sendo utilizada ela retorna um valor e não deixa o sistema cair em erro de nullPointException caso a pesquisa retorne nada
        //Dentro do <...> normalmente utilizado a o tipo da classe que queremos
        Optional<User> user = this.userRepository.findById(id);
        //A opção .orElseThrow() -> Se estiver vazio jogue uma Exception 
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id:" + id + "Tipo: " + User.class.getName()));
        
    }

    public List<User> findAllUsers(){
        List<User> task = this.userRepository.findAll();
        return task;
    }

   @Transactional
   public User creatUser(User obj){
    obj.setId(null);
    obj  = this.userRepository.save(obj);
    return obj;

   }

    @Transactional
    public User updateUser(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.userRepository.save(newObj);
    }
   
    @Transactional
    public void deletUser(Long id){
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois não há task relacionadas!");
        }
    }
}

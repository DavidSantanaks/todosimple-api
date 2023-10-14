package com.davidSantana.todosimple.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Como é uma classe de configuração se utiliza a anotação abaixo para o spring inicializala
@Configuration
//Traz as config de web do spring
@EnableWebMvc        //Para fazer o spring se comunicar com o front end
                    //sempre é necessitada essa implementação na classe
public class WebConfig implements WebMvcConfigurer {
    
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**");
    }
}

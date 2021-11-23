package com.example.EjerciciosSipring456.Controladores;

/*Crear una clase HelloController que sea un controlador REST.
Dentro de la clase crear un método que retorne un saludo.
Probar que retorna el saludo desde el navegador y desde Postman.*/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //Le indicamos que va a ser un controlador tipo REST
public class HelloController {
    @GetMapping("/saludo")
    public String saludo(){
        return "Hola desde HelloController";
    }

}

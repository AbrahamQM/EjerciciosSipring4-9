package com.example.EjerciciosSipring456;

import com.example.EjerciciosSipring456.Entities.Laptop;
import com.example.EjerciciosSipring456.Repositories.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/*Ejercicio 1

Crear un proyecto Spring Boot con las dependencias:

 - H2
 - Spring Data JPA
 - Spring Web
 - Spring Boot dev tools

Crear una clase HelloController que sea un controlador REST.
Dentro de la clase crear un método que retorne un saludo.
Probar que retorna el saludo desde el navegador y desde Postman.

*/

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//contexto y repositorio
		ApplicationContext context = SpringApplication.run(Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);
		//Desde LaptopController crear un método que devuelva una lista de objetos Laptop.
		//Probar que funciona desde Postman.
		//Los objetos Laptop se pueden insertar desde el método main de la clase principal.


//Agregamos 2 Laptop
		Laptop lap1 = new Laptop(null, "HP", "sj15'5/256/8bg", 565.53d, 8, 256);
		Laptop lap2 = new Laptop(null, "Asus", "aspire14'5/512/16", 622.10d, 16, 512);
//Los guardamos en el repositorio
		repository.save(lap1);
		repository.save(lap2);

		System.out.println("Nº Laptops en bbdd: "+ repository.findAll().size());
	//Ejercicio 3 sesiones 4,5,6
	//Crear un método en LaptopController que reciba un objeto Laptop enviado en formato JSON desde Postman y persistirlo en la base de datos.
	//Comprobar que al obtener de nuevo los laptops aparece el nuevo ordenador creado.

	//Ejercicio 2 sesiones 7,8,9
		//Implementar swagger sobre el API REST de Laptop y verificar que funciona en la URL:
		//http://localhost:8080/swagger-ui/
	}

}

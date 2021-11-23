package com.example.EjerciciosSipring456.Controladores;

import com.example.EjerciciosSipring456.Entities.Laptop;
import com.example.EjerciciosSipring456.Repositories.LaptopRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*****Dentro de la misma app crear las clases necesarias para trabajar con "ordenadores":
 - LaptopController (controlador) ****/

@RestController
public class LaptopController {
    //Atributos
    private LaptopRepository laptopRepository;

    //Constructores
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    //Ejercicio 2
    //Desde LaptopController crear un método que devuelva una lista de objetos Laptop.
    @GetMapping("/api/allLaptops")
    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }
/*
    //Ejercicio 3
    ////Crear un método en LaptopController que reciba un objeto Laptop enviado en formato JSON desde Postman y
    // persistirlo en la base de datos.
    @PostMapping("/api/addLaptop")
    public Laptop create(@RequestBody Laptop laptop) {
        return laptopRepository.save(laptop);//Guardar el libro recibido, en la bbdd y muestra el libro en pantalla
    }
*/

 /*******Comenzamos los ejercicios de las sesiones 7,8,9 de Spring *********/
 /**Ejercicio 1

  Implementar los métodos CRUD en el API REST de Laptop creada en ejercicios anteriores.

  Los métodos CRUD:

  a findAll()  **************LINEA 28 EJERCICIO 2*****************
  b findOneById()
  c create()
  d update()
  e delete()
  f deleteAll()
**/
    //b.--------------findOneById
    @PostMapping("/api/findOne/{id}")
    public ResponseEntity<Laptop> findOneById(@PathVariable Long id) {
        //@PathVariable recibe la variable{id}
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        if (laptopOpt.isPresent()) { //Comprobamos si existe
            return ResponseEntity.ok(laptopOpt.get()); //Si existe devuelve ok(código 200) + la laptop
        } else
            return ResponseEntity.notFound().build(); //No existe, construye un error 404(notFound)
    }

    //c.-------------create
    @PostMapping("/api/addLaptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){
        //@RequestBody extrae la información de la petición y la guarda en una clase Laptop que llamaremos laptop.
        //@RequestHeader devuelve la cabecera.
        System.out.println(headers.get("User-Agent")); //Imprimimos a consola quien es el que ha solicitado añadir laptop
        if(laptop.getId() != null){    //Comprobamos si la id de ese laptop ya existe
            return ResponseEntity.badRequest().build(); //Devolvería badRequest si el libro ya existe
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);//Guardar el libro recibido, en la bbdd y muestra el libro en pantalla

    }
    //d.-------------- update
    @PutMapping("api/update")
    public ResponseEntity<Object> update(@RequestBody Laptop laptop){
        //Comprobamos si el libro existe o no antes de modificarlo

        //Si no le pasamos id al llamarlo desde postman, quiere decir que es una creación.
        if (laptop.getId() == null){
            System.out.println("Error: No has indicado id, comprueba la id o crea el Laptop con POST /api/addLaptop");
            return ResponseEntity.badRequest().build(); //Devolver error 400
        }
        //Si no existe laptop con esa id
        if (!laptopRepository.existsById(laptop.getId())){
            System.out.println("Tratando de actualizar un laptop no existente");
            return ResponseEntity.notFound().build();
        }

        //En caso de que existe la ide en el repositorio
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    //e.----------delete()
    @DeleteMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        //Si no existe laptop con esa id
        if (!laptopRepository.existsById(id)){
            System.out.println("Tratando de eliminar un laptop no existente");
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build(); //Devuelve un aviso de laptop inexistente
    }

    //f.--------deleteAll()
    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        laptopRepository.deleteAll(); //Borramos all el repositorio de Laptop
        System.out.println("Borrados todos los Laptop");
        return ResponseEntity.noContent().build();
    }

/*****Ejercicio 3

 Crear casos de test para el controlador de Laptop desde Spring Boot.

 Con click derecho dentro del código de la clase LaptopController utilizar la opción Generate > Test
 para crear la clase con todos los tests unitarios e implementarlos siguiente el proceso visto en clase.
 Nuevo ordenador creado.
 **/

}


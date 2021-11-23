package com.example.EjerciciosSipring456.Controladores;

import com.example.EjerciciosSipring456.Entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**Ejercicio 3

 Crear casos de test para el controlador de Laptop desde Spring Boot.

 Con click derecho dentro del código de la clase LaptopController utilizar la opción Generate > Test
 para crear la clase con todos los tests unitarios e implementarlos siguiente el proceso visto en clase.
**/
 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort //Le asignamos a port el puerto local que se esté usando (Automáticamente)
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void findAll() {
        // Creamos una ResponseEntity <con array de laptops> llamada response, y le asignamos el resultado de la llamada
        // a /api/allLaptops que devuelve un array de Laptop(.class le resuelve el tipo de elemento que es Laptop[])
        ResponseEntity<Laptop[]> response = testRestTemplate.getForEntity("/api/allLaptops", Laptop[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        //Obtenemos los laptops en una List<Laptops>
        List<Laptop> laptops = Arrays.asList(response.getBody()); //Arrays.asList se encarga de convertir un (array[]) normal a ArrayList
        System.out.println(laptops.size());//Imprimimos el nº de libros en consola

    }

    @Test
    void create() {
        HttpHeaders headers = new HttpHeaders(); //Creamos cabeceras para poder trabajar con ellas
        headers.setContentType(MediaType.APPLICATION_JSON); //setContentType para indicarle la aplicación es json
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); //setAccept para indicarle que tipo de elementos acepta

        //Copiamos el código JSON que pusimos en postman para añadir un libro y modificamos lo que queramos
        String json = """ 
                {
                    "hdd": 256,
                    "manofacturer": "Lenovo",
                    "model": "l15.6/256/16",
                    "price": 1000,
                    "ram": 16
                }
                """;
        //Creamos una request de tipo http que envíe el String json creado arriba
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        //Comprobamos cosas con la request y si funciona, le asignamos el nuevo laptop a response
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/addLaptop", HttpMethod.POST, request, Laptop.class);

        //Creamos un objeto de clase Laptop que se va a llamar result y le asignamos el cuerpo de response
        Laptop result = response.getBody();

        //Realizamos los assertEquals
        assertEquals(1L, result.getId()); // comprobamos que el libro creado tiene la id 1
        assertEquals("l15.6/256/16", result.getModel()); // comprobamos que el laptop creado tiene el modelo
        // indicado desde json
    }

    @Test
    void findOneById() {
                                                           /**Aqui debería ir postFor...**/
//       ResponseEntity<Laptop> response = testRestTemplate.getForEntity("/api/findOne/1", Laptop.class); //getForEntity, lanza
        //una petición http a la url: Localhost:puerto/api/findOne/{1}

        //assertEquals
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode()); // Comprobamos que no hay laptops

    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}
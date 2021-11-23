package com.example.EjerciciosSipring456.Entities;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
/***********Dentro de la misma app crear las clases necesarias para trabajar con "ordenadores":
 - Laptop (entidad)****/
import javax.persistence.*;

@Entity
@Table(name= "laptops")
public class Laptop {
//Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String manofacturer;
    private String model;
    private Double price;
    private int ram;
    private int hdd;
//Constructores
    public Laptop() {
    }

    public Laptop(Long id, String manofacturer, String model, Double price, int ram, int hdd) {
        this.id = id;
        this.manofacturer = manofacturer;
        this.model = model;
        this.price = price;
        this.ram = ram;
        this.hdd = hdd;
    }
//Getter y Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManofacturer() {
        return manofacturer;
    }

    public void setManofacturer(String manofacturer) {
        this.manofacturer = manofacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getHdd() {
        return hdd;
    }

    public void setHdd(int hdd) {
        this.hdd = hdd;
    }
}

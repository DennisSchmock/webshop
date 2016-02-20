/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dennisschmock
 */
public class Topping {
    String name;
    Double price;
    int id;

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }
    
    Topping(String name, Double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }
    
}

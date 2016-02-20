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
public class Bottom {

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

    public Bottom(String name, Double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }
}

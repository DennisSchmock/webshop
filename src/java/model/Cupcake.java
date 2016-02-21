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
public class Cupcake implements Comparable<Cupcake> {

    private Bottom bottom;
    private Topping topping;
    private String name = "test";
    private int qty = 1;

    private double price;

    public Cupcake(Bottom bottom, Topping topping) {
        this.bottom = bottom;
        this.topping = topping;
        this.price = bottom.getPrice() + topping.getPrice();
    }

    @Override
    public int compareTo(Cupcake o) {
        if (this.bottom.id == o.getBottom().getId() && this.topping.getId() == o.getTopping().getId()) {
            return 0;
        }
        return 1;

    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public Topping getTopping() {
        return topping;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

}

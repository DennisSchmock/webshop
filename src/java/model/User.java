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
public class User {
    private String userName,fName,lName,street,city,email;
    private double balance;
    private int zip;

   
    public User(){
        
    }
    
    public User(String userName,String fName, String lName, String street, int zip, String city, String email,double balance){
        this.userName = userName;
        this.fName = fName;
        this.lName = lName;
        this.street = street;
        this.city = city;
        this.email = email;
        this.balance = balance;
    }
    
     public String getUserName() {
        return userName;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }
    
    
}

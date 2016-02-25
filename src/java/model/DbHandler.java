/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dennisschmock
 */
public class DbHandler {

    public static void main(String[] args) {

    }

    //Get user from DB and return a User object
    public User loadUser(String username) {
        User user = new User();
        String userName, fName, lName, street, city, email;
        int zip;
        int cosId;
        Double balance = 0.00;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cupCakeShop", "root", "Ospekos_22");

            String sqlString = "SELECT username,fname,lname,street,zip,city,email,balance,cos_id FROM customers WHERE username = ?;";
            PreparedStatement stmt = conn.prepareStatement(sqlString);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userName = rs.getString("username");
                fName = rs.getString("fname");
                lName = rs.getString("lname");
                zip = rs.getInt("zip");
                street = rs.getString("street");
                city = rs.getString("city");
                email = rs.getString("email");
                balance = rs.getDouble("balance");
                cosId = rs.getInt("cos_id");
                conn.close();
                return user = new User(userName, fName, lName, street, zip, city, email, balance, cosId);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return user;

    }

    //Check if user exist and correct password
    public boolean validateUser(String userName, String pwd) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cupCakeShop", "root", "Ospekos_22");

            String sqlString = "SELECT pwd FROM customers WHERE username = ?;";
            PreparedStatement stmt = conn.prepareStatement(sqlString);

            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                boolean validated = rs.getString("pwd").equals(pwd);
                conn.close();
                return validated;
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return false;

    }

    //Get toppings from DB and return a List
    public List<Topping> getToppings() {
        List<Topping> toppings = new ArrayList<>();
        Map<String, List<Object>> map = new HashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cupCakeShop", "root", "Ospekos_22");

            String sqlString = "SELECT top_id,top_name,top_price FROM toppings;";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("top_id");
                String name = rs.getString("top_name");
                Double price = rs.getDouble("top_price");
                toppings.add(new Topping(name, price, id));
            }
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toppings;

    }

    //Take bottoms and return them in a List
    public List<Bottom> getBottoms() {
        List<Bottom> bottoms = new ArrayList<>();
        Map<String, List<Object>> map = new HashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cupCakeShop", "root", "Ospekos_22");

            String sqlString = "SELECT bot_id,bot_name,bot_price FROM bottoms;";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("bot_id");
                String name = rs.getString("bot_name");
                Double price = rs.getDouble("bot_price");
                bottoms.add(new Bottom(name, price, id));
            }
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bottoms;

    }

    //Take contents from basket and put them in database.
    public boolean checkOut(List<Cupcake> shoppingCart, User user) {
        double balance = 0.00;
        double totalPrice = getTotalPrice(shoppingCart);
        int orderId = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cupCakeShop", "root", "Ospekos_22");

            String sqlString = "select balance from customers where cos_id=?;";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setInt(1, user.getCosId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                balance = rs.getDouble("balance");
            }
            if (balance - this.getTotalPrice(shoppingCart) < 0) {
                //return false;
            } else {
                sqlString = "insert into orders(cos_id) values(?);";
                stmt = conn.prepareStatement(sqlString);
                stmt.setInt(1, user.getCosId());
                stmt.execute();
                sqlString = "SELECT LAST_INSERT_ID();";
                rs = stmt.executeQuery(sqlString);
                rs.next();
                orderId = rs.getInt("LAST_INSERT_ID()");
                sqlString = "insert into order_details (order_id,top_id,bot_id,qty) values(?,?,?,?);";
                stmt = conn.prepareStatement(sqlString);
                for (Cupcake cupcake : shoppingCart) {
                    stmt.setInt(1, orderId);
                    stmt.setInt(2, cupcake.getTopping().getId());
                    stmt.setInt(3, cupcake.getBottom().getId());
                    stmt.setInt(4, cupcake.getQty());
                    stmt.execute();
                }
                sqlString = "update customers set balance = balance - ? where cos_id=?;";
                stmt = conn.prepareStatement(sqlString);
                stmt.setDouble(1, totalPrice);
                stmt.setInt(2, user.getCosId());
                stmt.execute();
                conn.close();

                return true;

            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        return false;

    }

    //Helper method for getting total price
    private double getTotalPrice(List<Cupcake> shoppingCart) {
        double totalPrice = 0.00;
        for (Cupcake cup : shoppingCart) {
            totalPrice = totalPrice + (cup.getPrice() * cup.getQty());
        }
        totalPrice = this.round(totalPrice, 2);
        return totalPrice;
    }

    //Helper method. Found on StackOverflow.
    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public boolean refillUserCredits(User user, Double credits) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cupCakeShop", "root", "Ospekos_22");

            String sqlString = "update customers set balance=balance + ? where cos_id=? ;;";
            PreparedStatement stmt = conn.prepareStatement(sqlString);
            stmt.setDouble(1, credits);
            stmt.setInt(2, user.getCosId());
            stmt.execute();
            conn.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
}

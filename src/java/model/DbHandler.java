/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
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
        DbHandler dbhandler = new DbHandler();
        System.out.println(dbhandler.validateUser("daeniz", "111"));
        dbhandler.loadUser("daeniz");
        List<Topping> toppings = dbhandler.getToppings();
        for (Topping topping : toppings) {
            System.out.println(topping.name);
            System.out.println(topping.id);
            System.out.println(topping.price);

        }

        List<Bottom> bottoms = dbhandler.getBottoms();

    }

    public User loadUser(String username) {
        User user = new User();
        String userName, fName, lName, street, city, email;
        int zip;
        Double balance = 0.00;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cupCakeShop", "root", "Ospekos_22");

            String sqlString = "SELECT username,fname,lname,street,zip,city,email,balance FROM customers WHERE username = ?;";
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
                return user = new User(userName, fName, lName, street, zip, city, email, balance);

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;

    }

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

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return toppings;

    }

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

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bottoms;

    }

    private Map<String, List<Object>> resultSetToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        Map<String, List<Object>> map = new HashMap<>(columns);
        for (int i = 1; i <= columns; ++i) {
            map.put(md.getColumnName(i), new ArrayList<>());
        }
        while (rs.next()) {
            for (int i = 1; i <= columns; ++i) {
                map.get(md.getColumnName(i)).add(rs.getObject(i));
            }
        }

        return map;
    }

}

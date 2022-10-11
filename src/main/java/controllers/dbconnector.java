package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnector {
    public Connection connection;

    public Connection getconnection()
    {

        try {
            String dbname="hotel_management";
            String username="root";
            String password="123";

            String port="3306";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbname,username,password);




        } catch (Exception e) {
            e.printStackTrace();
        }
       return connection;
    }
}

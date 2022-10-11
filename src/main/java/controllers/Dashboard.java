package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;


public class Dashboard implements Initializable {
    public Label todaybooks;
    @FXML
    private Label totalincome;
    @FXML
    private Label todayincome;
    @FXML
    private Label todaybook;
    dbconnector connector;
    Connection connection;
    int daybooks=0;

    public void setTodaybook() {

    }

    public void setTodayincome() {

    }

    public void setTotalincome() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setTodaybook();
        setTodayincome();
        setTotalincome();
    }

    private void getdate() throws SQLException {
        String date="2022-10-02";
        int t_total=0;
        int total=0;
        connector=new dbconnector();
        connection=connector.getconnection();
        String sql="select * from customer";
        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);
        while (rs.next())
        {
         if(date.equals(rs.getString(6)))
         {
             t_total=t_total+Integer.parseInt(rs.getString(5));
             daybooks++;
         }
         total=total+Integer.parseInt(rs.getString(5));
        }
        todaybooks.setText(Integer.toString(daybooks));
        todayincome.setText(Integer.toString(t_total)+" af");
        totalincome.setText(Integer.toString(total)+" af");
    }
}

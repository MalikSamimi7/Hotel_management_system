package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Customers implements Initializable {
    public TextField searchbox;
    public TableColumn col_checkout;
    public TableColumn col_checkedin;
    public TableColumn col_total;
    public TableColumn col_phone;
    public TableColumn col_lastname;
    public TableColumn col_firstname;
    public TableColumn col_id;
    public TableView customertable;
    ObservableList<customermodel> customerlist= FXCollections.observableArrayList();
    dbconnector connector;
    Connection connection;

    public void onsearchtyoe(KeyEvent keyEvent) {
        System.out.println("typed ");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loaddata();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void loaddata() throws SQLException {
        if(!customertable.getItems().isEmpty())
        {
            customerlist.clear();
            customertable.setItems(customerlist);
        }
        connector=new dbconnector();
        connection=connector.getconnection();
        String sql="select * from customer";
        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);
        while (rs.next())
        {

            customerlist.add(new customermodel(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
            ));
            customertable.setItems(customerlist);
            System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));


        }


        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        col_total.setCellValueFactory(new PropertyValueFactory<>("totalpayment"));
        col_checkedin.setCellValueFactory(new PropertyValueFactory<>("checkedindate"));
        col_checkout.setCellValueFactory(new PropertyValueFactory<>("checkedoutdate"));

    }
}

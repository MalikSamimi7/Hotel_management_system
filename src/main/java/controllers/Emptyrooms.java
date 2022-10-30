package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Emptyrooms implements Initializable {
    public TextField roomno;
    public TextField price;
    public Button checkin;
    public TableView<rooms> roomtable;
    public Button btnadd;

    public Button clear;

    public TableColumn<rooms,String> col_roomprice;
    public TableColumn<rooms,String> col_roomstatus;
    public TableColumn<rooms,String> col_roomtype;
    public TableColumn<rooms,String> col_roomno;
    public TextField searchbox;
    @FXML
    private ComboBox<String> roomtype;
    @FXML
    private ComboBox<String> roomstatus;
    ObservableList<rooms> roomlist=FXCollections.observableArrayList();
    dbconnector connector;
    Connection connection;
    FilteredList<rooms> filteredList=new FilteredList<>(roomlist,e ->true);
    String testroomno;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            checkroomstatus();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setChoiceBox();


        try {
            loaddata();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            setRoomno();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void checkroomstatus() throws SQLException {
        String sql= "update rooms set roomstatus='Available' where checkoutdate<curdate();";
        connector=new dbconnector();
        connection=connector.getconnection();
        Statement statement= connection.createStatement();
        statement.executeUpdate(sql);

    }

    private void loaddata() throws SQLException {
        if(!roomtable.getItems().isEmpty())
        {
           roomlist.clear();
           roomtable.setItems(roomlist);
        }
        
        String sql="select * from rooms";
        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);
        while (rs.next())
        {

                roomlist.add(new rooms(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
                roomtable.setItems(roomlist);
                System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));


            }


        col_roomno.setCellValueFactory(new PropertyValueFactory<>("Roomno"));
        col_roomtype.setCellValueFactory(new PropertyValueFactory<>("Roomtype"));
        col_roomstatus.setCellValueFactory(new PropertyValueFactory<>("Roomstatus"));
        col_roomprice.setCellValueFactory(new PropertyValueFactory<>("Price"));

    }

    public void setChoiceBox() {
        price.setText("");
        roomtype.getItems().addAll("Single Room","Double Room");
        roomtype.setValue("");
        //combobox1.setValue("Single Room");
        roomtype.setPromptText("Choose");
        roomtype.setCellFactory(param -> new ComboBoxListCell<String>() {{

                    setBackground(new Background(new BackgroundFill(Color.rgb(112,150,225),null,null)));

                }


              }
        );

        roomstatus.getItems().addAll("Available","Not Available","Occupied");
        //combobox1.setValue("Single Room");
        roomstatus.setValue("");
        roomstatus.setPromptText("Choose");
        roomstatus.setCellFactory(param -> new ComboBoxListCell<String>() {{

                    setBackground(new Background(new BackgroundFill(Color.rgb(112,150,225),null,null)));

                }


                }
        );

    }

    public void onadd(MouseEvent mouseEvent) {
        if(roomtype.getValue().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please choose Room type!");
            a.show();
            roomtype.requestFocus();
            return;
        }
        if(roomstatus.getValue().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please choose Room status!");
            a.show();
            roomstatus.requestFocus();
            return;
        }
        if(price.getText().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please Enter room price!");
            a.show();
            price.requestFocus();
            return;
        }

        try {
            addtodatabase();
            onclear(mouseEvent);
            loaddata();
        } catch (SQLException throwables) {
            Alert a =new Alert(Alert.AlertType.ERROR);
            a.setTitle("Input error");
            a.setHeaderText("Room with this number already added!");
            a.show();
            roomno.requestFocus();
            return;
        }


    }

    private void addtodatabase() throws SQLException {


        String sql="INSERT INTO `hotel_management`.`rooms` (`Roomno`,`Roomtype`, `Roomstatus`, `Price`) VALUES ('"+roomno.getText()+"','"+roomtype.getValue()+"','"+roomstatus.getValue()+"', '"+price.getText()+"')";
        System.out.println(sql);
        Statement statement= connection.createStatement();
        if(statement.executeUpdate(sql)>0)
        {
            System.out.println("added");
        }


    }
    public void onclear(MouseEvent mouseEvent) throws SQLException {
        setRoomno();
        roomtype.setValue("");
        roomstatus.setValue("");
        roomtype.setPromptText("Choose");
        roomstatus.setPromptText("Choose");
        price.setText("");
    }

    public void setRoomno() throws SQLException {
        int roomnotext=0;

        String customerquery="select * from rooms";

        Statement statement2=connection.createStatement();


        ResultSet rs2=statement2.executeQuery(customerquery);
        while (rs2.next())
        {
            roomnotext=Integer.parseInt(rs2.getString(1))+1 ;
        }
        roomno.setText(Integer.toString(roomnotext));
        testroomno=Integer.toString(roomnotext);

    }

    public void onprice(KeyEvent keyEvent)
    {
        price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*"))
                {
                    price.setText(newValue.replaceAll("[^\\d]",""));
                }
                searchlist(price);
            }
        });

    }

    public void searchlist(TextField textField)
    {

        textField.textProperty().addListener((observable, oldValue, newValue) -> {

            filteredList.setPredicate((Predicate<? super rooms>) rooms ->{
                if(newValue==null || newValue.isEmpty())
                {
                    return true;
                }
                String lnewvallue=newValue.toLowerCase();
                if(rooms.getRoomno().contains(lnewvallue))
                {
                    return true;
                }
                if(rooms.getRoomtype().toLowerCase().contains(lnewvallue))
                {
                    return true;
                }
                if(rooms.getRoomstatus().toLowerCase().contains(lnewvallue))
                {

                    return true;
                }
                if(rooms.getPrice().toLowerCase().contains(lnewvallue))
                {
                    return true;
                }

                return false;
            });

            SortedList<rooms> sortedList=new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(roomtable.comparatorProperty());
            roomtable.setItems(sortedList);

        });
    }


    public void oncheckin(MouseEvent mouseEvent) throws IOException {
        Stage newstage=new Stage();
        //Parent pane= FXMLLoader.load(getClass().getResource("../fxmls/login.fxml"));


        fxmlloader loader = new fxmlloader();
        AnchorPane view= loader.getpage("check_in");
        Scene scene=new Scene(view);
        newstage.initModality(Modality.WINDOW_MODAL);
        newstage.initOwner(new Main().getstage());
        newstage.setResizable(false);
        newstage.setHeight(650);
        newstage.setWidth(400);
        newstage.setScene(scene);
        newstage.show();


    }



    public void onroomstatus(ActionEvent actionEvent) {

    }



    public void onroomtype(ActionEvent actionEvent) {
    }

    public void onsearch(KeyEvent keyEvent) {
        searchlist(searchbox);
    }


    public void ontable(MouseEvent mouseEvent) {

        roomno.setText(roomtable.getSelectionModel().getSelectedItem().Roomno);
        roomtype.setValue(roomtable.getSelectionModel().getSelectedItem().Roomtype);
        roomstatus.setValue(roomtable.getSelectionModel().getSelectedItem().Roomstatus);
        price.setText(roomtable.getSelectionModel().getSelectedItem().Price);

    }

    public void onupdate(MouseEvent mouseEvent) throws SQLException {
        if(testroomno.equals(roomno.getText()))
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Room number doesn't exist!");
            a.show();
            return;
        }
        if(roomtype.getValue().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please choose Room type!");
            a.show();
            roomtype.requestFocus();
            return;
        }
        if(roomstatus.getValue().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please choose Room status!");
            a.show();
            roomstatus.requestFocus();
            return;
        }
        if(price.getText().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please Enter room price!");
            a.show();
            price.requestFocus();
            return;
        }

        String customerquery="UPDATE rooms SET Roomstatus='"+roomstatus.getValue()+"',Roomtype='"+roomtype.getValue()+"' ,price='"+Integer.parseInt(price.getText())+"' where Roomno='"+Integer.parseInt(roomno.getText())+"' ";

        Statement statement2=connection.createStatement();

        int rs2=statement2.executeUpdate(customerquery);
        if(rs2!=0)
        {

            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Update Succeed!");
            a.setHeaderText("Room number "+roomno.getText()+" has been successfully updated");
            a.show();
            loaddata();
            roomno.requestFocus();
            return;

        }



    }
}

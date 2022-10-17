package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class CheckIn implements Initializable{

    @FXML
    public ComboBox<String> roomno;
    @FXML
    public DatePicker checkindate;
    @FXML
    public DatePicker checkoutdate;
    @FXML
    public Label total;
    @FXML
    public TextField Email;
    @FXML
    public TextField phone;
    @FXML
    public TextField firstname;
    @FXML
    public TextField lastname;
    @FXML
    public Label customernolabel;
    @FXML
    public Button reset;
    @FXML
    public Button receipt;
    @FXML
    public Button checkin;
    public Label totaldayslabel;

    @FXML
    private ComboBox<String> roomtype;
    dbconnector connector;
    Connection connection;
    boolean checkedonce=false;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            connectdatabase();
            set();
            changeroomsstate("","");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void set() throws SQLException {

        int customerid=0;
        roomno.getItems().clear();
        roomtype.getItems().clear();
        String sql="select * from rooms";
        String customerquery="select * from customer";
        Statement statement= connection.createStatement();
        Statement statement2=connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);

        ResultSet rs2=statement2.executeQuery(customerquery);
        while (rs2.next())
        {
            customerid=Integer.parseInt(rs2.getString(1));
        }
         customernolabel.setText(Integer.toString(customerid+1));
        roomtype.setCellFactory(param -> new ComboBoxListCell<String>() {{

                    setBackground(new Background(new BackgroundFill(Color.rgb(112,150,225),null,null)));



                }


                }
        );

        roomno.setPromptText("Choose");
        roomno.setValue("Choose");
        roomno.setCellFactory(param -> new ComboBoxListCell<String>() {{

                    setBackground(new Background(new BackgroundFill(Color.rgb(112,150,225),null,null)));

                }


                }
        );

        HashMap<Integer,String> hm         =new HashMap<>();
        HashMap<Integer,String> hmroomtype=new HashMap<>();

        while (rs.next() ) {
            if(!rs.getString(3).equals("Available"))
            {
                continue;
            }
            hm.put(rs.getInt(1),rs.getString(1));
            hmroomtype.put(rs.getInt(1),rs.getString(2));
            roomno.getItems().add(rs.getString(1));
            roomtype.getItems().addAll(rs.getString(2));
        }



    }


    public void onreset(MouseEvent mouseEvent) throws SQLException {

       reset1();
    }

    public void onreceipt(MouseEvent mouseEvent) {



        if(firstname.getText().equals(null) || firstname.getText().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please fill firstname");
            a.show();
            firstname.requestFocus();
            return;
        }
        if(roomno.getValue().equals("Choose") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please select room number");
            a.show();
            roomno.requestFocus();
            return;
        }

        if(lastname.getText().equals(null) || lastname.getText().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please fill lastname");
            a.show();
            lastname.requestFocus();
            return;
        }
        if(phone.getText().equals(null) || phone.getText().equals("") || phone.getText().length()>10)
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please enter correct phone number!");
            a.show();
            phone.requestFocus();
            return;
        }
        Pattern p =Pattern.compile("^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$");
        if(Email.getText().equals(null) || Email.getText().equals("") || (!p.matcher(Email.getText().toString()).matches()) )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please enter correct email");
            a.show();
            Email.requestFocus();
            return;
        }
        try
        {
            if(roomno.getValue().equals(null) || roomno.getValue().equals("choose") )
            {
                Alert a =new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Input error");
                a.setHeaderText("Please choose correct room number!");
                a.show();
                roomno.requestFocus();
                return;
            }
            {

            }} catch (Exception e) {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please choose correct room number!");
            a.show();
            roomno.requestFocus();
            return;
        }





          pdfgenerator pdf=new pdfgenerator(customernolabel.getText(),firstname.getText(),lastname.getText(),checkindate.getValue().toString(),checkoutdate.getValue().toString()
          ,total.getText());
        Alert a =new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Receipt Created");
        a.setHeaderText("A Customer have been Receipted");
        a.show();
    }

    public void oncheckin(MouseEvent mouseEvent) throws SQLException {

        if(firstname.getText().equals(null) || firstname.getText().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please fill firstname");
            a.show();
            firstname.requestFocus();
            return;
        }
        if(roomno.getValue().equals("Choose") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please select room number");
            a.show();
            roomno.requestFocus();
            return;
        }

        if(lastname.getText().equals(null) || lastname.getText().equals("") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please fill lastname");
            a.show();
            lastname.requestFocus();
            return;
        }
        if(phone.getText().equals(null) || phone.getText().equals("") || phone.getText().length()>10)
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please enter correct phone number!");
            a.show();
            phone.requestFocus();
            return;
        }
        Pattern p =Pattern.compile("^(\\D)+(\\w)*((\\.(\\w)+)?)+@(\\D)+(\\w)*((\\.(\\D)+(\\w)*)+)?(\\.)[a-z]{2,}$");
        if(Email.getText().equals(null) || Email.getText().equals("") || (!p.matcher(Email.getText().toString()).matches()) )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please enter correct email");
            a.show();
            Email.requestFocus();
            return;
        }
        try
        {
        if(roomno.getValue().equals(null) || roomno.getValue().equals("choose") )
        {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please choose correct room number!");
            a.show();
            roomno.requestFocus();
            return;
        }
            {

            }} catch (Exception e) {
            Alert a =new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Input error");
            a.setHeaderText("Please choose correct room number!");
            a.show();
            roomno.requestFocus();
            return;
        }

        if(!checkedonce) {
            addtodatabase();
            //change room to not available state
            changeroomsstate(roomno.getValue(),checkoutdate.getValue().toString());
            //setCustomernolabel();
            //show checkin succesfull message
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Check-In Succeed");
            a.setHeaderText("A Customer have been Checked-In");
            a.show();
            checkedonce=true;
        }
        else
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Check-In Fail");
            a.setHeaderText("Customer have already Checked-In");
            a.show();
        }

    }

    private void changeroomsstate(String room, String nas_date) throws SQLException {
        if(room.equals("") || nas_date.equals(""))
        {
            String sql="update rooms set roomstatus='"+"Available"+"' where checkoutdate='"+LocalDate.now()+"'";
            Statement statement= connection.createStatement();
            statement.executeUpdate(sql);
        }


        else
        {

            String sql="update rooms set roomstatus='"+"Not Available"+"',checkoutdate='"+nas_date+"' where roomno='"+Integer.parseInt(room)+"'";
            Statement statement= connection.createStatement();
            statement.executeUpdate(sql);
        }
    }

    private void addtodatabase() throws SQLException {


        String sql="INSERT INTO `hotel_management`.`customer` (`First Name`, `Last Name`, `Phone`, `Total payment`, `Checked-In`, `Checked-out`) VALUES ('"+firstname.getText()+"','"+lastname.getText()+"', '"+phone.getText()+"', '"+total.getText()+"', '"+checkindate.getValue()+"', '"+checkoutdate.getValue()+"')";
        System.out.println(sql);
        Statement statement= connection.createStatement();
        if(statement.executeUpdate(sql)>0)
        {

        }


    }

    private void reset1() throws SQLException {
        checkedonce=false;
        setCustomernolabel();
        total.setText("0");
        totaldayslabel.setText("0");
        firstname.setText("");
        lastname.setText("");
        Email.setText("");
        phone.setText("");
        checkindate.setValue(null);
        checkoutdate.setValue(null);
        roomno.setValue("Choose");
        roomtype.setValue("Choose Room #");
    }

    public void onchckoutdate(ActionEvent mouseEvent) {

        if (checkindate.getValue()==null) {
             return;
        }
      else if (checkoutdate.getValue()==null) {
           return;
        }

        else {


            LocalDate localDate1 = checkoutdate.getValue();
            LocalDate localDate2 = checkindate.getValue();

            int totaldays = localDate1.getDayOfYear() - localDate2.getDayOfYear();

            System.out.println(totaldays);
            if (totaldays >= 1) {
                totaldayslabel.setText(Integer.toString(totaldays));
            } else {
                totaldayslabel.setText("0");
                Alert a =new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Invalid date");
                a.setHeaderText("Please choose a Correct date");
                a.show();
                checkoutdate.setValue(null);
            }
        }


    }

    public void oncheckindate(ActionEvent mouseEvent) {


        if (checkindate.getValue()==null) {
            return;
        }
       else if (checkoutdate.getValue()==null) {
            return;
        }

       else {
            LocalDate localDate1 = checkoutdate.getValue();
            LocalDate localDate2 = checkindate.getValue();

            int totaldays = localDate1.getDayOfYear() - localDate2.getDayOfYear();

            System.out.println(totaldays);
            if (totaldays >= 1) {

                totaldayslabel.setText(Integer.toString(totaldays));
            } else {
                totaldayslabel.setText("0");
                Alert a =new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Invalid date");
                a.setHeaderText("Please choose a Correct date");
                a.show();
                checkindate.setValue(null);
            }
        }
    }
    public void connectdatabase() throws SQLException {
        connector=new dbconnector();
        connection=connector.getconnection();
        String sql="select * from customer";
        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);
        while (rs.next())
        {
            System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
        }


    }



    public void onroomno(ActionEvent mouseEvent) throws SQLException {

        int index=roomno.getSelectionModel().getSelectedIndex();
        String value=roomtype.getItems().get(index);
        roomtype.setValue(value);


        String sql="select * from rooms";
        Statement statement= connection.createStatement();
        ResultSet rs= statement.executeQuery(sql);
        int daysint=0;
        int price=0;

        while (rs.next()) {

            if(rs.getString(1).equals(roomno.getValue())) {
                daysint = Integer.parseInt(totaldayslabel.getText());
                price = Integer.parseInt(rs.getString(4));
            }

        }

        total.setText(Integer.toString(daysint*price));

    }

    public void onselect()
    {
        roomno.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->
        {
            roomtype.setValue(roomtype.getValue());
            System.out.println("cliccccc");
        });
    }


    public void onphoneno(KeyEvent actionEvent) {
        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("\\d*"))
                {
                    phone.setText(newValue.replaceAll("[^\\d]",""));
                }
            }
        });
    }

    public void setCustomernolabel() throws SQLException {
        String customerquery="select * from customer";
        Statement statement2=connection.createStatement();

        int customerid=0;
        ResultSet rs2=statement2.executeQuery(customerquery);
        while (rs2.next())
        {
            customerid=Integer.parseInt(rs2.getString(1));
        }
        customernolabel.setText(Integer.toString(customerid+1));
    }

}

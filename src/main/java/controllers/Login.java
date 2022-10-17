package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;



import java.io.IOException;

public class Login {

    @FXML
    private Button btnlogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    public void btnforgot(ActionEvent actionEvent) {

    }

    public void login(ActionEvent actionEvent) throws IOException {
       controllers.Main m =new controllers.Main();
        if (username.getText().equals("password") && password.getText().equals("123"))
        {
          m.changescene("../fxmls/afterlogin.fxml");

        }
        else
        {
            Alert a =new Alert(Alert.AlertType.ERROR);
            a.setTitle("Invalid username or password!");
            a.setHeaderText("Please enter Correct username and password");
            a.show();
        }

    }

    public void onclose(MouseEvent mouseEvent) {
        System.exit(0);
    }
}

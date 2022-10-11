package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Afterlogin implements Initializable {


    @FXML
    private BorderPane boderpane;


    public void dashboardmenue(MouseEvent mouseEvent) {

        
    }

    public void onclose(MouseEvent mouseEvent) {
        System.exit(0);
    }

    public void onhboxtwo(MouseEvent mouseEvent) throws IOException {
        System.out.println("box2");
        loadpane("Emptyrooms");
    }

    public void onhbox3(MouseEvent mouseEvent) throws IOException {
        loadpane("Customers");
        System.out.println("box3");
    }

    public void onhbox(MouseEvent mouseEvent) throws IOException {

        loadpane("Dashboard");
    }

    public void loadpane(String name) throws IOException {
        fxmlloader loader = new fxmlloader();
        AnchorPane view= loader.getpage(name);
        boderpane.setCenter(view);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadpane("Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class fxmlloader {

    private AnchorPane view;


    public AnchorPane getpage(String name) throws IOException {

        URL fileurl = Main.class.getResource("../fxmls/" + name + ".fxml");

        view = new FXMLLoader().load(fileurl);
        return view;
    }

}

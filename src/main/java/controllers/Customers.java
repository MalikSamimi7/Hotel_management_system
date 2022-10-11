package controllers;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Customers {
    public TableView customertable;
    public TextField searchbox;

    public void onsearchtyoe(KeyEvent keyEvent) {
        System.out.println("typed ");
    }
}

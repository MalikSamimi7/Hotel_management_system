package controllers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {
    private static Stage stg;
    private double xoffset=0;
    private double yoffset=0;


    @Override
    public void start(Stage stage) throws IOException {

      Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("\\fxmls\\login.fxml"));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
             xoffset=event.getSceneX();
             yoffset=event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX()-xoffset);
                stage.setY(event.getScreenY()-yoffset);
            }
        });
        stg = stage;
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setX(200);
        stage.setY(100);
        stage.setScene(scene);
        stage.show();

    }

    public void changescene(String fxml) throws IOException {

        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            xoffset=event.getSceneX();
            yoffset=event.getSceneY();
        }
    });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            stg.setX(event.getScreenX()-xoffset);
            stg.setY(event.getScreenY()-yoffset);
        }
    });
         stg.setHeight(600);
         stg.setWidth(1099);
        stg.getScene().setRoot(pane);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       launch(args);
       // pdfgenerator pdf=new pdfgenerator("1","naveed","sultani","2022","2022","2000");
    }


    public Stage getstage() throws IOException {

        return stg;

    }
}
package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.controller.HomeController;

import java.io.IOException;

public class MangaMain extends Application {

    public static  Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
     primaryStage = stage;
     HomeController.getHomePage(new HomeController());
     primaryStage.show();

    }



}

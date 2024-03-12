package com.example.home;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HomeApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Crime c1 = new Crime();
        System.out.println(c1.getResolve_date());
//        c1.setResolve_date(new SimpleStringProperty("rsdate"));
        System.out.println(c1.getResolve_date());

        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 709, 436);
        stage.setTitle("HOME");
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
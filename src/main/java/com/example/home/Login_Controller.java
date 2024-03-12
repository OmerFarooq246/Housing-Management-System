package com.example.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login_Controller {

    public Login_Controller() throws SQLException {
    }

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button LoginButton;

    @FXML
    protected void LoginButtonController(ActionEvent event) throws SQLException, IOException {

        String username = tfUsername.getText();
        String password = tfPassword.getText();
        boolean corruser = false;
        boolean corrpass = false;

        if (username.isEmpty() || password.isEmpty()) {
            if (username.isEmpty()) {
                tfUsername.setPromptText("Username can't be empty");
            }
            if (password.isEmpty()) {
                tfPassword.setPromptText("Password can't be empty");
            }
        } else {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
            Statement statement = connection.createStatement();
            String query = "select user_name from user";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("user_name");
                if (name.equals(username)) {
                    corruser = true;
                    break;
                }
            }

            if (corruser == false) {
                tfUsername.clear();
                tfUsername.setPromptText("Invalid username");
                tfPassword.clear();
                tfPassword.setPromptText("Enter password");
            } else {
                rs = statement.executeQuery("select password from user where user_name = " + "\"" + username + "\"");
                while(rs.next()){
                    String pass = rs.getString("password");
                    if (pass.equals(password)) {
                        corrpass = true;
                        break;
                    }
                }

                if(corrpass == false){
                    tfPassword.clear();
                    tfPassword.setPromptText("Invalid password");
                }
            }

            if (corruser && corrpass) { //both true
                System.out.println("Login successful");
                //Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                Parent root = loader.load();
                HomePage_Controller homePage_controller = loader.getController();
                rs = statement.executeQuery("select type from user where user_name = " + "\"" + username + "\"");
                String type = "";
                while (rs.next()){
                    type = rs.getString(1);
                }
                homePage_controller.setUserType(type);

                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomePage.fxml")));
                stage.setScene(scene);
                stage.show();
            }
            connection.close();
        }
    }
}
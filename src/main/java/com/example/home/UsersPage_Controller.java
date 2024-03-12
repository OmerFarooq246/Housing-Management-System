package com.example.home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class UsersPage_Controller implements Initializable {

    @FXML
    private TableView<Users> usersTable;
    @FXML
    TableColumn<Users, Integer> User_ID;
    @FXML
    TableColumn<Users, String> Name;
    @FXML
    TableColumn<Users, String> Password;
    @FXML
    TableColumn<Users, String> Type;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfpass;
    @FXML
    private TextField tftype;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfname.getText().equals("") &&
                tfpass.getText().equals("") &&
                tftype.getText().equals("")
        ){
            usersTable.setItems(getUsers());
        }
        else {
            Users users = new Users();
            if(!tfid.getText().equals("")) users.setUser_ID(Integer.parseInt(tfid.getText()));
            if(!tfname.getText().equals("")) users.setName(tfname.getText());
            if(!tfpass.getText().equals("")) users.setPassword(tfpass.getText());
            if(!tftype.getText().equals("")) users.setType(tftype.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call userinsertion(?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,users.getUser_ID());
            callableStatement.setString(2,users.getName());
            callableStatement.setString(3,users.getPassword());
            callableStatement.setString(4,users.getType());
            callableStatement.execute();
            usersTable.setItems(getUsers());
            tfid.clear();
            tfname.clear();
            tfpass.clear();
            tftype.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfname.getText().equals("") &&
                tfpass.getText().equals("") &&
                tftype.getText().equals("")
        ){
            usersTable.setItems(getUsers());
        }
        else {
            Users users = new Users();
            if(!tfid.getText().equals("")) users.setUser_ID(Integer.parseInt(tfid.getText()));
            if(!tfname.getText().equals("")) users.setName(tfname.getText());
            if(!tfpass.getText().equals("")) users.setPassword(tfpass.getText());
            if(!tftype.getText().equals("")) users.setType(tftype.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from user where user_id like ? and user_name like ? and password like ? and type like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,users.getUser_ID());
            else preparedStatement.setString(1,"%");
            if(!tfname.getText().equals("")) preparedStatement.setString(2, users.getName());
            else preparedStatement.setString(2,"%");
            if(!tfpass.getText().equals("")) preparedStatement.setString(3, users.getPassword());
            else preparedStatement.setString(3,"%");
            if(!tftype.getText().equals("")) preparedStatement.setString(4, users.getType());
            else preparedStatement.setString(4,"%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Users> records = FXCollections.observableArrayList();
            while (rs.next()){
                Users uusers = new Users();
                uusers.setUser_ID(rs.getInt(1));
                uusers.setName(rs.getString(2));
                uusers.setPassword(rs.getString(3));
                uusers.setType(rs.getString(4));
                records.add(uusers);
            }
            usersTable.setItems(records);
            tfid.clear();
            tfname.clear();
            tfpass.clear();
            tftype.clear();
            connection.close();
        }
    }


    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfname.getText().equals("") &&
                tfpass.getText().equals("") &&
                tftype.getText().equals("")
        ){
            usersTable.setItems(getUsers());
        }
        else {
            Users users = new Users();
            if(!tfid.getText().equals("")) users.setUser_ID(Integer.parseInt(tfid.getText()));
            if(!tfname.getText().equals("")) users.setName(tfname.getText());
            if(!tfpass.getText().equals("")) users.setPassword(tfpass.getText());
            if(!tftype.getText().equals("")) users.setType(tftype.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from user where user_id like ? and user_name like ? and password like ? and type like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,users.getUser_ID());
            else preparedStatement.setString(1,"%");
            if(!tfname.getText().equals("")) preparedStatement.setString(2, users.getName());
            else preparedStatement.setString(2,"%");
            if(!tfpass.getText().equals("")) preparedStatement.setString(3, users.getPassword());
            else preparedStatement.setString(3,"%");
            if(!tftype.getText().equals("")) preparedStatement.setString(4, users.getType());
            else preparedStatement.setString(4,"%");
            preparedStatement.execute();
            usersTable.setItems(getUsers());
            tfid.clear();
            tfname.clear();
            tfpass.clear();
            tftype.clear();
            connection.close();
        }
    }

    @FXML
    private Button btBack;

    @FXML
    protected void BackButtonController(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("HomePage.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User_ID.setCellValueFactory(new PropertyValueFactory<Users, Integer>("user_ID"));
        Name.setCellValueFactory(new PropertyValueFactory<Users, String>("name"));
        Password.setCellValueFactory(new PropertyValueFactory<Users, String>("password"));
        Type.setCellValueFactory(new PropertyValueFactory<Users, String>("type"));
        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            usersTable.setItems(getUsers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Users> getUsers() throws SQLException {
        ObservableList<Users> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from user";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Users users = new Users();
            users.setUser_ID(rs.getInt(1));
            users.setName(rs.getString(2));
            users.setPassword(rs.getString(3));
            users.setType(rs.getString(4));
            records.add(users);
        }
        connection.close();
        return records;
    }
}

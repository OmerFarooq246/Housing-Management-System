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

public class StreetPage_Controller implements Initializable {

    @FXML
    private TableView<Street> streetTable;
    @FXML
    TableColumn<Street, Integer> Street_ID;
    @FXML
    TableColumn<Street, Integer> Street_Number;
    @FXML
    TableColumn<Street, String> Name;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfno;
    @FXML
    private TextField tfname;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
        tfno.getText().equals("") &&
        tfname.getText().equals("")){
            streetTable.setItems(getStreet());
        }
        else{
            Street street = new Street();
            if(!tfid.getText().equals("")) street.setStreet_ID(Integer.parseInt(tfid.getText()));
            if(!tfno.getText().equals("")) street.setStreet_number(Integer.parseInt(tfno.getText()));
            if(!tfname.getText().equals("")) street.setName(tfname.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call streetinsertion(?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,street.getStreet_ID());
            callableStatement.setInt(2,street.getStreet_number());
            callableStatement.setString(3,street.getName());
            callableStatement.execute();
            streetTable.setItems(getStreet());
            tfid.clear();
            tfno.clear();
            tfname.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfno.getText().equals("") &&
                tfname.getText().equals("")){
            streetTable.setItems(getStreet());
        }
        else{
            Street street = new Street();
            if(!tfid.getText().equals("")) street.setStreet_ID(Integer.parseInt(tfid.getText()));
            if(!tfno.getText().equals("")) street.setStreet_number(Integer.parseInt(tfno.getText()));
            if(!tfname.getText().equals("")) street.setName(tfname.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from street where street_ID like ? and number like ? and name like ?";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,street.getStreet_ID());
            else preparedStatement.setString(1,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(2,street.getStreet_number());
            else preparedStatement.setString(2,"%");
            if(!tfname.getText().equals("")) preparedStatement.setString(3,street.getName());
            else preparedStatement.setString(3,"%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Street> records = FXCollections.observableArrayList();
            while (rs.next()){
                Street sstreet = new Street();
                sstreet.setStreet_ID(rs.getInt(1));
                sstreet.setStreet_number(rs.getInt(2));
                sstreet.setName(rs.getString(3));
                records.add(sstreet);
            }
            streetTable.setItems(records);
            tfid.clear();
            tfno.clear();
            tfname.clear();
            connection.close();
        }
    }


    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfno.getText().equals("") &&
                tfname.getText().equals("")){
            streetTable.setItems(getStreet());
        }
        else{
            Street street = new Street();
            if(!tfid.getText().equals("")) street.setStreet_ID(Integer.parseInt(tfid.getText()));
            if(!tfno.getText().equals("")) street.setStreet_number(Integer.parseInt(tfno.getText()));
            if(!tfname.getText().equals("")) street.setName(tfname.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from street where street_ID like ? and number like ? and name like ?";
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,street.getStreet_ID());
            else preparedStatement.setString(1,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(2,street.getStreet_number());
            else preparedStatement.setString(2,"%");
            if(!tfname.getText().equals("")) preparedStatement.setString(3,street.getName());
            else preparedStatement.setString(3,"%");
            preparedStatement.execute();
            streetTable.setItems(getStreet());
            tfid.clear();
            tfno.clear();
            tfname.clear();
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
        Street_ID.setCellValueFactory(new PropertyValueFactory<Street, Integer>("street_ID"));
        Street_Number.setCellValueFactory(new PropertyValueFactory<Street, Integer>("street_number"));
        Name.setCellValueFactory(new PropertyValueFactory<Street, String>("name"));
        streetTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            streetTable.setItems(getStreet());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Street> getStreet() throws SQLException {
        ObservableList<Street> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from street";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Street street = new Street();
            street.setStreet_ID(rs.getInt(1));
            street.setStreet_number(rs.getInt(2));
            street.setName(rs.getString(3));
            records.add(street);
        }
        connection.close();
        return records;
    }
}

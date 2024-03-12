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

public class SuspectsPage_Controller implements Initializable {

    @FXML
    private TableView<Suspects> suspectsTable;
    @FXML
    TableColumn<Suspects,Integer> Suspect_ID;
    @FXML
    TableColumn<Suspects,Integer> Crime_ID;
    @FXML
    TableColumn<Suspects,String> Name;
    @FXML
    TableColumn<Suspects,String> Allegations;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcrime;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfall;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfcrime.getText().equals("") &&
                tfname.getText().equals("") &&
                tfall.getText().equals("")
        ){
            suspectsTable.setItems(getSuspects());
        }
        else{
            Suspects suspects = new Suspects();
            if(!tfid.getText().equals("")) suspects.setSuspect_ID(Integer.parseInt(tfid.getText()));
            if(!tfcrime.getText().equals("")) suspects.setCrime_ID(Integer.parseInt(tfcrime.getText()));
            if(!tfname.getText().equals("")) suspects.setName(tfname.getText());
            if(!tfall.getText().equals("")) suspects.setAllegations(tfall.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call suspectinsertion(?,?,?,?)}";
            CallableStatement callableStatement= connection.prepareCall(query);
            callableStatement.setInt(1,suspects.getSuspect_ID());
            callableStatement.setInt(2,suspects.getCrime_ID());
            callableStatement.setString(3,suspects.getName());
            callableStatement.setString(4,suspects.getAllegations());
            callableStatement.execute();
            suspectsTable.setItems(getSuspects());
            tfid.clear();
            tfcrime.clear();
            tfname.clear();
            tfall.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfcrime.getText().equals("") &&
                tfname.getText().equals("") &&
                tfall.getText().equals("")
        ){
            suspectsTable.setItems(getSuspects());
        }
        else{
            Suspects suspects = new Suspects();
            if(!tfid.getText().equals("")) suspects.setSuspect_ID(Integer.parseInt(tfid.getText()));
            if(!tfcrime.getText().equals("")) suspects.setCrime_ID(Integer.parseInt(tfcrime.getText()));
            if(!tfname.getText().equals("")) suspects.setName(tfname.getText());
            if(!tfall.getText().equals("")) suspects.setAllegations(tfall.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from suspect where suspect_ID like ? and crime_id like ? and suspect_name like ? and alegations like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,suspects.getSuspect_ID());
            else preparedStatement.setString(1,"%");
            if(!tfcrime.getText().equals("")) preparedStatement.setInt(2, suspects.getCrime_ID());
            else preparedStatement.setString(2,"%");
            if(!tfname.getText().equals("")) preparedStatement.setString(3,suspects.getName());
            else preparedStatement.setString(3,"%");
            if(!tfall.getText().equals("")) preparedStatement.setString(4, suspects.getAllegations());
            else preparedStatement.setString(4,"%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Suspects> records = FXCollections.observableArrayList();
            while (rs.next()){
                Suspects ssuspects = new Suspects();
                ssuspects.setSuspect_ID(rs.getInt(1));
                ssuspects.setCrime_ID(rs.getInt(2));
                ssuspects.setName(rs.getString(3));
                ssuspects.setAllegations(rs.getString(4));
                records.add(ssuspects);
            }
            suspectsTable.setItems(records);
            tfid.clear();
            tfcrime.clear();
            tfname.clear();
            tfall.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfcrime.getText().equals("") &&
                tfname.getText().equals("") &&
                tfall.getText().equals("")
        ){
            suspectsTable.setItems(getSuspects());
        }
        else{
            Suspects suspects = new Suspects();
            if(!tfid.getText().equals("")) suspects.setSuspect_ID(Integer.parseInt(tfid.getText()));
            if(!tfcrime.getText().equals("")) suspects.setCrime_ID(Integer.parseInt(tfcrime.getText()));
            if(!tfname.getText().equals("")) suspects.setName(tfname.getText());
            if(!tfall.getText().equals("")) suspects.setAllegations(tfall.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from suspect where suspect_ID like ? and crime_id like ? and suspect_name like ? and alegations like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,suspects.getSuspect_ID());
            else preparedStatement.setString(1,"%");
            if(!tfcrime.getText().equals("")) preparedStatement.setInt(2, suspects.getCrime_ID());
            else preparedStatement.setString(2,"%");
            if(!tfname.getText().equals("")) preparedStatement.setString(3,suspects.getName());
            else preparedStatement.setString(3,"%");
            if(!tfall.getText().equals("")) preparedStatement.setString(4, suspects.getAllegations());
            else preparedStatement.setString(4,"%");
            preparedStatement.execute();
            suspectsTable.setItems(getSuspects());
            tfid.clear();
            tfcrime.clear();
            tfname.clear();
            tfall.clear();
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
        Suspect_ID.setCellValueFactory(new PropertyValueFactory<Suspects,Integer>("suspect_ID"));
        Crime_ID.setCellValueFactory(new PropertyValueFactory<Suspects,Integer>("crime_ID"));
        Name.setCellValueFactory(new PropertyValueFactory<Suspects,String>("name"));
        Allegations.setCellValueFactory(new PropertyValueFactory<Suspects,String>("allegations"));
        suspectsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            suspectsTable.setItems(getSuspects());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Suspects> getSuspects() throws SQLException {
        ObservableList<Suspects> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from suspect";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Suspects suspects = new Suspects();
            suspects.setSuspect_ID(rs.getInt(1));
            suspects.setCrime_ID(rs.getInt(2));
            suspects.setName(rs.getString(3));
            suspects.setAllegations(rs.getString(4));
            records.add(suspects);
        }
        connection.close();
        return records;
    }
}

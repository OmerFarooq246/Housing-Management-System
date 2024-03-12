package com.example.home;

import javafx.beans.property.SimpleStringProperty;
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

public class CrimePage_Controller implements Initializable {

    @FXML
    private TableView<Crime> crimeTable;
    @FXML
    TableColumn<Crime, Integer> Crime_ID;
    @FXML
    TableColumn<Crime, String> Type;
    @FXML
    TableColumn<Crime, String> Date;
    @FXML
    TableColumn<Crime, String> resolved_Date;
    @FXML
    TableColumn<Crime, String> Status;
    @FXML
    TableColumn<Crime, Integer> Block_ID;
    @FXML
    TableColumn<Crime, Integer> Street_ID;
    @FXML
    TableColumn<Crime, Integer> Property_ID;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tfresdate;
    @FXML
    private TextField tfstatus;
    @FXML
    private TextField tfblock;
    @FXML
    private TextField tfstreet;
    @FXML
    private TextField tfproperty;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tftype.getText().equals("") &&
                tfdate.getText().equals("") &&
                tfresdate.getText().equals("") &&
                tfstatus.getText().equals("") &&
                tfblock.getText().equals("") &&
                tfstreet.getText().equals("") &&
                tfproperty.getText().equals("")
        ){
            crimeTable.setItems(getCrime());
        }
        else{
            Crime crime = new Crime();
            if(!tfid.getText().equals("")) crime.setCrime_ID(Integer.parseInt(tfid.getText()));
            if(!tftype.getText().equals("")) crime.setType(tftype.getText());
            if(!tfdate.getText().equals("")) crime.setDate(tfdate.getText());
            if(!tfresdate.getText().equals("")) crime.setResolve_date(tfresdate.getText());
            if(!tfstatus.getText().equals("")) crime.setStatus(tfstatus.getText());
            if(!tfblock.getText().equals("")) crime.setBlock_ID(Integer.parseInt(tfblock.getText()));
            if(!tfstreet.getText().equals("")) crime.setStreet_ID(Integer.parseInt(tfblock.getText()));
            if(!tfproperty.getText().equals("")) crime.setProperty_ID(Integer.parseInt(tfproperty.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call crimeinsertion(?,?,?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, crime.getCrime_ID());
            callableStatement.setString(2, crime.getType());
            callableStatement.setString(3, crime.getDate());
            callableStatement.setString(4, crime.getResolve_date());
            callableStatement.setString(5, crime.getStatus());
            callableStatement.setInt(6, crime.getBlock_ID());
            callableStatement.setInt(7, crime.getStreet_ID());
            callableStatement.setInt(8, crime.getProperty_ID());
            callableStatement.execute();
            crimeTable.setItems(getCrime());
            tfid.clear();
            tftype.clear();
            tfdate.clear();
            tfresdate.clear();
            tfstatus.clear();
            tfblock.clear();
            tfstreet.clear();
            tfproperty.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tftype.getText().equals("") &&
                tfdate.getText().equals("") &&
                tfresdate.getText().equals("") &&
                tfstatus.getText().equals("") &&
                tfblock.getText().equals("") &&
                tfstreet.getText().equals("") &&
                tfproperty.getText().equals("")
        ){
            crimeTable.setItems(getCrime());
        }
        else{
            Crime crime = new Crime();
            if(!tfid.getText().equals("")) crime.setCrime_ID(Integer.parseInt(tfid.getText()));
            if(!tftype.getText().equals("")) crime.setType(tftype.getText());
            if(!tfdate.getText().equals("")) crime.setDate(tfdate.getText());
            if(!tfresdate.getText().equals("")) crime.setResolve_date(tfresdate.getText());
            if(!tfstatus.getText().equals("")) crime.setStatus(tfstatus.getText());
            if(!tfblock.getText().equals("")) crime.setBlock_ID(Integer.parseInt(tfblock.getText()));
            if(!tfstreet.getText().equals("")) crime.setStreet_ID(Integer.parseInt(tfblock.getText()));
            if(!tfproperty.getText().equals("")) crime.setProperty_ID(Integer.parseInt(tfproperty.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from crime where crime_ID like ? and type like ? and date like ? and resolve_date like ? and status like ? and block_id like ? and street_id like ? and property_id like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if(!tfid.getText().equals("")) preparedStatement.setInt(1,crime.getCrime_ID());
            else preparedStatement.setString(1, "%");
            if(!tftype.getText().equals("")) preparedStatement.setString(2,crime.getType());
            else preparedStatement.setString(2, "%");
            if(!tfdate.getText().equals("")) preparedStatement.setString(3, crime.getDate());
            else preparedStatement.setString(3, "%");
            if(!tfresdate.getText().equals("")) preparedStatement.setString(4, crime.getResolve_date());
            else preparedStatement.setString(4, "%");
            if(!tfstatus.getText().equals("")) preparedStatement.setString(5,crime.getStatus());
            else preparedStatement.setString(5, "%");
            if(!tfblock.getText().equals("")) preparedStatement.setInt(6,crime.getBlock_ID());
            else preparedStatement.setString(6, "%");
            if(!tfstreet.getText().equals("")) preparedStatement.setInt(7,crime.getStreet_ID());
            else preparedStatement.setString(7, "%");
            if(!tfproperty.getText().equals("")) preparedStatement.setInt(8,crime.getProperty_ID());
            else preparedStatement.setString(8, "%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Crime> records = FXCollections.observableArrayList();
            while (rs.next()){
                Crime crime1 = new Crime();
                crime1.setCrime_ID(rs.getInt(1));
                crime1.setType(rs.getString(2));
                crime1.setDate(rs.getString(3));
                crime1.setResolve_date(rs.getString(4));
                crime1.setStatus(rs.getString(5));
                crime1.setBlock_ID(rs.getInt(6));
                crime1.setStreet_ID(rs.getInt(7));
                crime1.setProperty_ID(rs.getInt(8));
                records.add(crime1);
            }
            crimeTable.setItems(records);
            tfid.clear();
            tftype.clear();
            tfdate.clear();
            tfresdate.clear();
            tfstatus.clear();
            tfblock.clear();
            tfstreet.clear();
            tfproperty.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tftype.getText().equals("") &&
                tfdate.getText().equals("") &&
                tfresdate.getText().equals("") &&
                tfstatus.getText().equals("") &&
                tfblock.getText().equals("") &&
                tfstreet.getText().equals("") &&
                tfproperty.getText().equals("")
        ){
            return;
        }
        else{
            Crime crime = new Crime();
            if(!tfid.getText().equals("")) crime.setCrime_ID(Integer.parseInt(tfid.getText()));
            if(!tftype.getText().equals("")) crime.setType(tftype.getText());
            if(!tfdate.getText().equals("")) crime.setDate(tfdate.getText());
            if(!tfresdate.getText().equals("")) crime.setResolve_date(tfresdate.getText());
            if(!tfstatus.getText().equals("")) crime.setStatus(tfstatus.getText());
            if(!tfblock.getText().equals("")) crime.setBlock_ID(Integer.parseInt(tfblock.getText()));
            if(!tfstreet.getText().equals("")) crime.setStreet_ID(Integer.parseInt(tfblock.getText()));
            if(!tfproperty.getText().equals("")) crime.setProperty_ID(Integer.parseInt(tfproperty.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from crime where crime_ID like ? and type like ? and date like ? and resolve_date like ? and status like ? and block_id like ? and street_id like ? and property_id like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if(!tfid.getText().equals("")) preparedStatement.setInt(1,crime.getCrime_ID());
            else preparedStatement.setString(1, "%");
            if(!tftype.getText().equals("")) preparedStatement.setString(2,crime.getType());
            else preparedStatement.setString(2, "%");
            if(!tfdate.getText().equals("")) preparedStatement.setString(3, crime.getDate());
            else preparedStatement.setString(3, "%");
            if(!tfresdate.getText().equals("")) preparedStatement.setString(4, crime.getResolve_date());
            else preparedStatement.setString(4, "%");
            if(!tfstatus.getText().equals("")) preparedStatement.setString(5,crime.getStatus());
            else preparedStatement.setString(5, "%");
            if(!tfblock.getText().equals("")) preparedStatement.setInt(6,crime.getBlock_ID());
            else preparedStatement.setString(6, "%");
            if(!tfstreet.getText().equals("")) preparedStatement.setInt(7,crime.getStreet_ID());
            else preparedStatement.setString(7, "%");
            if(!tfproperty.getText().equals("")) preparedStatement.setInt(8,crime.getProperty_ID());
            else preparedStatement.setString(8, "%");
            preparedStatement.execute();
            crimeTable.setItems(getCrime());
            tfid.clear();
            tftype.clear();
            tfdate.clear();
            tfresdate.clear();
            tfstatus.clear();
            tfblock.clear();
            tfstreet.clear();
            tfproperty.clear();
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
        Crime_ID.setCellValueFactory(new PropertyValueFactory<Crime, Integer>("crime_ID"));
        Type.setCellValueFactory(new PropertyValueFactory<Crime, String>("type"));
        Date.setCellValueFactory(new PropertyValueFactory<Crime, String>("date"));
        resolved_Date.setCellValueFactory(new PropertyValueFactory<Crime, String>("resolve_date"));
        Status.setCellValueFactory(new PropertyValueFactory<Crime, String>("status"));
        Block_ID.setCellValueFactory(new PropertyValueFactory<Crime, Integer>("block_ID"));
        Street_ID.setCellValueFactory(new PropertyValueFactory<Crime, Integer>("street_ID"));
        Property_ID.setCellValueFactory(new PropertyValueFactory<Crime, Integer>("property_ID"));
        crimeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            crimeTable.setItems(getCrime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Crime> getCrime() throws SQLException {
        ObservableList<Crime> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from crime";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            Crime crime = new Crime();
            crime.setCrime_ID(rs.getInt(1));
            crime.setType(rs.getString(2));
            crime.setDate(rs.getString(3));
            crime.setResolve_date(rs.getString(4));
            crime.setStatus(rs.getString(5));
            crime.setBlock_ID(rs.getInt(6));
            crime.setStreet_ID(rs.getInt(7));
            crime.setProperty_ID(rs.getInt(8));
            records.add(crime);
        }

        connection.close();
        return records;
    }
}

package com.example.home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class OwnerPage_Controller implements Initializable {

    @FXML
    private TableView<Owner> ownerTable;
    @FXML
    TableColumn<Owner, Integer> Owner_ID;
    @FXML
    TableColumn<Owner, String> First_Name;
    @FXML
    TableColumn<Owner, String> Last_Name;
    @FXML
    TableColumn<Owner, Integer> CNIC;
    @FXML
    TableColumn<Owner, String> Address;
    @FXML
    TableColumn<Owner, Integer> Phone_Number;
    @FXML
    TableColumn<Owner, String> Profession;
    @FXML
    TableColumn<Owner, String> City;
    @FXML
    TableColumn<Owner, Integer> No_ofProperties;


    @FXML
    private TextField tfid;
    @FXML
    private TextField tffn;
    @FXML
    private TextField tflsn;
    @FXML
    private TextField tfcnic;
    @FXML
    private TextField tfaddress;
    @FXML
    private TextField tfno;
    @FXML
    private TextField tfprofession;
    @FXML
    private TextField tfcity;
    @FXML
    private TextField tfnoofprop;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tffn.getText().equals("") &&
                tflsn.getText().equals("") &&
                tfcnic.getText().equals("") &&
                tfaddress.getText().equals("") &&
                tfno.getText().equals("") &&
                tfprofession.getText().equals("") &&
                tfcity.getText().equals("") &&
                tfnoofprop.getText().equals("")
        ){
            ownerTable.setItems(getOwners());
        }
        else{
            Owner owner = new Owner();
            if(!tfid.getText().equals("")) owner.setOwner_ID(Integer.parseInt(tfid.getText()));
            if(!tffn.getText().equals("")) owner.setFirst_Name(tffn.getText());
            if(!tflsn.getText().equals("")) owner.setLast_name(tflsn.getText());
            if(!tfcnic.getText().equals("")) owner.setCNIC(Integer.parseInt(tfcnic.getText()));
            if(!tfaddress.getText().equals("")) owner.setAddress(tfaddress.getText());
            if(!tfno.getText().equals("")) owner.setPhone_number(Integer.parseInt(tfno.getText()));
            if(!tfprofession.getText().equals("")) owner.setProfession(tfprofession.getText());
            if(!tfcity.getText().equals("")) owner.setCity(tfcity.getText());
            if(!tfnoofprop.getText().equals("")) owner.setNo_ofProperties(Integer.parseInt(tfnoofprop.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call ownerinsertion(?,?,?,?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,owner.getOwner_ID());
            callableStatement.setString(2,owner.getFirst_Name());
            callableStatement.setString(3,owner.getLast_name());
            callableStatement.setInt(4, owner.getCNIC());
            callableStatement.setString(5,owner.getAddress());
            callableStatement.setInt(6,owner.getPhone_number());
            callableStatement.setString(7,owner.getProfession());
            callableStatement.setString(8,owner.getCity());
            callableStatement.setInt(9, owner.getNo_ofProperties());
            callableStatement.execute();
            ownerTable.setItems(getOwners());
            tfid.clear();
            tffn.clear();
            tflsn.clear();
            tfaddress.clear();
            tfno.clear();
            tfcnic.clear();
            tfprofession.clear();
            tfcity.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tffn.getText().equals("") &&
                tflsn.getText().equals("") &&
                tfcnic.getText().equals("") &&
                tfaddress.getText().equals("") &&
                tfno.getText().equals("") &&
                tfprofession.getText().equals("") &&
                tfcity.getText().equals("") &&
                tfnoofprop.getText().equals("")
        ){
            ownerTable.setItems(getOwners());
        }
        else{
            Owner owner = new Owner();
            if(!tfid.getText().equals("")) owner.setOwner_ID(Integer.parseInt(tfid.getText()));
            if(!tffn.getText().equals("")) owner.setFirst_Name(tffn.getText());
            if(!tflsn.getText().equals("")) owner.setLast_name(tflsn.getText());
            if(!tfcnic.getText().equals("")) owner.setCNIC(Integer.parseInt(tfcity.getText()));
            if(!tfaddress.getText().equals("")) owner.setAddress(tfaddress.getText());
            if(!tfno.getText().equals("")) owner.setPhone_number(Integer.parseInt(tfno.getText()));
            if(!tfprofession.getText().equals("")) owner.setProfession(tfprofession.getText());
            if(!tfcity.getText().equals("")) owner.setCity(tfcity.getText());
            if(!tfnoofprop.getText().equals("")) owner.setNo_ofProperties(Integer.parseInt(tfnoofprop.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from owner where owner_ID like ? and first_name like ? and last_name like ? and address like ? and phone_number like ? and cnic like ? and profession like ? and city like ? and no_of_properties like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,owner.getOwner_ID());
            else preparedStatement.setString(1,"%");
            if(!tffn.getText().equals("")) preparedStatement.setString(2,owner.getFirst_Name());
            else preparedStatement.setString(2,"%");
            if(!tflsn.getText().equals("")) preparedStatement.setString(3,owner.getLast_name());
            else preparedStatement.setString(3,"%");
            if(!tfcnic.getText().equals("")) preparedStatement.setInt(4,owner.getCNIC());
            else preparedStatement.setString(4,"%");
            if(!tfaddress.getText().equals("")) preparedStatement.setString(5,owner.getAddress());
            else preparedStatement.setString(5,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(6,owner.getPhone_number());
            else preparedStatement.setString(6,"%");
            if(!tfprofession.getText().equals("")) preparedStatement.setString(7,owner.getProfession());
            else preparedStatement.setString(7,"%");
            if(!tfcity.getText().equals("")) preparedStatement.setString(8,owner.getCity());
            else preparedStatement.setString(8,"%");
            if(!tfnoofprop.getText().equals("")) preparedStatement.setInt(9, owner.getNo_ofProperties());
            else preparedStatement.setString(9,"%");

            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Owner> records = FXCollections.observableArrayList();
            while (rs.next()){
                Owner cowner = new Owner();
                cowner.setOwner_ID(rs.getInt(1));
                cowner.setFirst_Name(rs.getString(2));
                cowner.setLast_name(rs.getString(3));
                cowner.setCNIC(rs.getInt(4));
                cowner.setAddress(rs.getString(5));
                cowner.setPhone_number(rs.getInt(6));
                cowner.setProfession(rs.getString(7));
                cowner.setCity(rs.getString(8));
                cowner.setNo_ofProperties(9);
                records.add(cowner);
            }
            ownerTable.setItems(records);
            tfid.clear();
            tffn.clear();
            tflsn.clear();
            tfaddress.clear();
            tfno.clear();
            tfcnic.clear();
            tfprofession.clear();
            tfcity.clear();
            tfnoofprop.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tffn.getText().equals("") &&
                tflsn.getText().equals("") &&
                tfcnic.getText().equals("") &&
                tfaddress.getText().equals("") &&
                tfno.getText().equals("") &&
                tfprofession.getText().equals("") &&
                tfcity.getText().equals("") &&
                tfnoofprop.getText().equals("")
        ){
            ownerTable.setItems(getOwners());
        }
        else{
            Owner owner = new Owner();
            if(!tfid.getText().equals("")) owner.setOwner_ID(Integer.parseInt(tfid.getText()));
            if(!tffn.getText().equals("")) owner.setFirst_Name(tffn.getText());
            if(!tflsn.getText().equals("")) owner.setLast_name(tflsn.getText());
            if(!tfcnic.getText().equals("")) owner.setCNIC(Integer.parseInt(tfcity.getText()));
            if(!tfaddress.getText().equals("")) owner.setAddress(tfaddress.getText());
            if(!tfno.getText().equals("")) owner.setPhone_number(Integer.parseInt(tfno.getText()));
            if(!tfprofession.getText().equals("")) owner.setProfession(tfprofession.getText());
            if(!tfcity.getText().equals("")) owner.setCity(tfcity.getText());
            if(!tfnoofprop.getText().equals("")) owner.setNo_ofProperties(Integer.parseInt(tfnoofprop.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from owner where owner_ID like ? and first_name like ? and last_name like ? and address like ? and phone_number like ? and cnic like ? and profession like ? and city like ? and no_of_properties like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,owner.getOwner_ID());
            else preparedStatement.setString(1,"%");
            if(!tffn.getText().equals("")) preparedStatement.setString(2,owner.getFirst_Name());
            else preparedStatement.setString(2,"%");
            if(!tflsn.getText().equals("")) preparedStatement.setString(3,owner.getLast_name());
            else preparedStatement.setString(3,"%");
            if(!tfcnic.getText().equals("")) preparedStatement.setInt(4,owner.getCNIC());
            else preparedStatement.setString(4,"%");
            if(!tfaddress.getText().equals("")) preparedStatement.setString(5,owner.getAddress());
            else preparedStatement.setString(5,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(6,owner.getPhone_number());
            else preparedStatement.setString(6,"%");
            if(!tfprofession.getText().equals("")) preparedStatement.setString(7,owner.getProfession());
            else preparedStatement.setString(7,"%");
            if(!tfcity.getText().equals("")) preparedStatement.setString(8,owner.getCity());
            else preparedStatement.setString(8,"%");
            if(!tfnoofprop.getText().equals("")) preparedStatement.setInt(9, owner.getNo_ofProperties());
            else preparedStatement.setString(9,"%");

            preparedStatement.execute();
            ownerTable.setItems(getOwners());
            tfid.clear();
            tffn.clear();
            tflsn.clear();
            tfaddress.clear();
            tfno.clear();
            tfcnic.clear();
            tfprofession.clear();
            tfcity.clear();
            tfnoofprop.clear();
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
        Owner_ID.setCellValueFactory(new PropertyValueFactory<Owner, Integer>("Owner_ID"));
        First_Name.setCellValueFactory(new PropertyValueFactory<Owner, String>("First_Name"));
        Last_Name.setCellValueFactory(new PropertyValueFactory<Owner, String>("last_name"));
        CNIC.setCellValueFactory(new PropertyValueFactory<Owner, Integer>("CNIC"));
        Address.setCellValueFactory(new PropertyValueFactory<Owner, String>("address"));
        Phone_Number.setCellValueFactory(new PropertyValueFactory<Owner, Integer>("phone_number"));
        Profession.setCellValueFactory(new PropertyValueFactory<Owner, String>("profession"));
        City.setCellValueFactory(new PropertyValueFactory<Owner, String>("city"));
        No_ofProperties.setCellValueFactory(new PropertyValueFactory<Owner, Integer>("No_ofProperties"));
        ownerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            ownerTable.setItems(getOwners());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Owner> getOwners() throws SQLException {
        ObservableList<Owner> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from owner";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Owner owner = new Owner();
            owner.setOwner_ID(rs.getInt(1));
            owner.setFirst_Name(rs.getString(2));
            owner.setLast_name(rs.getString(3));
            owner.setCNIC(rs.getInt(4));
            owner.setAddress(rs.getString(5));
            owner.setPhone_number(rs.getInt(6));
            owner.setProfession(rs.getString(7));
            owner.setCity(rs.getString(8));
            owner.setNo_ofProperties(rs.getInt(9));
            records.add(owner);
        }
        connection.close();
        return records;
    }
}

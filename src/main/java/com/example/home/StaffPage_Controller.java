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

public class StaffPage_Controller implements Initializable {

    @FXML
    private TableView<Staff> staffTable;
    @FXML
    TableColumn<Staff, Integer> Staff_ID;
    @FXML
    TableColumn<Staff, String> First_Name;
    @FXML
    TableColumn<Staff, String> Last_Name;
    @FXML
    TableColumn<Staff, Integer> CNIC;
    @FXML
    TableColumn<Staff, Integer> Phone_Number;
    @FXML
    TableColumn<Staff, String> Designation;
    @FXML
    TableColumn<Staff, Integer> Office_No;
    @FXML
    TableColumn<Staff, Integer> Team_ID;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tffn;
    @FXML
    private TextField tfln;
    @FXML
    private TextField tfcnic;
    @FXML
    private TextField tfno;
    @FXML
    private TextField tfdesignation;
    @FXML
    private TextField tfoffice;
    @FXML
    private TextField tfteam;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tffn.getText().equals("") &&
                tfln.getText().equals("") &&
                tfcnic.getText().equals("") &&
                tfno.getText().equals("") &&
                tfdesignation.getText().equals("") &&
                tfoffice.getText().equals("") &&
                tfteam.getText().equals("")
        ){
            staffTable.setItems(getStaff());
        }
        else{
            Staff staff = new Staff();
            if(!tfid.getText().equals("")) staff.setStaff_ID(Integer.parseInt(tfid.getText()));
            if(!tffn.getText().equals("")) staff.setFirst_Name(tffn.getText());
            if(!tfln.getText().equals("")) staff.setLast_name(tfln.getText());
            if(!tfcnic.getText().equals("")) staff.setCNIC(Integer.parseInt(tfcnic.getText()));
            if(!tfno.getText().equals("")) staff.setPhone_number(Integer.parseInt(tfno.getText()));
            if(!tfdesignation.getText().equals("")) staff.setDesignation(tfdesignation.getText());
            if(!tfoffice.getText().equals("")) staff.setOffice_no(Integer.parseInt(tfoffice.getText()));
            if(!tfteam.getText().equals("")) staff.setTeam_ID(Integer.parseInt(tfteam.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call staffinsertion(?,?,?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,staff.getStaff_ID());
            callableStatement.setString(2,staff.getFirst_Name());
            callableStatement.setString(3,staff.getLast_name());
            callableStatement.setInt(4, staff.getCNIC());
            callableStatement.setInt(5,staff.getPhone_number());
            callableStatement.setString(6,staff.getDesignation());
            callableStatement.setInt(7,staff.getOffice_no());
            callableStatement.setInt(8,staff.getTeam_ID());
            callableStatement.execute();
            staffTable.setItems(getStaff());
            tfid.clear();
            tffn.clear();
            tfln.clear();
            tfdesignation.clear();
            tfno.clear();
            tfcnic.clear();
            tfoffice.clear();
            tfteam.clear();
            connection.close();
        }
    }


    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tffn.getText().equals("") &&
                tfln.getText().equals("") &&
                tfcnic.getText().equals("") &&
                tfno.getText().equals("") &&
                tfdesignation.getText().equals("") &&
                tfoffice.getText().equals("") &&
                tfteam.getText().equals("")
        ){
            staffTable.setItems(getStaff());
        }
        else{
            Staff staff = new Staff();
            if(!tfid.getText().equals("")) staff.setStaff_ID(Integer.parseInt(tfid.getText()));
            if(!tffn.getText().equals("")) staff.setFirst_Name(tffn.getText());
            if(!tfln.getText().equals("")) staff.setLast_name(tfln.getText());
            if(!tfcnic.getText().equals("")) staff.setCNIC(Integer.parseInt(tfcnic.getText()));
            if(!tfno.getText().equals("")) staff.setPhone_number(Integer.parseInt(tfno.getText()));
            if(!tfdesignation.getText().equals("")) staff.setDesignation(tfdesignation.getText());
            if(!tfoffice.getText().equals("")) staff.setOffice_no(Integer.parseInt(tfoffice.getText()));
            if(!tfteam.getText().equals("")) staff.setTeam_ID(Integer.parseInt(tfteam.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from staff where staff_ID like ? and first_name like ? and last_name like ? and cnic like ? and phone_number like ? and designation like ? and office_no like ? and team_id like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,staff.getStaff_ID());
            else preparedStatement.setString(1,"%");
            if(!tffn.getText().equals("")) preparedStatement.setString(2,staff.getFirst_Name());
            else preparedStatement.setString(2,"%");
            if(!tfln.getText().equals("")) preparedStatement.setString(3,staff.getLast_name());
            else preparedStatement.setString(3,"%");
            if(!tfcnic.getText().equals("")) preparedStatement.setInt(4,staff.getCNIC());
            else preparedStatement.setString(4,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(5,staff.getPhone_number());
            else preparedStatement.setString(5,"%");
            if(!tfdesignation.getText().equals("")) preparedStatement.setString(6,staff.getDesignation());
            else preparedStatement.setString(6,"%");
            if(!tfoffice.getText().equals("")) preparedStatement.setInt(7,staff.getOffice_no());
            else preparedStatement.setString(7,"%");
            if(!tfteam.getText().equals("")) preparedStatement.setInt(8,staff.getTeam_ID());
            else preparedStatement.setString(8,"%");

            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Staff> records = FXCollections.observableArrayList();
            while (rs.next()){
                Staff sstaff = new Staff();
                sstaff.setStaff_ID(rs.getInt(1));
                sstaff.setFirst_Name(rs.getString(2));
                sstaff.setLast_name(rs.getString(3));
                sstaff.setCNIC(rs.getInt(4));
                sstaff.setPhone_number(rs.getInt(5));
                sstaff.setDesignation(rs.getString(6));
                sstaff.setOffice_no(rs.getInt(7));
                sstaff.setTeam_ID(rs.getInt(8));
                records.add(sstaff);
            }
            staffTable.setItems(records);
            tfid.clear();
            tffn.clear();
            tfln.clear();
            tfdesignation.clear();
            tfno.clear();
            tfcnic.clear();
            tfoffice.clear();
            tfteam.clear();
            connection.close();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tffn.getText().equals("") &&
                tfln.getText().equals("") &&
                tfcnic.getText().equals("") &&
                tfno.getText().equals("") &&
                tfdesignation.getText().equals("") &&
                tfoffice.getText().equals("") &&
                tfteam.getText().equals("")
        ){
            staffTable.setItems(getStaff());
        }
        else{
            Staff staff = new Staff();
            if(!tfid.getText().equals("")) staff.setStaff_ID(Integer.parseInt(tfid.getText()));
            if(!tffn.getText().equals("")) staff.setFirst_Name(tffn.getText());
            if(!tfln.getText().equals("")) staff.setLast_name(tfln.getText());
            if(!tfcnic.getText().equals("")) staff.setCNIC(Integer.parseInt(tfcnic.getText()));
            if(!tfno.getText().equals("")) staff.setPhone_number(Integer.parseInt(tfno.getText()));
            if(!tfdesignation.getText().equals("")) staff.setDesignation(tfdesignation.getText());
            if(!tfoffice.getText().equals("")) staff.setOffice_no(Integer.parseInt(tfoffice.getText()));
            if(!tfteam.getText().equals("")) staff.setTeam_ID(Integer.parseInt(tfteam.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from staff where staff_ID like ? and first_name like ? and last_name like ? and cnic like ? and phone_number like ? and designation like ? and office_no like ? and team_id like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,staff.getStaff_ID());
            else preparedStatement.setString(1,"%");
            if(!tffn.getText().equals("")) preparedStatement.setString(2,staff.getFirst_Name());
            else preparedStatement.setString(2,"%");
            if(!tfln.getText().equals("")) preparedStatement.setString(3,staff.getLast_name());
            else preparedStatement.setString(3,"%");
            if(!tfcnic.getText().equals("")) preparedStatement.setInt(4,staff.getCNIC());
            else preparedStatement.setString(4,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(5,staff.getPhone_number());
            else preparedStatement.setString(5,"%");
            if(!tfdesignation.getText().equals("")) preparedStatement.setString(6,staff.getDesignation());
            else preparedStatement.setString(6,"%");
            if(!tfoffice.getText().equals("")) preparedStatement.setInt(7,staff.getOffice_no());
            else preparedStatement.setString(7,"%");
            if(!tfteam.getText().equals("")) preparedStatement.setInt(8,staff.getTeam_ID());
            else preparedStatement.setString(8,"%");

            preparedStatement.execute();
            staffTable.setItems(getStaff());
            tfid.clear();
            tffn.clear();
            tfln.clear();
            tfdesignation.clear();
            tfno.clear();
            tfcnic.clear();
            tfoffice.clear();
            tfteam.clear();
            connection.close();
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
        Staff_ID.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("staff_ID"));
        First_Name.setCellValueFactory(new PropertyValueFactory<Staff, String>("First_Name"));
        Last_Name.setCellValueFactory(new PropertyValueFactory<Staff, String>("last_name"));
        CNIC.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("CNIC"));
        Phone_Number.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("phone_number"));
        Designation.setCellValueFactory(new PropertyValueFactory<Staff, String>("designation"));
        Office_No.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("office_no"));
        Team_ID.setCellValueFactory(new PropertyValueFactory<Staff, Integer>("team_ID"));
        staffTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            staffTable.setItems(getStaff());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Staff> getStaff() throws SQLException {
        ObservableList<Staff> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from staff";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Staff staff = new Staff();
            staff.setStaff_ID(rs.getInt(1));
            staff.setFirst_Name(rs.getString(2));
            staff.setLast_name(rs.getString(3));
            staff.setCNIC(rs.getInt(4));
            staff.setPhone_number(rs.getInt(5));
            staff.setDesignation(rs.getString(6));
            staff.setOffice_no(rs.getInt(7));
            staff.setTeam_ID(rs.getInt(8));
            records.add(staff);
        }
        connection.close();
        return records;
    }
}

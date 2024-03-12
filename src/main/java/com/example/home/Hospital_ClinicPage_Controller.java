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

import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Hospital_ClinicPage_Controller implements Initializable {

    @FXML
    private TableView<Hospital_Clinic> hospital_clinicTable;
    @FXML
    TableColumn<Hospital_Clinic, Integer> Hospital_ID;
    @FXML
    TableColumn<Hospital_Clinic, Integer> Property_ID;
    @FXML
    TableColumn<Hospital_Clinic, String> Name;
    @FXML
    TableColumn<Hospital_Clinic, String> Type;
    @FXML
    TableColumn<Hospital_Clinic, String> Emergency;
    @FXML
    TableColumn<Hospital_Clinic, String> Ambulance;


    @FXML
    private TextField tfid;
    @FXML
    private TextField tfpid;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfemer;
    @FXML
    private TextField tfambu;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
        tfpid.getText().equals("") &&
        tfname.getText().equals("") &&
        tftype.getText().equals("") &&
        tfemer.getText().equals("") &&
        tfambu.getText().equals("")
        ){
            hospital_clinicTable.setItems(getHospitals());
        }
        else{
            Hospital_Clinic hospital_clinic = new Hospital_Clinic();
            if(!tfid.getText().equals("")) hospital_clinic.setHospital_ID(Integer.parseInt(tfid.getText()));
            if(!tfpid.getText().equals("")) hospital_clinic.setProperty_ID(Integer.parseInt(tfpid.getText()));
            if(!tfname.getText().equals("")) hospital_clinic.setName(tfname.getText());
            if(!tftype.getText().equals("")) hospital_clinic.setType(tftype.getText());
            if(!tfemer.getText().equals("")) hospital_clinic.setEmergency(tfemer.getText());
            if(!tfambu.getText().equals("")) hospital_clinic.setAmbulance(tfambu.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call hospitalinsertion(?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,hospital_clinic.getHospital_ID());
            callableStatement.setInt(2,hospital_clinic.getProperty_ID());
            callableStatement.setString(3,hospital_clinic.getName());
            callableStatement.setString(4,hospital_clinic.getType());
            callableStatement.setString(5,hospital_clinic.getEmergency());
            callableStatement.setString(6,hospital_clinic.getAmbulance());
            callableStatement.execute();
            hospital_clinicTable.setItems(getHospitals());
            tfid.clear();
            tfpid.clear();
            tfname.clear();
            tftype.clear();
            tfemer.clear();
            tfambu.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfpid.getText().equals("") &&
                tfname.getText().equals("") &&
                tftype.getText().equals("") &&
                tfemer.getText().equals("") &&
                tfambu.getText().equals("")
        ){
            hospital_clinicTable.setItems(getHospitals());
        }
        else{
            Hospital_Clinic hospital_clinic = new Hospital_Clinic();
            if(!tfid.getText().equals("")) hospital_clinic.setHospital_ID(Integer.parseInt(tfid.getText()));
            if(!tfpid.getText().equals("")) hospital_clinic.setProperty_ID(Integer.parseInt(tfpid.getText()));
            if(!tfname.getText().equals("")) hospital_clinic.setName(tfname.getText());
            if(!tftype.getText().equals("")) hospital_clinic.setType(tftype.getText());
            if(!tfemer.getText().equals("")) hospital_clinic.setEmergency(tfemer.getText());
            if(!tfambu.getText().equals("")) hospital_clinic.setAmbulance(tfambu.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from `hospital/clinic` where hospitals_id like ? and property_id like ? and name like ? and type like ? and emergency like ? and ambulance like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,hospital_clinic.getHospital_ID());
            else preparedStatement.setString(1, "%");
            if(!tfpid.getText().equals("")) preparedStatement.setInt(2,hospital_clinic.getProperty_ID());
            else preparedStatement.setString(2, "%");
            if(!tfname.getText().equals("")) preparedStatement.setString(3,hospital_clinic.getName());
            else preparedStatement.setString(3, "%");
            if(!tftype.getText().equals("")) preparedStatement.setString(4,hospital_clinic.getType());
            else preparedStatement.setString(4, "%");
            if(!tfemer.getText().equals("")) preparedStatement.setString(5,hospital_clinic.getEmergency());
            else preparedStatement.setString(5, "%");
            if(!tfambu.getText().equals("")) preparedStatement.setString(6,hospital_clinic.getAmbulance());
            else preparedStatement.setString(6, "%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Hospital_Clinic> records = FXCollections.observableArrayList();
            while (rs.next()){
                Hospital_Clinic hhospital_clinic= new Hospital_Clinic();
                hhospital_clinic.setHospital_ID(rs.getInt(1));
                hhospital_clinic.setProperty_ID(rs.getInt(2));
                hhospital_clinic.setName(rs.getString(3));
                hhospital_clinic.setType(rs.getString(4));
                hhospital_clinic.setEmergency(rs.getString(5));
                hhospital_clinic.setAmbulance(rs.getString(6));
                records.add(hhospital_clinic);
            }

            hospital_clinicTable.setItems(records);
            tfid.clear();
            tfpid.clear();
            tfname.clear();
            tftype.clear();
            tfemer.clear();
            tfambu.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfpid.getText().equals("") &&
                tfname.getText().equals("") &&
                tftype.getText().equals("") &&
                tfemer.getText().equals("") &&
                tfambu.getText().equals("")
        ){
            hospital_clinicTable.setItems(getHospitals());
        }
        else{
            Hospital_Clinic hospital_clinic = new Hospital_Clinic();
            if(!tfid.getText().equals("")) hospital_clinic.setHospital_ID(Integer.parseInt(tfid.getText()));
            if(!tfpid.getText().equals("")) hospital_clinic.setProperty_ID(Integer.parseInt(tfpid.getText()));
            if(!tfname.getText().equals("")) hospital_clinic.setName(tfname.getText());
            if(!tftype.getText().equals("")) hospital_clinic.setType(tftype.getText());
            if(!tfemer.getText().equals("")) hospital_clinic.setEmergency(tfemer.getText());
            if(!tfambu.getText().equals("")) hospital_clinic.setAmbulance(tfambu.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from `hospital/clinic` where hospitals_id like ? and property_id like ? and name like ? and type like ? and emergency like ? and ambulance like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,hospital_clinic.getHospital_ID());
            else preparedStatement.setString(1, "%");
            if(!tfpid.getText().equals("")) preparedStatement.setInt(2,hospital_clinic.getProperty_ID());
            else preparedStatement.setString(2, "%");
            if(!tfname.getText().equals("")) preparedStatement.setString(3,hospital_clinic.getName());
            else preparedStatement.setString(3, "%");
            if(!tftype.getText().equals("")) preparedStatement.setString(4,hospital_clinic.getType());
            else preparedStatement.setString(4, "%");
            if(!tfemer.getText().equals("")) preparedStatement.setString(5,hospital_clinic.getEmergency());
            else preparedStatement.setString(5, "%");
            if(!tfambu.getText().equals("")) preparedStatement.setString(6,hospital_clinic.getAmbulance());
            else preparedStatement.setString(6, "%");
            preparedStatement.execute();
            hospital_clinicTable.setItems(getHospitals());
            tfid.clear();
            tfpid.clear();
            tfname.clear();
            tftype.clear();
            tfemer.clear();
            tfambu.clear();
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
        Hospital_ID.setCellValueFactory(new PropertyValueFactory<Hospital_Clinic, Integer>("hospital_ID"));
        Property_ID.setCellValueFactory(new PropertyValueFactory<Hospital_Clinic, Integer>("property_ID"));
        Name.setCellValueFactory(new PropertyValueFactory<Hospital_Clinic, String>("name"));
        Type.setCellValueFactory(new PropertyValueFactory<Hospital_Clinic, String>("type"));
        Emergency.setCellValueFactory(new PropertyValueFactory<Hospital_Clinic, String>("emergency"));
        Ambulance.setCellValueFactory(new PropertyValueFactory<Hospital_Clinic, String>("ambulance"));
        hospital_clinicTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            hospital_clinicTable.setItems(getHospitals());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Hospital_Clinic> getHospitals() throws SQLException {
        ObservableList<Hospital_Clinic> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from `hospital/clinic`";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Hospital_Clinic hospital_clinic= new Hospital_Clinic();
            hospital_clinic.setHospital_ID(rs.getInt(1));
            hospital_clinic.setProperty_ID(rs.getInt(2));
            hospital_clinic.setName(rs.getString(3));
            hospital_clinic.setType(rs.getString(4));
            hospital_clinic.setEmergency(rs.getString(5));
            hospital_clinic.setAmbulance(rs.getString(6));
            records.add(hospital_clinic);
        }
        connection.close();
        return records;
    }
}

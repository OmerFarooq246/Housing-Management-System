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

public class ComplainPage_Controller implements Initializable {

    @FXML
    private TableView<Complain> complainTable;
    @FXML
    TableColumn<Complain, Integer> Complain_ID;
    @FXML
    TableColumn<Complain, String> Name;
    @FXML
    TableColumn<Complain, Integer> Phone_Number;
    @FXML
    TableColumn<Complain, Integer> House_ID;
    @FXML
    TableColumn<Complain, String> Complain;
    @FXML
    TableColumn<Complain, String> Time;
    @FXML
    TableColumn<Complain, String> Status;


    @FXML
    private TextField tfID;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfnumber;
    @FXML
    private TextField tfhouse;
    @FXML
    private TextField tfcomplain;
    @FXML
    private TextField tftime;
    @FXML
    private TextField tfstatus;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfID.getText().equals("") &&
        tfcomplain.getText().equals("") &&
        tfhouse.getText().equals("") &&
        tfname.getText().equals("") &&
        tfnumber.getText().equals("") &&
        tfcomplain.getText().equals("") &&
        tfstatus.getText().equals("")){
            complainTable.setItems(getComplain());
        }
        else{
            Complain complain = new Complain();
            if(!tfID.getText().equals(""))
                complain.setComplain_ID(Integer.parseInt(tfID.getText()));
            else complain.setComplain_ID(0);
            if(!tfname.getText().equals(""))
                complain.setName(tfname.getText());
            else complain.setName("");
            if(!tfnumber.getText().equals(""))
                complain.setPhone_Number(Integer.parseInt(tfnumber.getText()));
            else complain.setPhone_Number(0);
            if(!tfhouse.getText().equals(""))
                complain.setHouse_ID(Integer.parseInt(tfhouse.getText()));
            else complain.setHouse_ID(0);
            if(!tfcomplain.getText().equals(""))
                complain.setComplain(tfcomplain.getText());
            else complain.setComplain("");
            if(!tftime.getText().equals(""))
                complain.setTime(tftime.getText());
            else complain.setTime("");
            if(!tfstatus.getText().equals(""))
                complain.setStatus(tfstatus.getText());
            else complain.setStatus("");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call complainInertion(?,?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, complain.getComplain_ID());
            callableStatement.setString(2, complain.getName());
            callableStatement.setInt(3, complain.getPhone_Number());
            callableStatement.setInt(4, complain.getHouse_ID());
            callableStatement.setString(5, complain.getComplain());
            callableStatement.setString(6, complain.getTime());
            callableStatement.setString(7, complain.getStatus());
            callableStatement.execute();
            complainTable.setItems(getComplain());
            tfID.clear();
            tfname.clear();
            tfnumber.clear();
            tfhouse.clear();
            tfcomplain.clear();
            tftime.clear();
            tfstatus.clear();
            connection.close();
        }
    }


    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfID.getText().equals("") &&
                tfcomplain.getText().equals("") &&
                tfhouse.getText().equals("") &&
                tfname.getText().equals("") &&
                tfnumber.getText().equals("") &&
                tfcomplain.getText().equals("") &&
                tfstatus.getText().equals("")){
            complainTable.setItems(getComplain());
        }
        else{
            Complain complain = new Complain();
            if(!tfID.getText().equals(""))
                complain.setComplain_ID(Integer.parseInt(tfID.getText()));
            else complain.setComplain_ID(0);
            if(!tfname.getText().equals(""))
                complain.setName(tfname.getText());
            else complain.setName("");
            if(!tfnumber.getText().equals(""))
                complain.setPhone_Number(Integer.parseInt(tfnumber.getText()));
            else complain.setPhone_Number(0);
            if(!tfhouse.getText().equals(""))
                complain.setHouse_ID(Integer.parseInt(tfhouse.getText()));
            else complain.setHouse_ID(0);
            if(!tfcomplain.getText().equals(""))
                complain.setComplain(tfcomplain.getText());
            else complain.setComplain("");
            if(!tftime.getText().equals(""))
                complain.setTime(tftime.getText());
            else complain.setTime("");
            if(!tfstatus.getText().equals(""))
                complain.setStatus(tfstatus.getText());
            else complain.setStatus("");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from complain where complain_id like ? and name like ? and phone_number like ? and house_id like ? and complain like ? and time like ? and status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if(!tfID.getText().equals("")) preparedStatement.setInt(1, complain.getComplain_ID());
            else preparedStatement.setString(1, "%");
            if(!tfname.getText().equals("")) preparedStatement.setString(2, complain.getName());
            else preparedStatement.setString(2, "%");
            if(!tfnumber.getText().equals("")) preparedStatement.setInt(3, complain.getPhone_Number());
            else preparedStatement.setString(3, "%");
            if(!tfhouse.getText().equals("")) preparedStatement.setInt(4, complain.getHouse_ID());
            else preparedStatement.setString(4, "%");
            if(!tfcomplain.getText().equals("")) preparedStatement.setString(5, complain.getComplain());
            else preparedStatement.setString(5, "%");
            if(!tftime.getText().equals("")) preparedStatement.setString(6, complain.getTime());
            else preparedStatement.setString(6, "%");
            if(!tfstatus.getText().equals("")) preparedStatement.setString(7, complain.getStatus());
            else preparedStatement.setString(7, "%");

            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Complain> records = FXCollections.observableArrayList();
            while (rs.next()){
                Complain c1 = new Complain();
                c1.setComplain_ID(rs.getInt(1));
                c1.setName(rs.getString(2));
                c1.setPhone_Number(rs.getInt(3));
                c1.setHouse_ID(rs.getInt(4));
                c1.setComplain(rs.getString(5));
                c1.setTime(rs.getString(6));
                c1.setStatus(rs.getString(7));
                records.add(c1);
            }
            complainTable.setItems(records);
            tfID.clear();
            tfname.clear();
            tfnumber.clear();
            tfhouse.clear();
            tfcomplain.clear();
            tftime.clear();
            tfstatus.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfID.getText().equals("") &&
                tfcomplain.getText().equals("") &&
                tfhouse.getText().equals("") &&
                tfname.getText().equals("") &&
                tfnumber.getText().equals("") &&
                tfcomplain.getText().equals("") &&
                tfstatus.getText().equals("")){
            complainTable.setItems(getComplain());
        }
        else{
            Complain complain = new Complain();
            if(!tfID.getText().equals(""))
                complain.setComplain_ID(Integer.parseInt(tfID.getText()));
            else complain.setComplain_ID(0);
            if(!tfname.getText().equals(""))
                complain.setName(tfname.getText());
            else complain.setName("");
            if(!tfnumber.getText().equals(""))
                complain.setPhone_Number(Integer.parseInt(tfnumber.getText()));
            else complain.setPhone_Number(0);
            if(!tfhouse.getText().equals(""))
                complain.setHouse_ID(Integer.parseInt(tfhouse.getText()));
            else complain.setHouse_ID(0);
            if(!tfcomplain.getText().equals(""))
                complain.setComplain(tfcomplain.getText());
            else complain.setComplain("");
            if(!tftime.getText().equals(""))
                complain.setTime(tftime.getText());
            else complain.setTime("");
            if(!tfstatus.getText().equals(""))
                complain.setStatus(tfstatus.getText());
            else complain.setStatus("");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from complain where complain_id like ? and name like ? and phone_number like ? and house_id like ? and complain like ? and time like ? and status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if(!tfID.getText().equals("")) preparedStatement.setInt(1, complain.getComplain_ID());
            else preparedStatement.setString(1, "%");
            if(!tfname.getText().equals("")) preparedStatement.setString(2, complain.getName());
            else preparedStatement.setString(2, "%");
            if(!tfnumber.getText().equals("")) preparedStatement.setInt(3, complain.getPhone_Number());
            else preparedStatement.setString(3, "%");
            if(!tfhouse.getText().equals("")) preparedStatement.setInt(4, complain.getHouse_ID());
            else preparedStatement.setString(4, "%");
            if(!tfcomplain.getText().equals("")) preparedStatement.setString(5, complain.getComplain());
            else preparedStatement.setString(5, "%");
            if(!tftime.getText().equals("")) preparedStatement.setString(6, complain.getTime());
            else preparedStatement.setString(6, "%");
            if(!tfstatus.getText().equals("")) preparedStatement.setString(7, complain.getStatus());
            else preparedStatement.setString(7, "%");

            preparedStatement.execute();
            complainTable.setItems(getComplain());
            tfID.clear();
            tfname.clear();
            tfnumber.clear();
            tfhouse.clear();
            tfcomplain.clear();
            tftime.clear();
            tfstatus.clear();
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
        Complain_ID.setCellValueFactory(new PropertyValueFactory<Complain, Integer>("Complain_ID"));
        Name.setCellValueFactory(new PropertyValueFactory<Complain, String>("Name"));
        Phone_Number.setCellValueFactory(new PropertyValueFactory<Complain, Integer>("Phone_Number"));
        House_ID.setCellValueFactory(new PropertyValueFactory<Complain, Integer>("House_ID"));
        Complain.setCellValueFactory(new PropertyValueFactory<Complain, String>("Complain"));
        Time.setCellValueFactory(new PropertyValueFactory<Complain, String>("Time"));
        Status.setCellValueFactory(new PropertyValueFactory<Complain, String>("Status"));
        complainTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            complainTable.setItems(getComplain());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Complain> getComplain() throws SQLException {
        ObservableList<Complain> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from complain";
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()){
            Complain c1 = new Complain();
            c1.setComplain_ID(rs.getInt(1));
            c1.setName(rs.getString(2));
            c1.setPhone_Number(rs.getInt(3));
            c1.setHouse_ID(rs.getInt(4));
            c1.setComplain(rs.getString(5));
            c1.setTime(rs.getString(6));
            c1.setStatus(rs.getString(7));
            records.add(c1);
        }
        connection.close();
        return records;
    }
}

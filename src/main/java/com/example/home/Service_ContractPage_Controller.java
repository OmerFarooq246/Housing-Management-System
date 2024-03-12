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

public class Service_ContractPage_Controller implements Initializable {

    @FXML
    private TableView<Service_Contract> service_contractTable;
    @FXML
    TableColumn<Service_Contract, Integer> Contract_ID;
    @FXML
    TableColumn<Service_Contract, Integer> Service_ID;
    @FXML
    TableColumn<Service_Contract, String> Company;
    @FXML
    TableColumn<Service_Contract, String> Start_Date;
    @FXML
    TableColumn<Service_Contract, String> End_Date;
    @FXML
    TableColumn<Service_Contract, Integer> Amount;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfsid;
    @FXML
    private TextField tfcompany;
    @FXML
    private TextField tfsd;
    @FXML
    private TextField tfed;
    @FXML
    private TextField tfamount;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfsid.getText().equals("") &&
                tfcompany.getText().equals("") &&
                tfsd.getText().equals("") &&
                tfed.getText().equals("") &&
                tfamount.getText().equals("")
        ){
            service_contractTable.setItems(getServiceContract());
        }
        else{
            Service_Contract service_contract = new Service_Contract();
            if(!tfid.getText().equals("")) service_contract.setContract_ID(Integer.parseInt(tfid.getText()));
            if(!tfsid.getText().equals("")) service_contract.setService_ID(Integer.parseInt(tfsd.getText()));
            if(!tfcompany.getText().equals("")) service_contract.setCompany(tfcompany.getText());
            if(!tfsd.getText().equals("")) service_contract.setStart_date(tfsd.getText());
            if(!tfed.getText().equals("")) service_contract.setEnd_date(tfed.getText());
            if(!tfamount.getText().equals("")) service_contract.setAmount(Integer.parseInt(tfamount.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call serviceContractinsertion(?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,service_contract.getContract_ID());
            callableStatement.setInt(2,service_contract.getService_ID());
            callableStatement.setString(3,service_contract.getCompany());
            callableStatement.setString(4,service_contract.getStart_date());
            callableStatement.setString(5,service_contract.getEnd_date());
            callableStatement.setInt(6,service_contract.getAmount());
            service_contractTable.setItems(getServiceContract());
            tfid.clear();
            tfsid.clear();
            tfcompany.clear();
            tfsd.clear();
            tfed.clear();
            tfamount.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfsid.getText().equals("") &&
                tfcompany.getText().equals("") &&
                tfsd.getText().equals("") &&
                tfed.getText().equals("") &&
                tfamount.getText().equals("")
        ){
            service_contractTable.setItems(getServiceContract());
        }
        else{
            Service_Contract service_contract = new Service_Contract();
            if(!tfid.getText().equals("")) service_contract.setContract_ID(Integer.parseInt(tfid.getText()));
            if(!tfsid.getText().equals("")) service_contract.setService_ID(Integer.parseInt(tfsd.getText()));
            if(!tfcompany.getText().equals("")) service_contract.setCompany(tfcompany.getText());
            if(!tfsd.getText().equals("")) service_contract.setStart_date(tfsd.getText());
            if(!tfed.getText().equals("")) service_contract.setEnd_date(tfed.getText());
            if(!tfamount.getText().equals("")) service_contract.setAmount(Integer.parseInt(tfamount.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from service_contract where contract_ID like ? and service_ID like ? and provider_company like ? and start_date like ? and end_date like ? and amount like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,service_contract.getContract_ID());
            else preparedStatement.setString(1,"%");
            if(!tfsid.getText().equals("")) preparedStatement.setInt(2,service_contract.getService_ID());
            else preparedStatement.setString(2,"%");
            if(!tfcompany.getText().equals("")) preparedStatement.setString(3,service_contract.getCompany());
            else preparedStatement.setString(3,"%");
            if(!tfsd.getText().equals("")) preparedStatement.setString(4,service_contract.getStart_date());
            else preparedStatement.setString(4,"%");
            if(!tfed.getText().equals("")) preparedStatement.setString(5,service_contract.getEnd_date());
            else preparedStatement.setString(5,"%");
            if(!tfamount.getText().equals("")) preparedStatement.setInt(6,service_contract.getAmount());
            else preparedStatement.setString(6,"%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Service_Contract> records = FXCollections.observableArrayList();
            while (rs.next()){
                Service_Contract sservice_contract = new Service_Contract();
                sservice_contract.setContract_ID(rs.getInt(1));
                sservice_contract.setService_ID(rs.getInt(2));
                sservice_contract.setCompany(rs.getString(3));
                sservice_contract.setStart_date(rs.getString(4));
                sservice_contract.setEnd_date(rs.getString(5));
                sservice_contract.setAmount(rs.getInt(6));
                records.add(sservice_contract);
            }
            service_contractTable.setItems(records);
            tfid.clear();
            tfsid.clear();
            tfcompany.clear();
            tfsd.clear();
            tfed.clear();
            tfamount.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfsid.getText().equals("") &&
                tfcompany.getText().equals("") &&
                tfsd.getText().equals("") &&
                tfed.getText().equals("") &&
                tfamount.getText().equals("")
        ){
            service_contractTable.setItems(getServiceContract());
        }
        else{
            Service_Contract service_contract = new Service_Contract();
            if(!tfid.getText().equals("")) service_contract.setContract_ID(Integer.parseInt(tfid.getText()));
            if(!tfsid.getText().equals("")) service_contract.setService_ID(Integer.parseInt(tfsd.getText()));
            if(!tfcompany.getText().equals("")) service_contract.setCompany(tfcompany.getText());
            if(!tfsd.getText().equals("")) service_contract.setStart_date(tfsd.getText());
            if(!tfed.getText().equals("")) service_contract.setEnd_date(tfed.getText());
            if(!tfamount.getText().equals("")) service_contract.setAmount(Integer.parseInt(tfamount.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from service_contract where contract_ID like ? and service_ID like ? and provider_company like ? and start_date like ? and end_date like ? and amount like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,service_contract.getContract_ID());
            else preparedStatement.setString(1,"%");
            if(!tfsid.getText().equals("")) preparedStatement.setInt(2,service_contract.getService_ID());
            else preparedStatement.setString(2,"%");
            if(!tfcompany.getText().equals("")) preparedStatement.setString(3,service_contract.getCompany());
            else preparedStatement.setString(3,"%");
            if(!tfsd.getText().equals("")) preparedStatement.setString(4,service_contract.getStart_date());
            else preparedStatement.setString(4,"%");
            if(!tfed.getText().equals("")) preparedStatement.setString(5,service_contract.getEnd_date());
            else preparedStatement.setString(5,"%");
            if(!tfamount.getText().equals("")) preparedStatement.setInt(6,service_contract.getAmount());
            else preparedStatement.setString(6,"%");
            preparedStatement.execute();
            service_contractTable.setItems(getServiceContract());
            tfid.clear();
            tfsid.clear();
            tfcompany.clear();
            tfsd.clear();
            tfed.clear();
            tfamount.clear();
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
        Contract_ID.setCellValueFactory(new PropertyValueFactory<Service_Contract, Integer>("contract_ID"));
        Service_ID.setCellValueFactory(new PropertyValueFactory<Service_Contract, Integer>("service_ID"));
        Company.setCellValueFactory(new PropertyValueFactory<Service_Contract, String>("company"));
        Start_Date.setCellValueFactory(new PropertyValueFactory<Service_Contract, String>("start_date"));
        End_Date.setCellValueFactory(new PropertyValueFactory<Service_Contract, String>("end_date"));
        Amount.setCellValueFactory(new PropertyValueFactory<Service_Contract, Integer>("amount"));
        service_contractTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            service_contractTable.setItems(getServiceContract());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Service_Contract> getServiceContract() throws SQLException {
        ObservableList<Service_Contract> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from service_contract";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Service_Contract service_contract = new Service_Contract();
            service_contract.setContract_ID(rs.getInt(1));
            service_contract.setService_ID(rs.getInt(2));
            service_contract.setCompany(rs.getString(3));
            service_contract.setStart_date(rs.getString(4));
            service_contract.setEnd_date(rs.getString(5));
            service_contract.setAmount(rs.getInt(6));
            records.add(service_contract);
        }
        connection.close();
        return records;
    }
}



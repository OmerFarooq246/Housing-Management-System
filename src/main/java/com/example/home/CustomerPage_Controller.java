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
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class CustomerPage_Controller implements Initializable {
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    TableColumn<Customer, Integer> Customer_ID;
    @FXML
    TableColumn<Customer, String> First_Name;
    @FXML
    TableColumn<Customer, String> Last_Name;
    @FXML
    TableColumn<Customer, Integer> CNIC;
    @FXML
    TableColumn<Customer, String> Address;
    @FXML
    TableColumn<Customer, Integer> Phone_Number;
    @FXML
    TableColumn<Customer, String> Profession;
    @FXML
    TableColumn<Customer, String> City;


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
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tffn.getText().equals("") &&
                tflsn.getText().equals("") &&
                tfcnic.getText().equals("") &&
                tfaddress.getText().equals("") &&
                tfno.getText().equals("") &&
                tfprofession.getText().equals("") &&
                tfcity.getText().equals("")
        ){
            customerTable.setItems(getCustomer());
        }
        else{
            Customer customer = new Customer();
            if(!tfid.getText().equals("")) customer.setCustomer_ID(Integer.parseInt(tfid.getText()));
            if(!tffn.getText().equals("")) customer.setFirst_Name(tffn.getText());
            if(!tflsn.getText().equals("")) customer.setLast_name(tflsn.getText());
            if(!tfcnic.getText().equals("")) customer.setCNIC(Integer.parseInt(tfcnic.getText()));
            if(!tfaddress.getText().equals("")) customer.setAddress(tfaddress.getText());
            if(!tfno.getText().equals("")) customer.setPhone_number(Integer.parseInt(tfno.getText()));
            if(!tfprofession.getText().equals("")) customer.setProfession(tfprofession.getText());
            if(!tfcity.getText().equals("")) customer.setCity(tfcity.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call customerinsertion(?,?,?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,customer.getCustomer_ID());
            callableStatement.setString(2,customer.getFirst_Name());
            callableStatement.setString(3,customer.getLast_name());
            callableStatement.setInt(4, customer.getCNIC());
            callableStatement.setString(5,customer.getAddress());
            callableStatement.setInt(6,customer.getPhone_number());
            callableStatement.setString(7,customer.getProfession());
            callableStatement.setString(8,customer.getCity());
            callableStatement.execute();
            customerTable.setItems(getCustomer());
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
                tfcity.getText().equals("")
        ){
            customerTable.setItems(getCustomer());
        }
        else{
            Customer customer = new Customer();
            if(!tfid.getText().equals("")) customer.setCustomer_ID(Integer.parseInt(tfid.getText()));
            if(!tffn.getText().equals("")) customer.setFirst_Name(tffn.getText());
            if(!tflsn.getText().equals("")) customer.setLast_name(tflsn.getText());
            if(!tfcnic.getText().equals("")) customer.setCNIC(Integer.parseInt(tfcnic.getText()));
            if(!tfaddress.getText().equals("")) customer.setAddress(tfaddress.getText());
            if(!tfno.getText().equals("")) customer.setPhone_number(Integer.parseInt(tfno.getText()));
            if(!tfprofession.getText().equals("")) customer.setProfession(tfprofession.getText());
            if(!tfcity.getText().equals("")) customer.setCity(tfcity.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from customers where customers_ID like ? and first_name like ? and last_name like ? and address like ? and phone_number like ? and cnic like ? and profession like ? and city like ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,customer.getCustomer_ID());
            else preparedStatement.setString(1,"%");
            if(!tffn.getText().equals("")) preparedStatement.setString(2,customer.getFirst_Name());
            else preparedStatement.setString(2,"%");
            if(!tflsn.getText().equals("")) preparedStatement.setString(3,customer.getLast_name());
            else preparedStatement.setString(3,"%");
            if(!tfcnic.getText().equals("")) preparedStatement.setInt(4,customer.getCNIC());
            else preparedStatement.setString(4,"%");
            if(!tfaddress.getText().equals("")) preparedStatement.setString(5,customer.getAddress());
            else preparedStatement.setString(5,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(6,customer.getPhone_number());
            else preparedStatement.setString(6,"%");
            if(!tfprofession.getText().equals("")) preparedStatement.setString(7,customer.getProfession());
            else preparedStatement.setString(7,"%");
            if(!tfcity.getText().equals("")) preparedStatement.setString(8,customer.getCity());
            else preparedStatement.setString(8,"%");

            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Customer> records = FXCollections.observableArrayList();
            while (rs.next()){
                Customer ccustomer = new Customer();
                ccustomer.setCustomer_ID(rs.getInt(1));
                ccustomer.setFirst_Name(rs.getString(2));
                ccustomer.setLast_name(rs.getString(3));
                ccustomer.setCNIC(rs.getInt(4));
                ccustomer.setAddress(rs.getString(5));
                ccustomer.setPhone_number(rs.getInt(6));
                ccustomer.setProfession(rs.getString(7));
                ccustomer.setCity(rs.getString(8));
                records.add(ccustomer);
            }
            customerTable.setItems(records);
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
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tffn.getText().equals("") &&
                tflsn.getText().equals("") &&
                tfcnic.getText().equals("") &&
                tfaddress.getText().equals("") &&
                tfno.getText().equals("") &&
                tfprofession.getText().equals("") &&
                tfcity.getText().equals("")
        ){
            customerTable.setItems(getCustomer());
        }
        else{
            Customer customer = new Customer();
            if(!tfid.getText().equals("")) customer.setCustomer_ID(Integer.parseInt(tfid.getText()));
            if(!tffn.getText().equals("")) customer.setFirst_Name(tffn.getText());
            if(!tflsn.getText().equals("")) customer.setLast_name(tflsn.getText());
            if(!tfcnic.getText().equals("")) customer.setCNIC(Integer.parseInt(tfcity.getText()));
            if(!tfaddress.getText().equals("")) customer.setAddress(tfaddress.getText());
            if(!tfno.getText().equals("")) customer.setPhone_number(Integer.parseInt(tfno.getText()));
            if(!tfprofession.getText().equals("")) customer.setProfession(tfprofession.getText());
            if(!tfcity.getText().equals("")) customer.setCity(tfcity.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from customers where customers_ID like ? and first_name like ? and last_name like ? and address like ? and phone_number like ? and cnic like ? and profession like ? and city like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,customer.getCustomer_ID());
            else preparedStatement.setString(1,"%");
            if(!tffn.getText().equals("")) preparedStatement.setString(2,customer.getFirst_Name());
            else preparedStatement.setString(2,"%");
            if(!tflsn.getText().equals("")) preparedStatement.setString(3,customer.getLast_name());
            else preparedStatement.setString(3,"%");
            if(!tfcnic.getText().equals("")) preparedStatement.setInt(4,customer.getCNIC());
            else preparedStatement.setString(4,"%");
            if(!tfaddress.getText().equals("")) preparedStatement.setString(5,customer.getAddress());
            else preparedStatement.setString(5,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(6,customer.getPhone_number());
            else preparedStatement.setString(6,"%");
            if(!tfprofession.getText().equals("")) preparedStatement.setString(7,customer.getProfession());
            else preparedStatement.setString(7,"%");
            if(!tfcity.getText().equals("")) preparedStatement.setString(8,customer.getCity());
            else preparedStatement.setString(8,"%");

            preparedStatement.execute();
            customerTable.setItems(getCustomer());
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
        Customer_ID.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("customer_ID"));
        First_Name.setCellValueFactory(new PropertyValueFactory<Customer, String>("First_Name"));
        Last_Name.setCellValueFactory(new PropertyValueFactory<Customer, String>("last_name"));
        CNIC.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CNIC"));
        Address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        Phone_Number.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("phone_number"));
        Profession.setCellValueFactory(new PropertyValueFactory<Customer, String>("profession"));
        City.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            customerTable.setItems(getCustomer());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Customer> getCustomer() throws SQLException {
        ObservableList<Customer> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from customers";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Customer customer = new Customer();
            customer.setCustomer_ID(rs.getInt(1));
            customer.setFirst_Name(rs.getString(2));
            customer.setLast_name(rs.getString(3));
            customer.setCNIC(rs.getInt(4));
            customer.setAddress(rs.getString(5));
            customer.setPhone_number(rs.getInt(6));
            customer.setProfession(rs.getString(7));
            customer.setCity(rs.getString(8));
            records.add(customer);
        }
        connection.close();
        return records;
    }
}

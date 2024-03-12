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

public class Property_PurchasesPage_Controller implements Initializable {

    @FXML
    private TableView<Property_Purchases> property_purchasesTable;
    @FXML
    TableColumn<Property_Purchases, Integer> Purchase_ID;
    @FXML
    TableColumn<Property_Purchases, Integer> Customer_ID;
    @FXML
    TableColumn<Property_Purchases, Integer> Property_ID;
    @FXML
    TableColumn<Property_Purchases, String> Date;
    @FXML
    TableColumn<Property_Purchases, Integer> Total_Amount;
    @FXML
    TableColumn<Property_Purchases, Integer> Amount_Received;
    @FXML
    TableColumn<Property_Purchases, Integer> AmountRemaining;
    @FXML
    TableColumn<Property_Purchases, Integer> No_ofInstallments;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcustomerid;
    @FXML
    private TextField tfpropertyid;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tftotal;
    @FXML
    private TextField tfreceived;
    @FXML
    private TextField tfremaining;
    @FXML
    private TextField tfno;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfcustomerid.getText().equals("") &&
                tfpropertyid.getText().equals("") &&
                tfdate.getText().equals("") &&
                tftotal.getText().equals("") &&
                tfreceived.getText().equals("") &&
                //tfremaining.getText().equals("") &&
                tfno.getText().equals("")
        ){
            property_purchasesTable.setItems(getPurchases());
        }
        else{
            Property_Purchases purchases = new Property_Purchases();
            if(!tfid.getText().equals("")) purchases.setPurchase_ID(Integer.parseInt(tfid.getText()));
            if(!tfcustomerid.getText().equals("")) purchases.setCustomer_ID(Integer.parseInt(tfcustomerid.getText()));
            if(!tfpropertyid.getText().equals("")) purchases.setProperty_ID(Integer.parseInt(tfpropertyid.getText()));
            if(!tfdate.getText().equals("")) purchases.setDate(tfdate.getText());
            if(!tftotal.getText().equals("")) purchases.setTotal_amount(Integer.parseInt(tftotal.getText()));
            if(!tfreceived.getText().equals("")) purchases.setAmount_received(Integer.parseInt(tfreceived.getText()));
            //if(!tfremaining.getText().equals(""));
            if(!tfno.getText().equals("")) purchases.setNo_ofInstallments(Integer.parseInt(tfno.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call propertyPurchasesInsertion(?,?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,purchases.getPurchase_ID());
            callableStatement.setInt(2, purchases.getCustomer_ID());
            callableStatement.setInt(3, purchases.getProperty_ID());
            callableStatement.setString(4, purchases.getDate());
            callableStatement.setInt(5, purchases.getTotal_amount());
            callableStatement.setInt(6, purchases.getAmount_received());
            callableStatement.setInt(7, purchases.getNo_ofInstallments());
            callableStatement.execute();
            property_purchasesTable.setItems(getPurchases());
            tfid.clear();
            tfcustomerid.clear();
            tfpropertyid.clear();
            tfdate.clear();
            tftotal.clear();
            tfreceived.clear();
            tfremaining.clear();
            tfno.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfcustomerid.getText().equals("") &&
                tfpropertyid.getText().equals("") &&
                tfdate.getText().equals("") &&
                tftotal.getText().equals("") &&
                tfreceived.getText().equals("") &&
                tfremaining.getText().equals("") &&
                tfno.getText().equals("")
        ){
            property_purchasesTable.setItems(getPurchases());
        }
        else{
            Property_Purchases purchases = new Property_Purchases();
            if(!tfid.getText().equals("")) purchases.setPurchase_ID(Integer.parseInt(tfid.getText()));
            if(!tfcustomerid.getText().equals("")) purchases.setCustomer_ID(Integer.parseInt(tfcustomerid.getText()));
            if(!tfpropertyid.getText().equals("")) purchases.setProperty_ID(Integer.parseInt(tfpropertyid.getText()));
            if(!tfdate.getText().equals("")) purchases.setDate(tfdate.getText());
            if(!tftotal.getText().equals("")) purchases.setTotal_amount(Integer.parseInt(tftotal.getText()));
            if(!tfreceived.getText().equals("")) purchases.setAmount_received(Integer.parseInt(tfreceived.getText()));
            if(!tfremaining.getText().equals("")) purchases.setAmount_remaining(Integer.parseInt(tfremaining.getText()));
            if(!tfno.getText().equals("")) purchases.setNo_ofInstallments(Integer.parseInt(tfno.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from property_purchases where purchase_ID like ? and customer_ID like ? and Property_ID like ? and date like ? and total_amount like ? and amount_recieved like ? and amount_remaining like ? and No_of_Installments like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1, purchases.getPurchase_ID());
            else preparedStatement.setString(1, "%");
            if(!tfcustomerid.getText().equals("")) preparedStatement.setInt(2, purchases.getCustomer_ID());
            else preparedStatement.setString(2, "%");
            if(!tfpropertyid.getText().equals("")) preparedStatement.setInt(3, purchases.getProperty_ID());
            else preparedStatement.setString(3, "%");
            if(!tfdate.getText().equals("")) preparedStatement.setString(4, purchases.getDate());
            else preparedStatement.setString(4, "%");
            if(!tftotal.getText().equals("")) preparedStatement.setInt(5, purchases.getTotal_amount());
            else preparedStatement.setString(5, "%");
            if(!tfreceived.getText().equals("")) preparedStatement.setInt(6, purchases.getAmount_received());
            else preparedStatement.setString(6, "%");
            if(!tfremaining.getText().equals("")) preparedStatement.setInt(7, purchases.getAmount_remaining());
            else preparedStatement.setString(7, "%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(8, purchases.getNo_ofInstallments());
            else preparedStatement.setString(8, "%");
            ResultSet rs = preparedStatement.executeQuery();

            ObservableList<Property_Purchases> records = FXCollections.observableArrayList();

            while (rs.next()){
                Property_Purchases property_purchases = new Property_Purchases();
                property_purchases.setPurchase_ID(rs.getInt(1));
                property_purchases.setCustomer_ID(rs.getInt(2));
                property_purchases.setProperty_ID(rs.getInt(3));
                property_purchases.setDate(rs.getString(4));
                property_purchases.setTotal_amount(rs.getInt(5));
                property_purchases.setAmount_received(rs.getInt(6));
                property_purchases.setAmount_remaining(rs.getInt(7));
                property_purchases.setNo_ofInstallments(rs.getInt(8));
                records.add(property_purchases);
            }
            property_purchasesTable.setItems(records);
            tfid.clear();
            tfcustomerid.clear();
            tfpropertyid.clear();
            tfdate.clear();
            tftotal.clear();
            tfremaining.clear();
            tfreceived.clear();
            tfno.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfcustomerid.getText().equals("") &&
                tfpropertyid.getText().equals("") &&
                tfdate.getText().equals("") &&
                tftotal.getText().equals("") &&
                tfreceived.getText().equals("") &&
                tfremaining.getText().equals("") &&
                tfno.getText().equals("")
        ){
            property_purchasesTable.setItems(getPurchases());
        }
        else{
            Property_Purchases purchases = new Property_Purchases();
            if(!tfid.getText().equals("")) purchases.setPurchase_ID(Integer.parseInt(tfid.getText()));
            if(!tfcustomerid.getText().equals("")) purchases.setCustomer_ID(Integer.parseInt(tfcustomerid.getText()));
            if(!tfpropertyid.getText().equals("")) purchases.setProperty_ID(Integer.parseInt(tfpropertyid.getText()));
            if(!tfdate.getText().equals("")) purchases.setDate(tfdate.getText());
            if(!tftotal.getText().equals("")) purchases.setTotal_amount(Integer.parseInt(tftotal.getText()));
            if(!tfreceived.getText().equals("")) purchases.setAmount_received(Integer.parseInt(tfreceived.getText()));
            if(!tfremaining.getText().equals("")) purchases.setAmount_remaining(Integer.parseInt(tfremaining.getText()));
            if(!tfno.getText().equals("")) purchases.setNo_ofInstallments(Integer.parseInt(tfno.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from property_purchases where purchase_ID like ? and customer_ID like ? and Property_ID like ? and date like ? and total_amount like ? and amount_recieved like ? and amount_remaining like ? and No_of_Installments like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1, purchases.getPurchase_ID());
            else preparedStatement.setString(1, "%");
            if(!tfcustomerid.getText().equals("")) preparedStatement.setInt(2, purchases.getCustomer_ID());
            else preparedStatement.setString(2, "%");
            if(!tfpropertyid.getText().equals("")) preparedStatement.setInt(3, purchases.getProperty_ID());
            else preparedStatement.setString(3, "%");
            if(!tfdate.getText().equals("")) preparedStatement.setString(4, purchases.getDate());
            else preparedStatement.setString(4, "%");
            if(!tftotal.getText().equals("")) preparedStatement.setInt(5, purchases.getTotal_amount());
            else preparedStatement.setString(5, "%");
            if(!tfreceived.getText().equals("")) preparedStatement.setInt(6, purchases.getAmount_received());
            else preparedStatement.setString(6, "%");
            if(!tfremaining.getText().equals("")) preparedStatement.setInt(7, purchases.getAmount_remaining());
            else preparedStatement.setString(7, "%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(8, purchases.getNo_ofInstallments());
            else preparedStatement.setString(8, "%");
            preparedStatement.execute();
            property_purchasesTable.setItems(getPurchases());
            tfid.clear();
            tfcustomerid.clear();
            tfpropertyid.clear();
            tfdate.clear();
            tftotal.clear();
            tfremaining.clear();
            tfreceived.clear();
            tfno.clear();
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
        Purchase_ID.setCellValueFactory(new PropertyValueFactory<Property_Purchases, Integer>("purchase_ID"));
        Customer_ID.setCellValueFactory(new PropertyValueFactory<Property_Purchases, Integer>("customer_ID"));
        Property_ID.setCellValueFactory(new PropertyValueFactory<Property_Purchases, Integer>("property_ID"));
        Date.setCellValueFactory(new PropertyValueFactory<Property_Purchases, String>("date"));
        Total_Amount.setCellValueFactory(new PropertyValueFactory<Property_Purchases, Integer>("total_amount"));
        Amount_Received.setCellValueFactory(new PropertyValueFactory<Property_Purchases, Integer>("amount_received"));
        AmountRemaining.setCellValueFactory(new PropertyValueFactory<Property_Purchases, Integer>("amount_remaining"));
        No_ofInstallments.setCellValueFactory(new PropertyValueFactory<Property_Purchases, Integer>("no_ofInstallments"));
        property_purchasesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            property_purchasesTable.setItems(getPurchases());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Property_Purchases> getPurchases() throws SQLException {
        ObservableList<Property_Purchases> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from property_purchases";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Property_Purchases property_purchases = new Property_Purchases();
            property_purchases.setPurchase_ID(rs.getInt(1));
            property_purchases.setCustomer_ID(rs.getInt(2));
            property_purchases.setProperty_ID(rs.getInt(3));
            property_purchases.setDate(rs.getString(4));
            property_purchases.setTotal_amount(rs.getInt(5));
            property_purchases.setAmount_received(rs.getInt(6));
            property_purchases.setAmount_remaining(rs.getInt(7));
            property_purchases.setNo_ofInstallments(rs.getInt(8));
            records.add(property_purchases);
        }
        connection.close();
        return records;
    }
}

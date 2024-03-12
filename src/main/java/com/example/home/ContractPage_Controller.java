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

public class ContractPage_Controller implements Initializable {

    @FXML
    private TableView<Contracts> contractsTable;
    @FXML
    TableColumn<Contracts, Integer> Contract_ID;
    @FXML
    TableColumn<Contracts, Integer> Contractor_ID;
    @FXML
    TableColumn<Contracts, String> start_date;
    @FXML
    TableColumn<Contracts, String> end_date;
    @FXML
    TableColumn<Contracts, Integer> Total_amount;
    @FXML
    TableColumn<Contracts, Integer> amount_received;
    @FXML
    TableColumn<Contracts, Integer> amount_remaining;
    @FXML
    TableColumn<Contracts, String> Type;


    @FXML
    private TextField tfid;
    @FXML
    private TextField tfcotractorid;
    @FXML
    private TextField tfsd;
    @FXML
    private TextField tfed;
    @FXML
    private TextField tftotal;
    @FXML
    private TextField tfreceived;
    @FXML
    private TextField tfremaining;
    @FXML
    private TextField tftype;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfcotractorid.getText().equals("") &&
                tfsd.getText().equals("") &&
                tfed.getText().equals("") &&
                tftotal.getText().equals("") &&
                tfreceived.getText().equals("") &&
                //tfremaining.getText().equals("") &&
                tftype.getText().equals("")
        ){
            contractsTable.setItems(getContracts());
        }
        else{
            Contracts contract = new Contracts();
            if(!tfid.getText().equals("")) contract.setContract_ID(Integer.parseInt(tfid.getText()));
            if(!tfcotractorid.getText().equals("")) contract.setContractor_ID(Integer.parseInt(tfcotractorid.getText()));
            if(!tfsd.getText().equals("")) contract.setStart_date(tfsd.getText());
            if(!tfed.getText().equals("")) contract.setEnd_date(tfed.getText());
            if(!tftotal.getText().equals("")) contract.setTotal_amount(Integer.parseInt(tftotal.getText()));
            if(!tfreceived.getText().equals("")) contract.setAmount_received(Integer.parseInt(tfreceived.getText()));
            //if(!tfremaining.getText().equals(""));
            if(!tftype.getText().equals("")) contract.setType(tftype.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call contractinsertion(?,?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,contract.getContract_ID());
            callableStatement.setInt(2, contract.getContractor_ID());
            callableStatement.setString(3, contract.getStart_date());
            callableStatement.setString(4, contract.getEnd_date());
            callableStatement.setInt(5, contract.getTotal_amount());
            callableStatement.setInt(6, contract.getAmount_received());
            callableStatement.setString(7, contract.getType());
            callableStatement.execute();
            contractsTable.setItems(getContracts());
            tfid.clear();
            tfcotractorid.clear();
            tfsd.clear();
            tfed.clear();
            tftotal.clear();
            tfreceived.clear();
            tfremaining.clear();
            tftype.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfcotractorid.getText().equals("") &&
                tfsd.getText().equals("") &&
                tfed.getText().equals("") &&
                tftotal.getText().equals("") &&
                tfreceived.getText().equals("") &&
                //tfremaining.getText().equals("") &&
                tftype.getText().equals("")
        ){
            contractsTable.setItems(getContracts());
        }
        else{
            Contracts contract = new Contracts();
            if(!tfid.getText().equals("")) contract.setContract_ID(Integer.parseInt(tfid.getText()));
            if(!tfcotractorid.getText().equals("")) contract.setContractor_ID(Integer.parseInt(tfcotractorid.getText()));
            if(!tfsd.getText().equals("")) contract.setStart_date(tfsd.getText());
            if(!tfed.getText().equals("")) contract.setEnd_date(tfed.getText());
            if(!tftotal.getText().equals("")) contract.setTotal_amount(Integer.parseInt(tftotal.getText()));
            if(!tfreceived.getText().equals("")) contract.setAmount_received(Integer.parseInt(tfreceived.getText()));
            if(!tfremaining.getText().equals("")) contract.setAmount_remaining(Integer.parseInt(tfremaining.getText()));
            if(!tftype.getText().equals("")) contract.setType(tftype.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from contract where contract_ID like ? and contractor_id like ? and start_date like ? and end_date like ? and total_amount like ? and amount_recieved like ? and amount_remaining like ? and type like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1, contract.getContract_ID());
            else preparedStatement.setString(1, "%");
            if(!tfcotractorid.getText().equals("")) preparedStatement.setInt(2, contract.getContractor_ID());
            else preparedStatement.setString(2, "%");
            if(!tfsd.getText().equals("")) preparedStatement.setString(3, contract.getStart_date());
            else preparedStatement.setString(3, "%");
            if(!tfed.getText().equals("")) preparedStatement.setString(4, contract.getEnd_date());
            else preparedStatement.setString(4, "%");
            if(!tftotal.getText().equals("")) preparedStatement.setInt(5, contract.getTotal_amount());
            else preparedStatement.setString(5, "%");
            if(!tfreceived.getText().equals("")) preparedStatement.setInt(6, contract.getAmount_received());
            else preparedStatement.setString(6, "%");
            if(!tfremaining.getText().equals("")) preparedStatement.setInt(7, contract.getAmount_remaining());
            else preparedStatement.setString(7, "%");
            if(!tftype.getText().equals("")) preparedStatement.setString(8, contract.getType());
            else preparedStatement.setString(8, "%");
            ResultSet rs = preparedStatement.executeQuery();

            ObservableList<Contracts> records = FXCollections.observableArrayList();

            while (rs.next()){
                Contracts contracts = new Contracts();
                contracts.setContract_ID(rs.getInt(1));
                contracts.setContractor_ID(rs.getInt(2));
                contracts.setStart_date(rs.getString(3));
                contracts.setEnd_date(rs.getString(4));
                contracts.setTotal_amount(rs.getInt(5));
                contracts.setAmount_received(rs.getInt(6));
                contracts.setAmount_remaining(rs.getInt(7));
                contracts.setType(rs.getString(8));

                records.add(contracts);
            }
            contractsTable.setItems(records);
            tfid.clear();
            tfcotractorid.clear();
            tfsd.clear();
            tfed.clear();
            tftotal.clear();
            tfremaining.clear();
            tfreceived.clear();
            tftype.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfcotractorid.getText().equals("") &&
                tfsd.getText().equals("") &&
                tfed.getText().equals("") &&
                tftotal.getText().equals("") &&
                tfreceived.getText().equals("") &&
                //tfremaining.getText().equals("") &&
                tftype.getText().equals("")
        ){
            return;
        }
        else{
            Contracts contract = new Contracts();
            if(!tfid.getText().equals("")) contract.setContract_ID(Integer.parseInt(tfid.getText()));
            if(!tfcotractorid.getText().equals("")) contract.setContractor_ID(Integer.parseInt(tfcotractorid.getText()));
            if(!tfsd.getText().equals("")) contract.setStart_date(tfsd.getText());
            if(!tfed.getText().equals("")) contract.setEnd_date(tfed.getText());
            if(!tftotal.getText().equals("")) contract.setTotal_amount(Integer.parseInt(tftotal.getText()));
            if(!tfreceived.getText().equals("")) contract.setAmount_received(Integer.parseInt(tfreceived.getText()));
            //if(!tfremaining.getText().equals(""));
            if(!tftype.getText().equals("")) contract.setType(tftype.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from contract where contract_ID like ? and contractor_id like ? and start_date like ? and end_date like ? and total_amount like ? and amount_recieved like ? and amount_remaining like ? and type like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1, contract.getContract_ID());
            else preparedStatement.setString(1, "%");
            if(!tfcotractorid.getText().equals("")) preparedStatement.setInt(2, contract.getContractor_ID());
            else preparedStatement.setString(2, "%");
            if(!tfsd.getText().equals("")) preparedStatement.setString(3, contract.getStart_date());
            else preparedStatement.setString(3, "%");
            if(!tfed.getText().equals("")) preparedStatement.setString(4, contract.getEnd_date());
            else preparedStatement.setString(4, "%");
            if(!tftotal.getText().equals("")) preparedStatement.setInt(5, contract.getTotal_amount());
            else preparedStatement.setString(5, "%");
            if(!tfreceived.getText().equals("")) preparedStatement.setInt(6, contract.getAmount_received());
            else preparedStatement.setString(6, "%");
            if(!tfremaining.getText().equals("")) preparedStatement.setInt(7, contract.getAmount_remaining());
            else preparedStatement.setString(7, "%");
            if(!tftype.getText().equals("")) preparedStatement.setString(8, contract.getType());
            else preparedStatement.setString(8, "%");
            preparedStatement.execute();

            contractsTable.setItems(getContracts());
            tfid.clear();
            tfcotractorid.clear();
            tfsd.clear();
            tfed.clear();
            tftotal.clear();
            tfremaining.clear();
            tfreceived.clear();
            tftype.clear();
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
        Contract_ID.setCellValueFactory(new PropertyValueFactory<Contracts, Integer>("contract_ID"));
        Contractor_ID.setCellValueFactory(new PropertyValueFactory<Contracts, Integer>("contractor_ID"));
        start_date.setCellValueFactory(new PropertyValueFactory<Contracts, String>("start_date"));
        end_date.setCellValueFactory(new PropertyValueFactory<Contracts, String>("end_date"));
        Total_amount.setCellValueFactory(new PropertyValueFactory<Contracts, Integer>("total_amount"));
        amount_received.setCellValueFactory(new PropertyValueFactory<Contracts, Integer>("amount_received"));
        amount_remaining.setCellValueFactory(new PropertyValueFactory<Contracts, Integer>("amount_remaining"));
        Type.setCellValueFactory(new PropertyValueFactory<Contracts, String>("type"));
        contractsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            contractsTable.setItems(getContracts());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Contracts> getContracts() throws SQLException {
        ObservableList<Contracts> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from contract";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Contracts contractss = new Contracts();
            contractss.setContract_ID(rs.getInt(1));
            contractss.setContractor_ID(rs.getInt(2));
            contractss.setStart_date(rs.getString(3));
            contractss.setEnd_date(rs.getString(4));
            contractss.setTotal_amount(rs.getInt(5));
            contractss.setAmount_received(rs.getInt(6));
            contractss.setAmount_remaining(rs.getInt(7));
            contractss.setType(rs.getString(8));

            records.add(contractss);
        }
        connection.close();
        return records;
    }
}

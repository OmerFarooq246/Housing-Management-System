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

public class Paid_InstallmentsPage_Controller implements Initializable {

    @FXML
    private TableView<Paid_Installments> paid_installmentsTable;
    @FXML
    TableColumn<Paid_Installments, Integer> Installment_ID;
    @FXML
    TableColumn<Paid_Installments, Integer> Purchase_ID;
    @FXML
    TableColumn<Paid_Installments, Integer> Installment_No;
    @FXML
    TableColumn<Paid_Installments, Integer> Amount;
    @FXML
    TableColumn<Paid_Installments, String> Date;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfpid;
    @FXML
    private TextField tfno;
    @FXML
    private TextField tfamount;
    @FXML
    private TextField tfdate;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
        tfpid.getText().equals("") &&
        tfno.getText().equals("") &&
        tfamount.getText().equals("") &&
        tfdate.getText().equals("")
        ){
            paid_installmentsTable.setItems(getInstallments());
        }
        else{
            Paid_Installments paid_installments = new Paid_Installments();
            if(!tfid.getText().equals("")) paid_installments.setInstallment_ID(Integer.parseInt(tfid.getText()));
            if(!tfpid.getText().equals("")) paid_installments.setPurchase_ID(Integer.parseInt(tfpid.getText()));
            if(!tfno.getText().equals("")) paid_installments.setInstallment_no(Integer.parseInt(tfno.getText()));
            if(!tfamount.getText().equals("")) paid_installments.setAmount(Integer.parseInt(tfamount.getText()));;
            if(!tfdate.getText().equals("")) paid_installments.setDate(tfdate.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call paid_installmentsinsertion(?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, paid_installments.getInstallment_ID());
            callableStatement.setInt(2, paid_installments.getPurchase_ID());
            callableStatement.setInt(3, paid_installments.getInstallment_no());
            callableStatement.setInt(4, paid_installments.getAmount());
            callableStatement.setString(5, paid_installments.getDate());
            callableStatement.execute();
            paid_installmentsTable.setItems(getInstallments());
            tfid.clear();
            tfpid.clear();
            tfno.clear();
            tfamount.clear();
            tfdate.clear();
            connection.close();
        }

    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfpid.getText().equals("") &&
                tfno.getText().equals("") &&
                tfamount.getText().equals("") &&
                tfdate.getText().equals("")
        ){
            paid_installmentsTable.setItems(getInstallments());
        }
        else{
            Paid_Installments paid_installments = new Paid_Installments();
            if(!tfid.getText().equals("")) paid_installments.setInstallment_ID(Integer.parseInt(tfid.getText()));
            if(!tfpid.getText().equals("")) paid_installments.setPurchase_ID(Integer.parseInt(tfpid.getText()));
            if(!tfno.getText().equals("")) paid_installments.setInstallment_no(Integer.parseInt(tfno.getText()));
            if(!tfamount.getText().equals("")) paid_installments.setAmount(Integer.parseInt(tfamount.getText()));;
            if(!tfdate.getText().equals("")) paid_installments.setDate(tfdate.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from paid_installments where installment_ID like ? and purchase_ID like ? and installment_number like ? and amount like ? and date like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1, paid_installments.getInstallment_ID());
            else preparedStatement.setString(1,"%");
            if(!tfpid.getText().equals("")) preparedStatement.setInt(2, paid_installments.getPurchase_ID());
            else preparedStatement.setString(2,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(3, paid_installments.getInstallment_no());
            else preparedStatement.setString(3,"%");
            if(!tfamount.getText().equals("")) preparedStatement.setInt(4, paid_installments.getAmount());
            else preparedStatement.setString(4,"%");
            if(!tfdate.getText().equals("")) preparedStatement.setString(5, paid_installments.getDate());
            else preparedStatement.setString(5,"%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Paid_Installments> records = FXCollections.observableArrayList();
            while (rs.next()){
                Paid_Installments ppaid_installments = new Paid_Installments();
                ppaid_installments.setInstallment_ID(rs.getInt(1));
                ppaid_installments.setPurchase_ID(rs.getInt(2));
                ppaid_installments.setInstallment_no(rs.getInt(3));
                ppaid_installments.setAmount(rs.getInt(4));
                ppaid_installments.setDate(rs.getString(5));
                records.add(ppaid_installments);
            }
            paid_installmentsTable.setItems(records);
            tfid.clear();
            tfpid.clear();
            tfno.clear();
            tfamount.clear();
            tfdate.clear();
            connection.close();
        }

    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfpid.getText().equals("") &&
                tfno.getText().equals("") &&
                tfamount.getText().equals("") &&
                tfdate.getText().equals("")
        ){
            paid_installmentsTable.setItems(getInstallments());
        }
        else{
            Paid_Installments paid_installments = new Paid_Installments();
            if(!tfid.getText().equals("")) paid_installments.setInstallment_ID(Integer.parseInt(tfid.getText()));
            if(!tfpid.getText().equals("")) paid_installments.setPurchase_ID(Integer.parseInt(tfpid.getText()));
            if(!tfno.getText().equals("")) paid_installments.setInstallment_no(Integer.parseInt(tfno.getText()));
            if(!tfamount.getText().equals("")) paid_installments.setAmount(Integer.parseInt(tfamount.getText()));;
            if(!tfdate.getText().equals("")) paid_installments.setDate(tfdate.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from paid_installments where installment_ID like ? and purchase_ID like ? and installment_number like ? and amount like ? and date like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1, paid_installments.getInstallment_ID());
            else preparedStatement.setString(1,"%");
            if(!tfpid.getText().equals("")) preparedStatement.setInt(2, paid_installments.getPurchase_ID());
            else preparedStatement.setString(2,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(3, paid_installments.getInstallment_no());
            else preparedStatement.setString(3,"%");
            if(!tfamount.getText().equals("")) preparedStatement.setInt(4, paid_installments.getAmount());
            else preparedStatement.setString(4,"%");
            if(!tfdate.getText().equals("")) preparedStatement.setString(5, paid_installments.getDate());
            else preparedStatement.setString(5,"%");
            preparedStatement.execute();
            paid_installmentsTable.setItems(getInstallments());
            tfid.clear();
            tfpid.clear();
            tfno.clear();
            tfamount.clear();
            tfdate.clear();
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
        Installment_ID.setCellValueFactory(new PropertyValueFactory<Paid_Installments, Integer>("installments_ID"));
        Purchase_ID.setCellValueFactory(new PropertyValueFactory<Paid_Installments, Integer>("purchase_ID"));
        Installment_No.setCellValueFactory(new PropertyValueFactory<Paid_Installments, Integer>("installments_no"));
        Amount.setCellValueFactory(new PropertyValueFactory<Paid_Installments, Integer>("amount"));
        Date.setCellValueFactory(new PropertyValueFactory<Paid_Installments, String>("date"));
        paid_installmentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            paid_installmentsTable.setItems(getInstallments());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Paid_Installments> getInstallments() throws SQLException {
        ObservableList<Paid_Installments> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from paid_installments";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Paid_Installments paid_installments = new Paid_Installments();
            paid_installments.setInstallment_ID(rs.getInt(1));
            paid_installments.setPurchase_ID(rs.getInt(2));
            paid_installments.setInstallment_no(rs.getInt(3));
            paid_installments.setAmount(rs.getInt(4));
            paid_installments.setDate(rs.getString(5));
            records.add(paid_installments);
        }
        connection.close();
        return records;
    }
}

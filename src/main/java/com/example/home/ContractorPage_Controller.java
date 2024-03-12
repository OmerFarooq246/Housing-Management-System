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

public class ContractorPage_Controller implements Initializable {

    @FXML
    private TableView<Contractor> contractorTable;
    @FXML
    TableColumn<Contractor, Integer> Contractor_ID;
    @FXML
    TableColumn<Contractor, String> Name;
    @FXML
    TableColumn<Contractor, String> Type;


    @FXML
    private TextField tfID;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tftype;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfID.getText().equals("") && tfname.getText().equals("") && tftype.getText().equals("")){
           contractorTable.setItems(getContractors());
        }
        else{
            Contractor contractor = new Contractor();
            if(!tfID.getText().equals("")) contractor.setContractor_ID(Integer.parseInt(tfID.getText()));
            if(!tfname.getText().equals("")) contractor.setName(tfname.getText());
            if(!tftype.getText().equals("")) contractor.setType(tftype.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call contractorinsertion(?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, contractor.getContractor_ID());
            callableStatement.setString(2, contractor.getName());
            callableStatement.setString(3, contractor.getType());
            callableStatement.execute();
            contractorTable.setItems(getContractors());
            tfID.clear();
            tfname.clear();
            tftype.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfID.getText().equals("") && tfname.getText().equals("") && tftype.getText().equals("")){
            contractorTable.setItems(getContractors());
        }
        else{
            Contractor contractor = new Contractor();
            if(!tfID.getText().equals("")) contractor.setContractor_ID(Integer.parseInt(tfID.getText()));
            if(!tfname.getText().equals("")) contractor.setName(tfname.getText());
            if(!tftype.getText().equals("")) contractor.setType(tftype.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from contractor where contractor_ID like ? and name like ? and type like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if(!tfID.getText().equals("")) preparedStatement.setInt(1, contractor.getContractor_ID());
            else preparedStatement.setString(1, "%");
            if(!tfname.getText().equals("")) preparedStatement.setString(2, contractor.getName());
            else preparedStatement.setString(2, "%");
            if(!tftype.getText().equals("")) preparedStatement.setString(3, contractor.getType());
            else preparedStatement.setString(3, "%");
            ResultSet rs = preparedStatement.executeQuery();

            ObservableList<Contractor> records = FXCollections.observableArrayList();
            while (rs.next()){
                Contractor contractor1 = new Contractor();
                contractor1.setContractor_ID(rs.getInt(1));
                contractor1.setName(rs.getString(2));
                contractor1.setType(rs.getString(3));
                records.add(contractor1);
            }
            contractorTable.setItems(records);
            tfID.clear();
            tfname.clear();
            tftype.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfID.getText().equals("") && tfname.getText().equals("") && tftype.getText().equals("")){
            contractorTable.setItems(getContractors());
        }
        else{
            Contractor contractor = new Contractor();
            if(!tfID.getText().equals("")) contractor.setContractor_ID(Integer.parseInt(tfID.getText()));
            if(!tfname.getText().equals("")) contractor.setName(tfname.getText());
            if(!tftype.getText().equals("")) contractor.setType(tftype.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from contractor where contractor_ID like ? and name like ? and type like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if(!tfID.getText().equals("")) preparedStatement.setInt(1, contractor.getContractor_ID());
            else preparedStatement.setString(1, "%");
            if(!tfname.getText().equals("")) preparedStatement.setString(2, contractor.getName());
            else preparedStatement.setString(2, "%");
            if(!tftype.getText().equals("")) preparedStatement.setString(3, contractor.getType());
            else preparedStatement.setString(3, "%");
            preparedStatement.execute();

            contractorTable.setItems(getContractors());
            tfID.clear();
            tfname.clear();
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
        Contractor_ID.setCellValueFactory(new PropertyValueFactory<Contractor, Integer>("Contractor_ID"));
        Name.setCellValueFactory(new PropertyValueFactory<Contractor, String>("Name"));
        Type.setCellValueFactory(new PropertyValueFactory<Contractor, String>("Type"));
        contractorTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            contractorTable.setItems(getContractors());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Contractor> getContractors() throws SQLException {
        ObservableList<Contractor> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from contractor";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Contractor con1 = new Contractor();
            con1.setContractor_ID(rs.getInt(1));
            con1.setName(rs.getString(2));
            con1.setType(rs.getString(3));

            records.add(con1);
        }
        connection.close();
        return records;
    }
}

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

public class ServiceConnectionPage_Controller implements Initializable {

    @FXML
    private TableView<Service_Connections> service_connectionsTable;
    @FXML
    TableColumn<Service_Connections, Integer> Connection_ID;
    @FXML
    TableColumn<Service_Connections, Integer> Service_ID;
    @FXML
    TableColumn<Service_Connections, Integer> Property_ID;
    @FXML
    TableColumn<Service_Connections, String> Start_Date;
    @FXML
    TableColumn<Service_Connections, String> End_Date;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfsid;
    @FXML
    private TextField tfpid;
    @FXML
    private TextField tfsd;
    @FXML
    private TextField tfed;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfsid.getText().equals("") &&
                tfpid.getText().equals("") &&
                tfsd.getText().equals("") &&
                tfed.getText().equals("")
        ){
            service_connectionsTable.setItems(getServiceConnections());
        }
        else{
            Service_Connections service_connections = new Service_Connections();
            if(!tfid.getText().equals("")) service_connections.setConnection_ID(Integer.parseInt(tfid.getText()));
            if(!tfsid.getText().equals("")) service_connections.setService_ID(Integer.parseInt(tfsid.getText()));
            if(!tfpid.getText().equals("")) service_connections.setProperty_ID(Integer.parseInt(tfpid.getText()));
            if(!tfsd.getText().equals("")) service_connections.setStart_date(tfsd.getText());
            if(!tfed.getText().equals("")) service_connections.setEnd_date(tfed.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call connectioninsertion(?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, service_connections.getConnection_ID());
            callableStatement.setInt(2,service_connections.getService_ID());
            callableStatement.setInt(3,service_connections.getProperty_ID());
            callableStatement.setString(4, service_connections.getStart_date());
            callableStatement.setString(5,service_connections.getEnd_date());
            callableStatement.execute();
            service_connectionsTable.setItems(getServiceConnections());
            tfid.clear();
            tfsid.clear();
            tfpid.clear();
            tfsd.clear();
            tfed.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfsid.getText().equals("") &&
                tfpid.getText().equals("") &&
                tfsd.getText().equals("") &&
                tfed.getText().equals("")
        ){
            service_connectionsTable.setItems(getServiceConnections());
        }
        else{
            Service_Connections service_connections = new Service_Connections();
            if(!tfid.getText().equals("")) service_connections.setConnection_ID(Integer.parseInt(tfid.getText()));
            if(!tfsid.getText().equals("")) service_connections.setService_ID(Integer.parseInt(tfsid.getText()));
            if(!tfpid.getText().equals("")) service_connections.setProperty_ID(Integer.parseInt(tfpid.getText()));
            if(!tfsd.getText().equals("")) service_connections.setStart_date(tfsd.getText());
            if(!tfed.getText().equals("")) service_connections.setEnd_date(tfed.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from service_connection where connection_id like ? and service_id like ? and  property_id like ? and start_date like ? and end_date like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,service_connections.getConnection_ID());
            else preparedStatement.setString(1,"%");
            if(!tfsid.getText().equals("")) preparedStatement.setInt(2,service_connections.getService_ID());
            else preparedStatement.setString(2,"%");
            if(!tfpid.getText().equals("")) preparedStatement.setInt(3,service_connections.getProperty_ID());
            else preparedStatement.setString(3,"%");
            if(!tfsd.getText().equals("")) preparedStatement.setString(4,service_connections.getStart_date());
            else preparedStatement.setString(4,"%");
            if(!tfed.getText().equals("")) preparedStatement.setString(5,service_connections.getEnd_date());
            else preparedStatement.setString(5,"%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Service_Connections> records = FXCollections.observableArrayList();
            while (rs.next()){
                Service_Connections sservice_connections = new Service_Connections();
                sservice_connections.setConnection_ID(rs.getInt(1));
                sservice_connections.setService_ID(rs.getInt(2));
                sservice_connections.setProperty_ID(rs.getInt(3));
                sservice_connections.setStart_date(rs.getString(4));
                sservice_connections.setEnd_date(rs.getString(5));
                records.add(sservice_connections);
            }
            service_connectionsTable.setItems(records);
            tfid.clear();
            tfsid.clear();
            tfpid.clear();
            tfsd.clear();
            tfed.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfsid.getText().equals("") &&
                tfpid.getText().equals("") &&
                tfsd.getText().equals("") &&
                tfed.getText().equals("")
        ){
            service_connectionsTable.setItems(getServiceConnections());
        }
        else{
            Service_Connections service_connections = new Service_Connections();
            if(!tfid.getText().equals("")) service_connections.setConnection_ID(Integer.parseInt(tfid.getText()));
            if(!tfsid.getText().equals("")) service_connections.setService_ID(Integer.parseInt(tfsid.getText()));
            if(!tfpid.getText().equals("")) service_connections.setProperty_ID(Integer.parseInt(tfpid.getText()));
            if(!tfsd.getText().equals("")) service_connections.setStart_date(tfsd.getText());
            if(!tfed.getText().equals("")) service_connections.setEnd_date(tfed.getText());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from service_connection where connection_id like ? and service_id like ? and  property_id like ? and start_date like ? and end_date like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1,service_connections.getConnection_ID());
            else preparedStatement.setString(1,"%");
            if(!tfsid.getText().equals("")) preparedStatement.setInt(2,service_connections.getService_ID());
            else preparedStatement.setString(2,"%");
            if(!tfpid.getText().equals("")) preparedStatement.setInt(3,service_connections.getProperty_ID());
            else preparedStatement.setString(3,"%");
            if(!tfsd.getText().equals("")) preparedStatement.setString(4,service_connections.getStart_date());
            else preparedStatement.setString(4,"%");
            if(!tfed.getText().equals("")) preparedStatement.setString(5,service_connections.getEnd_date());
            else preparedStatement.setString(5,"%");
            preparedStatement.execute();
            service_connectionsTable.setItems(getServiceConnections());
            tfid.clear();
            tfsid.clear();
            tfpid.clear();
            tfsd.clear();
            tfed.clear();
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
        Connection_ID.setCellValueFactory(new PropertyValueFactory<Service_Connections, Integer>("connection_ID"));
        Service_ID.setCellValueFactory(new PropertyValueFactory<Service_Connections, Integer>("service_ID"));
        Property_ID.setCellValueFactory(new PropertyValueFactory<Service_Connections, Integer>("property_ID"));
        Start_Date.setCellValueFactory(new PropertyValueFactory<Service_Connections, String>("start_date"));
        End_Date.setCellValueFactory(new PropertyValueFactory<Service_Connections, String>("end_date"));
        service_connectionsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            service_connectionsTable.setItems(getServiceConnections());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Service_Connections> getServiceConnections() throws SQLException {
        ObservableList<Service_Connections> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from service_connection";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Service_Connections service_connections = new Service_Connections();
            service_connections.setConnection_ID(rs.getInt(1));
            service_connections.setService_ID(rs.getInt(2));
            service_connections.setProperty_ID(rs.getInt(3));
            service_connections.setStart_date(rs.getString(4));
            service_connections.setEnd_date(rs.getString(5));
            records.add(service_connections);
        }
        connection.close();
        return records;
    }
}

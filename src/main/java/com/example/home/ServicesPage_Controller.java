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

public class ServicesPage_Controller implements Initializable {

    @FXML
    private TableView<Services> servicesTable;
    @FXML
    TableColumn<Services, Integer> Service_ID;
    @FXML
    TableColumn<Services, String> Type;
    @FXML
    TableColumn<Services, Integer> No_ofConnections;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfno;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tftype.getText().equals("") &&
                tfno.getText().equals("")
        ){
            servicesTable.setItems(getServices());
        }
        else {
            Services services = new Services();
            if(!tfid.getText().equals("")) services.setService_ID(Integer.parseInt(tfid.getText()));
            if(!tftype.getText().equals("")) services.setType(tftype.getText());
            if(!tfno.getText().equals("")) services.setNo_ofConnections(Integer.parseInt(tfno.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call serviceinsertion(?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,services.getService_ID());
            callableStatement.setString(2,services.getType());
            callableStatement.setInt(3,services.getNo_ofConnections());
            callableStatement.execute();
            servicesTable.setItems(getServices());
            tfid.clear();
            tftype.clear();
            tfno.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tftype.getText().equals("") &&
                tfno.getText().equals("")
        ){
            servicesTable.setItems(getServices());
        }
        else {
            Services services = new Services();
            if(!tfid.getText().equals("")) services.setService_ID(Integer.parseInt(tfid.getText()));
            if(!tftype.getText().equals("")) services.setType(tftype.getText());
            if(!tfno.getText().equals("")) services.setNo_ofConnections(Integer.parseInt(tfno.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from service where service_id like ? and type like ? and no_of_connections like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals(""))preparedStatement.setInt(1,services.getService_ID());
            else preparedStatement.setString(1,"%");
            if(!tftype.getText().equals("")) preparedStatement.setString(2,services.getType());
            else preparedStatement.setString(2,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(3,services.getNo_ofConnections());
            else preparedStatement.setString(3,"%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Services> records = FXCollections.observableArrayList();
            while (rs.next()){
                Services sservices = new Services();
                sservices.setService_ID(rs.getInt(1));
                sservices.setType(rs.getString(2));
                sservices.setNo_ofConnections(rs.getInt(3));
                records.add(sservices);
            }
            servicesTable.setItems(records);
            tfid.clear();
            tftype.clear();
            tfno.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tftype.getText().equals("") &&
                tfno.getText().equals("")
        ){
            servicesTable.setItems(getServices());
        }
        else {
            Services services = new Services();
            if(!tfid.getText().equals("")) services.setService_ID(Integer.parseInt(tfid.getText()));
            if(!tftype.getText().equals("")) services.setType(tftype.getText());
            if(!tfno.getText().equals("")) services.setNo_ofConnections(Integer.parseInt(tfno.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from service where service_id like ? and type like ? and no_of_connections like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals(""))preparedStatement.setInt(1,services.getService_ID());
            else preparedStatement.setString(1,"%");
            if(!tftype.getText().equals("")) preparedStatement.setString(2,services.getType());
            else preparedStatement.setString(2,"%");
            if(!tfno.getText().equals("")) preparedStatement.setInt(3,services.getNo_ofConnections());
            else preparedStatement.setString(3,"%");
            preparedStatement.execute();
            servicesTable.setItems(getServices());
            tfid.clear();
            tftype.clear();
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
        Service_ID.setCellValueFactory(new PropertyValueFactory<Services, Integer>("service_ID"));
        Type.setCellValueFactory(new PropertyValueFactory<Services, String >("type"));
        No_ofConnections.setCellValueFactory(new PropertyValueFactory<Services, Integer>("no_ofConnections"));
        servicesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            servicesTable.setItems(getServices());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Services> getServices() throws SQLException {
        ObservableList<Services> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from service";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Services services = new Services();
            services.setService_ID(rs.getInt(1));
            services.setType(rs.getString(2));
            services.setNo_ofConnections(rs.getInt(3));
            records.add(services);
        }
        connection.close();
        return records;
    }
}

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

public class PropertyPage_Controller implements Initializable {

    @FXML
    private TableView<Property> propertyTable;
    @FXML
    TableColumn<Property, Integer> Property_ID;
    @FXML
    TableColumn<Property, Integer> Owner_ID;
    @FXML
    TableColumn<Property, Integer> Street_ID;
    @FXML
    TableColumn<Property, Integer> Block_ID;
    @FXML
    TableColumn<Property, String> Type;
    @FXML
    TableColumn<Property, Integer> Length;
    @FXML
    TableColumn<Property, Integer> Width;
    @FXML
    TableColumn<Property, Integer> Floors;
    @FXML
    TableColumn<Property, Integer> Size;

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfoid;
    @FXML
    private TextField tfsid;
    @FXML
    private TextField tfbid;
    @FXML
    private TextField tftype;
    @FXML
    private TextField tfl;
    @FXML
    private TextField tfw;
    @FXML
    private TextField tffloors;
    @FXML
    private TextField tfsize;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfoid.getText().equals("") &&
                tfsid.getText().equals("") &&
                tfbid.getText().equals("") &&
                tftype.getText().equals("") &&
                tfl.getText().equals("") &&
                tfw.getText().equals("") &&
                tffloors.getText().equals("") &&
                tfsize.getText().equals("")
        ){
            propertyTable.setItems(getProperty());
        }
        else{
            Property property = new Property();
            if(!tfid.getText().equals("")) property.setProperty_ID(Integer.parseInt(tfid.getText()));
            if(!tfoid.getText().equals("")) property.setOwner_ID(Integer.parseInt(tfoid.getText()));
            if(!tfsid.getText().equals("")) property.setStreet_ID(Integer.parseInt(tfsid.getText()));
            if(!tfbid.getText().equals("")) property.setBlock_ID(Integer.parseInt(tfbid.getText()));
            if(!tftype.getText().equals("")) property.setType(tftype.getText());
            if(!tfl.getText().equals("")) property.setLength(Integer.parseInt(tfl.getText()));
            if(!tfw.getText().equals("")) property.setWidth(Integer.parseInt(tfw.getText()));
            if(!tffloors.getText().equals("")) property.setFloors(Integer.parseInt(tffloors.getText()));
            if(!tfsize.getText().equals("")) property.setStreet_ID(Integer.parseInt(tfsid.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call property_insertion(?,?,?,?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,property.getProperty_ID());
            callableStatement.setInt(2,property.getOwner_ID());
            callableStatement.setInt(3,property.getStreet_ID());
            callableStatement.setInt(4,property.getBlock_ID());
            callableStatement.setString(5,property.getType());
            callableStatement.setInt(6,property.getLength());
            callableStatement.setInt(7,property.getWidth());
            callableStatement.setInt(8,property.getFloors());
            //callableStatement.setInt(8,property.getSize());
            callableStatement.execute();
            propertyTable.setItems(getProperty());
            tfid.clear();
            tfoid.clear();
            tfsid.clear();
            tfbid.clear();
            tftype.clear();
            tfl.clear();
            tfw.clear();
            tffloors.clear();
            tfsize.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfoid.getText().equals("") &&
                tfsid.getText().equals("") &&
                tfbid.getText().equals("") &&
                tftype.getText().equals("") &&
                tfl.getText().equals("") &&
                tfw.getText().equals("") &&
                tffloors.getText().equals("") &&
                tfsize.getText().equals("")
        ){
            propertyTable.setItems(getProperty());
        }
        else{
            Property property = new Property();
            if(!tfid.getText().equals("")) property.setProperty_ID(Integer.parseInt(tfid.getText()));
            if(!tfoid.getText().equals("")) property.setOwner_ID(Integer.parseInt(tfoid.getText()));
            if(!tfsid.getText().equals("")) property.setStreet_ID(Integer.parseInt(tfsid.getText()));
            if(!tfbid.getText().equals("")) property.setBlock_ID(Integer.parseInt(tfbid.getText()));
            if(!tftype.getText().equals("")) property.setType(tftype.getText());
            if(!tfl.getText().equals("")) property.setLength(Integer.parseInt(tfl.getText()));
            if(!tfw.getText().equals("")) property.setWidth(Integer.parseInt(tfw.getText()));
            if(!tffloors.getText().equals("")) property.setFloors(Integer.parseInt(tffloors.getText()));
            if(!tfsize.getText().equals("")) property.setStreet_ID(Integer.parseInt(tfsid.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from property where property_ID like ? and owner_ID like ? and street_ID like ? and block_ID like ? and type like ? and length like ? and width like ? and floors like ? and size like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if(!tfid.getText().equals("")) preparedStatement.setInt(1, property.getProperty_ID());
            else preparedStatement.setString(1,"%");
            if(!tfoid.getText().equals("")) preparedStatement.setInt(2, property.getOwner_ID());
            else preparedStatement.setString(2,"%");
            if(!tfsid.getText().equals("")) preparedStatement.setInt(3, property.getStreet_ID());
            else preparedStatement.setString(3,"%");
            if(!tfbid.getText().equals("")) preparedStatement.setInt(4, property.getBlock_ID());
            else preparedStatement.setString(4,"%");
            if(!tftype.getText().equals("")) preparedStatement.setString(5, property.getType());
            else preparedStatement.setString(5,"%");
            if(!tfl.getText().equals("")) preparedStatement.setInt(6, property.getLength());
            else preparedStatement.setString(6,"%");
            if(!tfw.getText().equals("")) preparedStatement.setInt(7, property.getWidth());
            else preparedStatement.setString(7,"%");
            if(!tffloors.getText().equals("")) preparedStatement.setInt(8, property.getFloors());
            else preparedStatement.setString(8,"%");
            if(!tfsize.getText().equals("")) preparedStatement.setInt(9, property.getSize());
            else preparedStatement.setString(9,"%");

            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Property> records = FXCollections.observableArrayList();
            while (rs.next()){
                Property pproperty = new Property();
                pproperty.setProperty_ID(rs.getInt(1));
                pproperty.setOwner_ID(rs.getInt(2));
                pproperty.setStreet_ID(rs.getInt(3));
                pproperty.setBlock_ID(rs.getInt(4));
                pproperty.setType(rs.getString(5));
                pproperty.setLength(rs.getInt(6));
                pproperty.setWidth(rs.getInt(7));
                pproperty.setFloors(rs.getInt(8));
                pproperty.setSize(rs.getInt(9));
                records.add(pproperty);
            }
            propertyTable.setItems(records);
            tfid.clear();
            tfoid.clear();
            tfsid.clear();
            tfbid.clear();
            tftype.clear();
            tfl.clear();
            tfw.clear();
            tffloors.clear();
            tfsize.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfoid.getText().equals("") &&
                tfsid.getText().equals("") &&
                tfbid.getText().equals("") &&
                tftype.getText().equals("") &&
                tfl.getText().equals("") &&
                tfw.getText().equals("") &&
                tffloors.getText().equals("") &&
                tfsize.getText().equals("")
        ){
            propertyTable.setItems(getProperty());
        }
        else{
            Property property = new Property();
            if(!tfid.getText().equals("")) property.setProperty_ID(Integer.parseInt(tfid.getText()));
            if(!tfoid.getText().equals("")) property.setOwner_ID(Integer.parseInt(tfoid.getText()));
            if(!tfsid.getText().equals("")) property.setStreet_ID(Integer.parseInt(tfsid.getText()));
            if(!tfbid.getText().equals("")) property.setBlock_ID(Integer.parseInt(tfbid.getText()));
            if(!tftype.getText().equals("")) property.setType(tftype.getText());
            if(!tfl.getText().equals("")) property.setLength(Integer.parseInt(tfl.getText()));
            if(!tfw.getText().equals("")) property.setWidth(Integer.parseInt(tfw.getText()));
            if(!tffloors.getText().equals("")) property.setFloors(Integer.parseInt(tffloors.getText()));
            if(!tfsize.getText().equals("")) property.setStreet_ID(Integer.parseInt(tfsid.getText()));

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from property where property_ID like ? and owner_ID like ? and street_ID like ? and block_ID like ? and type like ? and length like ? and width like ? and floors like ? and size like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if(!tfid.getText().equals("")) preparedStatement.setInt(1, property.getProperty_ID());
            else preparedStatement.setString(1,"%");
            if(!tfoid.getText().equals("")) preparedStatement.setInt(2, property.getOwner_ID());
            else preparedStatement.setString(2,"%");
            if(!tfsid.getText().equals("")) preparedStatement.setInt(3, property.getStreet_ID());
            else preparedStatement.setString(3,"%");
            if(!tfbid.getText().equals("")) preparedStatement.setInt(4, property.getBlock_ID());
            else preparedStatement.setString(4,"%");
            if(!tftype.getText().equals("")) preparedStatement.setString(5, property.getType());
            else preparedStatement.setString(5,"%");
            if(!tfl.getText().equals("")) preparedStatement.setInt(6, property.getLength());
            else preparedStatement.setString(6,"%");
            if(!tfw.getText().equals("")) preparedStatement.setInt(7, property.getWidth());
            else preparedStatement.setString(7,"%");
            if(!tffloors.getText().equals("")) preparedStatement.setInt(8, property.getFloors());
            else preparedStatement.setString(8,"%");
            if(!tfsize.getText().equals("")) preparedStatement.setInt(9, property.getSize());
            else preparedStatement.setString(9,"%");

            preparedStatement.execute();
            propertyTable.setItems(getProperty());
            tfid.clear();
            tfoid.clear();
            tfsid.clear();
            tfbid.clear();
            tftype.clear();
            tfl.clear();
            tfw.clear();
            tffloors.clear();
            tfsize.clear();
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
        Property_ID.setCellValueFactory(new PropertyValueFactory<Property, Integer>("Property_ID"));
        Owner_ID.setCellValueFactory(new PropertyValueFactory<Property, Integer>("Owner_ID"));
        Street_ID.setCellValueFactory(new PropertyValueFactory<Property, Integer>("Street_ID"));
        Block_ID.setCellValueFactory(new PropertyValueFactory<Property, Integer>("Block_ID"));
        Type.setCellValueFactory(new PropertyValueFactory<Property, String>("type"));
        Length.setCellValueFactory(new PropertyValueFactory<Property, Integer>("length"));
        Width.setCellValueFactory(new PropertyValueFactory<Property, Integer>("width"));
        Floors.setCellValueFactory(new PropertyValueFactory<Property, Integer>("floors"));
        Size.setCellValueFactory(new PropertyValueFactory<Property, Integer>("size"));
        propertyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            propertyTable.setItems(getProperty());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Property> getProperty() throws SQLException {
        ObservableList<Property> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from property";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Property property = new Property();
            property.setProperty_ID(rs.getInt(1));
            property.setOwner_ID(rs.getInt(2));
            property.setStreet_ID(rs.getInt(3));
            property.setBlock_ID(rs.getInt(4));
            property.setType(rs.getString(5));
            property.setLength(rs.getInt(6));
            property.setWidth(rs.getInt(7));
            property.setFloors(rs.getInt(8));
            property.setSize(rs.getInt(9));
            records.add(property);
        }
        connection.close();
        return records;
    }
}

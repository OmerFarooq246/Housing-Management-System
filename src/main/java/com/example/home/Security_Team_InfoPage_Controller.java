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

public class Security_Team_InfoPage_Controller implements Initializable {

    @FXML
    private TableView<Security_Team_Info> security_team_infoTable;
    @FXML
    TableColumn<Security_Team_Info, Integer> Team_ID;
    @FXML
    TableColumn<Security_Team_Info, Integer> Block_ID;
    @FXML
    TableColumn<Security_Team_Info, Integer> No_ofMembers;
    @FXML
    TableColumn<Security_Team_Info, String> Task;
    @FXML
    TableColumn<Security_Team_Info, String> Status;


    @FXML
    private TextField tfid;
    @FXML
    private TextField tfblockid;
    @FXML
    private TextField tfmembers;
    @FXML
    private TextField tftask;
    @FXML
    private TextField tfstatus;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfblockid.getText().equals("") &&
                tfmembers.getText().equals("") &&
                tftask.getText().equals("") &&
                tfstatus.getText().equals("")
    ){
            security_team_infoTable.setItems(getTeamInfo());
        }
        else{
            Security_Team_Info security_team_info = new Security_Team_Info();
            if(!tfid.getText().equals("")) security_team_info.setTeam_ID(Integer.parseInt(tfid.getText()));
            if(!tfblockid.getText().equals("")) security_team_info.setBlock_ID(Integer.parseInt(tfblockid.getText()));
            if(!tfmembers.getText().equals("")) security_team_info.setNo_ofMembers(Integer.parseInt(tfmembers.getText()));
            if(!tftask.getText().equals("")) security_team_info.setTask(tftask.getText());
            if(!tfstatus.getText().equals("")) security_team_info.setStatus(tfstatus.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call TeamInfoinsertion(?,?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, security_team_info.getTeam_ID());
            callableStatement.setInt(2, security_team_info.getBlock_ID());
            callableStatement.setInt(3, security_team_info.getNo_ofMembers());
            callableStatement.setString(4, security_team_info.getTask());
            callableStatement.setString(5, security_team_info.getStatus());
            callableStatement.execute();
            security_team_infoTable.setItems(getTeamInfo());
            tfid.clear();
            tfblockid.clear();
            tfmembers.clear();
            tftask.clear();
            tfstatus.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfblockid.getText().equals("") &&
                tfmembers.getText().equals("") &&
                tftask.getText().equals("") &&
                tfstatus.getText().equals("")
        ){
            security_team_infoTable.setItems(getTeamInfo());
        }
        else{
            Security_Team_Info security_team_info = new Security_Team_Info();
            if(!tfid.getText().equals("")) security_team_info.setTeam_ID(Integer.parseInt(tfid.getText()));
            if(!tfblockid.getText().equals("")) security_team_info.setBlock_ID(Integer.parseInt(tfblockid.getText()));
            if(!tfmembers.getText().equals("")) security_team_info.setNo_ofMembers(Integer.parseInt(tfmembers.getText()));
            if(!tftask.getText().equals("")) security_team_info.setTask(tftask.getText());
            if(!tfstatus.getText().equals("")) security_team_info.setStatus(tfstatus.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from security_team_info where team_id like ? and block_id like ? and no_of_members like ? and task like ? and status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1, security_team_info.getTeam_ID());
            else preparedStatement.setString(1,"%");
            if(!tfblockid.getText().equals("")) preparedStatement.setInt(2, security_team_info.getBlock_ID());
            else preparedStatement.setString(2,"%");
            if(!tfmembers.getText().equals("")) preparedStatement.setInt(3, security_team_info.getNo_ofMembers());
            else preparedStatement.setString(3,"%");
            if(!tftask.getText().equals("")) preparedStatement.setString(4, security_team_info.getTask());
            else preparedStatement.setString(4,"%");
            if(!tfstatus.getText().equals(""))preparedStatement.setString(5, security_team_info.getStatus());
            else preparedStatement.setString(5,"%");

            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Security_Team_Info> records = FXCollections.observableArrayList();
            while (rs.next()){
                Security_Team_Info ssecurity_team_info = new Security_Team_Info();
                ssecurity_team_info.setTeam_ID(rs.getInt(1));
                ssecurity_team_info.setBlock_ID(rs.getInt(2));
                ssecurity_team_info.setNo_ofMembers(rs.getInt(3));
                ssecurity_team_info.setTask(rs.getString(4));
                ssecurity_team_info.setStatus(rs.getString(5));
                records.add(ssecurity_team_info);
            }
            security_team_infoTable.setItems(records);
            tfid.clear();
            tfblockid.clear();
            tfmembers.clear();
            tftask.clear();
            tfstatus.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfid.getText().equals("") &&
                tfblockid.getText().equals("") &&
                tfmembers.getText().equals("") &&
                tftask.getText().equals("") &&
                tfstatus.getText().equals("")
        ){
            security_team_infoTable.setItems(getTeamInfo());
        }
        else{
            Security_Team_Info security_team_info = new Security_Team_Info();
            if(!tfid.getText().equals("")) security_team_info.setTeam_ID(Integer.parseInt(tfid.getText()));
            if(!tfblockid.getText().equals("")) security_team_info.setBlock_ID(Integer.parseInt(tfblockid.getText()));
            if(!tfmembers.getText().equals("")) security_team_info.setNo_ofMembers(Integer.parseInt(tfmembers.getText()));
            if(!tftask.getText().equals("")) security_team_info.setTask(tftask.getText());
            if(!tfstatus.getText().equals("")) security_team_info.setStatus(tfstatus.getText());

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from security_team_info where team_id like ? and block_id like ? and no_of_members like ? and task like ? and status like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfid.getText().equals("")) preparedStatement.setInt(1, security_team_info.getTeam_ID());
            else preparedStatement.setString(1,"%");
            if(!tfblockid.getText().equals("")) preparedStatement.setInt(2, security_team_info.getBlock_ID());
            else preparedStatement.setString(2,"%");
            if(!tfmembers.getText().equals("")) preparedStatement.setInt(3, security_team_info.getNo_ofMembers());
            else preparedStatement.setString(3,"%");
            if(!tftask.getText().equals("")) preparedStatement.setString(4, security_team_info.getTask());
            else preparedStatement.setString(4,"%");
            if(!tfstatus.getText().equals(""))preparedStatement.setString(5, security_team_info.getStatus());
            else preparedStatement.setString(5,"%");

            preparedStatement.execute();
            security_team_infoTable.setItems(getTeamInfo());
            tfid.clear();
            tfblockid.clear();
            tfmembers.clear();
            tftask.clear();
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
        Team_ID.setCellValueFactory(new PropertyValueFactory<Security_Team_Info, Integer>("team_ID"));
        Block_ID.setCellValueFactory(new PropertyValueFactory<Security_Team_Info, Integer>("block_ID"));
        No_ofMembers.setCellValueFactory(new PropertyValueFactory<Security_Team_Info, Integer>("no_ofMembers"));
        Task.setCellValueFactory(new PropertyValueFactory<Security_Team_Info, String>("task"));
        Status.setCellValueFactory(new PropertyValueFactory<Security_Team_Info, String>("status"));
        security_team_infoTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            security_team_infoTable.setItems(getTeamInfo());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Security_Team_Info> getTeamInfo() throws SQLException {
        ObservableList<Security_Team_Info> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from security_team_info";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Security_Team_Info security_team_info = new Security_Team_Info();
            security_team_info.setTeam_ID(rs.getInt(1));
            security_team_info.setBlock_ID(rs.getInt(2));
            security_team_info.setNo_ofMembers(rs.getInt(3));
            security_team_info.setTask(rs.getString(4));
            security_team_info.setStatus(rs.getString(5));
            records.add(security_team_info);
        }
        connection.close();
        return records;
    }
}

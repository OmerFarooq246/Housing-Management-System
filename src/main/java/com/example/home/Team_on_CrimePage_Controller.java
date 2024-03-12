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

public class Team_on_CrimePage_Controller implements Initializable {

    @FXML
    private TableView<Team_on_Crime> team_on_crimeTable;
    @FXML
    TableColumn<Team_on_Crime, Integer> Crime_ID;
    @FXML
    TableColumn<Team_on_Crime, Integer> Team_ID;

    @FXML
    private TextField tfcrimeid;
    @FXML
    private TextField tfteamid;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if(tfcrimeid.getText().equals("") &&
        tfteamid.getText().equals("")
        ){
            team_on_crimeTable.setItems(getTeamOnCrime());
        }
        else{
            Team_on_Crime team_on_crime = new Team_on_Crime();
            if(!tfcrimeid.getText().equals("")) team_on_crime.setCrime_ID(Integer.parseInt(tfcrimeid.getText()));
            if(!tfteamid.getText().equals("")) team_on_crime.setTeam_ID(Integer.parseInt(tfteamid.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call TeamonCrimeinsertion(?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1,team_on_crime.getCrime_ID());
            callableStatement.setInt(2,team_on_crime.getTeam_ID());
            callableStatement.execute();
            team_on_crimeTable.setItems(getTeamOnCrime());
            tfcrimeid.clear();
            tfteamid.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if(tfcrimeid.getText().equals("") &&
                tfteamid.getText().equals("")
        ){
            team_on_crimeTable.setItems(getTeamOnCrime());
        }
        else{
            Team_on_Crime team_on_crime = new Team_on_Crime();
            if(!tfcrimeid.getText().equals("")) team_on_crime.setCrime_ID(Integer.parseInt(tfcrimeid.getText()));
            if(!tfteamid.getText().equals("")) team_on_crime.setTeam_ID(Integer.parseInt(tfteamid.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from team_on_crime where crime_id like ? and team_id like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfcrimeid.getText().equals("")) preparedStatement.setInt(1, team_on_crime.getCrime_ID());
            else preparedStatement.setString(1, "%");
            if(!tfteamid.getText().equals("")) preparedStatement.setInt(2, team_on_crime.getTeam_ID());
            else preparedStatement.setString(2, "%");
            ResultSet rs = preparedStatement.executeQuery();
            ObservableList<Team_on_Crime> records = FXCollections.observableArrayList();
            while (rs.next()){
                Team_on_Crime tteam_on_crime = new Team_on_Crime();
                tteam_on_crime.setCrime_ID(rs.getInt(1));
                tteam_on_crime.setTeam_ID(rs.getInt(2));
                records.add(tteam_on_crime);
            }
            team_on_crimeTable.setItems(records);
            tfcrimeid.clear();
            tfteamid.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if(tfcrimeid.getText().equals("") &&
                tfteamid.getText().equals("")
        ){
            team_on_crimeTable.setItems(getTeamOnCrime());
        }
        else{
            Team_on_Crime team_on_crime = new Team_on_Crime();
            if(!tfcrimeid.getText().equals("")) team_on_crime.setCrime_ID(Integer.parseInt(tfcrimeid.getText()));
            if(!tfteamid.getText().equals("")) team_on_crime.setTeam_ID(Integer.parseInt(tfteamid.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from team_on_crime where crime_id like ? and team_id like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            if(!tfcrimeid.getText().equals("")) preparedStatement.setInt(1, team_on_crime.getCrime_ID());
            else preparedStatement.setString(1, "%");
            if(!tfteamid.getText().equals("")) preparedStatement.setInt(2, team_on_crime.getTeam_ID());
            else preparedStatement.setString(2, "%");
            preparedStatement.execute();
            team_on_crimeTable.setItems(getTeamOnCrime());
            tfcrimeid.clear();
            tfteamid.clear();
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
        Crime_ID.setCellValueFactory(new PropertyValueFactory<Team_on_Crime, Integer>("crime_ID"));
        Team_ID.setCellValueFactory(new PropertyValueFactory<Team_on_Crime, Integer>("team_ID"));
        team_on_crimeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            team_on_crimeTable.setItems(getTeamOnCrime());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Team_on_Crime> getTeamOnCrime() throws SQLException {
        ObservableList<Team_on_Crime> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from team_on_crime";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Team_on_Crime team_on_crime = new Team_on_Crime();
            team_on_crime.setCrime_ID(rs.getInt(1));
            team_on_crime.setTeam_ID(rs.getInt(2));
            records.add(team_on_crime);
        }
        connection.close();
        return records;
    }
}

package com.example.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage_Controller {

    public static String usertype;

    @FXML
    private MenuButton mbProfile;
    @FXML
    private MenuItem miChangePassword;
    @FXML
    private MenuItem miLogOut;

    @FXML
    private Button btProperty;
    @FXML
    private Button btOwner;
    @FXML
    private Button btCustomer;
    @FXML
    private Button btProperty_Purchases;
    @FXML
    private Button btPaid_Installments;
    @FXML
    private Button btHospital_Clinic;
    @FXML
    private Button btServices;
    @FXML
    private Button btComplain;
    @FXML
    private Button btService_Contract;
    @FXML
    private Button btService_Connections;
    @FXML
    private Button btBlock;
    @FXML
    private Button btStreet;
    @FXML
    private Button btContractor;
    @FXML
    private Button btContracts;
    @FXML
    private Button btCrime;
    @FXML
    private Button btSuspects;
    @FXML
    private Button btSecurity_Team_Info;
    @FXML
    private Button btTeam_on_Crime;
    @FXML
    private Button btStaff;
    @FXML
    private Button btUsers;
    @FXML
    private Label tfTerminal;

    public void setUserType(String Usertype){
        HomePage_Controller.usertype = Usertype;
        System.out.println(Usertype);
    }


    @FXML
    protected void PropertyButtonController(ActionEvent event) throws IOException {
        if(!usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("PropertyPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void OwnerButtonController(ActionEvent event) throws IOException {
        if(!usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("OwnerPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void CustomerButtonController(ActionEvent event) throws IOException {
        if(!(usertype.equals("admin") || usertype.equals("data"))){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("CustomerPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void Property_PurchasesButtonController(ActionEvent event) throws IOException {
        if(!usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Property_PurchasesPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void Paid_InstallmentsButtonController(ActionEvent event) throws IOException {
        if(!usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Paid_InstallmentsPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void Hospital_ClinicButtonController(ActionEvent event) throws IOException {
        if(!usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Hospital_ClinicPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void ServicesButtonController(ActionEvent event) throws IOException {
        if(!(usertype.equals("admin")  || usertype.equals("data") || usertype.equals("service"))){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ServicesPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void ComplainButtonController(ActionEvent event) throws IOException {
        if(!(usertype.equals("admin") || usertype.equals("data") || usertype.equals("service"))){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ComplainPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void Service_ContractButtonController(ActionEvent event) throws IOException {
        if(!(usertype.equals("admin") || usertype.equals("data") || usertype.equals("service"))){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Service_ContractPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void Service_ConnectionButtonController(ActionEvent event) throws IOException {
        if(!(usertype.equals("admin") || usertype.equals("data") || usertype.equals("service"))){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Service_ConnectionPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void BlockButtonController(ActionEvent event) throws IOException {
        if(usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("BlockPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void StreetButtonController(ActionEvent event) throws IOException {
        if(!usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("StreetPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void ContractorButtonController(ActionEvent event) throws IOException {
        if(!usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ContractorPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void ContractsButtonController(ActionEvent event) throws IOException {
        if(!usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("ContractsPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void CrimeButtonController(ActionEvent event) throws IOException {
        if(!(usertype.equals("admin") || usertype.equals("data") || usertype.equals("security"))){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("CrimePage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void SuspectsButtonController(ActionEvent event) throws IOException {
        if(!(usertype.equals("admin") || usertype.equals("data") || usertype.equals("security"))){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("SuspectsPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void Security_Team_InfoButtonController(ActionEvent event) throws IOException {
        if(!(usertype.equals("admin") || usertype.equals("data") || usertype.equals("security"))){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Security_Team_InfoPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void Team_on_CrimeButtonController(ActionEvent event) throws IOException {
        if(!(usertype.equals("admin") || usertype.equals("data") || usertype.equals("security"))){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Team_on_CrimePage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void StaffButtonController(ActionEvent event) throws IOException {
        if(usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("StaffPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    protected void UsersButtonController(ActionEvent event) throws IOException {
        if(!usertype.equals("admin")){
            tfTerminal.setText("  >_ Access denied");
        }
        else {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("UsersPage.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
}

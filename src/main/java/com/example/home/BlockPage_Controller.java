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

public class BlockPage_Controller implements Initializable {

    @FXML
    private TableView<Block> blockTable;
    @FXML
    TableColumn<Block, Integer> block_ID;
    @FXML
    TableColumn<Block, String> Block_Name;
    @FXML
    TableColumn<Block, Integer> No_ofProperties;
    @FXML
    TableColumn<Block, Integer> size;


    @FXML
    private TextField tfID;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tfno;
    @FXML
    private TextField tfsize;

    @FXML
    private Button btInsert;

    @FXML
    protected void InsertButtonController(ActionEvent event) throws SQLException {
        if (tfID.getText().equals("") && tfname.getText().equals("") && tfno.getText().equals("") && tfsize.getText().equals("")) {
            blockTable.setItems(getBlock());
        } else {
            Block b = new Block();
            if (tfID.getText() != "")
                b.setBlock_ID(Integer.parseInt(tfID.getText()));
            else b.setBlock_ID(0);
            if (tfname.getText() != "")
                b.setBlock_Name(tfname.getText());
            else b.setBlock_Name("");
            if (tfno.getText() != "")
                b.setNo_ofProperties(Integer.parseInt(tfno.getText()));
            else b.setNo_ofProperties(0);
            if (tfsize.getText() != "")
                b.setSize(Integer.parseInt(tfsize.getText()));
            else b.setSize(0);

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "{call block_insertion(?,?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, b.getBlock_ID());
            callableStatement.setString(2, b.getBlock_Name());
            callableStatement.setInt(3, b.getNo_ofProperties());
            callableStatement.setInt(4, b.getSize());
            callableStatement.execute();
            blockTable.setItems(getBlock());
            tfID.clear();
            tfno.clear();
            tfsize.clear();
            tfname.clear();
            connection.close();
        }
    }

    @FXML
    protected void SearchButtonController(ActionEvent event) throws SQLException {
        if (tfID.getText().equals("") && tfname.getText().equals("") && tfno.getText().equals("") && tfsize.getText().equals("")) {
            blockTable.setItems(getBlock());
        } else {
            Block b = new Block();
            if (tfID.getText() != "")
                b.setBlock_ID(Integer.parseInt(tfID.getText()));
            if (tfname.getText() != "")
                b.setBlock_Name(tfname.getText());
            if (tfno.getText() != "")
                b.setNo_ofProperties(Integer.parseInt(tfno.getText()));
            if (tfsize.getText() != "")
                b.setSize(Integer.parseInt(tfsize.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "select * from block where block_id like ? and block_name like ? and no_of_properties like ? and size like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if (!tfID.getText().equals("")) preparedStatement.setInt(1, b.getBlock_ID());
            else preparedStatement.setString(1, "%");
            if (!tfname.getText().equals("")) preparedStatement.setString(2, b.getBlock_Name());
            else preparedStatement.setString(2, "%");
            if (!tfno.getText().equals("")) preparedStatement.setInt(3, b.getNo_ofProperties());
            else preparedStatement.setString(3, "%");
            if (!tfsize.getText().equals("")) preparedStatement.setInt(4, b.getSize());
            else preparedStatement.setString(4, "%");
            ResultSet rs = preparedStatement.executeQuery();

            ObservableList<Block> records = FXCollections.observableArrayList();
            while (rs.next()) {
                Block b1 = new Block();
                b1.setBlock_ID(rs.getInt(1));
                b1.setBlock_Name(rs.getString(2));
                b1.setNo_ofProperties(rs.getInt(3));
                b1.setSize(rs.getInt(4));
                records.add(b1);
            }
            blockTable.setItems(records);
            tfID.clear();
            tfno.clear();
            tfsize.clear();
            tfname.clear();
            connection.close();
        }
    }

    @FXML
    protected void DeleteButtonController(ActionEvent event) throws SQLException {
        if (tfID.getText().equals("") && tfname.getText().equals("") && tfno.getText().equals("") && tfsize.getText().equals("")) {
            blockTable.setItems(getBlock());
        } else {
            Block b = new Block();
            if (tfID.getText() != "")
                b.setBlock_ID(Integer.parseInt(tfID.getText()));
            if (tfname.getText() != "")
                b.setBlock_Name(tfname.getText());
            if (tfno.getText() != "")
                b.setNo_ofProperties(Integer.parseInt(tfno.getText()));
            if (tfsize.getText() != "")
                b.setSize(Integer.parseInt(tfsize.getText()));
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home", "root", "root_123");
            String query = "delete from block where block_id like ? and block_name like ? and no_of_properties like ? and size like ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            if (!tfID.getText().equals("")) preparedStatement.setInt(1, b.getBlock_ID());
            else preparedStatement.setString(1, "%");
            if (!tfname.getText().equals("")) preparedStatement.setString(2, b.getBlock_Name());
            else preparedStatement.setString(2, "%");
            if (!tfno.getText().equals("")) preparedStatement.setInt(3, b.getNo_ofProperties());
            else preparedStatement.setString(3, "%");
            if (!tfsize.getText().equals("")) preparedStatement.setInt(4, b.getSize());
            else preparedStatement.setString(4, "%");
            preparedStatement.execute();
            blockTable.setItems(getBlock());
            tfID.clear();
            tfno.clear();
            tfsize.clear();
            tfname.clear();
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
        block_ID.setCellValueFactory(new PropertyValueFactory<Block, Integer>("Block_ID"));
        Block_Name.setCellValueFactory(new PropertyValueFactory<Block, String>("Block_Name"));
        No_ofProperties.setCellValueFactory(new PropertyValueFactory<Block, Integer>("No_ofProperties"));
        size.setCellValueFactory(new PropertyValueFactory<Block, Integer>("size"));
        blockTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        try {
            blockTable.setItems(getBlock());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Block> getBlock() throws SQLException {
        ObservableList<Block> records = FXCollections.observableArrayList();

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/home","root", "root_123");
        Statement statement = connection.createStatement();
        String query = "select * from block";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()){
            Block b1 = new Block();
            b1.setBlock_ID(rs.getInt(1));
            b1.setBlock_Name(rs.getString(2));
            b1.setNo_ofProperties(rs.getInt(3));
            b1.setSize(rs.getInt(4));

            records.add(b1);
        }
        connection.close();
        return records;
    }
}

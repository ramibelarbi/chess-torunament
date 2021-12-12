package com.example.chess.Conrollers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.EventListener;
import java.util.ResourceBundle;

public class PerdantController{

    @FXML
    private TextField nom;
    @FXML
    private Button valid;
    private ObservableList joueursList;


    public void delete(ActionEvent event)
    {
        Connection conn =getConnection();
        String query1 ="Delete from tornoi where nom=?";
        try {
            PreparedStatement pst = conn.prepareStatement(query1);
            pst.setString(1, nom.getText());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        valid.getScene().getWindow().hide();
    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
            System.out.println("succes");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
}


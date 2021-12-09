package com.example.chess;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.EventListener;

public class PerdantController {

    @FXML
    private TextField nom;

    public void delete(ActionEvent event)
    {
        String query1 ="Delete from tornoi where nom=?";
        try {
            Connection conn=getConnection();
            PreparedStatement pst = conn.prepareStatement(query1);
            pst.setString(1, nom.getText());
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
}


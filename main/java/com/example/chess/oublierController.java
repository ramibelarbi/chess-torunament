package com.example.chess;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class oublierController implements Initializable {

        @FXML
        private TextField Nom;

        @FXML
        private TextField txtadress;

        @FXML
        private Button btnBack;

        @FXML
        private TextField pass;

        @FXML
        private Button valid;
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        ObservableList<joueur> joueursList;
        String ad;
        String pa;


    @FXML
        void GoToPrincipal(ActionEvent event) throws IOException {
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.close();
            Stage primaryStage = new Stage();
            btnBack.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("adminview.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }

        @FXML
        void valid(ActionEvent event) {
        try{
            conn=getConnection();
            String u_nom=Nom.getText().trim();
            String sql ="select cin,nom,adresse from joueur where nom=? and adresse=?";
            pst= conn.prepareStatement(sql);
            System.out.println(u_nom);
            pst.setString(1,u_nom);
            pst.setString(2,txtadress.getText());
            System.out.println(pst.toString());
            rs= pst.executeQuery();
            /*
            if (rs.next())
            {
               /* Nom.setText(rs.getString("nom"));
                ad=rs.getString("adresse");
                pa=rs.getString("cin");
                if(ad.equals(txtadress.getText().trim()))
                System.out.println(rs.toString());
                    pass.setText(rs.getString("cin"));*/
            rs.next();
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        }
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TDoo
        try {
            Connection conn = getConnection();
            System.out.println("con2nn");
            ResultSet rs = conn.createStatement().executeQuery
                    ("SELECT cin,nom,prenom,adresse,numtel FROM joueur");
            System.out.println("con2nn");
        } catch (SQLException ex) {
            System.out.println("erreur");
        }
    }

    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "");
            return conn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;}
    }}

package com.example.chess;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

import com.example.chess.Modele.Match;
import com.example.chess.Modele.Tornoi;
import com.example.chess.Modele.joueur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class classementController implements Initializable {

    @FXML
    private TableColumn<joueur,String> tbadress;

    @FXML
    private TableColumn<joueur,String> tbcin;

    @FXML
    private TableView<joueur> tbjoueur;

    @FXML
    private TableColumn<joueur,String> tbnom;

    @FXML
    private TableColumn<joueur,String> tbnumtel;

    @FXML
    private TableColumn<joueur,String> tbprenom;

    @FXML
    private Button btnBack;
    ObservableList<joueur> joueursList;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TDoo
        showjoueur();
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

    public ObservableList<joueur> getJoueurList() {
        ObservableList<joueur> joueurList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT nom,prenom FROM tornoi";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = ((Statement) st).executeQuery(query);
            joueur books;
            while (rs.next()) {
                books = new joueur("", rs.getString("nom"), rs.getString("prenom"), "", "");
                joueurList.add(books);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return joueurList;
    }
    public ObservableList<joueur> upadateJoueur() {
        ObservableList<joueur> joueurList = getJoueurList();
        ObservableList<joueur> joueurList1 = FXCollections.observableArrayList();
        try {
            Match match = new Match();
            Tornoi t = new Tornoi(joueurList);
            t.matchpool(joueurList);
            for (int i = 0; i < t.matchpool(joueurList).size(); i++) {
                List<Match> match1 = t.matchpool(joueurList);
                joueurList1.add(t.choisirgagnant(match1.get(i)));
            }
            showjoueur();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joueurList1;
    }
        public void showjoueur(){
        ObservableList<joueur> list = getJoueurList();
        tbnom.setCellValueFactory(new PropertyValueFactory<joueur, String>("nom"));
        tbprenom.setCellValueFactory(new PropertyValueFactory<joueur, String>("prenom"));
        tbjoueur.setItems(list);
    }
    @FXML
    void GoToPrincipal(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        btnBack.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("loginuser.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setTitle("                                                                                 LOGIN USERS PAGE");
        primaryStage.getIcons().add(new Image("com/example/chess/images.png"));
    }
    @FXML
    void GoToPrincipal1(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        btnBack.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("loginadmin.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setTitle("                                                                                 LOGIN ADMIN PAGE");
        primaryStage.getIcons().add(new Image("com/example/chess/t??l??chargement (2).png"));
    }
}

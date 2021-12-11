package com.example.chess;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class loginadminController implements Initializable{

    @FXML
    private TextField Adress;

    @FXML
    private TextField CIN;

    @FXML
    private TextField Nom;

    @FXML
    private TextField Prenom;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClassement;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TextField numtel;

    @FXML
    private TableColumn<joueur, String> tbAdress;

    @FXML
    private TableColumn<joueur, String> tbCIN;

    @FXML
    private TableColumn<joueur, String> tbNom;

    @FXML
    private TableColumn<joueur, String> tbNumtel;

    @FXML
    private TableColumn<joueur, String> tbPrenom;

    @FXML
    private TableView<joueur> tbjoueur;
    ObservableList<joueur> joueursList = FXCollections.observableArrayList();

    @FXML
    void voirleclassement(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        btnBack.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("classement.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
    @FXML
    void GoToPrincipal(ActionEvent event) throws IOException {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        btnBack.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminview.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
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

    public ObservableList<joueur> getJoueurList(){
        ObservableList<joueur> joueurList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM joueur";
        Statement st;
        ResultSet rs;

        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            joueur books;
            while(rs.next()){
                books = new joueur(rs.getString("cin"), rs.getString("nom"), rs.getString("prenom"),rs.getString("adresse"), rs.getString("numtel"));
                joueurList.add(books);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return joueurList;
    }

    public void showjoueur(){
        ObservableList<joueur> list = getJoueurList();

        tbCIN.setCellValueFactory(new PropertyValueFactory<joueur, String>("cin"));
        tbNom.setCellValueFactory(new PropertyValueFactory<joueur, String>("nom"));
        tbPrenom.setCellValueFactory(new PropertyValueFactory<joueur, String>("prenom"));
        tbAdress.setCellValueFactory(new PropertyValueFactory<joueur, String>("adresse"));
        tbNumtel.setCellValueFactory(new PropertyValueFactory<joueur, String>("numtel"));


        tbjoueur.setItems(list);
    }

    @FXML
    void ajouterjoueur(ActionEvent event) {
    	PreparedStatement pst;
        String requete="INSERT INTO joueur (cin , nom , prenom ,adresse,numtel)"
                + " VALUES ('"+CIN.getText()+"','"+Nom.getText()+"','"+Prenom.getText()+"',' "+Adress.getText()+"',"
                + "'"+numtel.getText()+"');";  
        try
        {	Connection conn = getConnection();
           pst = conn.prepareStatement(requete);
            pst.executeUpdate(requete);
            
           
        } catch (SQLException ex) {
            System.out.println("Erreur d'ajout !");
        }
        tbjoueur.getItems().clear();
        try {
        	Connection conn = getConnection();
            ResultSet rs = conn.createStatement().executeQuery
            ("SELECT cin,nom,prenom,adresse,numtel FROM joueur");
            
            while(rs.next())
            {
            	joueursList.add(new joueur(rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"),rs.getString("numtel")));

            }
           
        }catch (SQLException ex) {
        	System.out.print("Error");
        }
    	showjoueur();
 }
    public void modifierjoueur(ActionEvent event)
    {
        try
        {
            Connection conn=getConnection();

            String value1=CIN.getText();
            String value2=Nom.getText();
            String value3=Prenom.getText();
            String value4=Adress.getText();
            String value5=numtel.getText();
            String sql = "UPDATE joueur set cin= '"+value1+"',nom= '"+value2+"',prenom= '"+
                value3+"',adresse= '"+value4+"',numtel= '"+value5+"' where cin='"+value1+"' ";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            showjoueur();
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void supprimerjoueur(ActionEvent event)
    {
        Connection conn =getConnection();
        String sql = "DELETE from joueur where cin = ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, CIN.getText());
            pst.execute();
            showjoueur();
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



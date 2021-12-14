package com.example.chess;

import com.example.chess.Modele.joueur;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.PasswordField;

import javafx.scene.control.Hyperlink;



public class adminviewController{
	@FXML
	private TextField userid;
	@FXML
	private PasswordField password;
	@FXML
	private Button btnLogIn;
	@FXML
	private Button btnBack;
	@FXML
	private Hyperlink link;
	@FXML
	private Label wronglogin;
	Connection conn;
	PreparedStatement pst;
	ResultSet rs;
	ObservableList<joueur> joueursList;

	// Event Listener on Button[#btnLogIn].onAction
	public void login(ActionEvent event) throws IOException {
		Stage primaryStage = new Stage();
		if (userid.getText().toString().equals("superadmin") && password.getText().toString().equals("superadmin")) {
			btnLogIn.getScene().getWindow().hide();
			Parent root=FXMLLoader.load(getClass().getResource("loginadmin.fxml"));
			Scene scene= new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("                                                                                 LOGIN ADMIN PAGE");
			primaryStage.getIcons().add(new Image("com/example/chess/téléchargement (2).png"));

        }
		else if (userid.getText().isEmpty() && password.getText().isEmpty()) {
			wronglogin.setText("write something please! ");
		}
		else
		{
			try
			{
				conn=getConnection();
				pst=conn.prepareStatement("Select * from joueur where nom=? and cin=?");
				pst.setString(1,userid.getText());
				pst.setString(2,password.getText());
				rs= pst.executeQuery();
				if (rs.next())
				{
					btnLogIn.getScene().getWindow().hide();
					Parent root=FXMLLoader.load(getClass().getResource("loginuser.fxml"));
					Scene scene= new Scene(root);
					primaryStage.setScene(scene);
					primaryStage.show();
					primaryStage.setTitle("                                                                                 LOGIN USERS PAGE");
					primaryStage.getIcons().add(new Image("com/example/chess/images.png"));
				}
				else
				{
					wronglogin.setText("login failed");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			while(rs.next())
			{
				joueursList.add(new joueur(rs.getString("cin"),rs.getString("nom"),rs.getString("prenom"),rs.getString("adresse"),rs.getString("numtel")));
			}

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
			return null;
		}
	}
	void setlink(ActionEvent event) {

	}

}

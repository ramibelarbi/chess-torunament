package com.example.chess;

import java.io.IOException;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class loginuserController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnCLASSEMENT;

    @FXML
    private Button btnJOUER;

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
    void play(ActionEvent event) throws IOException {
        // hedha ysir mba3ed matoufa il game donc inti implements game lina
        // w chouf
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        btnBack.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Perdant.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
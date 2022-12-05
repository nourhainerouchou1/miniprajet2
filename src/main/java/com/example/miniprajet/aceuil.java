package com.example.miniprajet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.StageStyle;

import java.io.IOException;

public class aceuil {

    @FXML
    void onDate(ActionEvent event) {

    }
    @FXML
    void handlcontactas(ActionEvent event) {
        startWindow();
    }
    private void startWindow() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("splach.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleAction(ActionEvent event) {
        secretairewindow();
    }


    private void secretairewindow(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("espacesecretaire.fxml"));
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    void gotoplanning(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("schedule.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void gotoRDV(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("espacesecretaire2.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    void gotoabout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("aboutas.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public void gotoP(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("espacesecretaire.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

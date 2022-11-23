package com.example.miniprajet;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controlermed {

    @FXML
    private AnchorPane ap;

    @FXML
    private BorderPane bq;

    @FXML
    void dossiermedicale(MouseEvent event) {
   bq.setCenter(ap);
    }

    @FXML
    void planning(MouseEvent event) {
     loadPage("planning");
    }
private void loadPage(String page){
    Parent root = null;
    try {
        root = FXMLLoader.load(getClass().getResource(page+".fxml"));
    } catch (IOException ex){
        Logger.getLogger(controlermed.class.getName()).log(Level.SEVERE,null,ex);
    }
    bq.setCenter(root);
    }
}


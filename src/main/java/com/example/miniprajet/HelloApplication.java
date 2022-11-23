package com.example.miniprajet;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.PasswordField;
import java.io.IOException;

public class HelloApplication extends Application {
    public static final String USERNAME ="Admin";
    public static final String PASSWORD ="123";

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));


        Stage splsh=new Stage();
        FXMLLoader Loader = new FXMLLoader(getClass().getResource("splach.fxml"));
        Parent root1=Loader.load();
        Scene s=new Scene(root1);
        splsh.setScene(s);
        splsh.show();
        // attendre 2 sec puis afficher le stage1
        // CREE UN THREAD EN ARRIERE
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                //ici traitement a faire
                try {
                    Thread.sleep(2000);//endormit 2s
                } catch (InterruptedException e ){
                    throw new RuntimeException();
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        splsh.hide();
                        stage.show();
                    }
                });
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package com.example.miniprajet;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import java.sql.Connection;
import java.sql.PreparedStatement;



import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btncross;
    @FXML
    private Hyperlink labelsignup;
    @FXML
    private Hyperlink lblforget;
    @FXML
    private Button btnlogin;

    @FXML
    private TextField tfusername;
    @FXML
    private Button btnhide;

    @FXML
    private PasswordField pfpassword;



    @FXML
    private Label errormsglabel;
    @FXML
    private Button btnmed;

    @FXML
    private Button btnsec;

    @FXML
    void setOnMouseClicked(ActionEvent event) {
        System.exit( 0);
    }
    @FXML
    void MouseClicked(ActionEvent event) {
        startHome();
    }
    @FXML
    void MouseClick(ActionEvent event) {
        startWindow();
    }
    @FXML
    void handleButtonAction(ActionEvent event) throws SQLException {
        errorMessage = "";
        if (isFieldFilled() && isValid()){
            startHomeWindow();
        }
    }
    private String errorMessage = "";
    private  boolean isFieldFilled() {
        boolean isFilled = true;
        if (tfusername.getText().isEmpty()){
            isFilled = false;
            errorMessage = "Username is Empty!";
        }
        if (pfpassword.getText().isEmpty()){
            isFilled = false;
            if ( (errorMessage.isEmpty())){
                errorMessage="Password is Empty!";
            } else {
                errorMessage += "\nPassword is Empty!";
            }
        }
     errormsglabel.setText(errorMessage);
        return  isFilled;
    }
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
   private boolean isValid() throws SQLException {
        String username=tfusername.getText();
        String mp=pfpassword.getText();

       con= MaConnexion.connecter();
         pst= con.prepareStatement("select * from login where username=? and password =?" );
         pst.setString(1, username);
         pst.setString(2,mp);
         rs = pst.executeQuery();
         if (rs.next())
         {
             errorMessage = "Login Success";
             System.out.println(errorMessage);
             startHomeWindow();

         }
         else
         {
             errorMessage = "Login Failed";
             tfusername.setText("");
             pfpassword.setText("");
             tfusername.requestFocus();


         }


       boolean isValid = true;
     if (!tfusername.getText().equals(HelloApplication.USERNAME)){
         isValid = false;
         errorMessage = "Invalid Username!";
     }
     if (!pfpassword.getText().equals(HelloApplication.PASSWORD)){
         isValid = false;
         if (errorMessage.isEmpty()){
             errorMessage = "Invalid Password!";
         }else {
             errorMessage +="\nInvalid Password!";
         }
     }
     errormsglabel.setText((errorMessage));
     return isValid;
   }
    private void startHome(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("forgetpass.fxml"));
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void startWindow(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("createAccount.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
   private void startHomeWindow(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("page3.fxml"));
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
   }

    @Override
    public void initialize(URL location , ResourceBundle resource) {


    }

    public void handlSignUp(ActionEvent event) {
       startWindow();
    }

    public void handlForget(ActionEvent event) {
       startHome();
    }
}

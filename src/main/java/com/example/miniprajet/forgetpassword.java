package com.example.miniprajet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class forgetpassword implements Initializable {
    ObservableList<rendezvous> data = FXCollections.observableArrayList();
    @FXML
    private CheckBox checkbox1;

    @FXML
    private CheckBox checkbox2;

    @FXML
    private TextField txtpass;

    @FXML
    private TextField txtpass2;
    @FXML
    private PasswordField passf;
    @FXML
    private PasswordField passc;
    @FXML
    private TextField txtuser;



    @FXML
    void changevisibility1(ActionEvent event) {
        if (checkbox1.isSelected()){
            txtpass.setText(passf.getText());
            txtpass.setVisible(true);

            return;
        }
        passf.setText(txtpass.getText());
        passf.setVisible(true);
        txtpass.setVisible(false);
    }

    @FXML
    void changevisibility2(ActionEvent event) {
        if (checkbox2.isSelected()){
            txtpass2.setText(passc.getText());
            txtpass2.setVisible(true);

            return;
        }
        passc.setText(txtpass2.getText());
        passc.setVisible(true);
        txtpass2.setVisible(false);
    }
    @FXML
    void resetpassword(ActionEvent event) {

        String user=txtuser.getText();
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Reset your Password ");
        dialog.setContentText("Please enter your new password:");
        Connection con=MaConnexion.connecter();

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        String req="update login set password = '"+ result.get()+ "' where username = " + user + ";";
        try {
            PreparedStatement ps=con.prepareStatement(req);

            int a=ps.executeUpdate();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }


}

    private void initialize(Object o, Object o1) {
        Connection con = MaConnexion.connecter();
        data.clear();
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from rendezvous");
            while (rs.next()) {
                // parcours des données et insertion fil ddata
                data.add(new rendezvous(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection con = MaConnexion.connecter();
        data.clear();
        try {
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from login");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

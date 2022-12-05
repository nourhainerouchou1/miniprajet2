package com.example.miniprajet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class schedule implements Initializable  {
    ObservableList<rendezvous> data = FXCollections.observableArrayList();


    @FXML
    private DatePicker datepick;

    @FXML
    private TableColumn<rendezvous, String> commentairecol;
    @FXML
    private TableColumn<rendezvous, Integer> idcol;


    @FXML
    private TableColumn<rendezvous, String> datecol;
    @FXML
    private TableColumn<rendezvous, String> heurecol;

    @FXML
    private TableColumn<rendezvous, String> nomcol;

    @FXML
    private TableColumn<rendezvous, String> prenomcol;

    @FXML
    private TableView<rendezvous> tablerdv;

    @FXML
    private Button btnajouter;

    @FXML
    private Button btnupdate;



    @FXML
    void pick(ActionEvent event) {
        LocalDate mydate = datepick.getValue();

        String date=String.valueOf(mydate);
        System.out.println(date);
        if (mydate == null) {
            initialize(null,null);
        } else {
            Connection con = MaConnexion.connecter();
            String req = "select *  from rendezvous where daterdv like '%" + date + "%' ";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                data.clear();
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
                tablerdv.refresh();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @FXML
    void ajouterRDV(ActionEvent event) {

    }
    @FXML
    void deleteRDV(ActionEvent event) {

    }

    @FXML
    void updateRDV(ActionEvent event) {

    }
    @FXML
    void contact (ActionEvent event) {
        startWindo();
    }
    private void startWindo() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("splach.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        tablerdv.setItems(data);
        idcol.setCellValueFactory(new PropertyValueFactory<>("idrdv"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomcol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("daterdv"));
        heurecol.setCellValueFactory(new PropertyValueFactory<>("heurerdv"));
        commentairecol.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

    }
}


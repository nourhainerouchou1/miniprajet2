package com.example.miniprajet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class controllersecretaire implements Initializable {


    ObservableList<Patient> data= FXCollections.observableArrayList();

    // loula lclass eli chtaffichih eli houa patient e7na w thani type mte3 colone
    @FXML
    private TableColumn<Patient, String> adresseCol;

    @FXML
    private AnchorPane bq;

    @FXML
    private TableColumn<Patient, String> datenaissanceCol;

    @FXML
    private TableColumn<Patient, Integer> idpatientCol;

    @FXML
    private TableColumn<Patient, String> nomCol;

    @FXML
    private TableColumn<Patient, String> prenomCol;

    @FXML
    private TableColumn<Patient, String> professionCol;

    @FXML
    private TableColumn<Patient, String> sexeCol;

    @FXML
    private TableView<Patient> tableViewpatient;

    @FXML
    private TableColumn<Patient, String> telCol;
    @FXML
    private RadioButton br_femme;

    @FXML
    private RadioButton br_homme;
    @FXML
    private TextField tf_adresse;

    @FXML
    private TextField tf_datenaissance;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_nom;

    @FXML
    private TextField tf_numtel;

    @FXML
    private TextField tf_prenom;

    @FXML
    private TextField tf_profession;
    @FXML
    private Button btncherche;

    @FXML
    private Button btnclear;
    @FXML
    private TextField tf_rech;
    @FXML
    void handlcobtactas(ActionEvent event) {
        startWindow();
    }
    @FXML
    void Home(ActionEvent event) {

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
    Integer index;
    @FXML
    void getItem(MouseEvent event) {
        index = tableViewpatient.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        tf_id.setText(idpatientCol.getCellData(index).toString());
        tf_nom.setText(nomCol.getCellData(index).toString());
        tf_prenom.setText(prenomCol.getCellData(index).toString());
        tf_adresse.setText(adresseCol.getCellData(index).toString());
        tf_numtel.setText(telCol.getCellData(index).toString());
        tf_datenaissance.setText(datenaissanceCol.getCellData(index).toString());
        tf_profession.setText(professionCol.getCellData(index).toString());


    }

    @FXML
    void gotoPage2(ActionEvent event) throws IOException {


    }
    @FXML
    void chercher(KeyEvent event) {
        String nom = tf_rech.getText();
        if (nom.isEmpty()) {
            initialize(null,null);
        } else {
            Connection con = MaConnexion.connecter();
            String req = "select * from patient where nom like '%" + nom + "%' ";
            try {
                PreparedStatement ps = con.prepareStatement(req);
                ResultSet rs = ps.executeQuery();
                data.clear();
                while (rs.next())
                {
                    // parcours des données et insertion fil ddata
                    data.add(new Patient(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7),
                            rs.getString(8)));
                }
                tableViewpatient.refresh();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @FXML
    void clear(ActionEvent event) {
        tf_id.setText("");
        tf_nom.setText("");
        tf_prenom.setText("");
        tf_datenaissance.setText("");
        tf_profession.setText("");
        tf_numtel.setText("");
        tf_adresse.setText("");
    }
    @FXML
    void modifierPatient(ActionEvent event) {
        int id=Integer.parseInt(tf_id.getText());
        String nom=tf_nom.getText();
        String prenom=tf_prenom.getText();
        String adr=tf_adresse.getText();
        String num=tf_numtel.getText();
        String pro=tf_profession.getText();
        String date=tf_datenaissance.getText();

        Connection con=MaConnexion.connecter();
        String req="update patient set idpatient= "+id+", nom='"+nom+"' , prenom='"+prenom+"'," + " adresse='"+adr+"', numtel='"+num+"', datenaissance='"+date+"', profession='"+pro+"' where idpatient="+id+";";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.execute();

            initialize(null,null);
            tableViewpatient.refresh();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    @FXML
    void deletepatient(ActionEvent event) {
        int id=Integer.parseInt(tf_id.getText());

        Connection con=MaConnexion.connecter();
        String req="delete from patient where idpatient = "+id+"";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.executeUpdate();
            initialize(null,null);
            tableViewpatient.refresh();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    @FXML
    void ajouterPatient(ActionEvent e){

        int id=Integer.parseInt(tf_id.getText());
        String nom=tf_nom.getText();
        String prenom=tf_prenom.getText();
        String adr=tf_adresse.getText();
        String num=tf_numtel.getText();
        String pro=tf_profession.getText();
        String date=tf_datenaissance.getText();
        String sexe="homme";
        if(br_femme.isSelected())
            sexe=br_femme.getText();


        Connection con=MaConnexion.connecter();
        String req="insert into patient values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(req);
            ps.setInt(1,id);
            ps.setString(2,nom);
            ps.setString(3,prenom);
            ps.setString(4,adr);
            ps.setString(5,num);
            ps.setString(6,sexe);
            ps.setString(7,date);
            ps.setString(8,pro);

            int a=ps.executeUpdate();
            if(a>0)
            {
                Patient p=new Patient(id,nom,prenom,adr,num,sexe,date,pro);

                data.add(p);
                tableViewpatient.refresh();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    private void loadPage(String page){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex){
            Logger.getLogger(controlermed.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // hna njibou les donnnees w naffihchihom
        //n7adrou tableview kbel les données


        Connection con=MaConnexion.connecter();
        try {
            Statement st=con.createStatement();

            ResultSet rs=st.executeQuery("select * from patient");
            while (rs.next())
            {
                // parcours des données et insertion fil ddata
                data.add(new Patient(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        tableViewpatient.setItems(data);

        idpatientCol.setCellValueFactory(new PropertyValueFactory<>("idpatient"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        datenaissanceCol.setCellValueFactory(new PropertyValueFactory<>("datenaissance"));
        sexeCol.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        telCol.setCellValueFactory(new PropertyValueFactory<>("numtel"));
        professionCol.setCellValueFactory(new PropertyValueFactory<>("profession"));



    }
}

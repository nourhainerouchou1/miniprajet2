package com.example.miniprajet;

public class rendezvous {
    int idrdv;
    String nom;
    String prenom;
    String daterdv;
    String heurerdv;
    String commentaire;

    public rendezvous(int idrdv, String nom, String prenom, String daterdv, String heurerdv, String commentaire) {
        this.idrdv = idrdv;
        this.nom = nom;
        this.prenom = prenom;
        this.daterdv = daterdv;
        this.heurerdv = heurerdv;
        this.commentaire = commentaire;
    }

    public int getIdrdv() {
        return idrdv;
    }

    public void setIdrdv(int idrdv) {
        this.idrdv = idrdv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDaterdv() {
        return daterdv;
    }

    public void setDaterdv(String daterdv) {
        this.daterdv = daterdv;
    }

    public String getHeurerdv() {
        return heurerdv;
    }

    public void setHeurerdv(String heurerdv) {
        this.heurerdv = heurerdv;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
}


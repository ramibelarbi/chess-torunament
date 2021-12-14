package com.example.chess.Modele;


public class Match {
    private joueur joueurech;
    private joueur adversaire;
    private joueur gagnant;
    private joueur perdant;

    public Match(joueur joueurech, joueur adversaire, joueur gagnant, joueur perdant) {
        this.joueurech = joueurech;
        this.adversaire = adversaire;
        this.gagnant = gagnant;
        this.perdant = perdant;
    }

    public Match() {

    }

    public void setJoueurech(joueur joueurech) {
        this.joueurech = joueurech;
    }

    public void setAdversaire(joueur adversaire) {
        this.adversaire = adversaire;
    }

    public void setGagnant(joueur gagnant) {
        this.gagnant = gagnant;
    }

    public void setPerdant() {
        if (this.joueurech.equals(this.gagnant))
        {
            this.perdant=this.adversaire;
        }
        else
        {
            this.perdant=this.joueurech;
        }
    }

    public joueur getJoueurech() {
        return joueurech;
    }

    public joueur getAdversaire() {
        return adversaire;
    }

    public joueur getGagnant() {
        return gagnant;
    }

    public joueur getPerdant() {
        return perdant;
    }
}

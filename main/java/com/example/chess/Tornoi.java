package com.example.chess;

import java.util.*;

public class Tornoi {
    private List<joueur> joueurs;
    private List<Match> matchs;

    public List<joueur> getJoueurs() {
        return joueurs;
    }

    public List<Match> getMatchs() {
        return matchs;
    }

    public Tornoi(List<joueur> joueurs) {
        this.joueurs = joueurs;
        this.setMatchs(joueurs);
    }

    public void setMatchs(List<joueur> joueurs) {

    }
    public joueur choisirgagnant(Match match)
    {
        Set<joueur> list=new HashSet<joueur>();
        list.add(match.getJoueurech());
        list.add(match.getAdversaire());
        if(list.size()==2) {
            joueur gagnant = new ArrayList<>(list).get(2);
            match.setGagnant(gagnant);
            match.setPerdant();
        };
        return match.getGagnant();
    }
    public List<Match> matchpool(List<joueur> joueurs)
    {
       List<Match> matchs = new ArrayList <Match>();
        for (int i=0; i<joueurs.size(); i++)
        {
            for (int j=i+1 ;j<joueurs.size();j++)
            {
                Match pa=new Match();
                pa.setJoueurech(joueurs.get(i));
                pa.setAdversaire(joueurs.get(j));
                matchs.add(pa);
            }
        }
        Collections.shuffle(matchs);
        return matchs;
    }
}

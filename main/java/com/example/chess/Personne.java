package com.example.chess;

public class Personne {
	protected String nom;
	protected String prenom;
	protected String CIN;
	protected String adresse;
	protected String numtel;
	
	
	public Personne(String nom,String prenom,String CIN,String adresse,String numtel) {
		this.nom=nom;
		this.prenom=prenom;
		this.CIN=CIN;
		this.adresse=adresse;
		this.numtel=numtel;
	}
	public String getnom()
	{
		return nom;
	}
	public String getprenom()
	{
		return prenom;
	}
	public String getCIN()
	{
		return CIN;
	}
	public String getadresse()
	{
		return adresse;
	}
	public String getnumtel()
	{
		return numtel;
	}
	public void setnom(String nom)
	{
		this.nom=nom;
	}
	public void setprenom(String prenom)
	{
		this.prenom=prenom;
	}
	public void setCIN(String CIN)
	{
		this.CIN=CIN;
	}
	public void setadresse(String adresse)
	{
		this.adresse=adresse;
	}
	public void setnumtel(String numtel)
	{
		this.numtel=numtel;
	}

}

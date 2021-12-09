package com.example.chess;


public class joueur{
	

	private String cin;
	private String nom;
	private String prenom;
	private String adress;
	private String numtel;
	
	
	public joueur(String cin,String nom,String prenom,String adress,String numtel) {
		this.cin=cin;
		this.nom=nom;
		this.prenom=prenom;
		this.adress=adress;
		this.numtel=numtel;
	}
	public String getNom()
	{
		return nom;
	}
	public String getPrenom()
	{
		return prenom;
	}
	public String getCin()
	{
		return cin;
	}
	public String getAdresse()
	{
		return adress;
	}
	public String getNumtel()
	{
		return numtel;
	}
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	public void setPrenom(String prenom)
	{
		this.prenom=prenom;
	}
	public void setCin(String cin)
	{
		this.cin=cin;
	}
	public void setAdresse(String adress)
	{
		this.adress=adress;
	}
	public void setNumtel(String numtel)
	{
		this.numtel=numtel;
	}

}


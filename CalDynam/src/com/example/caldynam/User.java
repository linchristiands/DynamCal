package com.example.caldynam;

import java.util.ArrayList;

public class User {

	public static User currentUser;
	
	private String nom;
	private String prenom;
	private int taille;
	private float poids;
	private ArrayList<Entry> liste;
	
	public User(){	}
	
	public User(String n, String p, int t, float po){
		setNom(n);
		setPrenom(p);
		setTaille(t);
		setPoids(po);
		setListe(new ArrayList<Entry>());
	}

	public float getPoids() {
		return poids;
	}

	public void setPoids(float poids) {
		this.poids = poids;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Entry> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Entry> liste) {
		this.liste = liste;
	}
	
	public String getKey(){
		return nom+" "+prenom;
	}
	
	@Override
	public String toString(){
		String s =  nom + ";"+prenom+";"+ String.valueOf(taille)+";"+String.valueOf(poids);
		for(Entry ent : liste){
			s = s+";"+ent.toString();
		}
		return s;
	}
}

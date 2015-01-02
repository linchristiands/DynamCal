package com.example.caldynam;

public class User {

	public static User currentUser;

	private String nom;
	private String prenom;
	private int taille;
	private float poids;

	public User() {
	}

	public User(String n, String p, int t, float po) {
		setNom(n);
		setPrenom(p);
		setTaille(t);
		setPoids(po);
	}

	public User(String[] parts) {
		setNom(parts[0]);
		setPrenom(parts[1]);
		setTaille(Integer.parseInt(parts[2]));
		setPoids(Float.parseFloat(parts[3]));
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

	public String getKey() {
		return nom + " " + prenom;
	}

	@Override
	public String toString() {
		String s = nom + ";" + prenom + ";" + String.valueOf(taille) + ";"
				+ String.valueOf(poids);
		return s;
	}
}

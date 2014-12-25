package com.example.caldynam;

import org.json.JSONException;
import org.json.JSONObject;

public class Aliment {

	private String nom;
	private float nbCalorie;
	
	public Aliment(String nom,float calorie)
	{
		this.nom=nom;
		this.nbCalorie=calorie;
	}	
	public Aliment(JSONObject al) throws JSONException
	{
		nom=al.getString("item_name");
	}
	public Aliment(String str) {
		String[] parts = str.split("-");
		nom = parts[0];
		nbCalorie = Float.parseFloat(parts[1]);
	}
	public String getName()
	{
		return nom;
	}
	public float getCal()
	{
		return nbCalorie;
	}
	
	public String getCalString()
	{
		return Float.toString(nbCalorie);
	}
	
	@Override
	public String toString() {
		return nom+"-"+nbCalorie;
	}

}

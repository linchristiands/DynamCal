package com.example.caldynam;

import org.json.JSONException;
import org.json.JSONObject;

public class Aliment {

	private String nom;
	private float nbCalorie;
	
	public Aliment(JSONObject al) throws JSONException
	{
		nom=al.getString("item_name");
	}
	public String getName()
	{
		return nom;
	}
	public float getCal()
	{
		return nbCalorie;
	}
	
	public Aliment()
	{
	}	
}

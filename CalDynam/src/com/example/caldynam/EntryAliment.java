package com.example.caldynam;

import java.util.ArrayList;

public class EntryAliment {

	private String username; //format: nom prenom
	private float total;
	private ArrayList<Aliment> liste;
	private int day, month, year;

	public EntryAliment() {

	}

	public EntryAliment(String username, float total, int d, int m, int y, ArrayList<Aliment> liste) {
		this.username = username;
		this.total = total;
		day = d;
		month = m;
		year = y;
		this.liste = liste;
	}
	
	public EntryAliment(String str){
		String[] parts = str.split(";");
		username = parts[0];
		total = Float.parseFloat(parts[1]);
		day = Integer.parseInt(parts[2]);
		month = Integer.parseInt(parts[3]);
		year = Integer.parseInt(parts[4]);
		for(int i = 5;i<parts.length;i++){
			Aliment a = new Aliment(parts[i]);
			liste.add(a);
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getUsername() {
		return username;
	}

	public ArrayList<Aliment> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Aliment> liste) {
		this.liste = liste;
	}
	
	@Override
	public String toString() {
		String s = username+";"+String.valueOf(total)+";"+String.valueOf(day)+";"+String.valueOf(month)+";"+String.valueOf(year);
		for(Aliment al : liste){
			s =s+";"+al.toString();
		}
		return s;
	}
}

package com.example.caldynam;

public class Entry {

	private float totalIN;
	private float totalOUT;
	private int day, month, year;

	public Entry() {

	}

	public Entry(int d, int m, int y, int in, int out) {
		setTotalIN(in);
		setTotalOUT(out);
		setDay(d);
		setMonth(m);
		setYear(y);
	}
	
	public Entry(String str){
		String[] parts = str.split("-");
		totalIN = Float.parseFloat(parts[0]);
		totalOUT = Float.parseFloat(parts[1]);
		day = Integer.parseInt(parts[2]);
		month = Integer.parseInt(parts[3]);
		year = Integer.parseInt(parts[4]);
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

	public float getTotalOUT() {
		return totalOUT;
	}

	public void setTotalOUT(float totalOUT) {
		this.totalOUT = totalOUT;
	}

	public float getTotalIN() {
		return totalIN;
	}

	public void setTotalIN(float totalIN) {
		this.totalIN = totalIN;
	}

	@Override
	public String toString() {
		return String.valueOf(totalIN) + "-" + String.valueOf(totalOUT) + "-"
				+ day + "-" + month + "-" + year;
	}
}

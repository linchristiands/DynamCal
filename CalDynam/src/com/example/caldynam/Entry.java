package com.example.caldynam;

public class Entry {

	private int totalIN;
	private int totalOUT;
	private int day, month, year;
	
	public Entry(){
		
	}
	
	public Entry(int d, int m, int y, int in, int out){
		setTotalIN(in);
		setTotalOUT(out);
		setDay(d);
		setMonth(m);
		setYear(y);
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

	public int getTotalOUT() {
		return totalOUT;
	}

	public void setTotalOUT(int totalOUT) {
		this.totalOUT = totalOUT;
	}

	public int getTotalIN() {
		return totalIN;
	}

	public void setTotalIN(int totalIN) {
		this.totalIN = totalIN;
	}
}


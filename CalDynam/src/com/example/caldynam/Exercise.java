package com.example.caldynam;

public class Exercise {

	private float calorieUsed;
	private String exerciseName;
	
	public Exercise(float cal,String nom)
	{
		calorieUsed=cal;
		exerciseName=nom;
	}
	public Exercise(String str) {
		String[] parts = str.split("-");
		exerciseName = parts[0];
		calorieUsed = Float.parseFloat(parts[1]);
		
	}
	public String getName()
	{
		return exerciseName;
	}
	public float calorieUsed()
	{
		return calorieUsed;
	}
	
	@Override
	public String toString() {
		return exerciseName+"-"+calorieUsed;
	}
}

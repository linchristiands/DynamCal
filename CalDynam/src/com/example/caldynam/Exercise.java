package com.example.caldynam;

public class Exercise {

	private float calorieUsed;
	private String exerciseName;
	
	public Exercise(float cal,String nom)
	{
		calorieUsed=cal;
		exerciseName=nom;
	}
	public String getName()
	{
		return exerciseName;
	}
	public float calorieUsed()
	{
		return calorieUsed;
	}
}

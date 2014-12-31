package com.example.caldynam;

public class Exercise {

	private float calorieUsed;
	private String exerciseName;
	
	public Exercise(String nom,float cal)
	{
		calorieUsed=cal;
		exerciseName=nom;
	}
	public Exercise(String str) {
		String[] parts = str.split("#");
		exerciseName = parts[0];
		calorieUsed = Float.parseFloat(parts[1]);
		
	}
	public Exercise()
	{
		
	}
	public String getName()
	{
		return exerciseName;
	}
	public float calorieUsed()
	{
		return calorieUsed;
	}
	
	public void setName(String name)
	{
		this.exerciseName=name;
	}
	
	public void setCal(float cal)
	{
		this.calorieUsed=cal;
	}
	
	public void setCalAndName(String name,float cal)
	{
		this.exerciseName=name;
		this.calorieUsed=cal;
	}
	@Override
	public String toString() {
		return exerciseName+"#"+calorieUsed;
	}
}

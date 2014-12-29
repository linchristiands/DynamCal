package com.example.caldynam;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ExerciseListAdapter extends ArrayAdapter<Exercise>{
	
	public ExerciseListAdapter(Context context, ArrayList<Exercise> e) 
	{
	       super(context, 0, e);   
	}
	
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	       // Get the data item for this position
	       Exercise e = getItem(position);    
	       // Check if an existing view is being reused, otherwise inflate the view
	       if (convertView == null) {
	          convertView = LayoutInflater.from(getContext()).inflate(R.layout.ex_list_adapter, parent, false);
	       }
	       // Lookup view for data population
	       TextView ExName = (TextView) convertView.findViewById(R.id.ExName);
	       TextView CalorieEx = (TextView) convertView.findViewById(R.id.CalorieEx);
	       Button b =(Button)convertView.findViewById(R.id.addAlButton);
	       // Populate the data into the template view using the data object
	       ExName.setText(e.getName());
	       CalorieEx.setText(e.calorieUsed()+" calories");
	       // Return the completed view to render on screen
	       return convertView;
	   }
}

package com.example.caldynam;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AlimListAdapter extends ArrayAdapter<Aliment>{
	
	public AlimListAdapter(Context context, ArrayList<Aliment> alim) 
	{
	       super(context, 0, alim);   
	}
	
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	       // Get the data item for this position
	       Aliment a = getItem(position);    
	       // Check if an existing view is being reused, otherwise inflate the view
	       if (convertView == null) {
	          convertView = LayoutInflater.from(getContext()).inflate(R.layout.alim_list_adapter, parent, false);
	       }
	       // Lookup view for data population
	       TextView AlimName = (TextView) convertView.findViewById(R.id.AlimName);
	       TextView CalorieAlim = (TextView) convertView.findViewById(R.id.CalorieAlim);
	       // Populate the data into the template view using the data object
	       AlimName.setText(a.getName());
	       CalorieAlim.setText(a.getCalString()+" calories");
	       // Return the completed view to render on screen
	       return convertView;
	   }
}

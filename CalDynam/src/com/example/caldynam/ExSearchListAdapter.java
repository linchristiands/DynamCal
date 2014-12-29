package com.example.caldynam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.caldynam.MainActivity.Globalvar;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ExSearchListAdapter extends ArrayAdapter<Exercise>{

	private EditText editTime;
	public ExSearchListAdapter(Context context, ArrayList<Exercise> alim) 
	{
	       super(context, 0, alim);   
	}
	
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	       // Get the data item for this position
	       final Exercise e = getItem(position);

	       // Check if an existing view is being reused, otherwise inflate the view
	       if (convertView == null) {
	          convertView = LayoutInflater.from(getContext()).inflate(R.layout.ex_search_list_adapter, parent, false);
	       }
	       // Lookup view for data population
	       editTime=(EditText)convertView.findViewById(R.id.exerciseTimeEdit);
	       TextView ExName = (TextView) convertView.findViewById(R.id.ExSearchName);
	       Button b = (Button)convertView.findViewById(R.id.addExButton);
	      
	       b.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            	 String duration=editTime.getText().toString();
	            	 if(!duration.equals(""))
	            	 {
		            	 Exercise ne = new Exercise();
		            	 ne.setName(e.getName());
		            	 ne.setCal(Float.parseFloat(duration)*User.currentUser.getPoids()/e.calorieUsed());
		            	 Globalvar.userListExercise.add(ne);
		            	 Globalvar.ExAct.setAdapterView();
	            	 }
	            	 else
	            	 {
	            		 Toast.makeText(getContext(),"Please enter a duration" , Toast.LENGTH_SHORT).show();
	            	 }
	             }
	         });

	       // Populate the data into the template view using the data object
	       ExName.setText(e.getName());
	       // Return the completed view to render on screen
	       return convertView;
	   }
}

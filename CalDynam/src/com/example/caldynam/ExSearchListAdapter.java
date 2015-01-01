package com.example.caldynam;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caldynam.MainActivity.Globalvar;

public class ExSearchListAdapter extends ArrayAdapter<Exercise>{

	private EditText editTime;
	private String qtyString="";
	private float quantityTime;
	public ExSearchListAdapter(Context context, ArrayList<Exercise> alim) 
	{
	       super(context, 0, alim);   
	}
	
	 @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
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
	       editTime.setTag(position);
	       editTime.addTextChangedListener(new MyTextWatcher(convertView));
	       b.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            	 if(!qtyString.equals("")){
	            		 quantityTime=Float.parseFloat(qtyString);
		            	 Exercise ne = new Exercise();
		            	 ne.setName(e.getName());
		            	 Float calorie=quantityTime*User.currentUser.getPoids()/e.calorieUsed();
		            	 calorie=precision(2, calorie);
		            	 ne.setCal(calorie);
		            	 Globalvar.userListExercise.add(ne);
		            	 Globalvar.ExAct.setAdapterView();
	            	 }
	            	 else
	            		 Toast.makeText(getContext(),"Please enter a duration" , Toast.LENGTH_SHORT).show();
	             }
	         });

	       // Populate the data into the template view using the data object
	       ExName.setText(e.getName());
	       // Return the completed view to render on screen
	       return convertView;
	   }
	 private class MyTextWatcher implements TextWatcher{
		 
		  private View view;
		  private MyTextWatcher(View view) {
		   this.view = view;
		  }
		 
		  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		   //do nothing
		  }
		  public void onTextChanged(CharSequence s, int start, int before, int count) {
		   //do nothing
		  }
		  public void afterTextChanged(Editable s) {
		    
		   qtyString = s.toString().trim();
		  }
		 }
	 public Float precision(int decimalPlace, Float d) {

		    BigDecimal bd = new BigDecimal(Float.toString(d));
		    bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		    return bd.floatValue();
		  }
	 
}

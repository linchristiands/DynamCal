package com.example.caldynam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
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
	private float out;
	private String qtyString;
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
		            	 out=ne.calorieUsed();
		            	 String entryExpt=ne.getName()+"#"+calorie.toString()+":";
		            	 Globalvar.ExAct.setEntry(entryExpt);
		            	 Globalvar.ExAct.setTotalOut(out);
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

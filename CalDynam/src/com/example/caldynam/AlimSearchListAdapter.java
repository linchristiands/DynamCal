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
import android.widget.TextView;
import android.widget.Toast;

public class AlimSearchListAdapter extends ArrayAdapter<String>{
	
	String foodname;
	String foodID;
	String entry="";
	public AlimSearchListAdapter(Context context, ArrayList<String> alim) 
	{
	       super(context, 0, alim);   
	}
	
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	       // Get the data item for this position
	       String a = getItem(position);
	       String[] split=a.split(":");
	        foodname=split[0];
	        foodID=split[1];
	       // Check if an existing view is being reused, otherwise inflate the view
	       if (convertView == null) {
	          convertView = LayoutInflater.from(getContext()).inflate(R.layout.alim_search_list_adapter, parent, false);
	       }
	       // Lookup view for data population
	       TextView AlimName = (TextView) convertView.findViewById(R.id.AlimSearchName);
	       Button b = (Button)convertView.findViewById(R.id.addAlButton);
	       b.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 // Perform action on click
	            	 new RetrieveCal().execute(foodID);
	             }
	         });

	       // Populate the data into the template view using the data object
	       AlimName.setText(foodname);
	       // Return the completed view to render on screen
	       return convertView;
	   }
	 
		public class RetrieveCal extends AsyncTask<String, Void, String> {

		    private Exception exception;

		    protected String doInBackground(String... urls) {
		    	DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
				String url="https://api.nutritionix.com/v1_1/item?id="+urls[0]+"&appId=bb655221&appKey=54235ea3948c6bbb61ff9a32701bbae7";
				HttpGet httpget = new HttpGet(url);
				// Depends on your web service

				InputStream inputStream = null;
				String result = null;
				try {
				    HttpResponse response = httpclient.execute(httpget);           
				    HttpEntity entity = response.getEntity();

				    inputStream = entity.getContent();
				    // json is UTF-8 by default
				    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
				    StringBuilder sb = new StringBuilder();
				    
				    String line = null;
				    while ((line = reader.readLine()) != null)
				    {
				        sb.append(line + "\n");
				    }
				    result = sb.toString();
				    return result;
				  
				} catch (Exception e) { 
				    return null;
				}
				finally {
				    try{
				    	if(inputStream != null)inputStream.close();}catch(Exception squish){}
				}
		    }

		    protected void onPostExecute(String res) {
		        // TODO: check this.exception 
		        // TODO: do something with the feed
		    	  try {
					JSONObject food = new JSONObject(res);
					double calorie=food.getDouble("nf_calories");
					String fname=food.getString("item_name");
					String AlimCapitalized= fname.substring(0,1).toUpperCase()+fname.substring(1);
					Aliment a = new Aliment(AlimCapitalized,(float) calorie);
					Globalvar.userListAliment.add(a);
					Globalvar.AlimAct.RefreshListView();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		}

}

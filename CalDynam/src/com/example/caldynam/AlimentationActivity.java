package com.example.caldynam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.caldynam.R;
import com.example.caldynam.MainActivity.Globalvar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AlimentationActivity extends Activity implements OnClickListener {
	
	private Button btnRechercheAliment, btnTerminerAliment;
	private EditText edtRechercheAliment;
	private TextView txtListeAliment;
	private ListView ListViewAliment;
	private float totalIN;
	private ArrayList<Aliment> ListAliment;
	private AlimListAdapter adapter;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().hide();
	    setContentView(R.layout.activity_alimentation);
	    btnRechercheAliment = (Button)findViewById(R.id.btnRechercheAliment);
	    btnTerminerAliment = (Button)findViewById(R.id.btnTerminerAliment);
	    edtRechercheAliment = (EditText) findViewById(R.id.edtRechercheAliment);
	    txtListeAliment = (TextView) findViewById(R.id.txtListeAliment);
	    ListViewAliment=(ListView)findViewById(R.id.AlimentList);
	    totalIN=0;
	    ListAliment=new ArrayList<Aliment>();
	    if(!Globalvar.UserListAliment.isEmpty())
	    {
	    	for(Aliment a : Globalvar.UserListAliment)
	    	{
	    		ListAliment.add(a);
	    	}
	    }
	    adapter = new AlimListAdapter(this,ListAliment);
	    ListViewAliment.setAdapter(adapter);
	    btnRechercheAliment.setOnClickListener(this);
	    btnTerminerAliment.setOnClickListener(this);

	  }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnRechercheAliment:
			//bouton Ajouter
			//Rechercher l'aliment entré dans edtRechercheAliment avec l'API, récupérer la valeur calorique et l'ajouter au totalIN si trouvé 
			//et ajouter l'aliment dans la liste txtListeAliment si trouvé
			new RetrieveID().execute();
			break;
			
			
		case R.id.btnTerminerAliment:
			//bouton Terminer

			//Retourner le total des valeurs caloriques ajoutées 
			Intent i = new Intent();
			i.putExtra("totalIN",totalIN);
			setResult(RESULT_OK,i);
			finish();
			break;
		}
	}
	class RetrieveID extends AsyncTask<String, Void, String> {

	    private Exception exception;

	    protected String doInBackground(String... urls) {
	    	DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
			String url="https://api.nutritionix.com/v1_1/search/"+edtRechercheAliment.getText()+"?appId=bb655221&appKey=54235ea3948c6bbb61ff9a32701bbae7";
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
				JSONArray jArray = food.getJSONArray("hits");
				JSONObject one=jArray.getJSONObject(0);
				JSONObject foodFields = one.getJSONObject("fields");
				
				new RetrieveCal().execute(foodFields.getString("item_id"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
	class RetrieveCal extends AsyncTask<String, Void, String> {

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
				String userEntry=edtRechercheAliment.getText().toString();
				String AlimCapitalized= userEntry.substring(0,1).toUpperCase()+userEntry.substring(1);
				Aliment a = new Aliment(AlimCapitalized,(float) calorie);
				ListAliment.add(a);
				Globalvar.UserListAliment.add(a);
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(edtRechercheAliment.getWindowToken(), 0);
				ListViewAliment.setAdapter(adapter);
				Toast.makeText(getApplicationContext(), "Item found, "+calorie+" calories added", Toast.LENGTH_SHORT).show();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
}

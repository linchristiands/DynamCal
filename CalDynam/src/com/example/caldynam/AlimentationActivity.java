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
import com.example.fragment.MenuFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
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
	private ListView listViewAliment;
	private ListView listViewSearchAliment;
	private float totalIN;
	private String entryAliment;
	private AlimListAdapter adapter;
	private AlimSearchListAdapter searchAdapter;
	private  ArrayList<String> foundItem;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().hide();
	    Globalvar.AlimAct=this;
	    setContentView(R.layout.activity_alimentation);
	    btnRechercheAliment = (Button)findViewById(R.id.btnRechercheAliment);
	    btnTerminerAliment = (Button)findViewById(R.id.btnTerminerAliment);
	    edtRechercheAliment = (EditText) findViewById(R.id.edtRechercheAliment);
	    listViewAliment=(ListView)findViewById(R.id.AlimentList);
	    listViewSearchAliment=(ListView)findViewById(R.id.AlimentSearchList);
	    totalIN=0;
	    entryAliment="";
	    checkUserAlimList();
	    foundItem = new ArrayList<String>();
	    adapter = new AlimListAdapter(this,Globalvar.userListAliment);
	    searchAdapter = new AlimSearchListAdapter(this,foundItem);
	    listViewAliment.setAdapter(adapter);
	    btnRechercheAliment.setOnClickListener(this);
	    btnTerminerAliment.setOnClickListener(this);
	    registerForContextMenu(listViewAliment);
	  }

	private void checkUserAlimList()
	{
		for(EntryAliment ea : Globalvar.entryAlimList)
		{
			if((ea.getUsername().equals(User.currentUser.getKey()))&&(MenuFragment.day==ea.getDay()&&MenuFragment.month==ea.getMonth()&&MenuFragment.year==ea.getYear()))
			{
				Globalvar.userListAliment=ea.getListe();
			}
		}
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnRechercheAliment:
			//bouton Ajouter
			//Rechercher l'aliment entr� dans edtRechercheAliment avec l'API, r�cup�rer la valeur calorique et l'ajouter au totalIN si trouv� 
			//et ajouter l'aliment dans la liste txtListeAliment si trouv�
			foundItem.clear();
			new RetrieveID().execute();
			break;
			
			
		case R.id.btnTerminerAliment:
		
			Intent i = new Intent();
			i.putExtra("totalIN",totalIN);
			i.putExtra("entryAliment" , entryAliment);
			setResult(RESULT_OK,i);
			finish();
			
		}
	}
	
	public void setViewWithData(double cal,String txt)
	{
		listViewAliment.setAdapter(adapter);
		totalIN+=cal;
		entryAliment+=txt;
	}
	public void RefreshListView()
	{
		listViewAliment.setAdapter(adapter);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);

	    menu.setHeaderTitle("");
	    menu.add(0, v.getId(), 0, "Delete");

	}
	@Override 
    public boolean onContextItemSelected(MenuItem item)
    { 
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
		int IndexSelected=info.position;              
		if(item.getTitle()=="Delete")
		{
			Globalvar.userListAliment.remove(IndexSelected);
			RefreshListView();
		}
		return true; 
               
                           
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
				for(int i=0;i<jArray.length();i++)
				{
					JSONObject item =jArray.getJSONObject(i);
					JSONObject fields = item.getJSONObject("fields");
					foundItem.add(fields.getString("item_name")+" - "+fields.getString("brand_name")+":"+fields.getString("item_id"));
				}
				listViewSearchAliment.setAdapter(searchAdapter);
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(edtRechercheAliment.getWindowToken(), 0);
				//new RetrieveCal().execute(foodFields.getString("item_id"));
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
	    		entryAliment+=":";
				JSONObject food = new JSONObject(res);
				double calorie=food.getDouble("nf_calories");
				String userEntry=edtRechercheAliment.getText().toString();
				String AlimCapitalized= userEntry.substring(0,1).toUpperCase()+userEntry.substring(1);
				Aliment a = new Aliment(AlimCapitalized,(float) calorie);
				Globalvar.userListAliment.add(a);
				entryAliment+=a.getName()+"#"+a.getCalString();
				totalIN+=calorie;
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(edtRechercheAliment.getWindowToken(), 0);
				listViewAliment.setAdapter(adapter);
				Toast.makeText(getApplicationContext(), "Item found, "+calorie+" calories added", Toast.LENGTH_SHORT).show();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
	
}

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.caldynam.MainActivity.Globalvar;
import com.example.fragment.MenuFragment;

public class AlimentationActivity extends Activity implements OnClickListener {
	
	private Button btnRechercheAliment, btnTerminerAliment;
	private EditText edtRechercheAliment;
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
		Globalvar.userListAliment.clear();
		for(EntryAliment ea : Globalvar.entryAlimList)
		{
			if((ea.getUsername().equals(User.currentUser.getKey()))&&(MenuFragment.day==ea.getDay()&&MenuFragment.month==ea.getMonth()&&MenuFragment.year==ea.getYear()))
			{
				Globalvar.userListAliment=ea.getListe();
			}
		}
	}
	
	@Override
	public void onBackPressed() {
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
			if(isNetworkAvailable())
				new RetrieveID().execute();
			else
				Toast.makeText(getApplication().getApplicationContext(), "Error, no internet connection", Toast.LENGTH_LONG).show();
			break;
			
			
		case R.id.btnTerminerAliment:
		
			totalIN=Globalvar.sumCalAlim();
			entryAliment=Globalvar.sumStringUserListAlim();
			Intent i = new Intent();
			i.putExtra("totalIN",totalIN);
			i.putExtra("entryAliment" , entryAliment);
			setResult(RESULT_OK,i);
			finish();
			
		}
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
	
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	
	class RetrieveID extends AsyncTask<String, Void, String> {

	    protected String doInBackground(String... urls) {
	    	DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
			String url="https://api.nutritionix.com/v1_1/search/"+edtRechercheAliment.getText()+"?appId=bb655221&appKey=54235ea3948c6bbb61ff9a32701bbae7";
			HttpGet httpget = new HttpGet(url);

			InputStream inputStream = null;
			String result = null;
			try {
			    HttpResponse response = httpclient.execute(httpget);           
			    HttpEntity entity = response.getEntity();

			    inputStream = entity.getContent();
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
	    	  try {
	    		
				JSONObject food = new JSONObject(res);
				JSONArray jArray = food.getJSONArray("hits");
				for(int i=0;i<jArray.length();i++)
				{
					JSONObject item =jArray.getJSONObject(i);
					JSONObject fields = item.getJSONObject("fields");
					foundItem.add(fields.getString("item_name")+" - "+fields.getString("brand_name")+":"+fields.getString("item_id"));
				}
				listViewSearchAliment.setAdapter(searchAdapter);
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(edtRechercheAliment.getWindowToken(), 0);
			} catch (JSONException e) {
				e.printStackTrace();
			}
	    }
	}
	
}

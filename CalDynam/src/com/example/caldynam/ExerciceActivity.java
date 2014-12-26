package com.example.caldynam;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.caldynam.AlimentationActivity.RetrieveCal;
import com.example.caldynam.MainActivity.Globalvar;
import com.example.caldynam.R;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ExerciceActivity extends Activity implements OnClickListener{
	
	private Button btnRechercheExercice, btnTerminerExercice;
	private EditText edtRechercheExercice;
	private TextView txtListeExercice;
	private float totalOUT;
	private String[] split;
	private String signature="&oauth_signature=";
	private String result;
	private String parameters;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().hide();
	    setContentView(R.layout.activity_exercice);
	    btnRechercheExercice = (Button)findViewById(R.id.btnRechercheExercice);
	    btnTerminerExercice = (Button)findViewById(R.id.btnTerminerExercice);
	    edtRechercheExercice = (EditText) findViewById(R.id.edtRechercheExercice);
	    txtListeExercice = (TextView) findViewById(R.id.txtListeExercice);
	    totalOUT = 0;
	    btnRechercheExercice.setOnClickListener(this);
	    btnTerminerExercice.setOnClickListener(this);
	    if(Globalvar.exerciseList.isEmpty())
	    {
	    	Globalvar.populateExerciseList();
	    }
	  }
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnRechercheExercice:
			//idem que pour totalIN mais pour le totalOUT
			break;
			
		case R.id.btnTerminerExercice:
			Intent i = new Intent();
			i.putExtra("totalOUT",totalOUT);
			setResult(RESULT_OK,i);
			finish();
			break;
		}
	}

}

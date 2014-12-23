package com.example.caldynam;

import com.example.caldynam.MainActivity.Globalvar;
import com.example.caldynam.R;

import android.app.Activity;
import android.content.Intent;
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
	private int totalOUT;
	
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    CreateOrCheckExList();
	    getActionBar().hide();
	    setContentView(R.layout.activity_exercice);
	    btnRechercheExercice = (Button)findViewById(R.id.btnRechercheExercice);
	    btnTerminerExercice = (Button)findViewById(R.id.btnTerminerExercice);
	    edtRechercheExercice = (EditText) findViewById(R.id.edtRechercheExercice);
	    txtListeExercice = (TextView) findViewById(R.id.txtListeExercice);
	    totalOUT = 0;
	    btnRechercheExercice.setOnClickListener(this);
	    btnTerminerExercice.setOnClickListener(this);
	   
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
	
	private void CreateOrCheckExList()
	{
		if(Globalvar.exerciseList.isEmpty())
		{
			Exercise ex = new Exercise(,"");
			Globalvar.exerciseList.add(ex);
		}
	}

}

package com.example.caldynam;

import com.example.caldynam.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AlimentationActivity extends Activity implements OnClickListener {
	
	private Button btnRechercheAliment, btnTerminerAliment;
	private EditText edtRechercheAliment;
	private TextView txtListeAliment;
	private int totalIN;
	
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().hide();
	    setContentView(R.layout.activity_alimentation);
	    btnRechercheAliment = (Button)findViewById(R.id.btnRechercheAliment);
	    btnTerminerAliment = (Button)findViewById(R.id.btnTerminerAliment);
	    edtRechercheAliment = (EditText) findViewById(R.id.edtRechercheAliment);
	    txtListeAliment = (TextView) findViewById(R.id.txtListeAliment);
	    totalIN=0;
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
	
	
}

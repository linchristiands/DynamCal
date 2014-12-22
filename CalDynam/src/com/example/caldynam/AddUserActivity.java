package com.example.caldynam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends Activity implements OnClickListener{

	private Button btnConfirmUser, btnCancelUser;
	private EditText edtUserName, edtUserFirstName, edtUserWeight, edtUserHeight;
	
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().hide();
	    setContentView(R.layout.activity_adduser);
	    btnConfirmUser = (Button)findViewById(R.id.btnConfirmUser);
	    btnConfirmUser.setOnClickListener(this);
	    btnCancelUser = (Button)findViewById(R.id.btnCancelUser);
	    btnCancelUser.setOnClickListener(this);
	    edtUserName = (EditText)findViewById(R.id.edtUserName);
	    edtUserFirstName = (EditText)findViewById(R.id.edtUserFirstName);
	    edtUserWeight = (EditText)findViewById(R.id.edtUserWeight);
	    edtUserHeight = (EditText)findViewById(R.id.edtUserHeight);
	    
	  }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnConfirmUser:
			//écriture fichier utilisateur
			
			
			Intent i = new Intent();
			setResult(RESULT_OK, i);
			finish();
			break;
			
		case R.id.btnCancelUser:
			Intent returnIntent = new Intent();
			setResult(RESULT_CANCELED, returnIntent);
			finish();
			break;
		}
	}
}

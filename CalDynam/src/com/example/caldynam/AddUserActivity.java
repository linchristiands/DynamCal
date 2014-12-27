package com.example.caldynam;

import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
			if(!edtUserName.getText().toString().equals("")&&!edtUserFirstName.getText().toString().equals("")&&!edtUserHeight.getText().toString().equals("")&&!edtUserWeight.getText().toString().equals("")){	
			User user = new User(edtUserName.getText().toString(), edtUserFirstName.getText().toString(),Integer.parseInt(edtUserHeight.getText().toString()),Float.parseFloat(edtUserWeight.getText().toString()));		
			if(!checkExist(user.getKey())){
			Context context = this;
			SharedPreferences sharedPref = context.getSharedPreferences("CalDynamUsers", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPref.edit();
			editor.putString(user.getKey(), user.toString());
			editor.commit();	
			Intent i = new Intent();
			setResult(RESULT_OK, i);
			finish();
			}
			}
			break;
			
		case R.id.btnCancelUser:
			Intent returnIntent = new Intent();
			setResult(RESULT_CANCELED, returnIntent);
			finish();
			break;
		}
	}

	private boolean checkExist(String username) {
		SharedPreferences sharedPref = this.getSharedPreferences("CalDynamUsers", Context.MODE_PRIVATE);
		Map<String,?> keys = sharedPref.getAll();
		int i=0;
		for(Map.Entry<String,?> ent : keys.entrySet()){
		            
		            if(ent.getKey().equals(username)){
		            	return true;
		            }
		  
		 }
		return false;
	}
}

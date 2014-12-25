package com.example.caldynam;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ModifUserActivity extends Activity implements OnClickListener {

	private Button btnConfirmUser, btnCancelUser;
	private EditText edtUserName, edtUserFirstName, edtUserWeight,
			edtUserHeight;
	private String username, userinfos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_modifuser);
		btnConfirmUser = (Button) findViewById(R.id.btnConfirmUser);
		btnConfirmUser.setOnClickListener(this);
		btnCancelUser = (Button) findViewById(R.id.btnCancelUser);
		btnCancelUser.setOnClickListener(this);
		edtUserName = (EditText) findViewById(R.id.edtUserName);
		edtUserFirstName = (EditText) findViewById(R.id.edtUserFirstName);
		edtUserWeight = (EditText) findViewById(R.id.edtUserWeight);
		edtUserHeight = (EditText) findViewById(R.id.edtUserHeight);

		username = getIntent().getStringExtra("Name");
		SharedPreferences sharedPref = this.getSharedPreferences(
				"CalDynamUsers", Context.MODE_PRIVATE);
		userinfos = sharedPref.getString(username, "");
		String[] parts = userinfos.split(";");
		edtUserName.setText(parts[0]);
		edtUserFirstName.setText(parts[1]);
		edtUserHeight.setText(parts[2]);
		edtUserWeight.setText(parts[3]);
		edtUserName.setEnabled(false);
		edtUserFirstName.setEnabled(false);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnConfirmUser:

			if (!edtUserName.getText().toString().equals("")
					&& !edtUserFirstName.getText().toString().equals("")
					&& !edtUserHeight.getText().toString().equals("")
					&& !edtUserWeight.getText().toString().equals("")) {
				User user = new User(edtUserName.getText().toString(),
						edtUserFirstName.getText().toString(),
						Integer.parseInt(edtUserHeight.getText().toString()),
						Float.parseFloat(edtUserWeight.getText().toString()));
				Context context = this;
				SharedPreferences sharedPref = context.getSharedPreferences(
						"CalDynamUsers", Context.MODE_PRIVATE);
				SharedPreferences.Editor editor = sharedPref.edit();
				// remove the old one
				if(User.currentUser!=null){
					if(User.currentUser.getKey().equals(username)){//si on supprime alors que profil actif
						User.currentUser=null;
					}}
				editor.remove(username);
				editor.commit();
				
				// add the new one
				editor.putString(user.getKey(), user.toString());
				editor.commit();
				Intent i = new Intent();
				setResult(RESULT_OK, i);
				finish();
			}
			break;

		case R.id.btnCancelUser:
			Intent returnIntent = new Intent();
			setResult(RESULT_CANCELED, returnIntent);
			finish();
			break;
		}
	}
}

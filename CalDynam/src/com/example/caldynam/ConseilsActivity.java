package com.example.caldynam;

import com.example.caldynam.R;

import android.app.Activity;
import android.os.Bundle;

public class ConseilsActivity extends Activity{

	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().hide();
	    setContentView(R.layout.activity_conseils);
	  }
}

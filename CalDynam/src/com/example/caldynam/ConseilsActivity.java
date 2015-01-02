package com.example.caldynam;

import com.example.caldynam.MainActivity.Globalvar;
import com.example.caldynam.R;
import com.example.fragment.MenuFragment;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ConseilsActivity extends Activity{

	private float totalIN=0;
	private float totalOUT=0;
	private TextView DateAdvice;
	private TextView totalinTv;
	private TextView totaloutTv;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().hide();
	    setContentView(R.layout.activity_conseils);
	    DateAdvice=(TextView)findViewById(R.id.DateAdvice);
	    totalinTv=(TextView)findViewById(R.id.totalinAdv);
	    totaloutTv=(TextView)findViewById(R.id.totaloutAdv);
	    DateAdvice.setText(MenuFragment.day+"/"+MenuFragment.month+"/"+MenuFragment.year);
	    checkUserExList();
	    checkUserAlimList();
	    for(Aliment a : Globalvar.userListAliment)
	    {
	    	totalIN+=a.getCal();
	    }
	    for(Exercise e : Globalvar.userListExercise)
	    {
	    	totalOUT+=e.calorieUsed();
	    }
	    totalinTv.setText("Total in :"+String.valueOf(totalIN)+" calories");
	    totaloutTv.setText("Total out :"+String.valueOf(totalOUT)+" calories");
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
	
	private void checkUserExList()
	{
		Globalvar.userListExercise.clear();
		for(EntryExercice ee : Globalvar.entryExoList)
		{
			if((ee.getUsername().equals(User.currentUser.getKey()))&&(MenuFragment.day==ee.getDay()&&MenuFragment.month==ee.getMonth()&&MenuFragment.year==ee.getYear()))
			{
				Globalvar.userListExercise=ee.getListe();
			}
		}
	}
}

package com.example.caldynam;

import com.example.caldynam.MainActivity.Globalvar;
import com.example.caldynam.R;
import com.example.fragment.MenuFragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConseilsActivity extends Activity{

	private float totalIN=0;
	private float totalOUT=0;
	private float IMC=0;
	private float Diff=0;
	private TextView DateAdvice;
	private TextView totalinTv;
	private TextView totaloutTv;
	private TextView IMCTv;
	private TextView diffTv;
	private Button back;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().hide();
	    setContentView(R.layout.activity_conseils);
	    DateAdvice=(TextView)findViewById(R.id.DateAdvice);
	    totalinTv=(TextView)findViewById(R.id.totalinAdv);
	    totaloutTv=(TextView)findViewById(R.id.totaloutAdv);
	    IMCTv=(TextView)findViewById(R.id.IMCTv);
	    diffTv=(TextView)findViewById(R.id.diffTv);
	    back=(Button)findViewById(R.id.backButtonReport);
	    int month =MenuFragment.month+1;
	    DateAdvice.setText(MenuFragment.day+"/"+month+"/"+MenuFragment.year);
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
	    Diff=totalIN-totalOUT;
	    float poids=User.currentUser.getPoids();
	    float taillemetre=(float)User.currentUser.getTaille()/100;
	    float taillecarre=(float) Math.pow(taillemetre,2);
	    IMC=poids/taillecarre;
	    IMCTv.setText("IMC : "+String.format("%.02f",IMC));
	    diffTv.setText("Difference in and out : "+String.valueOf(Diff));
	    back.setOnClickListener(new OnClickListener()
	    {
			@Override
			public void onClick(View v) {
				if(v.getId()==R.id.backButtonReport)
				{
					finish();
				}
			}
	    	
	    });

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
	public void onBackPressed() {
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

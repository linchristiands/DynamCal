package com.example.caldynam;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.example.caldynam.MainActivity.Globalvar;
import com.example.caldynam.R;
import com.example.fragment.MenuFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ExerciceActivity extends Activity implements OnClickListener{
	
	private Button btnRechercheExercice, btnTerminerExercice;
	private EditText edtRechercheExercice;
	private float totalOUT;
	private String entryEx;
	private ArrayList<Exercise> foundEx;
	private ExSearchListAdapter exSearch;
	private ExerciseListAdapter exerciseList;
	private ListView searchList;
	private ListView exList;
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    getActionBar().hide();
	    setContentView(R.layout.activity_exercice);
	    Globalvar.ExAct=this;
	    btnRechercheExercice = (Button)findViewById(R.id.btnRechercheExercice);
	    btnTerminerExercice = (Button)findViewById(R.id.btnTerminerExercice);
	    edtRechercheExercice = (EditText) findViewById(R.id.edtRechercheExercice);
	    totalOUT = 0;
	    entryEx="";
	    foundEx= new ArrayList<Exercise>();
	    btnRechercheExercice.setOnClickListener(this);
	    btnTerminerExercice.setOnClickListener(this);
	    checkUserExList();
	    searchList=(ListView)findViewById(R.id.ExerciseListSearch);
	    exList=(ListView)findViewById(R.id.ExerciseList);
	    exSearch=new ExSearchListAdapter(this,foundEx);
	    exerciseList= new ExerciseListAdapter(this,Globalvar.userListExercise);
	    exList.setItemsCanFocus(true);
	    registerForContextMenu(exList);
	    setAdapterView();
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
	private void bodyMetabolism()
	{
		int i=0;
		for(Exercise e : Globalvar.userListExercise)
		{
			if(e.getName().equals("Body Metabolism"))
			{
				i++;
			}
			
		}
		if (i<1)
			Globalvar.userListExercise.add(new Exercise("Body Metabolism",User.currentUser.getPoids()*24));
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btnRechercheExercice:
			//idem que pour totalIN mais pour le totalOUT
			foundEx.clear();
			SearchExList(edtRechercheExercice.getText().toString());
			break;
			
		case R.id.btnTerminerExercice:
			bodyMetabolism();
			totalOUT=Globalvar.sumCalEx();
			entryEx=Globalvar.sumStringUserListEx();
			Intent i = new Intent();
			i.putExtra("totalOUT",totalOUT);
			i.putExtra("entryExercise",entryEx);
			setResult(RESULT_OK,i);
			finish();
			break;
		}
	}
	@Override
	public void onBackPressed() {
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
			Globalvar.userListExercise.remove(IndexSelected);
			setAdapterView();
		}
		return true;        
                           
      }  
	
	public void setAdapterView()
	{
		exList.setAdapter(exerciseList);
	}
	
	private void SearchExList(String s)
	{
		for(Exercise e : Globalvar.exerciseList)
		{
			if(Pattern.compile(Pattern.quote(s),Pattern.CASE_INSENSITIVE).matcher(e.getName()).find())
			{
				foundEx.add(e);
			}
		}
		searchList.setAdapter(exSearch);
	}

}

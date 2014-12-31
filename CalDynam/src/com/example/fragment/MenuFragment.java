package com.example.fragment;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.caldynam.Aliment;
import com.example.caldynam.AlimentationActivity;
import com.example.caldynam.ConseilsActivity;
import com.example.caldynam.EntryAliment;
import com.example.caldynam.EntryExercice;
import com.example.caldynam.ExerciceActivity;
import com.example.caldynam.Exercise;
import com.example.caldynam.R;
import com.example.caldynam.User;
import com.example.caldynam.MainActivity.Globalvar;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

public class MenuFragment extends Fragment implements OnClickListener {
	
	private float totalIN, totalOUT;
	private Button btnAlimentation, btnExercice, btnConseils, btnDate;
	private Intent i;
	private int day,month,year;
	private String entryAliment, entryExercise;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        btnAlimentation = (Button) rootView.findViewById(R.id.btnAlimentation);
        btnExercice = (Button) rootView.findViewById(R.id.btnExercice);
        btnConseils = (Button) rootView.findViewById(R.id.btnConseils);
        btnDate = (Button) rootView.findViewById(R.id.btnDate);
        btnAlimentation.setOnClickListener(this);
        btnExercice.setOnClickListener(this);
        btnConseils.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        btnDate.setText(day + " / " + (month + 1) + " / " + year);  
        
        //Il faut d'abord sélectionner un user
        if(User.currentUser==null){
        	btnAlimentation.setEnabled(false);
        	btnExercice.setEnabled(false);
        	btnConseils.setEnabled(false);
        	btnDate.setEnabled(false);
        }        
        return rootView;
    }
    

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.btnAlimentation:
			i = new Intent(this.getActivity(), AlimentationActivity.class);
			startActivityForResult(i, 1);
			break;
		case R.id.btnExercice:
			i = new Intent(this.getActivity(), ExerciceActivity.class);
			startActivityForResult(i, 2);
			break;
		case R.id.btnConseils:
			i = new Intent(this.getActivity(), ConseilsActivity.class);
			startActivity(i);
			break;
		case R.id.btnDate:
			showDatePicker();
			break;
		}
	}
	
	private void showDatePicker() {
		  DatePickerFragment date = new DatePickerFragment();

		  Calendar calendar = Calendar.getInstance();
		  Bundle args = new Bundle();
		  args.putInt("year", calendar.get(Calendar.YEAR));
		  args.putInt("month", calendar.get(Calendar.MONTH));
		  args.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
		  date.setArguments(args);
		  /**
		   * Set Call back to capture selected date
		   */
		  date.setCallBack(ondate);
		  date.show(getActivity().getSupportFragmentManager(), "Date Picker");
		 }

		 OnDateSetListener ondate = new OnDateSetListener() {
		  @Override
		  public void onDateSet(DatePicker view, int y, int m,
		    int d) {
			  day = d;
			  year = y;
			  month = m;
			  btnDate.setText(day + " / " + (month + 1) + " / " + year);
		  }
		 };

	
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) { 
	    	//On ecrit l'entry dans un sharedpref
	    	if(data!=null){
			totalIN = data.getFloatExtra("totalIN", 0);
	    	entryAliment = data.getStringExtra("entryAliment");	
	    	if(entryAliment.length()>1){
	    	String[] parts = entryAliment.split(":");
	    	ArrayList<Aliment> lst = new ArrayList<Aliment>();
	    	for(int i=0;i<parts.length;i++){
	    		lst.add(new Aliment(parts[i]));
	    	}
	    	EntryAliment e = new EntryAliment(User.currentUser.getKey(), totalIN, day, month, year, lst);
	    	if(!checkDateExist(e))
	    		Globalvar.entryAlimList.add(e);
	    	}        
	    	}
	    }
	    else if (requestCode == 2){
	    	//On écrit l'entry dans un sharedpref
	    	if(data!=null){
			totalOUT = data.getFloatExtra("totalOUT", 0);
	    	entryExercise = data.getStringExtra("entryExercise");	
	    	if(entryExercise.length()>1){
	    	String[] parts = entryExercise.split(":");
	    	ArrayList<Exercise> lst = new ArrayList<Exercise>();
	    	for(int i=0;i<parts.length;i++){
	    		lst.add(new Exercise(parts[i]));
	    	}
	    	
	    	EntryExercice e = new EntryExercice(User.currentUser.getKey(), totalIN, day, month, year, lst);
	    	if(!checkDateExist(e))
	    		Globalvar.entryExoList.add(e);
	    	}
	    	}
	    }
	}

	
	private boolean checkDateExist(EntryAliment e) {
		for(EntryAliment ent : Globalvar.entryAlimList){
			if(ent.getUsername().equals(e.getUsername())){
				if(ent.getDay() == e.getDay() && ent.getMonth()==e.getMonth() && ent.getYear() == e.getYear()){
					return true;
				}
			}
		}
		return false;
	}


	private boolean checkDateExist(EntryExercice e) {
		for(EntryExercice ent : Globalvar.entryExoList){
			if(ent.getUsername().equals(e.getUsername())){
				if(ent.getDay() == e.getDay() && ent.getMonth()==e.getMonth() && ent.getYear() == e.getYear()){
					return true;
				}
			}
		}
		return false;
	}
    
   
}

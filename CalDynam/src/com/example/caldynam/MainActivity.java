package com.example.caldynam;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.fragment.TabsPagerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class MainActivity extends FragmentActivity implements TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "User", "Menu", "Graph" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Init
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		getExList();
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
	
		mAdapter.notifyDataSetChanged();
		viewPager.setCurrentItem(tab.getPosition());
	
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
	
	public void OnDestroy()
	{
		saveExList();
	}
	
	public void getExList()
	{
		SharedPreferences mPrefs= getPreferences(MODE_PRIVATE);
		String json = mPrefs.getString("ExList", "");
		Gson gson = new Gson();
		Type type=new TypeToken<ArrayList<Exercise>>(){}.getType();
		Globalvar.exerciseList=gson.fromJson(json, type);
		if(Globalvar.exerciseList==null)
		{
			Globalvar.populateExerciseList();
		}

	}
	public void saveExList()
	{
			Globalvar.populateExerciseList();
			SharedPreferences  mPrefs = getPreferences(MODE_PRIVATE);
			Editor prefsEditor = mPrefs.edit();
			Gson gson = new Gson();
		    String json = gson.toJson(Globalvar.exerciseList);
		    prefsEditor.putString("ExList", json);
		    prefsEditor.commit();
	}
	
	public static class Globalvar
	{
	
		public static ArrayList<Aliment> userListAliment=new ArrayList<Aliment>();
		public static ArrayList<Exercise> exerciseList=new ArrayList<Exercise>();
		
		public static void setExList(ArrayList<Exercise> a)
		{
			exerciseList=a;
		}
		public static void setAlimList(ArrayList<Aliment> a)
		{
			userListAliment=a;
		}
		
		public static void populateExerciseList()
		{
			Globalvar.exerciseList=new ArrayList<Exercise>();
			Exercise ex= new Exercise("Aquagym",15.15f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Badminton",15.15f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("BaseBall", 12.19f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Basket-Ball", 10.2f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Body-Building", 10.2f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Body metabolism", 1f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Bowling", 20.4f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Boxing", 6.75f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Climbing", 5.5f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Climb stairs", 4.06f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Cycling", 10f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Fencing", 10.2f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Football", 6.2f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Handball", 5.07f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Hunting", 12.19f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Jogging", 8.6f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Judo", 6.09f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Karate", 6.09f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Resting", 1f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Rugby", 6.09f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Running", 7.63f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Skipping Rope", 6.09f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Squash", 5.07f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Swimming", 9.09f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Tennis", 7.63f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Vacuum", 10.2f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Volley-Ball", 14.3f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Walking", 20f);
			Globalvar.exerciseList.add(ex);
			ex= new Exercise("Weightlifting", 20.4f);
			Globalvar.exerciseList.add(ex);
			
		}
	}
}

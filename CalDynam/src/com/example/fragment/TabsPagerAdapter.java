package com.example.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsPagerAdapter extends FragmentStatePagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
           
            return new UserFragment();
        case 1:
          
            return new MenuFragment();

        case 2:
            
            return new GraphFragment();    
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get number of tabs
        return 3;
    }
}

package com.example.caldynam;

import com.example.fragment.GraphFragment;
import com.example.fragment.InFragment;
import com.example.fragment.OutFragment;
import com.example.fragment.UserFragment;

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
          
            return new InFragment();
        case 2:
          
            return new OutFragment();
        case 3:
            
            return new GraphFragment();    
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get number of tabs
        return 4;
    }
}

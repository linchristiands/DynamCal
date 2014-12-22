package com.example.fragment;

import com.example.caldynam.AddUserActivity;
import com.example.caldynam.AlimentationActivity;
import com.example.caldynam.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class UserFragment extends Fragment implements OnClickListener {

	private ListView lstUser;
	private Button btnAddUser;
	String[] users = {"Test1","Test2","Test3","Test4"};
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
        lstUser = (ListView)rootView.findViewById(R.id.lstUser);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.listitem, users);
        lstUser.setAdapter(adapter);
        registerForContextMenu(lstUser);
        btnAddUser= (Button)rootView.findViewById(R.id.btnAddUser);
        btnAddUser.setOnClickListener(this);
        
        return rootView;
    }
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	  if (v.getId()==R.id.lstUser) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
	    menu.setHeaderTitle(users[info.position]);
	    String[] menuItems = {"sélectionner","modifier","supprimer"};
	    for (int i = 0; i<menuItems.length; i++) {
	      menu.add(Menu.NONE, i, i, menuItems[i]);
	    }
	  }
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  int menuItemIndex = item.getItemId();
	  String[] menuItems = {"sélectionner","modifier","supprimer"};
	  String menuItemName = menuItems[menuItemIndex];
	  String listItemName = users[info.position];
	  System.out.println(menuItemName);
	  System.out.println(listItemName);
	  //text.setText(String.format("Selected %s for item %s", menuItemName, listItemName));
	  return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(this.getActivity(), AddUserActivity.class);
		startActivityForResult(i, 1);
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) { 
	    	
	    }
	}
}

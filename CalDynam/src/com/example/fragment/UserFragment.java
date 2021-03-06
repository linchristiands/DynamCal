package com.example.fragment;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.caldynam.AddUserActivity;
import com.example.caldynam.EntryAliment;
import com.example.caldynam.EntryExercice;
import com.example.caldynam.MainActivity.Globalvar;
import com.example.caldynam.ModifUserActivity;
import com.example.caldynam.R;
import com.example.caldynam.User;

public class UserFragment extends Fragment implements OnClickListener {

	private ListView lstUser;
	private Button btnAddUser;
	String[] users = new String[]{"","","","","","","","","","" };
	ArrayAdapter<String> adapter;
	String temp;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user, container, false);
   		fillListView();
        lstUser = (ListView)rootView.findViewById(R.id.lstUser);
        adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.listitem, users);
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
	    if(!users[info.position].equals("")){
	    menu.setHeaderTitle(users[info.position]);
	    String[] menuItems = {"Select","Modify","Delete"};
	    for (int i = 0; i<menuItems.length; i++) {
	      menu.add(Menu.NONE, i, i, menuItems[i]);
	    }
	    }
	  }
	}
	
	private void removeUserEntries(String username)
	{
		ArrayList<EntryExercice> temp= new ArrayList<EntryExercice>();
		ArrayList<EntryAliment> temp2= new ArrayList<EntryAliment>();
		for(EntryAliment ea : Globalvar.entryAlimList)
		{
			if(ea.getUsername().equals(username))
				temp2.add(ea);
		}
		for(EntryExercice ee : Globalvar.entryExoList)
		{
			if(ee.getUsername().equals(username))
			temp.add(ee);
		}
		for(EntryAliment ea : temp2)
		{
			Globalvar.entryAlimList.remove(ea);
		}
		for(EntryExercice ee : temp)
		{
			Globalvar.entryExoList.remove(ee);
		}
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	  AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	  int menuItemIndex = item.getItemId();
	  String[] menuItems = {"select","modify","delete"};
	  String menuItemName = menuItems[menuItemIndex];
	  String listItemName = users[info.position];
	  if(menuItemName.equals("delete")){
			SharedPreferences sharedPref = getActivity().getSharedPreferences("CalDynamUsers", Context.MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPref.edit();
			if(User.currentUser!=null){
			if(User.currentUser.getKey().equals(listItemName)){//si on supprime alors que profil actif
				User.currentUser=null;
			}}
			editor.remove(listItemName);
			editor.commit();	
			fillListView();
			adapter.notifyDataSetChanged();
			removeUserEntries(listItemName);
			
	  }
	  
	  else if(menuItemName.equals("modify")){
		  	temp = listItemName;
		  	Intent i = new Intent(this.getActivity(), ModifUserActivity.class);
		  	i.putExtra("Name", temp);
			startActivityForResult(i, 1);
	  }
	  else if(menuItemName.equals("select")){
		  
		  	 SharedPreferences sharedPref = getActivity().getSharedPreferences("CalDynamUsers", Context.MODE_PRIVATE);
			String userinfos = sharedPref.getString(listItemName, "");
				String[] parts = userinfos.split(";");
				User.currentUser = new User(parts);//utilisateur courant, sélectionné, profil actif
				
	  }
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
	    	fillListView();
			adapter.notifyDataSetChanged();
	    }
	}
	
	private void fillListView(){
		
		Context context = getActivity();
		SharedPreferences sharedPref = context.getSharedPreferences("CalDynamUsers", Context.MODE_PRIVATE);
		Map<String,?> keys = sharedPref.getAll();
		int i=0;
		for(Map.Entry<String,?> ent : keys.entrySet()){
		       
		            users[i]=ent.getKey();
		            i++;
		  
		 }
		for(;i<users.length;i++){
			users[i]="";
		}
	}
}

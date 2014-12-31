package com.example.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import com.example.caldynam.EntryAliment;
import com.example.caldynam.EntryExercice;
import com.example.caldynam.R;
import com.example.caldynam.User;
import com.example.caldynam.MainActivity.Globalvar;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class GraphFragment extends Fragment {
	
	
	GraphView graph;
	LineGraphSeries<DataPoint> series, series2,series3;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_graph, container, false);
        graph = (GraphView) rootView.findViewById(R.id.graph);

        if(User.currentUser!=null && Globalvar.entryAlimList.size()>0){
        	setGraphAlimData();
        }
       
        if(User.currentUser!=null && Globalvar.entryExoList.size()>0){
        	setGraphExoData();
        }
        graph.setTitle("Calorie Graph");
        graph.getLegendRenderer().setVisible(true);        
        return rootView;
    }
	
	private void setGraphAlimData(){
		
		final ArrayList<String> dates = new ArrayList<String>();
		ArrayList<EntryAliment> list = new ArrayList<EntryAliment>();
		
		//keep entries of current user
		for(EntryAliment e : Globalvar.entryAlimList){
			if(e.getUsername().equals(User.currentUser.getKey())){
			list.add(e);
			}
		}
		
		//sort entries per date
		if(list.size()>1){
		Collections.sort(list,new Comparator<EntryAliment>(){

			@Override
			public int compare(EntryAliment e1, EntryAliment e2) {
				// TODO Auto-generated method stub
				int r = e1.getYear() - e2.getYear();
				if(r > 0){
					return 1;
				}
				else if(r <0){
					return -1;
				}
				else {
					int r2 = e1.getMonth() - e2.getMonth();
					if(r2 > 0){
						return 1;
					}
					else if(r2 <0){
						return -1;
					}
					else{
						int r3 = e1.getDay() - e2.getDay();
						if(r3 > 0){
							return 1;
						}
						else if(r3 <0){
							return -1;
						}
						else
							return 0;
					}
				
				}
			}
		});
		}
		System.out.println("list : "+ list.toString());
		//fill series with last 7 entries
		DataPoint[] dataPoints=null;
		if(list.size()>=7){ 
		dataPoints = new DataPoint[7];
		for(int i=list.size()-7,j=0;i<list.size();i++,j++){
			dataPoints[j] = new DataPoint(j,list.get(i).getTotal());
			dates.add(list.get(i).getDay()+"/"+list.get(i).getMonth()+"/"+list.get(i).getYear());
		}
		}
		else if(list.size()>0){ //if less than 7 entries
			dataPoints = new DataPoint[list.size()];
			for(int j=0; j< list.size(); j++){
				dataPoints[j]= new DataPoint(j, list.get(j).getTotal());
				dates.add(list.get(j).getDay()+"/"+(list.get(j).getMonth()+1)+"/"+list.get(j).getYear());
			}
		}
		
		series = new LineGraphSeries<DataPoint>(dataPoints);
		series.setColor(Color.RED);
		series.setTitle("food");
        series.setThickness(2);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(3);
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getActivity(), dates.get((int) dataPoint.getX())+" - " + dataPoint.getY()+" cal", Toast.LENGTH_SHORT).show();
            }
        });
        graph.addSeries(series);
	}
	
private void setGraphExoData(){
		
		final ArrayList<String> dates = new ArrayList<String>();
		ArrayList<EntryExercice> list = new ArrayList<EntryExercice>();
		
		//keep entries of current user
		for(EntryExercice e : Globalvar.entryExoList){
			if(e.getUsername().equals(User.currentUser.getKey())){
			list.add(e);
			}
		}
		
		//sort entries per date
		if(list.size()>1){
		Collections.sort(list,new Comparator<EntryExercice>(){

			@Override
			public int compare(EntryExercice e1, EntryExercice e2) {
				// TODO Auto-generated method stub
				int r = e1.getYear() - e2.getYear();
				if(r > 0){
					return 1;
				}
				else if(r <0){
					return -1;
				}
				else {
					int r2 = e1.getMonth() - e2.getMonth();
					if(r2 > 0){
						return 1;
					}
					else if(r2 <0){
						return -1;
					}
					else{
						int r3 = e1.getDay() - e2.getDay();
						if(r3 > 0){
							return 1;
						}
						else if(r3 <0){
							return -1;
						}
						else
							return 0;
					}
				
				}
			}
		});
		}
		System.out.println("list : "+ list.toString());
		//fill series with last 7 entries
		DataPoint[] dataPoints=null;
		if(list.size()>=7){ 
		dataPoints = new DataPoint[7];
		for(int i=list.size()-7,j=0;i<list.size();i++,j++){
			dataPoints[j] = new DataPoint(j,list.get(i).getTotal());
			dates.add(list.get(i).getDay()+"/"+list.get(i).getMonth()+"/"+list.get(i).getYear());
		}
		}
		else if(list.size()>0){ //if less than 7 entries
			dataPoints = new DataPoint[list.size()];
			for(int j=0; j< list.size(); j++){
				dataPoints[j]= new DataPoint(j, list.get(j).getTotal());
				dates.add(list.get(j).getDay()+"/"+(list.get(j).getMonth()+1)+"/"+list.get(j).getYear());
			}
		}
		
		series2 = new LineGraphSeries<DataPoint>(dataPoints);
		series2.setColor(Color.BLACK);
		series2.setTitle("exercise");
        series2.setThickness(2);
        series2.setDrawDataPoints(true);
        series2.setDataPointsRadius(3);
        series2.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Toast.makeText(getActivity(), dates.get((int) dataPoint.getX())+" - " + dataPoint.getY()+" cal", Toast.LENGTH_SHORT).show();
            }
        });
        graph.addSeries(series2);
	}
}

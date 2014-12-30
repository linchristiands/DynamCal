package com.example.fragment;

import com.example.caldynam.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GraphFragment extends Fragment {
	
	View rootView;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.fragment_graph, container, false);
        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                  new DataPoint(0, 1),
                  new DataPoint(1, 5),
                  new DataPoint(2, 3),
                  new DataPoint(3, 2)
        });
        
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(2, 2),
                new DataPoint(8, 6)
      });
        series.setColor(Color.RED);
        series.setThickness(2);
        series2.setThickness(2);
        series2.setColor(Color.BLUE);
        graph.addSeries(series);
        graph.addSeries(series2);
        graph.setTitle("in/out Graph");
        return rootView;
    }
}

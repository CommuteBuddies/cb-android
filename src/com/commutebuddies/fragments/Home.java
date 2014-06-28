package com.commutebuddies.fragments;

import com.commutebuddies.trips.CreateTrip;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.commutebuddies.R;

public class Home extends Fragment {
        
		
	
        public static Fragment newInstance(Context context) {
            Home f = new Home();
            return f;
        }
     

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.home, container, false);
            
            Button create_trip = (Button) rootView.findViewById(R.id.create_trip_button);
            create_trip.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                	Intent i=new Intent(getActivity(), CreateTrip.class);
                	startActivity(i);
                }
            });
            
            return rootView;
        }
    }
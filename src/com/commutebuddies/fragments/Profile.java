package com.commutebuddies.fragments;

import com.commutebuddies.R;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Profile extends Fragment {
        
        public static Fragment newInstance(Context context) {
            Profile f = new Profile();
            return f;
        }
     

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.profile, container, false);
            return rootView;
        }
    }
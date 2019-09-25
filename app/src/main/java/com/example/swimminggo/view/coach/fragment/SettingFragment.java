package com.example.swimminggo.view.coach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.swimminggo.R;
import com.example.swimminggo.view.coach.AddSwimmer;


public class SettingFragment extends Fragment {
    View view;

    public SettingFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_setting, container, false);
        return view;
    }
}

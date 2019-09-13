package com.example.swimminggo.view.coach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.swimminggo.R;

import java.util.ArrayList;

public class TeamFragment extends Fragment {
    View view;
    public TeamFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);
        SwipeMenuListView swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.listView);

        ArrayList<String> list = new ArrayList<>();
        list.add("Team 1");
        list.add("Team 2");
        list.add("Team 3");
        list.add("Team 4");
        list.add("Team 5");
        ArrayAdapter adapter;
        adapter = new ArrayAdapter(TeamFragment,android.R.layout.simple_list_item_1,list);

        return view;
    }
}

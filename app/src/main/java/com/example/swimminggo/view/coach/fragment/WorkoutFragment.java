package com.example.swimminggo.view.coach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.swimminggo.R;
import com.example.swimminggo.view.coach.CreateExercise;
import com.example.swimminggo.view.coach.CreateLesson;
import com.example.swimminggo.view.coach.CreateLessonPlan;
import com.example.swimminggo.view.coach.Record;

public class WorkoutFragment extends Fragment {
    View view;
    Button btn_excercise, btn_lesson, btn_lesson_plan, btn_record;
    public WorkoutFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workout, container, false);
        button();
        return view;
    }

    public void button(){
        btn_excercise = (Button) view.findViewById(R.id.create_exercise);
        btn_lesson = (Button) view.findViewById(R.id.lesson);
        btn_lesson_plan = (Button) view.findViewById(R.id.lesson_plan);
        btn_record = (Button) view.findViewById(R.id.record);
        btn_excercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateExercise.class);
                startActivity(intent);
            }
        });
        btn_lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateLesson.class);
                startActivity(intent);
            }
        });
        btn_lesson_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateLessonPlan.class);
                startActivity(intent);
            }
        });
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Record.class);
                startActivity(intent);
            }
        });

    }
}

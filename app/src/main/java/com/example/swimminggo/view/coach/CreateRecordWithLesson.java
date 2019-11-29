package com.example.swimminggo.view.coach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.swimminggo.R;
import com.example.swimminggo.adapter.LessonAdapter;
import com.example.swimminggo.models.Exercise;
import com.example.swimminggo.models.Lesson;
import com.example.swimminggo.models.Record;
import com.example.swimminggo.presenter.LessonPresenter;
import com.example.swimminggo.presenter.RecordPresenter;
import com.example.swimminggo.presenter.presenterImpl.LessonPresenterImpl;
import com.example.swimminggo.presenter.presenterImpl.RecordPresenterImpl;
import com.example.swimminggo.singleton.TotalRecord;

import java.util.ArrayList;
import java.util.List;

public class CreateRecordWithLesson extends AppCompatActivity {

    RecyclerView recyclerView;
    LessonPresenter lessonPresenter;
    static Lesson lesson;
    Button btnFinish;
    static List<List<Record>> lists = new ArrayList<>();
    static int phaseId;
    int teamId;
    RecordPresenter recordPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record_with_lesson);
        initComponent();
        initDatabase();
        action();
    }

    private void action() {
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFullList()) {
                    ArrayList<Record> records = new ArrayList<>();
                    for (ArrayList<Record> listRecords : TotalRecord.getInstance().getTotalRecord()){
                        for(Record record : listRecords){
                            records.add(record);
                        }
                    }
                    recordPresenter.onCreateRecord(records);
                } else{
                    Toast.makeText(CreateRecordWithLesson.this, "Nhập hết đi đã", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isFullList() {
        for(ArrayList<Record> records : TotalRecord.getInstance().getTotalRecord()){
            if (records.size() == 0){
                return false;
            }
        }
        return true;
    }

    private void initComponent() {
        if (teamId == 0)
            teamId = getIntent().getIntExtra("team_id", 0);
        if (lesson == null)
            lesson = (Lesson) getIntent().getSerializableExtra("lesson");
        if (phaseId == 0)
            phaseId = getIntent().getIntExtra("phase_id", 0);
        lessonPresenter = new LessonPresenterImpl(this);
        recordPresenter = new RecordPresenterImpl(this);
        btnFinish = findViewById(R.id.btn_finish);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void initDatabase() {
        lessonPresenter.onGetListExerciseByPhaseId(phaseId, lesson.getId());
    }

    public void setUpRecyclerView(List<Exercise> exercises) {
        if (TotalRecord.getInstance().getTotalRecord().size() == 0) {
            for (Exercise exercise : exercises) {
                TotalRecord.getInstance().getTotalRecord().add(new ArrayList<>());
            }
        }
        LessonAdapter lessonAdapter = new LessonAdapter(exercises, teamId, phaseId);
        recyclerView.setAdapter(lessonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreateRecordWithLesson.this, LinearLayoutManager.VERTICAL, false));

    }

    public void doCreateRecord(boolean b) {
        if (b){
            startActivity(new Intent(CreateRecordWithLesson.this, MainActivity.class));
        }
    }
}

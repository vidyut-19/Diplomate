package com.example.arunnagarajan.diplomate.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.arunnagarajan.diplomate.Models.Task;
import com.example.arunnagarajan.diplomate.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    Button Datebtn;
    TextView dateView;
Button submitBtn  ;
EditText taskName, descText;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Datebtn = findViewById(R.id.dateBtn);
        dateView = findViewById(R.id.dateView);
        submitBtn = findViewById(R.id.submitBtn);
        taskName = findViewById(R.id.taskName);
        descText = findViewById(R.id.descText);
mAuth = FirebaseAuth.getInstance();
        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateView.setText(
                        i2 + "-" + (i1+1) + "-" + i);


            }
        };
        final Calendar calendar = Calendar.getInstance();
        View.OnClickListener datePicker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog Picker = new DatePickerDialog(
                        AddTaskActivity.this, dateListener, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
                );
                Picker.show();
            }
        };
    Datebtn.setOnClickListener(datePicker);

    submitBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Task myTask = new Task(
                    taskName.getText().toString(),
                    descText.getText().toString(),
                    dateView.getText().toString()

            ); Map<String,Task> map = new HashMap<>();
            map.put("Task", myTask);
            database.collection("Users").document(mAuth.getCurrentUser().getEmail()).collection("Tasks").add(map);
        }
    });


    }
}

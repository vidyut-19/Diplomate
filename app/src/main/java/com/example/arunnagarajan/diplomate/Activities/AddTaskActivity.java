package com.example.arunnagarajan.diplomate.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arunnagarajan.diplomate.Adapters.AlarmReceiver;
import com.example.arunnagarajan.diplomate.Adapters.Notifications;
import com.example.arunnagarajan.diplomate.Models.Task;
import com.example.arunnagarajan.diplomate.Models.UserProfile;
import com.example.arunnagarajan.diplomate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddTaskActivity extends AppCompatActivity {
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    String email;
    Button Datebtn;
    TextView dateView;
    Button submitBtn;
    EditText taskName, descText;
    Spinner subject_spinner;
    Date taskDate;
    ConstraintLayout layout;
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
        layout = findViewById(R.id.dateText);
        mAuth = FirebaseAuth.getInstance();
        email = mAuth.getCurrentUser().getEmail();
String currentTimeString = new SimpleDateFormat("dd-MM-yyyy").format(new Date());


        database.collection("Users").document(email).collection("profile").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {

            }
        });
        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                dateView.setText(
                        i2 + "-" + (i1 + 1) + "-" + i);


            }
        };
        final Calendar calendar = Calendar.getInstance();
        dateView.setText(currentTimeString);

        View.OnClickListener datePicker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog Picker = new DatePickerDialog(
                        AddTaskActivity.this, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
                );
                Picker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                Picker.show();
            }
        };
        Datebtn.setOnClickListener(datePicker);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!taskName.getText().toString().isEmpty() && !dateView.getText().toString().isEmpty()) {
                    Task myTask = new Task(
                            taskName.getText().toString(),
                            descText.getText().toString(),
                            dateView.getText().toString()


                    );
                    Map<String, Task> map = new HashMap<>();
                    map.put("Task", myTask);
                    database.collection("Users").document(mAuth.getCurrentUser().getEmail()).collection("Tasks").add(myTask);

                    Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
                    startActivity(intent);
//                Notifications.setReminder(this,AlarmReceiver.class, );
                } else {
                    Snackbar.make(layout, "Please add task and select due date", Snackbar.LENGTH_SHORT).show();

                }
            }
        });


    }
}

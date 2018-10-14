package com.example.arunnagarajan.diplomate.Activities;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.arunnagarajan.diplomate.Models.Task;
import com.example.arunnagarajan.diplomate.Models.UserProfile;
import com.example.arunnagarajan.diplomate.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileEditActivity extends AppCompatActivity {
    EditText Name, emailID;
    Button pickYear, saveBtn;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        firebaseAuth = FirebaseAuth.getInstance();
        Name = findViewById(R.id.Name);
        emailID = findViewById(R.id.emailID);
        pickYear = findViewById(R.id.pickYear);
        saveBtn = findViewById(R.id.saveBtn);
        emailID.setText(firebaseAuth.getCurrentUser().getEmail());

        final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                pickYear.setText(i);


            }
        };
        final Calendar calendar = Calendar.getInstance();
        View.OnClickListener datePicker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog Picker = new DatePickerDialog(
                        ProfileEditActivity.this, dateListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
                );
                Picker.show();
            }
        };
        pickYear.setOnClickListener(datePicker);
        final List<String> subjects = new ArrayList<>();
        View.OnClickListener saveListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProfile profile = new UserProfile(Name.getText().toString(), emailID.getText().toString(), subjects);
                Map<String,UserProfile> map = new HashMap<>();
                map.put(emailID.getText().toString(), profile);
                database.collection("Users").document(emailID.getText().toString()).collection("profile").add(profile);
            }
        };
        saveBtn.setOnClickListener(saveListener);
    }


}

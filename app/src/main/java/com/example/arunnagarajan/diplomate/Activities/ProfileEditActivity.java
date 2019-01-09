package com.example.arunnagarajan.diplomate.Activities;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.arunnagarajan.diplomate.Models.Task;
import com.example.arunnagarajan.diplomate.Models.UserProfile;
import com.example.arunnagarajan.diplomate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileEditActivity extends AppCompatActivity {
    private static final String TAG = "APCheck";
    EditText Name, emailID;
    Button pickYear, saveBtn;
    FirebaseAuth firebaseAuth;
    DatePickerDialog.OnDateSetListener dateListener;

    FirebaseFirestore database = FirebaseFirestore.getInstance();
    List<String> subjects = new ArrayList<>();
Spinner hl1, hl2, hl3, sl1, sl2, sl3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        firebaseAuth = FirebaseAuth.getInstance();
//        database.collection("users").document(firebaseAuth.getCurrentUser().getEmail()).collection("profile");
        Name = findViewById(R.id.Name);
        emailID = findViewById(R.id.emailID);
        pickYear = findViewById(R.id.pickYear);
        saveBtn = findViewById(R.id.saveBtn);
        sl1 = findViewById(R.id.sl1);
        sl2 = findViewById(R.id.sl2);
        sl3 = findViewById(R.id.sl3);
        hl1 = findViewById(R.id.hl1);
        hl2 = findViewById(R.id.hl2);
        hl3 = findViewById(R.id.hl3);
        emailID.setText(firebaseAuth.getCurrentUser().  getEmail());

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

               Log.d("testing", i + "");
               if(i != 0) {
                    pickYear.setText(i+"");
                }
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

        View.OnClickListener saveListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjects.add(sl1.getSelectedItem().toString());
                subjects.add(sl2.getSelectedItem().toString());
                subjects.add(sl3.getSelectedItem().toString());
                subjects.add(hl1.getSelectedItem().toString());
                subjects.add(hl2.getSelectedItem().toString());
                subjects.add(hl3.getSelectedItem().toString());
                UserProfile profile = new UserProfile(Name.getText().toString(), emailID.getText().toString(),pickYear.getText().toString(), subjects);
                Map<String,UserProfile> map = new HashMap<>();
                map.put("profile", profile);
                database.collection("Users").document(emailID.getText().toString()).set(map);
            }
        };
        saveBtn.setOnClickListener(saveListener);

        // get profile info to update!
        DocumentReference docRef = database.collection("Users").document(firebaseAuth.getCurrentUser().getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null) {
                        Log.d(TAG, task.getResult().getData().get("name").toString());
                        Log.d(TAG, task.getResult().getData().toString());
                        UserProfile user = task.getResult().toObject(UserProfile.class);
//                        Log.d(TAG, user.getEmail());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
            });
    }


}

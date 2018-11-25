package com.example.arunnagarajan.diplomate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.arunnagarajan.diplomate.Fragments.TaskFragment;
import com.example.arunnagarajan.diplomate.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addTaskbtn;
ImageButton ProfileEditButton;
FrameLayout fragmentContainer;

    private TextView mTextMessage;
    FirebaseFirestore database = FirebaseFirestore.getInstance();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            selectedFragment = new TaskFragment();
            //TODO verify this
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   selectedFragment = new TaskFragment();
                    break;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frame, selectedFragment);
            transaction.commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addTaskbtn = findViewById(R.id.floatingActionButton);
        fragmentContainer = findViewById(R.id.frame);
        View.OnClickListener fabListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToaddtask= new Intent(MainActivity.this,AddTaskActivity.class);
            startActivity(goToaddtask);
            }
        };
        addTaskbtn.setOnClickListener(fabListener);
        mTextMessage = (TextView) findViewById(R.id.message);
        ProfileEditButton = findViewById(R.id.ProfileButton);
        ProfileEditButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent ProfileEditActivity = new Intent(MainActivity.this, ProfileEditActivity.class);
                        startActivity(ProfileEditActivity);
                    }
                }
        );
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}

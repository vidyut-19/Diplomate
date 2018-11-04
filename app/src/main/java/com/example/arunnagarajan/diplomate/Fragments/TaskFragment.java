package com.example.arunnagarajan.diplomate.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arunnagarajan.diplomate.Adapters.TaskListAdapter;
import com.example.arunnagarajan.diplomate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class TaskFragment extends Fragment {

    FirebaseFirestore database;
    FirebaseAuth mAuth;
    String userEmail;
RecyclerView Tasklistview;
TextView emptyTasktext;
    public TaskFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        userEmail = mAuth.getCurrentUser().getEmail();
        database = FirebaseFirestore.getInstance();

      CollectionReference dataBasePointer = database.collection("Users").document(userEmail).collection("Tasks");
dataBasePointer.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
    @Override
    public void onComplete(@NonNull Task<QuerySnapshot> task) {
        ArrayList<com.example.arunnagarajan.diplomate.Models.Task> taskList = new ArrayList<>();
        if (task.isSuccessful()) {
            for (QueryDocumentSnapshot x :
                    task.getResult()) {
                com.example.arunnagarajan.diplomate.Models.Task temp = x.toObject(com.example.arunnagarajan.diplomate.Models.Task.class);
                temp.getName();
                taskList.add(temp);
            }
            Log.i("Success tasks", taskList.size()+"");

            TaskListAdapter adapter = new TaskListAdapter(taskList);
            Tasklistview.setHasFixedSize(true);
            Tasklistview.setLayoutManager(new LinearLayoutManager(getContext()));
            Tasklistview.setAdapter(adapter);
            if(taskList.size() > 0)
            { emptyTasktext.setVisibility(View.GONE);

            }
        } else {
            Log.i("failed tasks", "get failed with"+ task.getException());
        }
    }
});
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        Tasklistview = view.findViewById(R.id.taskList);
        emptyTasktext =view.findViewById(R.id.emptyTasktext);

        return view;
    }

}

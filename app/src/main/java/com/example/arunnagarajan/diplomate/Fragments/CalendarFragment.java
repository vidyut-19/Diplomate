package com.example.arunnagarajan.diplomate.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.arunnagarajan.diplomate.Adapters.TaskListAdapter;
import com.example.arunnagarajan.diplomate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.riontech.calendar.CustomCalendar;
import com.riontech.calendar.dao.EventData;
import com.riontech.calendar.dao.dataAboutDate;
import com.riontech.calendar.utils.CalendarUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {
    CalendarView calendar;
private CustomCalendar customCalendar;
    FirebaseFirestore database;
    FirebaseAuth mAuth;
    String userEmail;
    ArrayList<com.example.arunnagarajan.diplomate.Models.Task> mainTasks = new ArrayList<>();
    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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


                    if(taskList.size() > 0)
                    { //emptyTasktext.setVisibility(View.GONE);
                        customCalendar.addAnEvent("2019-01-10",3,getEventDataList(3));
                        mainTasks = taskList;
                        for (com.example.arunnagarajan.diplomate.Models.Task mtask: mainTasks) {
                            customCalendar.addAnEvent(mtask.getDate(),3,getEventDataList(3));

                        }

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
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        customCalendar = view.findViewById(R.id.customCalendar);
        String [] dates = {};

      //  String[] arr = {"2019-01-10", "2019-01-11", "2019-01-15", "2019-01-16", "2019-01-25"};
        //for (int i = 0; i < 5; i++) {
            //int eventCount = 3;
          //  customCalendar.addAnEvent(arr[i], eventCount,getEventDataList(eventCount));
//        }
        return view;
    };

    public ArrayList<EventData> getEventDataList(int count) {
        ArrayList<EventData> eventDataList = new ArrayList();

        for (int i = 0; i < count; i++) {
            EventData dateData = new EventData();
            ArrayList<dataAboutDate> dataAboutDates = new ArrayList();

            dateData.setSection(CalendarUtils.getNAMES()[new Random().nextInt(CalendarUtils.getNAMES().length)]);
            dataAboutDate dataAboutDate = new dataAboutDate();

            int index = new Random().nextInt(CalendarUtils.getEVENTS().length);

            dataAboutDate.setTitle(CalendarUtils.getEVENTS()[index]);
            dataAboutDate.setSubject(CalendarUtils.getEventsDescription()[index]);
            dataAboutDates.add(dataAboutDate);

            dateData.setData(dataAboutDates);
            eventDataList.add(dateData);
        }

        return eventDataList;
    }

}

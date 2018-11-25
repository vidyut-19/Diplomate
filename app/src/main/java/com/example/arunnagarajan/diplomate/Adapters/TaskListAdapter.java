package com.example.arunnagarajan.diplomate.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arunnagarajan.diplomate.Models.Task;
import com.example.arunnagarajan.diplomate.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Task> tasks = new ArrayList<>();
    Context context;

    public TaskListAdapter(ArrayList<Task> tasks,Context context) {
        this.tasks = tasks;
        this.context = context;
    }


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rowView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_row, viewGroup, false);
        TaskViewHolder th = new TaskViewHolder(rowView);
        return th;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
        taskViewHolder.taskName.setText(tasks.get(i).getName());
        taskViewHolder.date.setText(tasks.get(i).getFormattedDate());
//        taskViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setMessage("Are you sure you want to delete the task? You may not be done with it. Don't delete just because you don't like the subject or the teacher ")
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//
//                            }
//                        })
//                        .setNegativeButton("Delete Task", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // User cancelled the dialog
//                            }
//                        });
//                // Create the AlertDialog object and return it
////                builder.create();
//                AlertDialog dialog = builder.create();
//                dialog.show();
//
//            }
//        });
        taskViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete the task? You may not be done with it. Don't delete just because you don't like the subject or the teacher ")
                                .setPositiveButton("Delete Task", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        db.collection("Users").document("vidyut.baradwaj2002@gmail.com").collection("Tasks").document("PjRd5MfUNjFPrVHGdd7f")
                                                .delete()
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });


                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // User cancelled the dialog
                                    }
                                });
                        // Create the AlertDialog object and return it
                AlertDialog dialog = builder.create();
                dialog.show();
                        return true;

            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView date, taskName;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dueDate);
            taskName = itemView.findViewById(R.id.taskName);
        }
    }

}
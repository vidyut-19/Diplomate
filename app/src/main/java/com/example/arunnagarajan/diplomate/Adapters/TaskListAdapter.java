package com.example.arunnagarajan.diplomate.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arunnagarajan.diplomate.Models.Task;
import com.example.arunnagarajan.diplomate.R;

import java.util.ArrayList;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    ArrayList<Task> tasks = new ArrayList<>();

    public TaskListAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
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
        taskViewHolder.date.setText(tasks.get(i).getDate());


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
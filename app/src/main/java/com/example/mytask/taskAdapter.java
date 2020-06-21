package com.example.mytask;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.MyViewHolder> {
    Context context;
    ArrayList<Mytask> mytask;
    public  taskAdapter(Context c, ArrayList<Mytask> p){
        context=c;
        mytask=p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.task_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            myViewHolder.taskTitle.setText(mytask.get(i).getTaskTitle());
            myViewHolder.taskDes.setText(mytask.get(i).getTaskDes());
            myViewHolder.taskDate.setText(mytask.get(i).getTaskDate());
    }

    @Override
    public int getItemCount() {
        return mytask.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
         private TextView taskTitle, taskDes,taskDate;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle=(TextView) itemView.findViewById(R.id.taskTitle);
            taskDes=(TextView) itemView.findViewById(R.id.desTask);
            taskDate=(TextView) itemView.findViewById(R.id.dateTask);

        }
    }
}

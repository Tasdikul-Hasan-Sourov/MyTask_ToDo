package com.example.mytask;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
            final String getTaskTitle=mytask.get(i).getTaskTitle();
            final String getTaskDes=mytask.get(i).getTaskDes();
            final String getTaskDate=mytask.get(i).getTaskDate();
            final String getKeyTask=mytask.get(i).getKeyTask();
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ed= new Intent(context, EditTask.class);
                    ed.putExtra("taskTitle",getTaskTitle);
                    ed.putExtra("taskDes",getTaskDes);
                    ed.putExtra("taskDate",getTaskDate);
                    ed.putExtra("keyTask",getKeyTask);
                    context.startActivity(ed);
                }
            });
    }

    @Override
    public int getItemCount() {
        return mytask.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
         private TextView taskTitle, taskDes,taskDate,keyTask;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTitle=(TextView) itemView.findViewById(R.id.taskTitle);
            taskDes=(TextView) itemView.findViewById(R.id.desTask);
            taskDate=(TextView) itemView.findViewById(R.id.dateTask);

        }
    }
}

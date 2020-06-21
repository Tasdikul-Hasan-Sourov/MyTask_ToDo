package com.example.mytask;

public class Mytask {
    String taskTitle, taskDes, taskDate;

    public Mytask() {

    }

    public Mytask(String taskTitle, String taskDes, String taskDate) {
        this.taskTitle = taskTitle;
        this.taskDes = taskDes;
        this.taskDate = taskDate;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDes() {
        return taskDes;
    }

    public void setTaskDes(String taskDes) {
        this.taskDes = taskDes;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }
}

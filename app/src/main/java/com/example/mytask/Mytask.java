package com.example.mytask;

public class Mytask {
   private String taskTitle;
   private String taskDes;
   private String taskDate;
   private String keyTask;
   private String uid;

    public Mytask() {

    }

    public Mytask(String taskTitle, String taskDes, String taskDate, String keyTask, String uid) {
        this.taskTitle = taskTitle;
        this.taskDes = taskDes;
        this.taskDate = taskDate;
        this.keyTask = keyTask;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKeyTask() {
        return keyTask;
    }

    public void setKeyTask(String keyTask) {
        this.keyTask = keyTask;
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


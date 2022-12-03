package com.example.seisd_pro;

public class ExamScheduleTableClass {

    private  String examDate;
    private  String courseName;
    private  String examTime;
    private  String ExamRoom;

    public ExamScheduleTableClass(String examDate, String courseName, String examTime, String examRoom) {
        this.examDate = examDate;
        this.courseName = courseName;
        this.examTime = examTime;
        this.ExamRoom = examRoom;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getExamRoom() {
        return ExamRoom;
    }

    public void setExamRoom(String examRoom) {
        ExamRoom = examRoom;
    }
}

package com.example.seisd_pro;

import java.time.LocalDate;

public class ExamScheduleTableClass {

    private  int no;
    private LocalDate examDate;
    private  String courseName;
    private  String examTime;
    private  String ExamRoom;



    public ExamScheduleTableClass(int no, LocalDate examDate, String courseName, String examTime, String examRoom) {
        this.no = no;
        this.examDate = examDate;
        this.courseName = courseName;
        this.examTime = examTime;
        this.ExamRoom = examRoom;
    }

    public int getNo() {return no;}
    public void setNo(int no) {this.no = no;}
    public LocalDate getExamDate() {
        return examDate;
    }
    public void setExamDate(LocalDate examDate) {
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

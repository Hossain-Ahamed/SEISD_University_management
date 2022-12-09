package com.example.seisd_pro;

public class viewRoutinetable {
    private  String Date;
    private  String CourseName;
    private  String Time;
    private  String Room;

    public viewRoutinetable(String date, String courseName, String time, String room) {
        Date = date;
        CourseName = courseName;
        Time = time;
        Room = room;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }
}

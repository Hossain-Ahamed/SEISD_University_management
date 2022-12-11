package com.example.seisd_pro;

public class Code_Name_Credit_table {
  private String CourseCode;
    private String CourseName;
    private String CourseCredit;
    public Code_Name_Credit_table(String courseCode, String courseName, String courseCredit) {
        CourseCode = courseCode;
        CourseName = courseName;
        CourseCredit = courseCredit;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCourseCredit() {
        return CourseCredit;
    }

    public void setCourseCredit(String courseCredit) {
        CourseCredit = courseCredit;
    }
}

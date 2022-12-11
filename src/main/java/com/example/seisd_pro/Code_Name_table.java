package com.example.seisd_pro;

public class Code_Name_table {
    private String CourseCode;
    private String CourseName;

    public Code_Name_table(String courseCode, String courseName) {
        CourseCode = courseCode;
        CourseName = courseName;

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

}

package com.example.seisd_pro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class test2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://uffttslvl1ffduya:bfDXWVyitdqs8PGBp4f4@bgs7tzjpys5zfnmraodc-mysql.services.clever-cloud.com:3306/bgs7tzjpys5zfnmraodc";
        String user = "uffttslvl1ffduya";
        String password = "bfDXWVyitdqs8PGBp4f4";
        //connection with driver
        Connection c1 = DriverManager.getConnection(url, user, password);
        // creaate a statement
        Statement s = c1.createStatement();
        //thorugh the connection, so it can be used later
        new jdbc(c1,s);


        // Get info about the semester
        String semOrder = "SELECT * FROM `information` WHERE attribute ='courseOffer'";
        String jsonText ="";
        ResultSet r = s.executeQuery(semOrder);
        while (r.next()) {jsonText=r.getString("value");}



        // Get info about the semester
        String runningCourseOrder = "SELECT * FROM `information` WHERE attribute ='runningCourseData'";
        String runningCourseJsonData ="";
        ResultSet r1 = s.executeQuery(runningCourseOrder);
        while (r1.next()) {runningCourseJsonData=r1.getString("value");}




        //all course name of batches
        Object obj1 =JSONValue.parse(jsonText);
        JSONObject CourseOFBatches_JsonObj = (JSONObject) obj1;
        System.out.println(CourseOFBatches_JsonObj);
        JSONArray assignedCourse ;



        //batch name that are assigned to that course
        Object obj2 =JSONValue.parse(runningCourseJsonData);
        JSONObject assignedBatchOfThatCourse_JsonObj = (JSONObject) obj2;
        System.out.println("course ------   "+assignedBatchOfThatCourse_JsonObj);
        JSONArray assignedBatch ;


        //keyset of jar array
        List<String> l = new ArrayList<String>(CourseOFBatches_JsonObj.keySet());
        for ( int j =0 ;j<l.size();j++){
            assignedCourse= (JSONArray) CourseOFBatches_JsonObj.get(l.get(j));
//            assignedCourse= (JSONArray) CourseOFBatches_JsonObj.get(0);


            System.out.println(l.get(j)+" : they are assigned for : "+assignedCourse);
            System.out.println(assignedCourse.get(0));// first course name of that batch course -- like cse111

        }

        // batch data collect of that course
        assignedBatch = (JSONArray) assignedBatchOfThatCourse_JsonObj.get("PHY113");


        //delete that course from the conflict and that own batch
        for (int i = 0; i < assignedBatch.size(); i++) {
            assignedCourse = (JSONArray) CourseOFBatches_JsonObj.get(assignedBatch.get(i).toString());
            assignedCourse.remove("PHY113");
        }









        System.out.println(CourseOFBatches_JsonObj);
        String jsonT = JSONValue.toJSONString(CourseOFBatches_JsonObj);
        System.out.print(jsonT);
        System.out.println(assignedBatchOfThatCourse_JsonObj);

    }
}

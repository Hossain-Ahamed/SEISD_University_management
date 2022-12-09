package com.example.seisd_pro;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class test3 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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

        HashMap<String, String> courseinfo = new HashMap<String, String>();

        String Order = "SELECT * FROM `courseinfo`";
        ResultSet r = s.executeQuery(Order);
        while (r.next()) {
            courseinfo.put(r.getString("CourseCode"),r.getString("CourseName"));

        }
//        System.out.println(courseinfo);

        // Getting an iterator
        Iterator hmIterator = courseinfo.entrySet().iterator();

        // Iterating through Hashmap and
        // adding some bonus marks for every student
        while (hmIterator.hasNext()) {

            Map.Entry mapElement
                    = (Map.Entry)hmIterator.next();

            // Printing mark corresponding to string entries
            System.out.println(mapElement.getKey()+": "+mapElement.getValue());
        }
        System.out.println("Searching Element : ENG121 : "+ courseinfo.get("ENG121"));
        String abc = "abc";
        int a = abc.length();
        System.out.println(abc.substring(0,a-1));



    }
}

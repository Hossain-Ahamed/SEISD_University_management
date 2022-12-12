package com.example.seisd_pro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class utilities {
    static Connection c1;
    static Statement s;
    public static void setJDBC(Connection c1, Statement s){
        utilities.c1 = c1;
        utilities.s = s;
    }
    public static String semsterName;

    public static void setSemester(String semsterName) {
        utilities.semsterName = semsterName;
    }


    public static String getJsonText(String order) throws SQLException {

        String JsonText = "";
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            JsonText = r.getString("value");
        }
        return JsonText;
    }
    public static JSONObject getJsonObj(String JSONTEXT) {
        Object obj = JSONValue.parse(JSONTEXT);
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }

    static JSONObject AllCourseJsonObj = new JSONObject();
    static JSONArray  AllCourseNameArray= new JSONArray();

    public static void AllCourseData() throws SQLException {
       ResultSet r = s.executeQuery("SELECT * FROM `courseinfo`");
       while(r.next()){
           String CourseCode = r.getString("CourseCode");
           String CourseName = r.getString("CourseName");
           String CourseCredit = r.getString("Credit");
           String Prerequisite = r.getString("Prerequisite");
           AllCourseNameArray.add(CourseCode);
       AllCourseJsonObj.put(CourseCode + "Name",CourseName);
       AllCourseJsonObj.put(CourseCode + "Credit",CourseCredit);
       AllCourseJsonObj.put(CourseCode + "Prerequisite",Prerequisite);
       }
    }

    public static String thisSemester() {
        return utilities.semsterName;
    }

    //return false if null
    public static boolean isNotNull(String string) {
        return !"".equals(string.trim());
    }

    public  static  String getTimeDate(){
        java.util.Date date = new java.util.Date();
        return date.toString();

    }
    //return true if string is a digit
    public static boolean isInteger(String digit) {
        int tryuserID;
        try {tryuserID = Integer.parseInt(digit.trim());} catch (NumberFormatException e) {return false;}
        return true;
    }

    //true if a mail
    public static String isEmail(String mail) {
        String mail_Regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern email_Pat = Pattern.compile(mail_Regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = email_Pat.matcher(mail);
        String ansToString = String.valueOf(matcher.find());
        return ansToString;
    }

    //return true if contains both upper n lower case,special character & digit exist
    public static boolean isValidPassword(String pass) {
        String Pass_Regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=!'])" + "(?=\\S+$).{8,20}$";
        Pattern pattern = Pattern.compile(Pass_Regex);
        if (pass == null) {return false;}
        Matcher matcher = pattern.matcher(pass);
        return matcher.matches();
    }




    public static boolean isValidTime(String time) {
        if (time != "") {
            String regex = "^(.*?):(.*?)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(time);
            matcher.find();
            if (matcher.matches() == true &&
                    isInteger(matcher.group(1)) ==true && isInteger(matcher.group(2)) ==true &&
                    Integer.parseInt(matcher.group(1)) >= 0 && Integer.parseInt(matcher.group(1)) <= 23 &&
                    Integer.parseInt(matcher.group(2)) >= 0 && Integer.parseInt(matcher.group(2)) <= 59) {
                return true;
            }
        }
        return false;
    }
}

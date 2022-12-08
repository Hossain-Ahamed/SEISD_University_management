package com.example.seisd_pro;
import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class utilities {
    public static String semsterName;

    public static void setSemester(String semsterName) {
        utilities.semsterName = semsterName;
    }


    public static String thisSemester() {
        return utilities.semsterName;
    }

    //return false if null
    public static boolean isNotNull(String string) {
        return !"".equals(string.trim());
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

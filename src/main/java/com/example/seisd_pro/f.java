package com.example.seisd_pro;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class f {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String a;
        while ( true){
            a = s.nextLine();
            String regex = "^(.*?)-(.*?)-(.*?)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(a);
            matcher.find();  //matcher.group(1) = 111 ,matcher.group(2) = 112,matcher.group(3) = 113

            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));

        }
    }
}

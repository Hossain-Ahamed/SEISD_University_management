package com.example.seisd_pro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.Iterator;

public class test {
    public static void main(String[] args) {


        JSONObject obj=new JSONObject();
        obj.put("name","sonoo");
        obj.put("age",27);
        obj.put("salary",600000);
        System.out.print(obj);


        ///convert to string to push
        String jsonText = JSONValue.toJSONString(obj);
        System.out.print(jsonText);


        //get dataa from string ----- convert string to json
        Object obj1 =JSONValue.parse(jsonText);
        JSONObject jsonObject = (JSONObject) obj1;
        String name = (String) jsonObject.get("name");
        double salary = (Double) jsonObject.get("salary");
        long age = (Long) jsonObject.get("age");


        //new input & change previous value
        jsonObject.put("bage",754743);
        jsonObject.put("age",000);

        //array add in obj
        JSONArray arr = new JSONArray();
        arr.add("sonoo");
        arr.add(27);
        arr.add(600000);
        jsonObject.put("arr",arr);


        System.out.println(jsonObject);
        jsonObject.remove("bage");
        System.out.println(jsonObject);


        //array data fetch
        JSONArray jararray = (JSONArray) jsonObject.get("arr");

        System.out.println(jararray.get(0));
        System.out.println(jararray.remove(0));
        System.out.println(jsonObject);

/*
        String a = "A,B,C,D";
        String b = "a,b,c,d";
        String[] s = a.split(",");
        String[] ss = b.split(",");
    ArrayList<ArrayList<String>> s1  = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < s.length; i++) {
          s1.add(new ArrayList<String>());
            s1.get(i).add(s[i]);
            for (int j = 0; j < ss.length; j++) {

                s1.get(i).add(ss[j]);

            }
        }


//       s1.get(s1.indexOf("A")).remove("a");

        for (int i = 0; i < s.length; i++) {

            for (int j = 0; j < s1.get(i).size(); j++) {

                System.out.printf(s1.get(i).get(j));


            }
            System.out.println("\n");
        }
//        for (int i = 0; i < s1.size(); i++) {
//            if(i==2){
//                s1.get(i).remove("b");
//            }
//            else
//                s1.get(i).remove("a");
//        }
//       s1.get(s1.indexOf("A")).remove("a");
        s1.get(0).remove(1);
        for (int i = 0; i < s.length; i++) {

            for (int j = 0; j <s1.get(i).size(); j++) {

                System.out.print(s1.get(i).get(j));


            }
            System.out.println("\n");
        }

        System.out.println(s1);
   ArrayList<String[]> outerArr = new ArrayList<String[]>();

        String[] myString1= {"hey","hey","hey","hey"};
        outerArr .add(myString1);
        String[] myString2= {"you","you","you","you"};
        outerArr .add(myString2);

        System.out.println(outerArr.size());
        for(int i=0;i<outerArr.size();i++){

            String[] myString= new String[4];
            myString=outerArr.get(i);
            for(int j=0;j<myString.length;j++){
                System.out.print(myString[j]+" ");
            }
            System.out.print("\n");

        }*/

    }
}

package com.example.seisd_pro;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
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
     /*   ArrayList<String[]> outerArr = new ArrayList<String[]>();

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

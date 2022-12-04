package com.example.seisd_pro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.Iterator;
public class jsonArrayOfObj {
    public static   Object getStringValueObj(String key,String value){
        JSONObject obj=new JSONObject();
        obj.put(key,value);
        return obj;
    }
    public static   Object getDoubleValueObj(String key,Double value){
        JSONObject obj=new JSONObject();
        obj.put(key,value);
        return obj;
    }


    public static void main(String[] args) {
        JSONObject obj=new JSONObject();
        obj.put("name","Hossain");
        obj.put("ID",20100056);

        System.out.println(obj);

        String jsonText = JSONValue.toJSONString(obj);
        System.out.println("text form "+jsonText);

        Object obj1 =JSONValue.parse(jsonText);
        JSONObject jsonObject = (JSONObject) obj1;





        //array add in obj
        JSONArray arr = new JSONArray();

        arr.add(jsonArrayOfObj.getDoubleValueObj("Cse311",100.0));
        arr.add(jsonArrayOfObj.getDoubleValueObj("Cse312",0.0));
        arr.add(jsonArrayOfObj.getDoubleValueObj("CSE313",3.5));
        String sem = "Fall2022";
        jsonObject.put(sem,arr);

        arr.clear();
        arr.add(jsonArrayOfObj.getDoubleValueObj("GED311",4.0));
        arr.add(jsonArrayOfObj.getDoubleValueObj("GED312",2.0));
        arr.add(jsonArrayOfObj.getDoubleValueObj("GED313",3.6));
        sem = "Spring2023";
        jsonObject.put(sem,arr);

        System.out.println(jsonObject);


        //array data fetch
        JSONArray jararray = (JSONArray) jsonObject.get(sem);


        Iterator<Object> iterator = jararray.iterator();

        while (iterator.hasNext()) {
            JSONObject jsonOb = (JSONObject) iterator.next();

            for (Object key : jsonOb.keySet()) {
//                System.out.println(key + "::::::::" + jsonOb.get(key));
                System.out.println(key);
            }
        }

    }
}

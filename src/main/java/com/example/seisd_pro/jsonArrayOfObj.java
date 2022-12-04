package com.example.seisd_pro;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

<<<<<<< HEAD
import java.sql.*;
=======
>>>>>>> 8dac319eb08e5fcc0398e0c216399aabd7c13376
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


<<<<<<< HEAD
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
  String jsonText = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/seisd?severTimezone=UTC";
        String user = "root";
        String password = "";
        //connection with driver
        Connection c1 = DriverManager.getConnection(url, user, password);
        // creaate a statement
        Statement s = c1.createStatement();
        //String order = "INSERT INTO `char` (`name`) VALUES ('"+jsonText+"')";
        String order = "SELECT * FROM `student`where id = 20100064";
       // s.executeUpdate(order);
        ResultSet r = s.executeQuery(order);
        while (r.next()) {
            jsonText = r.getString("info");




        }


        //thorugh the connection, so it can be used later
        new jdbc(c1,s);
=======
    public static void main(String[] args) {
        JSONObject obj=new JSONObject();
        obj.put("name","Hossain");
        obj.put("ID",20100056);

        System.out.println(obj);

        String jsonText = JSONValue.toJSONString(obj);
        System.out.println("text form "+jsonText);
>>>>>>> 8dac319eb08e5fcc0398e0c216399aabd7c13376

        Object obj1 =JSONValue.parse(jsonText);
        JSONObject jsonObject = (JSONObject) obj1;





        //array add in obj
        JSONArray arr = new JSONArray();

<<<<<<< HEAD
        String sem = "Spring2020";


=======
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
>>>>>>> 8dac319eb08e5fcc0398e0c216399aabd7c13376

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

<<<<<<< HEAD


=======
>>>>>>> 8dac319eb08e5fcc0398e0c216399aabd7c13376
    }
}

package com.example.seisd_pro;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.net.*;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class pc_info {
    public static LocalDateTime getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return now;
    }
    public static String pc_config(){

        InetAddress ip;
        try {
            JSONObject obj=new JSONObject();
            ip = InetAddress.getLocalHost();
            obj.put("ip",ip.getHostAddress());
            String nameOS= System.getProperty("os.name");
            String osType= System.getProperty("os.arch");
            String osVersion= System.getProperty("os.version");
            obj.put("OS",nameOS+",type "+ osType+",Version "+osVersion);
            obj.put("processorFamily", System.getenv("PROCESSOR_IDENTIFIER")+" "+System.getenv("PROCESSOR_ARCHITECTURE")
                    +" "+System.getenv("PROCESSOR_ARCHITEW6432")+" "+ System.getenv("NUMBER_OF_PROCESSORS"));
            /* Total number of processors or cores available to the JVM */
            obj.put("availableCore",Runtime.getRuntime().availableProcessors());
            long maxMemory = Runtime.getRuntime().maxMemory();
            obj.put("Memory","(byte)free "+Runtime.getRuntime().freeMemory()+",max "+(maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory)+",total "+Runtime.getRuntime().totalMemory());

            /* Get a list of all filesystem roots on this system */
            File[] roots = File.listRoots();
            String rootInfo = "f[file],t[total],Fr[free],u[usable]==";
            /* For each filesystem root, print some info */
            for (File root : roots) {
                rootInfo+="f ";
                rootInfo+=root.getAbsolutePath();


                rootInfo+= ",t ";
                rootInfo+=root.getTotalSpace();

                rootInfo+= ",Fr ";
                rootInfo+=root.getFreeSpace();

                rootInfo+= ",u ";
                rootInfo+=root.getUsableSpace();

                rootInfo+= " - ";
            }

            obj.put("drives",rootInfo);
            return JSONValue.toJSONString(obj);



        }
        catch (Exception e){


            e.printStackTrace();
            return e.toString();

        }


    }



    public static String[] getInforamtion() throws UnknownHostException, SocketException,IOException
    {
        //mac address
        InetAddress ipAddress = InetAddress.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(ipAddress);
        byte[] macAddressBytes = networkInterface.getHardwareAddress();
        StringBuilder macAddressBuilder = new StringBuilder();

        for (int macAddressByteIndex = 0; macAddressByteIndex < macAddressBytes.length; macAddressByteIndex++)
        {
            String macAddressHexByte = String.format("%02X", macAddressBytes[macAddressByteIndex]);
            macAddressBuilder.append(macAddressHexByte);

            if (macAddressByteIndex != macAddressBytes.length - 1)
            {
                macAddressBuilder.append(":");
            }
        }


        //public ip
        URL whatismyip = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

        String ip = in.readLine(); //you get the IP as a String

        String[] returned = {InetAddress.getLocalHost().getHostName(),macAddressBuilder.toString(),ip};
        return returned ;
    }
}

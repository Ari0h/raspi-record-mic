package ru.javastudy.springMVC.model;


import java.io.*;
import java.util.ArrayList;

public class ReadFromFile {


   // File file = new File("/home/anna/settings.txt");
   File file = new File("C:\\settings.txt");
    ArrayList<String> list = new ArrayList<String>();


    public ArrayList<String> read() {



       try {
           BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
           String s;
           try {
               while((s = in.readLine())!=null){
                   list.add(s);
               }

           }
           catch (IOException e) {
               e.printStackTrace();
           }

       }

       catch (FileNotFoundException e) {
           e.printStackTrace();
       }

       return list;

   }

    public String getName(){
        read();
        String name;
        name = list.get(0);

        return name;
    }

    public String getPath(){
       read();
       String path;
       path = list.get(1);

      return path;
    }
}

package ru.javastudy.springMVC.model;


import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTime {

    public long curTime = System.currentTimeMillis();

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HHmmss");
    public Date curDate = new Date(curTime);


    public Time curTim = new Time(curTime);

    public String getDate(){

        String date;

        date = simpleDateFormat.format(curDate);
        return date;
    }

    public String getTime(){
        String time;

        time = simpleTimeFormat.format(curDate);
        return time;
    }

}

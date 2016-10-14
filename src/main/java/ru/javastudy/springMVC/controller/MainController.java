package ru.javastudy.springMVC.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.javastudy.springMVC.model.DateAndTime;
import ru.javastudy.springMVC.model.JRecorder;
import ru.javastudy.springMVC.model.ReadFromFile;


import javax.sound.sampled.*;
import java.io.File;


@Controller
public class MainController {


   // JRecorder j = new JRecorder(targetDataLine, targetType, outputFile);
   JRecorder jRecorder = new JRecorder();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping(value = "/start")
    public ModelAndView start() {
    ModelAndView modelAndView = new ModelAndView();
        ReadFromFile rff = new ReadFromFile();
        DateAndTime dat = new DateAndTime();

        String path = rff.getPath();
        String name = rff.getName();
        String date = dat.getDate();
        String time = dat.getTime();

        File outputFile = new File(path+name+"-"+date+"-"+time+".wav");

        AudioFormat audioFormat = new AudioFormat(
                16000, 8, 1, false, false);

        DataLine.Info    info = new DataLine.Info(TargetDataLine.class, audioFormat);
        TargetDataLine    targetDataLine = null;

        {
            try {
                targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
            try {
                targetDataLine.open(audioFormat);
            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
                System.exit(1);
            }
        }
        AudioFileFormat.Type    targetType = AudioFileFormat.Type.WAVE;

        modelAndView.setViewName("start");

        jRecorder.setM_line(targetDataLine);
        jRecorder.setM_targetType(targetType);
        jRecorder.setM_outputFile(outputFile);

            jRecorder.start();

        System.out.println("Start rec");
        return modelAndView;
    }

    @RequestMapping(value = "/stop")
    public ModelAndView stop() {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("stop");
        jRecorder.stopRecording();
        System.out.println("Stop rec");
        return modelAndView;
    }

}

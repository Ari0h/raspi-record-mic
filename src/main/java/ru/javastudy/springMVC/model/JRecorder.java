package ru.javastudy.springMVC.model;

        import javax.sound.sampled.*;
        import java.io.*;
        import javax.sound.sampled.AudioFileFormat.Type;

public class JRecorder   {

    private TargetDataLine        m_line;
    private AudioFileFormat.Type    m_targetType;
    private AudioInputStream    m_audioInputStream;
    private File            m_outputFile;
    private volatile Thread thread;

    public JRecorder(){

    }

    public void setM_line(TargetDataLine m_line) {
        this.m_line = m_line;
        this.m_audioInputStream = new AudioInputStream(m_line);
    }

    public void setM_targetType(Type m_targetType) {
        this.m_targetType = m_targetType;
    }

    public void setM_outputFile(File m_outputFile) {
        this.m_outputFile = m_outputFile;
    }

    public void start()
    {
        m_line.start();
        this.thread = new Thread(new Runnable() {
            public void run() {
                try
                {
                    AudioSystem.write(
                            m_audioInputStream,
                            m_targetType,
                            m_outputFile);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        });
           thread.start();

         }

    public void stopRecording()
    {
        m_line.stop();
        m_line.close();
        thread.interrupt();
        thread = null;
    }

}

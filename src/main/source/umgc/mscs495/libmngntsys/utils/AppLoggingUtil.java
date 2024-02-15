package umgc.mscs495.libmngntsys.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create logging for the system.
 * @author jimiewang
 * @CreateDate 01/24/2024
 */
public class AppLoggingUtil {

    /**
     * @descriptin logging the login events.  
     * @param logContent 
     */
    public boolean log(String logContent) 
    {
    	boolean suc = true;
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter out = null;
        try {
            //get current timestamp
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String LoggingTime = sdf.format(new Date());
            String logLoc = getLogFileDir();
            Path path = Paths.get(logLoc);
            Files.createDirectories(path);            
            //get log file name
            String logginhFileNm = getLogFileName();
            //carete log file if does not exist or open the log file
            fw = new FileWriter(logginhFileNm, true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            //write log into the logfile
            out.println(LoggingTime + "  " + logContent);
            out.close();
        } 
        catch (IOException e) 
        {
        	suc = false;
            System.out.println("Logging exception: " + e.getMessage());
        }
        finally 
        {
            try 
            {
                if(out != null)
                    out.close();
            } 
            catch (Exception e) 
            {
                //exception handling left as an exercise for the reader
                System.out.println("logging error.");
            }
            try {
                if(bw != null)
                    bw.close();
            } 
            catch (IOException e) 
            {
                //exception handling left as an exercise for the reader
                System.out.println("logging error.");
            }
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
                System.out.println("logging error.");
            }
        }
        return suc;
    }

    /**
     * 
     * @description get log file name
     * @return String
     */    
    public String getLogFileName()
    {
        //get current date format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String logDate = sdf.format(new Date());
        //define the log file name
        String prevLoginRecordFile = System.getProperty("user.dir") 
                + File.separatorChar + "log" + File.separatorChar + "loginlog_" + logDate + ".log";
        
        return prevLoginRecordFile;
    }
    
    /**
     * Get system log location.
     * @return String
     */
    public String getLogFileDir()
    {
    	return System.getProperty("user.dir") + File.separatorChar + "log" + File.separatorChar;
    }
    
}

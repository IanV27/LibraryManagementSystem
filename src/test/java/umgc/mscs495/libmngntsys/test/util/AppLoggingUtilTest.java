package umgc.mscs495.libmngntsys.test.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import umgc.mscs495.libmngntsys.utils.AppLoggingUtil;

/**
 * JUnit test for APIs defined in AppLoggingUtil.java.
 * @author jimiewang
 * @CreateDate 01/26/2024
 */
public class AppLoggingUtilTest {
	AppLoggingUtil loggingUtil = new AppLoggingUtil();
	
	@Test
	public void getLogFileTest() {
		System.out.println("get log file name test");
		try {
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        String logDate = sdf.format(new Date());
	        String loggingFile = System.getProperty("user.dir") 
	                + File.separatorChar + "log" + File.separatorChar + "loginlog_" + logDate + ".log";
			
			String logFlName = loggingUtil.getLogFileName();
			assertEquals(loggingFile, logFlName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("get log file name completed");
	}

	@Test
	public void systemLoggingTest() {
		System.out.println("System logging test");
		try {
			boolean suc = loggingUtil.log("logging test");;
			assertEquals(suc, true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("System logging test completed");
	}
}

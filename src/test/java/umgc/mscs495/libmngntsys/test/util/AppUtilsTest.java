package umgc.mscs495.libmngntsys.test.util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import umgc.mscs495.libmngntsys.utils.AppUtils;

/**
 * JUnit test for APIs defined in AppLoggingUtil.java.
 * @author jimiewang
 * @CreateDate 02/11/2024
 */
public class AppUtilsTest {
	AppUtils appUtil = new AppUtils();
	
	@Test
	public void getAppIconTest() {
		System.out.println("get application icon test");
		try {
			String appIconFile = appUtil.getAppIcon();
			assertEquals(appIconFile, System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar 
	                + "resources" + File.separatorChar + "Images" + File.separatorChar + "lib.png");
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("get application icon completed");
	}
	
}

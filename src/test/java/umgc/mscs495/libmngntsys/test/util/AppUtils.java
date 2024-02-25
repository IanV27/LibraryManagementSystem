package umgc.mscs495.libmngntsys.test.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Test;

import umgc.mscs495.libmngntsys.utils.AppUtils;
import umgc.mscs495.libmngntsys.vo.UserRole;

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
	
	@Test
	public void getLibLoginUserRolesTest() {
		System.out.println("get library login user roles test");
		try {
			List<UserRole> rolesLst = appUtil.getLibLoginUserRoles();
			assertEquals(rolesLst.size(), 4);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("get library login user roles completed");
	}

	@Test
	public void getBookLanguagesTest() {
		System.out.println("get book languages test");
		try {
			List<String> languagesLst = appUtil.getBookLanguages();
			assertEquals(languagesLst.size(), 5);
			assertEquals(languagesLst.get(0), "English");
			assertEquals(languagesLst.get(1), "French");
			assertEquals(languagesLst.get(2), "German");
			assertEquals(languagesLst.get(3), "Italian");
			assertEquals(languagesLst.get(4), "Spanish");
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("get book languages test completed");
	}

	@Test
	public void getBookFormatsTest() {
		System.out.println("get book formats test");
		try {
			List<String> formatsLst = appUtil.getBookFormats();
			assertEquals(formatsLst.size(), 6);
			assertEquals(formatsLst.get(0), "Audio CD");
			assertEquals(formatsLst.get(1), "Audiobook");
			assertEquals(formatsLst.get(2), "Hardcover");
			assertEquals(formatsLst.get(3), "MP3 CD");
			assertEquals(formatsLst.get(4), "Paperback");
			assertEquals(formatsLst.get(5), "PDF");
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("get book formats test completed");
	}

	@Test
	public void getBookSubjectsTest() {
		System.out.println("get book subjects test");
		try {
			List<String> subjectsLst = appUtil.getBookSubjects();
			assertEquals(subjectsLst.size(), 23);
			assertEquals(subjectsLst.get(0), "Agriculture");
			assertEquals(subjectsLst.get(1), "Anthropology");
			assertEquals(subjectsLst.get(2), "Archaeology");
			assertEquals(subjectsLst.get(3), "Business");
			assertEquals(subjectsLst.get(4), "Communication");
			assertEquals(subjectsLst.get(5), "Computing");
			assertEquals(subjectsLst.get(6), "Economics");
			assertEquals(subjectsLst.get(7), "Education");
			assertEquals(subjectsLst.get(8), "Engineering");
			assertEquals(subjectsLst.get(9), "Fine arts");
			assertEquals(subjectsLst.get(10), "Geography");
			assertEquals(subjectsLst.get(11), "Hisotry");
			assertEquals(subjectsLst.get(12), "Languages");
			assertEquals(subjectsLst.get(13), "Law");
			assertEquals(subjectsLst.get(14), "Literature");
			assertEquals(subjectsLst.get(15), "Mathematics");
			assertEquals(subjectsLst.get(16), "Philosophy");
			assertEquals(subjectsLst.get(17), "Politics");
			assertEquals(subjectsLst.get(18), "Psychology");
			assertEquals(subjectsLst.get(19), "Psychology");
			assertEquals(subjectsLst.get(20), "Religion");
			assertEquals(subjectsLst.get(21), "Science");
			assertEquals(subjectsLst.get(22), "Sociology");
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("get book subjects test completed");
	}
}

package umgc.mscs495.libmngntsys.test.util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

import umgc.mscs495.libmngntsys.DAO.UsersAccountsOperations;
import umgc.mscs495.libmngntsys.vo.Account;

/**
 * JUnit test for APIs defined in AppLoggingUtil.java.
 * @author jimiewang
 * @CreateDate 02/21/2024
 */
public class UsersAccountsOperationsTest {
	private UsersAccountsOperations userAccntOpt = new UsersAccountsOperations();
	
	@Test
	public void getUserAccountTest() {
		System.out.println("get user account test");
		try {
			Account testAccount = userAccntOpt.getUserAccount("doesntexist");
			assertEquals(testAccount, null);
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("get user account completed test completed");
	}

	@Test
	public void disableUserAccountTest() {
		System.out.println("disable user account test");
		try {
			userAccntOpt.disableUserAccount("username");;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("disable  user account failed ");
		}
		System.out.println("disable  user account completed ");
	}
}

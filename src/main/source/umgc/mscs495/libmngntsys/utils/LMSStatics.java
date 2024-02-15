package umgc.mscs495.libmngntsys.utils;

/**
 * Create static common methods and variables for the application.
 * @author jimiewang
 * @CreateDate 01/31/2024
 */
public class LMSStatics {
	private static int roleCode = 3;
	private static String loginUserName;
	
	/**
	 * @return the roleCode
	 */
	public static int getRoleCode() {
		return roleCode;
	}
	
	/**
	 * @param roleCode the roleCode to set
	 */
	public static void setRoleCode(int roleCode) {
		LMSStatics.roleCode = roleCode;
	}
	
	/**
	 * @return the loginUserName
	 */
	public static String getLoginUserName() {
		return loginUserName;
	}
	
	/**
	 * @param loginUserName the loginUserName to set
	 */
	public static void setLoginUserName(String loginUserName) {
		LMSStatics.loginUserName = loginUserName;
	}

}

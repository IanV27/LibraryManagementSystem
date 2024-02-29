package umgc.mscs495.libmngntsys.utils;

/**
 * Create static common methods and variables for the application.
 * @author jimiewang
 * @CreateDate 01/31/2024
 */
public class LMSStatics {
	public static final int NONMEMBER_ROLE_CODE = 0;
	public static final int MEMBER_ROLE_CODE = 1;
	public static final int LIBRARIAN_ROLE_CODE = 2;
	public static final int DBA_ROLE_CODE = 3;
	public static final int INACTIVE_ACCOUNT = 0;
	public static final int MAX_TRIAL_COUNT = 2;
	
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

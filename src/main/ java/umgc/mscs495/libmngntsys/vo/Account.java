package umgc.mscs495.libmngntsys.vo;

/**
 * 
 * @Description This vo class is for account object.
 * @author jimiewang
 * @CreateDate 01/20/2024
 */
public class Account {
	private String ID;
	private String username;
	private String password;
	private int accountType;
	private int activeFlg;

	/**
	 * @return the iD
	 */
	public String getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(String iD) {
		ID = iD;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the accountType
	 */
	public int getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return the activeFlg
	 */
	public int getActiveFlg() {
		return activeFlg;
	}
	/**
	 * @param activeFlg the activeFlg to set
	 */
	public void setActiveFlg(int activeFlg) {
		this.activeFlg = activeFlg;
	}
	
	
}

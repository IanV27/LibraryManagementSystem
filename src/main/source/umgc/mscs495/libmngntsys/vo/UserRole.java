package umgc.mscs495.libmngntsys.vo;

/**
 * 
 * @Description This class is for user role object.
 * @author jimiewang
 * @CreateDate 02/11/2024
 */
public class UserRole {
	private String roleName = "";
	private int    roleID;
	
	/**
	 * @return the roleID
	 */
	public int getRoleID() {
		return roleID;
	}
	/**
	 * @param roleID the roleID to set
	 */
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}

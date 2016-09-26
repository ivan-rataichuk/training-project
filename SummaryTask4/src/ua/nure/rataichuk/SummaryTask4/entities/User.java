package ua.nure.rataichuk.SummaryTask4.entities;

/**
 * User entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class User extends Entity {

	private static final long serialVersionUID = -6889036256149495388L;
	
	private String login;
	
	private String password;
	
	private String email;
	
	private int roleId;
	
	private int localeId;
	
	public int getLocaleId() {
		return localeId;
	}

	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}

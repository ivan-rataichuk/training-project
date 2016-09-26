package ua.nure.rataichuk.SummaryTask4.viewEntitys;

import ua.nure.rataichuk.SummaryTask4.entities.Entity;

/**
 * Visitor entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class Visitor extends Entity{
	
	private static final long serialVersionUID = -6025036256149495388L;
	
	private String email;
	private String firstName = "Visitor";
	private String lastName = "";
	private String credentials;
	private int localeId;
	private int entrantId;
	
	public int getEntrantId() {
		return entrantId;
	}
	public void setEntrantId(int entrantId) {
		this.entrantId = entrantId;
	}
	public String getCredentials() {
		return credentials;
	}
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
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
}

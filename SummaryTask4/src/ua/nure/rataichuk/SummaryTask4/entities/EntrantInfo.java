package ua.nure.rataichuk.SummaryTask4.entities;


/**
 * EntrantInfo entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class EntrantInfo extends Entity{
	
	private static final long serialVersionUID = -6025034875149495388L;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private String adress;
	private String oblast;
	private String school;
	private int localeId;
	
	private String lang;
	
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getOblast() {
		return oblast;
	}
	public void setOblast(String oblast) {
		this.oblast = oblast;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getLocaleId() {
		return localeId;
	}
	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}
}

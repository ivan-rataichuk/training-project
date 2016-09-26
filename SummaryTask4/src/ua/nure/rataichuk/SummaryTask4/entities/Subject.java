package ua.nure.rataichuk.SummaryTask4.entities;

/**
 * Subject entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class Subject extends Entity {

	private static final long serialVersionUID = -6889036256149491478L;
	
	private String name;
	
	private int localeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLocaleId() {
		return localeId;
	}

	public void setLocaleId(int localeId) {
		this.localeId = localeId;
	}

}

package ua.nure.rataichuk.SummaryTask4.entities;

/**
 * Registration entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class Registration extends Entity {

	private static final long serialVersionUID = -6889036256149491287L;
	
	private int entrantId;
	
	private int facultyId;
	
	private int mgId;
	
	private int sgId;
	
	private int tgId;
	
	private String facultyName;
	

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public int getEntrantId() {
		return entrantId;
	}

	public void setEntrantId(int entrantId) {
		this.entrantId = entrantId;
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public int getMgId() {
		return mgId;
	}

	public void setMgId(int mgId) {
		this.mgId = mgId;
	}

	public int getSgId() {
		return sgId;
	}

	public void setSgId(int sgId) {
		this.sgId = sgId;
	}

	public int getTgId() {
		return tgId;
	}

	public void setTgId(int tgId) {
		this.tgId = tgId;
	}
	

}

package ua.nure.rataichuk.SummaryTask4.entities;

/**
 * ReportEntry entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class ReportEntry extends Entity {

	private static final long serialVersionUID = -6889036256145291287L;
	
	private int entrantId;
	
	private int facultyId;
	
	private int gradesSum;
	
	private String facultyName;
	
	private String fullName;
	
	private boolean isBlocked;
	
	private boolean isChecked;
	
	private boolean isBudget;
	
	
	
	
	public boolean isBudget() {
		return isBudget;
	}

	public void setBudget(boolean isBudget) {
		this.isBudget = isBudget;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
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

	public int getGradesSum() {
		return gradesSum;
	}

	public void setGradesSum(int gradesSum) {
		this.gradesSum = gradesSum;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	
}

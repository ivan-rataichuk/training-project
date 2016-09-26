package ua.nure.rataichuk.SummaryTask4.entities;

/**
 * Grade entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class Grade extends Entity {

	private static final long serialVersionUID = -6854636256149495388L;
	
	private int entrantId;
	private int subjectId;
	private int grade;
	private String subjectName;
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getEntrantId() {
		return entrantId;
	}
	public void setEntrantId(int entrantId) {
		this.entrantId = entrantId;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}

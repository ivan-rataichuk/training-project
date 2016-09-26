package ua.nure.rataichuk.SummaryTask4.viewEntitys;

import java.util.ArrayList;
import java.util.List;

import ua.nure.rataichuk.SummaryTask4.entities.Entity;
import ua.nure.rataichuk.SummaryTask4.entities.ReportEntry;

/**
 * Faculty report entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class FacultyReport extends Entity{
	
	private static final long serialVersionUID = -6025036256149492948L;
	
	int facultyId;
	String facultyName;
	
	List<ReportEntry> budget;
	List<ReportEntry> contract;
	
	{
		budget = new ArrayList<>();
		contract = new ArrayList<>();
	}

	public int getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(int facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public List<ReportEntry> getBudget() {
		return budget;
	}

	public void setBudget(List<ReportEntry> budget) {
		this.budget = budget;
	}

	public List<ReportEntry> getContract() {
		return contract;
	}

	public void setContract(List<ReportEntry> contract) {
		this.contract = contract;
	}

	

	
	
	
}

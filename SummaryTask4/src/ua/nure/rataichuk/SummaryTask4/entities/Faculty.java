package ua.nure.rataichuk.SummaryTask4.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Faculty entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class Faculty extends Entity {

	private static final long serialVersionUID = -6889036258959495388L;
	
	private int msId;
	
	private int ssId;
	
	private int tsId;
	
	private int positions;
	
	private int budgetPositions;
	
	private int positionsFilled;
	
	private int budgetPositionsFilled;
	
	private FacultyInfo fi;
	
	private List<FacultyInfo> fil;
	
	{
		fil = new ArrayList<>();
	}

	
	
	public FacultyInfo getInfo() {
		return fi;
	}

	public void setInfo(FacultyInfo fi) {
		this.fi = fi;
	}

	public List<FacultyInfo> getInfoList() {
		return fil;
	}

	public void setInfo(List<FacultyInfo> fil) {
		this.fil = fil;
	}

	public int getMsId() {
		return msId;
	}

	public void setMsId(int msId) {
		this.msId = msId;
	}

	public int getSsId() {
		return ssId;
	}

	public void setSsId(int ssId) {
		this.ssId = ssId;
	}

	public int getTsId() {
		return tsId;
	}

	public void setTsId(int tsId) {
		this.tsId = tsId;
	}

	public int getPositions() {
		return positions;
	}

	public void setPositions(int positions) {
		this.positions = positions;
	}

	public int getBudgetPositions() {
		return budgetPositions;
	}

	public void setBudgetPositions(int budgetPositions) {
		this.budgetPositions = budgetPositions;
	}

	public int getPositionsFilled() {
		return positionsFilled;
	}

	public void setPositionsFilled(int positionsFilled) {
		this.positionsFilled = positionsFilled;
	}

	public int getBudgetPositionsFilled() {
		return budgetPositionsFilled;
	}

	public void setBudgetPositionsFilled(int budgetPositionsFilled) {
		this.budgetPositionsFilled = budgetPositionsFilled;
	}
}

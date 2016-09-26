package ua.nure.rataichuk.SummaryTask4.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * FacultyInfo entity.
 * 
 * @author Ivan Rataichuk
 * 
 */
public class FacultyInfo extends Entity {

	private static final long serialVersionUID = -6889036258959268488L;
	
	
	private String name;
	
	private String description;
	
	private int locale_id;
	
	private List<String> subjectNames;
	
	private String lang;
	
	public FacultyInfo() {
		subjectNames = new ArrayList<>();
	}

	
	
	public String getLang() {
		return lang;
	}



	public void setLang(String lang) {
		this.lang = lang;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLocale_id() {
		return locale_id;
	}

	public void setLocale_id(int locale_id) {
		this.locale_id = locale_id;
	}

	public List<String> getSubjectNames() {
		return subjectNames;
	}

	public void setSubjectNames(List<String> subjectNames) {
		this.subjectNames = subjectNames;
	}

	
}

package ua.nure.rataichuk.SummaryTask4.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.entities.Grade;
import ua.nure.rataichuk.SummaryTask4.entities.ReportEntry;
import ua.nure.rataichuk.SummaryTask4.entities.Subject;

/**
 * Entities collection sorting service
 * 
 * @author Ivan Rataichuk
 *
 */
public class Sorter {
	
	private static final String SORT_ALPH_ASC = "sort_alph_asc";
	
	private static final String SORT_ALPH_DESC = "sort_alph_desc";
	
	private static final String SORT_BY_POS = "sort_pos";
	
	private static final String SORT_BY_BUD_POS = "sort_bud_pos";
	
	
	
	/**
	 * Sorts List of facultys in specified order
	 * @param facultys
	 * @param sort
	 */
	public static void sortFacultys(List<Faculty> facultys, String sort) {
		
		if (sort.equals(SORT_ALPH_ASC)) {
			Collections.sort(facultys, new Comparator<Faculty>() {

				public int compare(Faculty f1, Faculty f2) {
					String n1 = f1.getInfo().getName();
					String n2 = f2.getInfo().getName();
					return n1.compareTo(n2);
				}
			});
		}
		
		if (sort.equals(SORT_ALPH_DESC)) {
			Collections.sort(facultys, new Comparator<Faculty>() {

				public int compare(Faculty f1, Faculty f2) {
					String n1 = f1.getInfo().getName();
					String n2 = f2.getInfo().getName();
					return -n1.compareTo(n2);
				}
			});
		}
		
		if (sort.equals(SORT_BY_POS)) {
			Collections.sort(facultys, new Comparator<Faculty>() {

				public int compare(Faculty f1, Faculty f2) {
					int p1 = f1.getPositions();
					int p2 = f2.getPositions();
					return p2 - p1;
				}
			});
		}
		
		if (sort.equals(SORT_BY_BUD_POS)) {
			Collections.sort(facultys, new Comparator<Faculty>() {

				public int compare(Faculty f1, Faculty f2) {
					int p1 = f1.getBudgetPositions();
					int p2 = f2.getBudgetPositions();
					return p2 - p1;
				}
			});
		}
		
	}
	
	/**
	 * Sorts grades list in order of its subject id's
	 * 
	 * @param grades
	 */
	public static void sortGrades(List<Grade> grades) {
		Collections.sort(grades, new Comparator<Grade>() {

			@Override
			public int compare(Grade g1, Grade g2) {
				return g2.getSubjectId() - g1.getSubjectId();
			}
			
		});
	}
	
	/**
	 * Sorts subject list in order of its id's
	 * 
	 * @param grades
	 */
	public static void sortSubjects(List<Subject> subl) {
		Collections.sort(subl, new Comparator<Subject>() {

			@Override
			public int compare(Subject s1, Subject s2) {
				return s2.getId() - s2.getId();
			}
			
		});
	}
	
	/**
	 * Sorts ReportEntry List by grades sum
	 * 
	 * @param rel
	 */
	public static void sortReportEntrys(List<ReportEntry> rel) {
		Collections.sort(rel, new Comparator<ReportEntry>() {

			@Override
			public int compare(ReportEntry re1, ReportEntry re2) {
				return re2.getGradesSum() - re1.getGradesSum();
			}
			
		});
	}

}

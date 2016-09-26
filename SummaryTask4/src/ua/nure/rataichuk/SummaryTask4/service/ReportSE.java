package ua.nure.rataichuk.SummaryTask4.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.dao.EntrantDAO;
import ua.nure.rataichuk.SummaryTask4.dao.FacultyDAO;
import ua.nure.rataichuk.SummaryTask4.dao.LocaleDAO;
import ua.nure.rataichuk.SummaryTask4.dao.ReportDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Emessage;
import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.entities.ReportEntry;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.FacultyReport;

/**
 * Report service layer
 * 
 * @author Ivan Rataichuk
 *
 */
public class ReportSE {
	
	private static final Logger LOG = Logger.getLogger(ReportSE.class);

	/**
	 * Confirm final report and send corresponding emails
	 */
	public void confirmCheckedRegistrations() {
		try {
			ReportDAO rdao = ReportDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			EntrantDAO edao = EntrantDAO.getInstance();
			int localeId = ldao.read("en");
			
			List<ReportEntry> rel = rdao.read(localeId);
			Set<Integer> rejectedIds = new HashSet<>();
			
			Email em = new Email();
			
			for (ReportEntry re : rel) {
				if (re.isChecked()) {
					Emessage mes = new Emessage();
					Entrant e = edao.read(re.getEntrantId(), localeId);
					mes.setSubject(EmailMessages.SUBJECT_CONFIRM);
					mes.setMailFrom(EmailMessages.MAIL_FROM);
					mes.setMailTo(e.getEmail());
					
					StringBuilder sb = new StringBuilder();
					sb.append("Dear ");
					sb.append(e.getInfo().getFirstName());
					sb.append(" ");
					sb.append(e.getInfo().getLastName());
					sb.append(".");
					sb.append(EmailMessages.CONFIRM_REGISTRATION);
					sb.append(EmailMessages.CONFIRM_FACULTY);
					sb.append(re.getFacultyName());
					sb.append(EmailMessages.CONFIRM_POSITION);
					
					if (re.isBudget()) {
						sb.append("budget");
					} else {
						sb.append("contract");
					}
					mes.setText(sb.toString());
					
					
					em.SendMailSSL(mes);
				} else if (!re.isChecked() && !re.isBlocked()) {
					rejectedIds.add(re.getEntrantId());
				}
			}
			for (int id : rejectedIds) {
				Entrant e = edao.read(id, localeId);
				Emessage mes = new Emessage();
				mes.setSubject(EmailMessages.SUBJECT_REJECT);
				mes.setMailFrom(EmailMessages.MAIL_FROM);
				mes.setMailTo(e.getEmail());

				StringBuilder sb = new StringBuilder();
				sb.append("Dear ");
				sb.append(e.getInfo().getFirstName());
				sb.append(" ");
				sb.append(e.getInfo().getLastName());
				sb.append(".");
				sb.append(EmailMessages.REJECT_REGISTRATION);
				mes.setText(sb.toString());

				em.SendMailSSL(mes);
			}
				
			
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * Returns a list of Report entrys
	 * 
	 * @param loc Locale
	 * @return
	 */
	public List<FacultyReport> getFacultyReport(Locale loc) {
		List<FacultyReport> frl = new ArrayList<>();
		try {
			ReportDAO rdao = ReportDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			FacultyDAO fdao = FacultyDAO.getInstance();
			int localeId = ldao.read(loc.getLanguage());
			
			List<ReportEntry> rel = rdao.read(localeId);
			List<Faculty> fl = fdao.read(localeId);
			
			for (Faculty f : fl) {
				FacultyReport fr = new FacultyReport();
				List<ReportEntry> reSort = new ArrayList<>();
				
				fr.setFacultyId(f.getId());
				fr.setFacultyName(f.getInfo().getName());
				for (ReportEntry re : rel) {
					if (f.getId() == re.getFacultyId() && !re.isBlocked()) {
						reSort.add(re);
					}
				}
				
				Sorter.sortReportEntrys(reSort);
				Iterator<ReportEntry> it = reSort.iterator();
				
				int step = 0;
				while (it.hasNext() && (step < f.getPositions())) {
					if (step < f.getBudgetPositions()) {
						fr.getBudget().add(it.next());
					} else {
						fr.getContract().add(it.next());
					}
					step++;
				}
				frl.add(fr);
			}
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return frl;
	}
	
	/**
	 * Returns a list of Report entrys
	 * 
	 * @param loc Locale
	 * @return
	 */
	public List<ReportEntry> getReport(Locale loc) {
		List<ReportEntry> rel = null;
		try {
			ReportDAO rdao = ReportDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			rel = rdao.read(ldao.read(loc.getLanguage()));
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return rel;
	}
	
	/**
	 * Returns a CROPPED list of Report entrys
	 * 
	 * @param loc Locale
	 * @return
	 */
	public List<ReportEntry> getCroppedReport(Locale loc) {
		List<ReportEntry> rel = null;
		try {
			ReportDAO rdao = ReportDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			rel = rdao.readCropped(ldao.read(loc.getLanguage()));
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return rel;
	}
	
	public void saveReportToFile(String realPath) {
		Locale loc = new Locale("en");
		try {
			PDF pdf = new PDF();
			pdf.GenerateReportPDF(getFacultyReport(loc), realPath);
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
}

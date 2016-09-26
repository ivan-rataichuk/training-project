package ua.nure.rataichuk.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.entities.ReportEntry;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.exeptions.Messages;

/**
 * Report entity access class
 * 
 * @author Ivan Rataichuk
 *
 */
public class ReportDAO extends DAO {
	private static final Logger LOG = Logger.getLogger(ReportDAO.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static ReportDAO instance;

	public static synchronized ReportDAO getInstance() throws DBException {
		if (instance == null) {
			instance = new ReportDAO();
		}
		return instance;
	}

	private ReportDAO() throws DBException {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");

			ds = (DataSource) envContext.lookup("jdbc/SummaryTask4");
			LOG.trace("Data source ==> " + ds);
		} catch (NamingException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}

	// //////////////////////////////////////////////////////////
	// CRUD methods
	// //////////////////////////////////////////////////////////
	
	/**
	 * Returns a List of ReportEntry's.
	 * @param entrantId 	 
	 * 				int
	 * @return List<ReportEntry> ReportEntry collection.
	 * @throws DBException
	 */
	public List<ReportEntry> read(int localeId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<ReportEntry> list = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_REPORT_ENTRYS);
			int k = 1;
			pstmt.setLong(k++, localeId);
			pstmt.setLong(k++, localeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(extractReportEntry(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_REPORT_ENTRYS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * Returns a List of ReportEntry's.
	 * @param entrantId 	 
	 * 				int
	 * @return List<ReportEntry> ReportEntry collection.
	 * @throws DBException
	 */
	public List<ReportEntry> readCropped(int localeId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<ReportEntry> list = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_REPORT_ENTRYS);
			int k = 1;
			pstmt.setLong(k++, localeId);
			pstmt.setLong(k++, localeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReportEntry re = extractCroppedReportEntry(rs);
				if (re.getGradesSum() > 200) {
					list.add(re);
				}
				
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_REPORT_ENTRYS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}
	
	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////
	
	private ReportEntry extractReportEntry(ResultSet rs) throws SQLException {
		ReportEntry re = new ReportEntry();
		re.setId(rs.getInt(Fields.ENTITY_ID));
		re.setEntrantId(rs.getInt(Fields.RE_ENTRANT_ID));
		re.setFacultyId(rs.getInt(Fields.RE_FACULTY_ID));
		re.setFacultyName(rs.getString(Fields.RE_FACULTY_NAME));
		re.setBlocked(rs.getBoolean(Fields.RE_BLOCKED));
		re.setChecked(rs.getBoolean(Fields.RE_CHECKED));
		re.setBudget(rs.getBoolean(Fields.RE_BUDGET));
		re.setFullName(rs.getString(Fields.RE_LAST_NAME) + " "
						+ rs.getString(Fields.RE_FIRST_NAME) + " " 
						+ rs.getString(Fields.RE_MIDDLE_NAME));
		re.setGradesSum(rs.getInt(Fields.RE_M_GRADE) + rs.getInt(Fields.RE_S_GRADE) + rs.getInt(Fields.RE_T_GRADE));
		return re;
	}
	
	private ReportEntry extractCroppedReportEntry(ResultSet rs) throws SQLException {
		ReportEntry re = new ReportEntry();
		re.setId(rs.getInt(Fields.ENTITY_ID));
		re.setEntrantId(rs.getInt(Fields.RE_ENTRANT_ID));
		re.setFacultyId(rs.getInt(Fields.RE_FACULTY_ID));
		re.setFacultyName(rs.getString(Fields.RE_FACULTY_NAME));
		re.setBlocked(rs.getBoolean(Fields.RE_BLOCKED));
		re.setChecked(rs.getBoolean(Fields.RE_CHECKED));
		re.setBudget(rs.getBoolean(Fields.RE_BUDGET));
		re.setFullName(rs.getString(Fields.RE_LAST_NAME) + " "
						+ rs.getString(Fields.RE_FIRST_NAME) + " " 
						+ rs.getString(Fields.RE_MIDDLE_NAME));
		re.setGradesSum(rs.getInt(Fields.RE_M_GRADE) + rs.getInt(Fields.RE_S_GRADE) + rs.getInt(Fields.RE_T_GRADE));
		return re;
	}

}

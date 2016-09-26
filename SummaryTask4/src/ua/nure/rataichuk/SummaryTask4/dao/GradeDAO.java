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

import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.entities.Grade;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.exeptions.Messages;

/**
 * Grade entity access class
 * 
 * @author Ivan Rataichuk
 *
 */
public class GradeDAO extends DAO {
	private static final Logger LOG = Logger.getLogger(GradeDAO.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static GradeDAO instance;

	public static synchronized GradeDAO getInstance() throws DBException {
		if (instance == null) {
			instance = new GradeDAO();
		}
		return instance;
	}

	private GradeDAO() throws DBException {
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
	 * Create grade.
	 * 
	 * @param grade
	 *            Grade grade to create.
	 * @throws DBException
	 */
	public void create(Grade grade) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			createGrade(con, grade);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_GRADE, ex);
		} finally {
			close(con);
		}
	}
	
	/**
	 * Returns a List of grades for Entrant with given Id.
	 * @param entrantId 	 
	 * 				int entrantId
	 * @return List<Grade> Grades collection.
	 * @throws DBException
	 */
	public List<Grade> read(Entrant e) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Grade> grades = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_GRADES_BY_ENTRANT_ID);
			int k = 1;
			pstmt.setLong(k++, e.getId());
			pstmt.setLong(k++, e.getInfo().getLocaleId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				grades.add(extractGrade(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_GRADES, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return grades;
	}
	
	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////
	

	private void createGrade(Connection con, Grade grade) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.CREATE_GRADE);
			int k = 1;
			pstmt.setInt(k++, grade.getEntrantId());
			pstmt.setInt(k++, grade.getSubjectId());
			pstmt.setInt(k++, grade.getGrade());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}
	
	private Grade extractGrade(ResultSet rs) throws SQLException {
		Grade grade = new Grade();
		grade.setId(rs.getInt(Fields.ENTITY_ID));
		grade.setEntrantId(rs.getInt(Fields.GRADE_ENTRANT_ID));
		grade.setSubjectId(rs.getInt(Fields.GRADE_SUBJECT_ID));
		grade.setGrade(rs.getInt(Fields.GRADE_GRADE));
		grade.setSubjectName(rs.getString(Fields.SUBJECT_NAME));
		return grade;
	}
}

package ua.nure.rataichuk.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.entities.FacultyInfo;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.exeptions.Messages;

/**
 * Faculty entity access class
 * 
 * @author Ivan Rataichuk
 *
 */
public class FacultyDAO extends DAO {
	private static final Logger LOG = Logger.getLogger(FacultyDAO.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static FacultyDAO instance;

	public static synchronized FacultyDAO getInstance() throws DBException {
		if (instance == null) {
			instance = new FacultyDAO();
		}
		return instance;
	}

	private FacultyDAO() throws DBException {
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
	 * Create faculty.
	 * 
	 * @return int faculty ID
	 * @throws DBException
	 */
	public void create(Faculty faculty) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			createFaculty(con, faculty);
			for (FacultyInfo fi : faculty.getInfoList()) {
				createFacultyInfo(con, fi, faculty.getId());
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_FACULTY, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Delete faculty.
	 * 
	 * @param int
	 *            facultyId to delete
	 * @throws DBException
	 */
	public void delete(Faculty f) throws DBException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = null;
			try {
				pstmt = con.prepareStatement(Queries.DELETE_FACULTY);
				int k = 1;
				pstmt.setInt(k++, f.getId());
				pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_FACULTY, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Returns a List of facultys.
	 * 
	 * @param localeId
	 *            int
	 * @return Faculty collection.
	 * @throws DBException
	 */
	public List<Faculty> read(int localeId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Faculty> facultys = new ArrayList<>();
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_ALL_FACULTYS);
			int k = 1;
			pstmt.setLong(k++, localeId);
			pstmt.setLong(k++, localeId);
			pstmt.setLong(k++, localeId);
			pstmt.setLong(k++, localeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				facultys.add(extractFaculty(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_FACULTYS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return facultys;
	}

	/**
	 * Returns a Faculty instance.
	 * 
	 * @param facultyId
	 *            int
	 * @param localeId
	 *            int
	 * 
	 * @return Faculty instance.
	 * @throws DBException
	 */
	public Faculty read(int facultyId, int localeId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Faculty faculty = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_FACULTY_BY_ID);
			int k = 1;
			pstmt.setLong(k++, localeId);
			pstmt.setLong(k++, localeId);
			pstmt.setLong(k++, localeId);
			pstmt.setLong(k++, localeId);
			pstmt.setLong(k++, facultyId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				faculty = extractFaculty(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_FACULTYS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return faculty;
	}

	/**
	 * Update faculty.
	 * 
	 * @param faculty
	 *            faculty to update.
	 * 
	 * @throws DBException
	 */
	public void update(Faculty faculty) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateFaculty(con, faculty);
			for (FacultyInfo fi : faculty.getInfoList()) {
				updateFacultyInfo(con, fi, faculty.getId());
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_FACULTY_INFO, ex);
		} finally {
			close(con);
		}
	}

	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////

	private void createFaculty(Connection con, Faculty faculty) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			int k = 1;
			pstmt = con.prepareStatement(Queries.CREATE_FACULTY, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(k++, faculty.getMsId());
			pstmt.setInt(k++, faculty.getSsId());
			pstmt.setInt(k++, faculty.getTsId());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					faculty.setId(rs.getInt(1));
				}
			}
			pstmt.close();
			createPositions(con, faculty);
		} finally {
			close(pstmt);
		}
	}

	private void createFacultyInfo(Connection con, FacultyInfo fi, int facultyId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.CREATE_FACULTY_INFO);
			int k = 1;
			pstmt.setInt(k++, facultyId);
			pstmt.setString(k++, fi.getName());
			pstmt.setString(k++, fi.getDescription());
			pstmt.setInt(k++, fi.getLocale_id());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	/**
	 * Create positions.
	 * 
	 * @param pos
	 *            Positions
	 * @throws SQLException
	 */
	private void createPositions(Connection con, Faculty faculty) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			int k = 1;
			pstmt = con.prepareStatement(Queries.CREATE_POSITIONS);
			pstmt.setInt(k++, faculty.getId());
			pstmt.setInt(k++, faculty.getPositions());
			pstmt.setInt(k++, faculty.getBudgetPositions());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	public static Faculty extractFaculty(ResultSet rs) throws SQLException {
		Faculty faculty = new Faculty();
		FacultyInfo fi = new FacultyInfo();
		
		faculty.setId(rs.getInt(Fields.ENTITY_ID));
		faculty.setMsId(rs.getInt(Fields.FACULTY_MS_ID));
		faculty.setSsId(rs.getInt(Fields.FACULTY_SS_ID));
		faculty.setTsId(rs.getInt(Fields.FACULTY_TS_ID));
		faculty.setPositions(rs.getInt(Fields.FACULTY_POS));
		faculty.setBudgetPositions(rs.getInt(Fields.FACULTY_BUD_POS));
		faculty.setPositionsFilled(rs.getInt(Fields.FACULTY_POS_F));
		faculty.setBudgetPositionsFilled(rs.getInt(Fields.FACULTY_BUD_POS_F));
		
		fi.setName(rs.getString(Fields.FACULTY_NAME));
		fi.setDescription(rs.getString(Fields.FACULTY_DESCRIPTION));
		fi.setLocale_id(rs.getInt(Fields.FACULTY_LOCALE_ID));
		fi.getSubjectNames().add(rs.getString(Fields.FACULTY_MS_NAME));
		fi.getSubjectNames().add(rs.getString(Fields.FACULTY_SS_NAME));
		fi.getSubjectNames().add(rs.getString(Fields.FACULTY_TS_NAME));
		
		faculty.setInfo(fi);
		return faculty;
	}

	private void updateFaculty(Connection con, Faculty faculty) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.UPDATE_FACULTY);
			int k = 1;
			pstmt.setInt(k++, faculty.getMsId());
			pstmt.setInt(k++, faculty.getSsId());
			pstmt.setInt(k++, faculty.getTsId());
			pstmt.setInt(k++, faculty.getPositions());
			pstmt.setInt(k++, faculty.getBudgetPositions());
			pstmt.setInt(k++, faculty.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}
	
	private void updateFacultyInfo(Connection con, FacultyInfo fi, int facultyId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.UPDATE_FACULTY_INFO);
			int k = 1;
			pstmt.setString(k++, fi.getName());
			pstmt.setString(k++, fi.getDescription());
			pstmt.setInt(k++, facultyId);
			pstmt.setInt(k++, fi.getLocale_id());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}
}

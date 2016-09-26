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

import ua.nure.rataichuk.SummaryTask4.entities.Subject;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.exeptions.Messages;

/**
 * Subject entity access class
 * 
 * @author Ivan Rataichuk
 *
 */
public class SubjectDAO extends DAO {
	private static final Logger LOG = Logger.getLogger(SubjectDAO.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static SubjectDAO instance;

	public static synchronized SubjectDAO getInstance() throws DBException {
		if (instance == null) {
			instance = new SubjectDAO();
		}
		return instance;
	}

	private SubjectDAO() throws DBException {
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
	 * Create subject.
	 * 
	 * @return int 
	 *            subject ID
	 * @throws DBException
	 */
	public void create(Subject sub) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			createSubject(con, sub);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_SUBJECT, ex);
		} finally {
			close(con);
		}
	}
	
	/**
	 * Returns a List of subjects by given localeId.
	 * @param localeId 	 
	 * 				int
	 * @return List<Subject> Subject collection.
	 * @throws DBException
	 */
	public List<Subject> read(int localeId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Subject> list = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_ALL_SUBJECTS);
			pstmt.setLong(1, localeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(extractSubject(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_SUBJECTS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return list;
	}
	
	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////
	
	private void createSubject(Connection con, Subject sub) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			
			if (sub.getId() == 0) {
				pstmt = con.prepareStatement(Queries.CREATE_SUBJECT, Statement.RETURN_GENERATED_KEYS);
				if (pstmt.executeUpdate() > 0) {
					rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						sub.setId(rs.getInt(1)); 
					}
				}
				pstmt.close();
			}
			
			pstmt = con.prepareStatement(Queries.CREATE_SUBJECT_INFO);
			int k = 1;
			pstmt.setInt(k++, sub.getId());
			pstmt.setString(k++, sub.getName());
			pstmt.setInt(k++, sub.getLocaleId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}
	
	private Subject extractSubject(ResultSet rs) throws SQLException {
		Subject subject = new Subject();
		subject.setId(rs.getInt(Fields.SUBJECT_ID));
		subject.setName(rs.getString(Fields.SUBJECT_NAME));
		subject.setLocaleId(rs.getInt(Fields.SUBJECT_LOCALE_ID));
		return subject;
	}
}

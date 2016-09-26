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
import ua.nure.rataichuk.SummaryTask4.entities.Registration;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.exeptions.Messages;

/**
 * Registration entity access class
 * 
 * @author Ivan Rataichuk
 *
 */
public class RegistrationDAO extends DAO {
	private static final Logger LOG = Logger.getLogger(RegistrationDAO.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static RegistrationDAO instance;

	public static synchronized RegistrationDAO getInstance() throws DBException {
		if (instance == null) {
			instance = new RegistrationDAO();
		}
		return instance;
	}

	private RegistrationDAO() throws DBException {
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
	 * Delete registration.
	 * 
	 * @param int
	 * 			registration Id to delete
	 * @throws DBException
	 */
	public void delete(int regId) throws DBException {
		Connection con = null;
		
		try {
			con = getConnection();
			decrementPositions(con, regId);
			deleteRegistration(con, regId);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_REGISTRATION, ex);
		} finally {
			close(con);
		}
	}
	
	/**
	 * Reset registration for given entrant.
	 * 
	 * @param entrantId
	 * 			
	 * @throws DBException
	 */
	public void update(int entrantId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.RESET_REGISTRATION);
			int k = 1;
			pstmt.setInt(k++, entrantId);
			pstmt.executeUpdate();
			close(pstmt);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_REGISTRATION, ex);
		} finally {
			close(con);
		}
	}
	
	/**
	 * Check registration with given id and block others for given entrant.
	 * 
	 * @param regId
	 * @param entrantId
	 * 			
	 * @throws DBException
	 */
	public void update(int regId, int entrantId, boolean isBudget) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(Queries.BLOCK_REGISTRATION);
			int k = 1;
			pstmt.setInt(k++, regId);
			pstmt.setInt(k++, entrantId);
			pstmt.executeUpdate();
			close(pstmt);
			
			pstmt = con.prepareStatement(Queries.CHECK_REGISTRATION);
			k = 1;
			pstmt.setBoolean(k++, isBudget);
			pstmt.setInt(k++, regId);
			pstmt.executeUpdate();
			close(pstmt);
			
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_DELETE_REGISTRATION, ex);
		} finally {
			close(con);
		}
	}
	
	/**
	 * Returns a List of registrations for given Entrant.
	 * @param e Entrant
	 * @return List<Registration> Registration collection.
	 * @throws DBException
	 */
	public List<Registration> read(Entrant e) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Registration> regs = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_ALL_REGISTRATIONS_BY_ID);
			int k = 1;
			pstmt.setLong(k++, e.getId());
			pstmt.setLong(k++, e.getInfo().getLocaleId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				regs.add(extractRegistration(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_REGISTRATIONS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return regs;
	}
	

	public List<Registration> read(int localeId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Registration> regs = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_ALL_REGISTRATIONS);
			int k = 1;
			pstmt.setLong(k++, localeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				regs.add(extractRegistration(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_REGISTRATIONS, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return regs;
	}

	/**
	 * Create registration.
	 * 
	 * @param ri
	 *            Registration to create.
	 * @throws DBException
	 */
	public void create(Registration reg) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			createRegistration(con, reg);
			incrementPositions(con, reg);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_REGISTRATION, ex);
		} finally {
			close(con);
		}
	}
	
	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////
	
	private void createRegistration(Connection con, Registration reg) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.CREATE_REGISTRATION);
			int k = 1;
			pstmt.setInt(k++, reg.getEntrantId());
			pstmt.setInt(k++, reg.getFacultyId());
			pstmt.setInt(k++, reg.getMgId());
			pstmt.setInt(k++, reg.getSgId());
			pstmt.setInt(k++, reg.getTgId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}
	
	private Registration extractRegistration(ResultSet rs) throws SQLException {
		Registration reg = new Registration();
		reg.setId(rs.getInt(Fields.ENTITY_ID));
		reg.setEntrantId(rs.getInt(Fields.REG_ENTRANT_ID));
		reg.setFacultyId(rs.getInt(Fields.REG_FACULTY_ID));
		reg.setMgId(rs.getInt(Fields.REG_M_GRADE_ID));
		reg.setSgId(rs.getInt(Fields.REG_S_GRADE_ID));
		reg.setTgId(rs.getInt(Fields.REG_T_GRADE_ID));
		reg.setFacultyName(rs.getString(Fields.FACULTY_NAME));
		return reg;
		
	}
	
	private void incrementPositions(Connection con, Registration reg) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.UPDATE_FILLED_POSITIONS_INC);
			int k = 1;
			pstmt.setInt(k++, reg.getFacultyId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}
	
	private void decrementPositions(Connection con, int regId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.UPDATE_FILLED_POSITIONS_DEC);
			int k = 1;
			pstmt.setInt(k++, regId);
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}
	
	private void deleteRegistration(Connection con, int regId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.DELETE_REGISTRATION);
			int k = 1;
			pstmt.setInt(k++, regId);
			pstmt.executeUpdate();	
		} finally {
			close(pstmt);
		}
	}

}

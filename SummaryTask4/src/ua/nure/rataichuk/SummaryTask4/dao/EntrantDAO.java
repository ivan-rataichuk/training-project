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

import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.entities.EntrantInfo;
import ua.nure.rataichuk.SummaryTask4.entities.Grade;
import ua.nure.rataichuk.SummaryTask4.entities.User;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.exeptions.Messages;

/**
 * Entrant entity access class
 * 
 * @author Ivan Rataichuk
 *
 */
public class EntrantDAO extends DAO {
	private static final Logger LOG = Logger.getLogger(EntrantDAO.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static EntrantDAO instance;

	public static synchronized EntrantDAO getInstance() throws DBException {
		if (instance == null) {
			instance = new EntrantDAO();
		}
		return instance;
	}

	private EntrantDAO() throws DBException {
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
	 * Create entrant.
	 * 
	 * @param entrant
	 *            entrant to create.
	 * @param grades List of entrant's grades
	 * @throws DBException
	 */
	public int create(Entrant entrant) throws DBException {
		int entrantId;
		Connection con = null;
		try {
			con = getConnection();
			entrantId = createEntrant(con, entrant);
			createGrades(con, entrant);
			for (EntrantInfo ei : entrant.getInfoList()) {
				createEntrantInfo(con, ei, entrant.getId());
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_ENTRANT, ex);
		} finally {
			close(con);
		}
		return entrantId;
	}
	
	/**
	 * Update entrant.
	 * 
	 * @param entrant
	 *            entrant to update.
	 * @param grades List of entrant's grades
	 * @throws DBException
	 */
	public void update(Entrant entrant) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			updateEntrant(con, entrant);
			updateGrades(con, entrant);
			for (EntrantInfo ei : entrant.getInfoList()) {
				updateEntrantInfo(con, ei, entrant.getId());
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_ENTRANT, ex);
		} finally {
			close(con);
		}
	}
	
	/**
	 * Update entrant certificate info.
	 * 
	 * @param entrant
	 *            entrant to update.
	 * @param grades List of entrant's grades
	 * @throws DBException
	 */
	public void update(int entrantId, String file) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = null;
			try {
				pstmt = con.prepareStatement(Queries.UPDATE_ENTRANT_CERTIFICATE);
				int k = 1;
				pstmt.setString(k++, file);
				pstmt.setInt(k++, entrantId);
				pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_ENTRANT, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Block entrant.
	 * 
	 * @param isBlocked
	 * 
	 * @param entrantId
	 *            entrant ID.
	 * 
	 * @throws DBException
	 */
	public void update(boolean isBlocked, int entrantId) throws DBException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.UPDATE_ENTRANT_BLOCKING);
			int k = 1;
			pstmt.setBoolean(k++, isBlocked);
			pstmt.setInt(k++, entrantId);
			pstmt.executeUpdate();
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_ENTRANT, ex);
		} finally {
			close(con);
		}
	}

	/**
	 * Returns List of Entrants by localeId.
	 * 
	 * @param localeId
	 *            int.
	 * @return List<Entrant>.
	 * @throws DBException
	 */
	public List<Entrant> readAll(int localeId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<Entrant> eList = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_ALL_ENTRANTS);
			int k = 1;
			pstmt.setInt(k++, localeId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eList.add(extractEntrant(rs));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ENTRANT_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return eList;
	}

	/**
	 * Returns Entrant by Id and localeId.
	 * 
	 * @param e
	 *            Entrant.
	 * @return Entrant.
	 * @throws DBException
	 */
	public Entrant read(int entrantId, int localeId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Entrant eOut = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_ENTRANT_BY_ID);
			int k = 1;
			pstmt.setInt(k++, entrantId);
			pstmt.setInt(k++, localeId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				eOut = extractEntrant(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ENTRANT_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return eOut;
	}
	
	/**
	 * Returns Entrant and all localized info by Id.
	 * 
	 * @param entrantId
	 *            int.
	 * @return Entrant.
	 * @throws DBException
	 */
	public Entrant read(int entrantId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		Entrant eOut = null;
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(Queries.FIND_ENTRANT_INFO_E_BY_ID);
			int k = 1;
			pstmt.setInt(k++, entrantId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				eOut = extractEntrantE(rs);
			}
			close(pstmt);
			
			pstmt = con.prepareStatement(Queries.FIND_ENTRANT_INFO_INF_BY_ID);
			k = 1;
			pstmt.setInt(k++, entrantId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				eOut.getInfoList().add(extractEntrantInf(rs));
			}
			close(pstmt);
			
			con.commit();
			
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ENTRANT_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return eOut;
	}

	/**
	 * Returns an entrant by the given user ID.
	 * 
	 * @param u
	 *            User.
	 * @return Entrant entity.
	 * @throws DBException
	 */
	public Entrant read(User u) throws DBException {
		Entrant entrant = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_ENTRANT_BY_USER_ID);
			int k = 1;
			pstmt.setInt(k++, u.getId());
			pstmt.setInt(k++, u.getLocaleId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				entrant = extractEntrant(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ENTRANT_BY_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return entrant;
	}

	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////

	private int createEntrant(Connection con, Entrant entrant) throws SQLException {
		int entrantId = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			int k = 1;
			pstmt = con.prepareStatement(Queries.CREATE_ENTRANT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setBoolean(k++, entrant.isBlocked());
			pstmt.setLong(k++, entrant.getUserId());
			pstmt.setString(k++, entrant.getEmail());
			pstmt.setString(k++, entrant.getCertificateURL());
			pstmt.setString(k++, entrant.getTel());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					entrant.setId(rs.getInt(1));
					entrantId = rs.getInt(1);
				}
			}
		} finally {
			close(pstmt);
		}
		return entrantId;
	}
	
	private void updateEntrant(Connection con, Entrant entrant) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.UPDATE_ENTRANT);
			int k = 1;
			pstmt.setString(k++, entrant.getEmail());
			pstmt.setString(k++, entrant.getTel());
			pstmt.setInt(k++, entrant.getId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	private void createEntrantInfo(Connection con, EntrantInfo ei, int entrantId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.CREATE_ENTRANT_INFO);
			int k = 1;
			pstmt.setLong(k++, entrantId);
			pstmt.setString(k++, ei.getFirstName());
			pstmt.setString(k++, ei.getMiddleName());
			pstmt.setString(k++, ei.getLastName());
			pstmt.setString(k++, ei.getAdress());
			pstmt.setString(k++, ei.getOblast());
			pstmt.setString(k++, ei.getSchool());
			pstmt.setLong(k++, ei.getLocaleId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}
	
	private void updateEntrantInfo(Connection con, EntrantInfo ei, int entrantId) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.UPDATE_ENTRANT_INFO);
			int k = 1;
			pstmt.setString(k++, ei.getFirstName());
			pstmt.setString(k++, ei.getMiddleName());
			pstmt.setString(k++, ei.getLastName());
			pstmt.setString(k++, ei.getAdress());
			pstmt.setString(k++, ei.getOblast());
			pstmt.setString(k++, ei.getSchool());
			pstmt.setInt(k++, entrantId);
			pstmt.setInt(k++, ei.getLocaleId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}

	private void createGrades(Connection con, Entrant e) throws SQLException {
		PreparedStatement pstmt = null;
		for (Grade g : e.getGl()) {
			try {
				pstmt = con.prepareStatement(Queries.CREATE_GRADE);
				int k = 1;
				pstmt.setInt(k++, e.getId());
				pstmt.setInt(k++, g.getSubjectId());
				pstmt.setInt(k++, g.getGrade());
				pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
		}
	}
	
	private void updateGrades(Connection con, Entrant e) throws SQLException {
		PreparedStatement pstmt = null;
		for (Grade g : e.getGl()) {
			try {
				pstmt = con.prepareStatement(Queries.UPDATE_GRADES);
				int k = 1;
				pstmt.setInt(k++, g.getGrade());
				pstmt.setInt(k++, g.getId());
				pstmt.executeUpdate();
			} finally {
				close(pstmt);
			}
		}
	}

	private Entrant extractEntrant(ResultSet rs) throws SQLException {
		Entrant entrant = new Entrant();
		EntrantInfo ei = new EntrantInfo();
		entrant.setId(rs.getInt(Fields.ENTITY_ID));
		entrant.setBlocked(rs.getBoolean(Fields.ENTRANT_BLOCKED));
		entrant.setUserId(rs.getInt(Fields.ENTRANT_USER_ID));
		entrant.setEmail(rs.getString(Fields.ENTRANT_EMAIL));
		entrant.setCertificateURL(rs.getString(Fields.ENTRANT_CERTIFICATE));
		entrant.setTel(rs.getString(Fields.ENTRANT_TEL));
		
		ei.setFirstName(rs.getString(Fields.ENTRANT_F_NAME));
		ei.setMiddleName(rs.getString(Fields.ENTRANT_M_NAME));
		ei.setLastName(rs.getString(Fields.ENTRANT_L_NAME));
		ei.setAdress(rs.getString(Fields.ENTRANT_ADRESS));
		ei.setOblast(rs.getString(Fields.ENTRANT_OBLAST));
		ei.setSchool(rs.getString(Fields.ENTRANT_SCHOOL));
		ei.setLocaleId(rs.getInt(Fields.ENTRANT_LOCALE));
		
		entrant.setInfo(ei);
		return entrant;
	}
	
	private EntrantInfo extractEntrantInf(ResultSet rs) throws SQLException {
		EntrantInfo ei = new EntrantInfo();
		ei.setFirstName(rs.getString(Fields.ENTRANT_F_NAME));
		ei.setMiddleName(rs.getString(Fields.ENTRANT_M_NAME));
		ei.setLastName(rs.getString(Fields.ENTRANT_L_NAME));
		ei.setAdress(rs.getString(Fields.ENTRANT_ADRESS));
		ei.setOblast(rs.getString(Fields.ENTRANT_OBLAST));
		ei.setSchool(rs.getString(Fields.ENTRANT_SCHOOL));
		ei.setLocaleId(rs.getInt(Fields.ENTRANT_LOCALE));
		return ei;
	}
	
	private Entrant extractEntrantE(ResultSet rs) throws SQLException {
		Entrant entrant = new Entrant();
		entrant.setId(rs.getInt(Fields.ENTITY_ID));
		entrant.setBlocked(rs.getBoolean(Fields.ENTRANT_BLOCKED));
		entrant.setUserId(rs.getInt(Fields.ENTRANT_USER_ID));
		entrant.setEmail(rs.getString(Fields.ENTRANT_EMAIL));
		entrant.setTel(rs.getString(Fields.ENTRANT_TEL));
		entrant.setCertificateURL(rs.getString(Fields.ENTRANT_CERTIFICATE));
		return entrant;
	}
}

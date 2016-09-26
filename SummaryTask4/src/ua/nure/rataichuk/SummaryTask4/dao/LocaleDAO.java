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

import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.exeptions.Messages;

/**
 * Locale entity access class
 * 
 * @author Ivan Rataichuk
 *
 */
public class LocaleDAO extends DAO {
	private static final Logger LOG = Logger.getLogger(LocaleDAO.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static LocaleDAO instance;

	public static synchronized LocaleDAO getInstance() throws DBException {
		if (instance == null) {
			instance = new LocaleDAO();
		}
		return instance;
	}

	private LocaleDAO() throws DBException {
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
	 * Returns a language ID by its' name.
	 * 
	 * @param lang
	 *            Locale language.
	 * @return int ID.
	 * @throws DBException
	 */
	public int read(String lang) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int id = -1;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_LOCALE_ID);
			pstmt.setString(1, lang);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_LANG_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return id;
	}
	
	/**
	 * Returns a language by its' id.
	 * 
	 * @param id
	 *            int.
	 * @return lang.
	 * @throws DBException
	 */
	public String read(int id) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		String lang = new String();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_LOCALE_LANG);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				lang = rs.getString("lang");
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_LANG_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return lang;
	}
	
	/**
	 * Returns a list of language names.
	 * 
	 * @return List String of names.
	 * @throws DBException
	 */
	public List<String> read() throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		List<String> langs = new ArrayList<>();
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_ALL_LOCALES);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				langs.add(rs.getString("lang"));
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_LANG_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return langs;
	}
	
	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////
}

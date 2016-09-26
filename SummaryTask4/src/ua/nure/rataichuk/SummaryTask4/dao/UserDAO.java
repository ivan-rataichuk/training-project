package ua.nure.rataichuk.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.entities.User;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.exeptions.Messages;

/**
 * User entity access class
 * 
 * @author Ivan Rataichuk
 *
 */
public class UserDAO extends DAO {
	private static final Logger LOG = Logger.getLogger(UserDAO.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static UserDAO instance;

	public static synchronized UserDAO getInstance() throws DBException {
		if (instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}

	private UserDAO() throws DBException {
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
	 * Create user.
	 * 
	 * @param user
	 *            user to create.
	 * @throws DBException
	 */
	public void create(User user) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			createUser(con, user);
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_CREATE_USER, ex);
		} finally {
			close(con);
		}
	}
	
	/**
	 * Returns a user with the given login and password.
	 * 
	 * @param login
	 *            User login.
	 * @return User entity.
	 * @throws DBException
	 */
	public User read(String login, String password) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_USER_BY_LOGIN);
			int k = 1;
			pstmt.setString(k++, login);
			pstmt.setString(k++, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}
	
	/**
	 * Returns a user with the given email.
	 * 
	 * @param email
	 *           
	 * @return User entity.
	 * @throws DBException
	 */
	public User readByEmail(String email) throws DBException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_USER_BY_EMAIL);
			int k = 1;
			pstmt.setString(k++, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return user;
	}
	
	/**
	 * Returns a role ID by its' name.
	 * 
	 * @param name
	 *            String name.
	 * @return int ID.
	 * @throws DBException
	 */
	public int read(String name) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		int id = -1;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(Queries.FIND_ROLE_ID);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt("id");
			}
			con.commit();
		} catch (SQLException ex) {
			rollback(con);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_ROLE_ID, ex);
		} finally {
			close(con, pstmt, rs);
		}
		return id;
	}
	
	// //////////////////////////////////////////////////////////
	// Entity access methods
	// //////////////////////////////////////////////////////////
	
	private void createUser(Connection con, User user) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(Queries.CREATE_USER);
			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getPassword());
			pstmt.setString(k++, user.getEmail());
			pstmt.setLong(k++, user.getRoleId());
			pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
	}
	
	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(Fields.ENTITY_ID));
		user.setLogin(rs.getString(Fields.USER_LOGIN));
		user.setPassword(rs.getString(Fields.USER_PASSWORD));
		user.setEmail(rs.getString(Fields.USER_EMAIL));
		user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
		return user;
	}
}

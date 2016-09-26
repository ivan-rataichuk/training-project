package ua.nure.rataichuk.SummaryTask4.service;

import java.util.Locale;

import org.apache.log4j.Logger;

import ua.nure.rataichuk.SummaryTask4.dao.EntrantDAO;
import ua.nure.rataichuk.SummaryTask4.dao.LocaleDAO;
import ua.nure.rataichuk.SummaryTask4.dao.UserDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Emessage;
import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.entities.User;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;


/**
 * Authorization service layers
 * 
 * @author Ivan Rataichuk
 *
 */
public class AuthorizationSE {
	
	private static final Logger LOG = Logger.getLogger(AuthorizationSE.class);
	
	/**
	 * Find user for given login and password 
	 * and get it's credentials
	 * 
	 * @param login String
	 * @param password String
	 * @return Visitor entity
	 */
	public Visitor getCredentials(String login, String password, Locale loc) {
		Visitor v = new Visitor();
		try {
			UserDAO udao = UserDAO.getInstance();
			LocaleDAO ldao = LocaleDAO.getInstance();
			EntrantDAO edao = EntrantDAO.getInstance();
			User user = udao.read(login, password);
			
			if (user == null) {
				return null;
			}
			
			user.setLocaleId(ldao.read(loc.getLanguage()));
			
			if (udao.read("user") == user.getRoleId()) {
				Entrant e = edao.read(user);
				v.setId(user.getId());
				v.setEmail(user.getEmail());
				v.setCredentials("user");
				if (e != null) {
					v.setFirstName(e.getInfo().getFirstName());
					v.setLastName(e.getInfo().getLastName());
					v.setEntrantId(e.getId());
				}
			}
			
			if (udao.read("admin") == user.getRoleId()) {
				v.setId(user.getId());
				v.setEmail(user.getEmail());
				v.setFirstName("Admin");
				v.setCredentials("admin");
			}
			
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
		return v;
	}
	
	/**
	 * send user's credentials to email is it exists in db 
	 * 
	 * @param email
	 */
	public void remindPassword(String email) {
		try {
			UserDAO udao = UserDAO.getInstance();
			User u = udao.readByEmail(email);
			
			if (u == null) {
				return;
			}
			
			Emessage mes = new Emessage();
			
			mes.setMailTo(u.getEmail());
			mes.setMailFrom(EmailMessages.MAIL_FROM);
			mes.setSubject(EmailMessages.SUBJECT_CREDENTIALS_REMINDER);
			
			String text = "Your credentials:\n	Login: " + u.getLogin() + "\n	Password: " + u.getPassword();
			mes.setText(text);
			
			Email em = new Email();
			em.SendMailSSL(mes);
			
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}
	
	/**
	 * Create User with given parameters.
	 * 
	 * @param login
	 * @param password
	 * @param email
	 * @param role
	 */
	public void createUser(User u, Visitor v) {
		
		try {
			UserDAO udao = UserDAO.getInstance();
			if (v != null) {
				if (v.getCredentials().equals("admin")) {
					u.setRoleId(udao.read("admin"));
				} 
			} else {
				u.setRoleId(udao.read("user"));
			}
			udao.create(u);
		} catch (DBException e) {
			LOG.error(e.getMessage());
		}
	}
	
}

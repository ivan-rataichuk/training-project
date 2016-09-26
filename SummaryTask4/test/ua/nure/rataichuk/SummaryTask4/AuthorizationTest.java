package ua.nure.rataichuk.SummaryTask4;


import static org.junit.Assert.*;

import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import ua.nure.rataichuk.SummaryTask4.service.AuthorizationSE;
import ua.nure.rataichuk.SummaryTask4.viewEntitys.Visitor;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class AuthorizationTest {

	
	@BeforeClass
	public static void setUpClass() throws Exception {
		try {
			// Create initial context
			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
			System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
			InitialContext ic = new InitialContext();

			ic.createSubcontext("java:");
			ic.createSubcontext("java:/comp");
			ic.createSubcontext("java:/comp/env");
			ic.createSubcontext("java:/comp/env/jdbc");

			// Construct DataSource
			DataSource ds = new DataSource();
			ds.setUrl("jdbc:mysql://localhost:3306/webapp");
			ds.setUsername("air");
			ds.setPassword("air051088");
			ds.setDefaultAutoCommit(false);

			ic.bind("java:/comp/env/jdbc/SummaryTask4", ds);
		} catch (NamingException ex) {
			Logger.getLogger(AuthorizationTest.class.getName()).log(Level.ERROR, null, ex);
		}

	}

	@Test 
	public void testCredentialsUser() {
		String login = "user";
		String password = "user";
		Locale loc = new Locale("en");
		AuthorizationSE aut = new AuthorizationSE();
		Visitor v = aut.getCredentials(login, password, loc);
		
		assertTrue(v.getCredentials().equals("user"));
	}
	
	@Test 
	public void testCredentialsAdmin() {
		String login = "admin";
		String password = "admin";
		Locale loc = new Locale("en");
		AuthorizationSE aut = new AuthorizationSE();
		Visitor v = aut.getCredentials(login, password, loc);
		
		assertTrue(v.getCredentials().equals("admin"));
	}
	
	@Test 
	public void testCredentialsNull() {
		String login = "unknown";
		String password = "unknown";
		Locale loc = new Locale("en");
		AuthorizationSE aut = new AuthorizationSE();
		Visitor v = aut.getCredentials(login, password, loc);
		
		assertNull(v);
	}

}

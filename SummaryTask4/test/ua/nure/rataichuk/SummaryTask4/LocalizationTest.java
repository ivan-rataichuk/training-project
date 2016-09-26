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

import ua.nure.rataichuk.SummaryTask4.service.Localization;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class LocalizationTest {

	
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
			Logger.getLogger(LocalizationTest.class.getName()).log(Level.ERROR, null, ex);
		}

	}

	@Test 
	public void testExist() {
		Locale loc = new Locale("uk");
		Localization lc = new Localization();
		assertSame(lc.checkLocale(loc), loc);
	}
	
	@Test 
	public void testNotExist() {
		Locale loc = new Locale("cz");
		Localization lc = new Localization();
		Locale chk = lc.checkLocale(loc);
		assertTrue(chk.getLanguage().equals("en"));
	}
	

}

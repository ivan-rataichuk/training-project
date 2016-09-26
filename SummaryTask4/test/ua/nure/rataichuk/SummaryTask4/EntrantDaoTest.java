package ua.nure.rataichuk.SummaryTask4;


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

import ua.nure.rataichuk.SummaryTask4.dao.EntrantDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Entrant;
import ua.nure.rataichuk.SummaryTask4.entities.EntrantInfo;
import ua.nure.rataichuk.SummaryTask4.entities.Grade;
import ua.nure.rataichuk.SummaryTask4.entities.User;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class EntrantDaoTest {
	
	private int userId = 99;

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
			Logger.getLogger(EntrantDaoTest.class.getName()).log(Level.ERROR, null, ex);
		}

	}

	@Test
	public void testCreate() throws DBException {
		EntrantDAO edao = EntrantDAO.getInstance();
		Entrant e = new Entrant();
		EntrantInfo ei = new EntrantInfo();
		e.setEmail("test@test.com");
		e.setUserId(userId);
		ei.setFirstName("Test");
		ei.setMiddleName("Test");
		ei.setLastName("Test");
		ei.setAdress("Test");
		ei.setOblast("Test");
		ei.setSchool("Test");
		ei.setLocaleId(1);
		e.getInfoList().add(ei);

		Grade g = new Grade();
		g.setEntrantId(99);
		g.setSubjectId(99);
		g.setGrade(100);
		e.getGl().add(g);
		edao.create(e);
	}
	
	@Test
	public void testRead() throws DBException {
		EntrantDAO edao = EntrantDAO.getInstance();
		User u = new User();
		u.setId(userId);
		edao.read(u);
	}

	@Test(expected = Exception.class)
	public void testCreateEx() throws DBException {
		EntrantDAO edao = EntrantDAO.getInstance();
		Entrant e = new Entrant();
		EntrantInfo ei = new EntrantInfo();
		e.setEmail("test@test.com");
		e.setUserId(userId);
		ei.setFirstName("Test");
		ei.setMiddleName("Test");
		ei.setLastName(null);
		ei.setAdress("Test");
		ei.setOblast("Test");
		ei.setSchool("Test");
		ei.setLocaleId(1);
		e.getInfoList().add(ei);

		Grade g = new Grade();
		g.setEntrantId(99);
		g.setSubjectId(99);
		g.setGrade(100);
		e.getGl().add(g);
		edao.create(e);
	}
}

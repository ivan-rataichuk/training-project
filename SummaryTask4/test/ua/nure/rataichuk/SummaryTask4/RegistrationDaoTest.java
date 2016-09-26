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

import ua.nure.rataichuk.SummaryTask4.dao.RegistrationDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Registration;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class RegistrationDaoTest {
	

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
			Logger.getLogger(RegistrationDaoTest.class.getName()).log(Level.ERROR, null, ex);
		}

	}

	@Test
	public void testCreate() throws DBException {
		RegistrationDAO rdao = RegistrationDAO.getInstance();
		Registration reg = new Registration();
		reg.setEntrantId(99);
		reg.setFacultyId(99);
		reg.setMgId(1);
		reg.setSgId(1);
		reg.setTgId(1);
		rdao.create(reg);
	}
	
	@Test
	public void testRead() throws DBException {
		RegistrationDAO rdao = RegistrationDAO.getInstance();
		rdao.read(99);
	}
}

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

import ua.nure.rataichuk.SummaryTask4.dao.SubjectDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Subject;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class SubjectDaoTest {

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
			Logger.getLogger(SubjectDaoTest.class.getName()).log(Level.ERROR, null, ex);
		}

	}

	@Test
	public void testCreate() throws DBException {
		SubjectDAO sdao = SubjectDAO.getInstance();
		Subject sub = new Subject();
		sub.setName("test");
		sub.setLocaleId(1);
		sdao.create(sub);
	}
	
	@Test
	public void testRead() throws DBException {
		SubjectDAO sdao = SubjectDAO.getInstance();
		sdao.read(1);
	}

	@Test(expected = Exception.class)
	public void testCreateEx() throws DBException {
		SubjectDAO sdao = SubjectDAO.getInstance();
		Subject sub = new Subject();
		sub.setName(null);
		sub.setLocaleId(1);
		sdao.create(sub);
	}
}

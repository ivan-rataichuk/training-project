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

import ua.nure.rataichuk.SummaryTask4.dao.FacultyDAO;
import ua.nure.rataichuk.SummaryTask4.entities.Faculty;
import ua.nure.rataichuk.SummaryTask4.entities.FacultyInfo;
import ua.nure.rataichuk.SummaryTask4.exeptions.DBException;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class FacultyDaoTest {
	
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
			Logger.getLogger(FacultyDaoTest.class.getName()).log(Level.ERROR, null, ex);
		}

	}

	@Test
	public void testCreate() throws DBException {
		FacultyDAO fdao = FacultyDAO.getInstance();
		Faculty f = new Faculty();
		FacultyInfo fi = new FacultyInfo();
		f.setMsId(1);
		f.setSsId(2);
		f.setTsId(3);
		f.setPositions(5);
		f.setBudgetPositions(3);
		fi.setName("test");
		fi.setDescription("test");
		fi.setLocale_id(1);
		f.getInfoList().add(fi);
		fdao.create(f);
	}

	@Test(expected = Exception.class)
	public void testCreateEx() throws DBException {
		FacultyDAO fdao = FacultyDAO.getInstance();
		Faculty f = new Faculty();
		FacultyInfo fi = new FacultyInfo();
		f.setMsId(1);
		f.setSsId(2);
		f.setTsId(3);
		f.setPositions(5);
		f.setBudgetPositions(3);
		fi.setName(null);
		fi.setDescription("test");
		fi.setLocale_id(1);
		f.getInfoList().add(fi);
		fdao.create(f);
	}
}

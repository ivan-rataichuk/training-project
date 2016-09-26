package ua.nure.rataichuk.SummaryTask4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({EntrantDaoTest.class, UserDaoTest.class, FacultyDaoTest.class, SubjectDaoTest.class,
				RegistrationDaoTest.class, AuthorizationTest.class, LocalizationTest.class})
public class AllTests {
	
}

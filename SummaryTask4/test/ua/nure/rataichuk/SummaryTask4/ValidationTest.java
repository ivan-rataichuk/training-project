package ua.nure.rataichuk.SummaryTask4;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class ValidationTest {

	@Test 
	public void testEntrant() {
		
		MockClass mc = new MockClass();
		HttpServletRequest request = mc.getHttpServletRequest();
		
	}
	

}

package test.java.soapui;

import static org.junit.Assert.assertEquals;
import main.java.soapui.util.SoapUIUtil;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;

public class SoapUITest
{
	@BeforeClass
	public static void setUp()
	{
		SoapUIUtil.startMockService();
	}
	
	@Test
	public void simpleLoginAndLogoutWithPropSteps()
	{
		TestCase test = SoapUIUtil.getTestCaseByName("Simple Login and Logout w. Properties Steps");
		
		TestRunner runner = test.run(new PropertiesMap(), false);			
		assertEquals(Status.FINISHED, runner.getStatus());
	}
	
	@Test
	public void simpleLoginAndLogoutPropExpansion()
	{
		TestCase test = SoapUIUtil.getTestCaseByName("Simple Login and Logout Property Expansion");
		
		TestRunner runner = test.run(new PropertiesMap(), false);			
		assertEquals(Status.FINISHED, runner.getStatus());
	}
	
	@Test
	public void simpleLoginAndLogoutAndLoginAgain()
	{
		TestCase test = SoapUIUtil.getTestCaseByName("Simple Login and Logout and Login Again");
		
		TestRunner runner = test.run(new PropertiesMap(), false);			
		assertEquals(Status.FINISHED, runner.getStatus());
	}
	
	@Test
	public void simpleSearchTestCase()
	{
		TestCase test = SoapUIUtil.getTestCaseByName("Simple Search TestCase");
		
		TestRunner runner = test.run(new PropertiesMap(), false);			
		assertEquals(Status.FINISHED, runner.getStatus());
	}
	
	@AfterClass
	public static void tearDown()
	{
		SoapUIUtil.stopMockService();
	}
}

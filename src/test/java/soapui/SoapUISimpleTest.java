package soapui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.eviware.soapui.tools.SoapUITestCaseRunner;

import soapui.util.SoapUIUtil;

public class SoapUISimpleTest
{
	@BeforeClass
	public static void setUp()
	{
		SoapUIUtil.startMockService();
	}
	
	@Test
	public void runAllTests() throws Exception
	{
		SoapUITestCaseRunner runner = new SoapUITestCaseRunner();
		runner.setProjectFile("src/test/resources/" + SoapUIUtil.getProperty("soapui.project"));
		runner.run();
	}
	
	@AfterClass
	public static void tearDown()
	{
		SoapUIUtil.stopMockService();
	}
}

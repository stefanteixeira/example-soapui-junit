package main.java.soapui.util;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockRunner;
import com.eviware.soapui.impl.wsdl.mock.WsdlMockService;
import com.eviware.soapui.model.testsuite.TestCase;
import com.eviware.soapui.model.testsuite.TestSuite;

public class SoapUIUtil
{
	private static WsdlProject project;
	private static WsdlMockService mockService;
	private static WsdlMockRunner mockRunner;

	public static WsdlProject getProject()
	{
		try
		{
			project = new WsdlProject("src/test/resources/" + getProperty("soapui.project"));
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return project;
	}

	public static TestCase getTestCaseByName(String testCaseName)
	{
		Map<String, TestCase> testCases = new HashMap<String, TestCase>();

		WsdlProject wsdlProject = getProject();
		TestSuite suite = wsdlProject.getTestSuiteByName(getProperty("soapui.test.suite"));

		List<TestCase> testCaseList = suite.getTestCaseList();

		for (TestCase test : testCaseList)
		{
			testCases.put(test.getName(), test);
		}

		System.out.println("Running SoapUI test: " + testCaseName);
		return testCases.get(testCaseName);
	}

	public static void startMockService()
	{
		mockService = getProject().getMockServiceByName("ServiceSoapBinding MockService");
		try
		{
			mockRunner = mockService.start();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void stopMockService()
	{
		mockRunner.stop();
	}

	public static String getProperty(String property)
	{
		try
		{
			Properties file = loadPropertiesFile();
			return file.getProperty(property);
		} catch (Exception e)
		{
			e.printStackTrace();
			return "Could not get property from config file";
		}
	}

	private static Properties loadPropertiesFile() throws Exception
	{
		Properties config = new Properties();
		config.load(new FileReader("src/test/resources/config.properties"));

		return config;
	}
}

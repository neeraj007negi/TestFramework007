package com.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterUtility {

	private static ExtentSparkReporter sparkreporter;	
	private static ExtentReports extentreports;
	private static ThreadLocal<ExtentTest> extenttests= new ThreadLocal<ExtentTest>();
	
	
	public  static ExtentTest getExtenttests()
	{
		return extenttests.get();
	}

	public static void InitiateSparkReporter()
	{
	sparkreporter= new ExtentSparkReporter(System.getProperty("user.dir")+"//Extentreport.html");
	extentreports = new ExtentReports();
	extentreports.attachReporter(sparkreporter);
	}
	
	public static void CreateExtentTest(String testname)
	{
		ExtentTest etests = extentreports.createTest(testname);		
		extenttests.set(etests);
	}
	
	public static void FlushReports()
	{
		extentreports.flush();
	}
}

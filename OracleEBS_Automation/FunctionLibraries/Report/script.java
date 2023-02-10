import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.basic.api.exceptions.AbstractScriptException;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.webdom.api.*;
import lib.*;

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	@FunctionLibrary("CommonLibrary") lib.myEBS.myEBS.CommonLibrary commonLibrary;

	public void initialize() throws Exception {	}
	public void run() throws Exception { }
	public void finish() throws Exception {	}

	ExtentReports extent;
	ExtentTest test;

	private ExtentReports ExtentManager(String filePath) throws AbstractScriptException {
		if (extent == null) {
			extent = new ExtentReports(filePath, false);
			Map<String, String> sysInfo = new HashMap<String, String>();
			sysInfo.put("OATS Version", commonLibrary.getProperty("OATSVersion"));
			sysInfo.put("Environment", commonLibrary.getProperty("Environment"));
			extent
			.addSystemInfo(sysInfo);
		}
		return extent;
	}

	public void startTest(String testStepName, String desc) throws AbstractScriptException {
		commonLibrary.loadEnvironmentConfigForUI();
		String reportPath = commonLibrary.getBaseFolderPath() + "\\" + commonLibrary.getProperty("ReportFolder");
		String reportCleanUpDays = commonLibrary.getProperty("ReportCleanUpDays");
		commonLibrary.frameworkCleanUp(reportPath, reportCleanUpDays);

		String reportFilePath = commonLibrary.getBaseFolderPath() + "\\" + commonLibrary.getProperty("ReportFolder") + "\\" + commonLibrary.getProperty("ReportName");
		extent = ExtentManager(reportFilePath);
		test = extent.startTest(testStepName, desc);
		test.assignAuthor(commonLibrary.getProperty("ReportAuthor"));
		test.assignCategory(commonLibrary.getProperty("ReportCategory"));
	}

	public void endTest() {
		extent.endTest(test);
		extent.flush();
	}

//	public LogStatus getTestStatus(){
//		LogStatus status = test.getRunStatus();
//		//Assert.assertEquals(test.getRunStatus(), LogStatus.PASS);
//		return status;
//	}

	public void pass(String testStepName, String desc, boolean takeScreenshot) throws AbstractScriptException {
		if (takeScreenshot) {
			String img = test.addScreenCapture(commonLibrary.takeScreenshot());
			test.log(LogStatus.PASS, "<span style ='color:green'>" + testStepName + "</span>", "<span style ='color:green'>" + desc + "</span>" + img);
		} else {
			test.log(LogStatus.PASS, "<span style ='color:green'>" + testStepName + "</span>", "<span style ='color:green'>" + desc + "</span>");
		}
		commonLibrary.infoMsg(testStepName + " - " + desc);
	}

	public void fail(String testStepName, String desc, boolean takeScreenshot) throws AbstractScriptException {
		if (takeScreenshot) {
			String img = test.addScreenCapture(commonLibrary.takeScreenshot());
			test.log(LogStatus.FAIL, "<span style ='color:red'>" + testStepName + "</span>", "<span style ='color:red'>" + desc + "</span>" + img);
		} else {
			test.log(LogStatus.FAIL, "<span style ='color:red'>" + testStepName + "</span>", "<span style ='color:red'>" + desc + "</span>");
		}
		commonLibrary.warnMsg(testStepName + " - " + desc);
	}

	public void info(String testStepName, String desc) throws AbstractScriptException {
		test.log(LogStatus.INFO, "<span style ='color:blue'>" + testStepName + "</span>", "<span style ='color:blue'>" + desc + "</span>");
		commonLibrary.infoMsg(testStepName + " - " + desc);
	}

	public void skip(String testStepName, String desc) throws AbstractScriptException {
		test.log(LogStatus.SKIP, "<span style ='color:DodgerBlue'>" + testStepName + "</span>", "<span style ='color:DodgerBlue'>" + desc + "</span>");
		commonLibrary.infoMsg(testStepName + " - " + desc);
	}

	public void warn(String testStepName, String desc) throws AbstractScriptException {
		test.log(LogStatus.WARNING, "<span style ='color:brown'>" + testStepName + "</span>", "<span style ='color:brown'>" + desc + "</span>");
		commonLibrary.warnMsg(testStepName + " - " + desc);
	}

	public void fatal(String testStepName, String desc) throws AbstractScriptException {
		test.log(LogStatus.FATAL, "<span style ='font-weight:bold; color:maroon'>" + testStepName + "</span>", "<span style ='font-weight:bold; color:maroon'>" + desc + "</span>");
		commonLibrary.warnMsg(testStepName + " - " + desc);
	}

	public void error(String testStepName, String desc) throws AbstractScriptException {
		test.log(LogStatus.ERROR, "<span style ='font-weight:bold; color:orange'>" + testStepName + "</span>", "<span style ='font-weight:bold; color:orange'>" + desc + "</span>");
		commonLibrary.warnMsg(testStepName + " - " + desc);
	}
	
}

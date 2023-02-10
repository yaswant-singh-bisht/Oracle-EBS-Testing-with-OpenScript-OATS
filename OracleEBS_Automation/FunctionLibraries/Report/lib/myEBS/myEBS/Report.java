//WARNING!
//This file was created by Oracle OpenScript.
//Don't change it.

package lib.myEBS.myEBS;

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
import oracle.oats.scripting.modules.basic.api.internal.FuncLibraryWrapper;

public class Report extends FuncLibraryWrapper
{

	public void initialize() throws AbstractScriptException {
		checkInit();
		callFunction("initialize");
	}

	public void run() throws AbstractScriptException {
		checkInit();
		callFunction("run");
	}

	public void finish() throws AbstractScriptException {
		checkInit();
		callFunction("finish");
	}

	public void startTest(String testStepName, String desc)
			throws AbstractScriptException {
		checkInit();
		callFunction("startTest", testStepName, desc);
	}

	public void endTest() throws AbstractScriptException {
		checkInit();
		callFunction("endTest");
	}

	public void pass(String testStepName, String desc, boolean takeScreenshot)
			throws AbstractScriptException {
		checkInit();
		callFunction("pass", testStepName, desc, takeScreenshot);
	}

	public void fail(String testStepName, String desc, boolean takeScreenshot)
			throws AbstractScriptException {
		checkInit();
		callFunction("fail", testStepName, desc, takeScreenshot);
	}

	public void info(String testStepName, String desc)
			throws AbstractScriptException {
		checkInit();
		callFunction("info", testStepName, desc);
	}

	public void skip(String testStepName, String desc)
			throws AbstractScriptException {
		checkInit();
		callFunction("skip", testStepName, desc);
	}

	public void warn(String testStepName, String desc)
			throws AbstractScriptException {
		checkInit();
		callFunction("warn", testStepName, desc);
	}

	public void fatal(String testStepName, String desc)
			throws AbstractScriptException {
		checkInit();
		callFunction("fatal", testStepName, desc);
	}

	public void error(String testStepName, String desc)
			throws AbstractScriptException {
		checkInit();
		callFunction("error", testStepName, desc);
	}

}


//WARNING!
//This file was created by Oracle OpenScript.
//Don't change it.

package lib.myEBS.myEBS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.Normalizer.Form;
import java.util.Date;
import java.util.Properties;
import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.basic.api.exceptions.AbstractScriptException;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.browser.api.BrowserSettings.BrowserType;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.functionalTest.common.api.internal.types.FTCoreException;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.webdom.api.*;
import oracle.oats.scripting.modules.webdom.api.elements.DOMBrowser;
import oracle.oats.scripting.modules.webdom.api.elements.DOMButton;
import oracle.oats.scripting.modules.webdom.api.elements.DOMImage;
import oracle.oats.scripting.modules.webdom.api.elements.DOMText;
import oracle.oats.scripting.modules.formsFT.api.*;
import oracle.oats.scripting.modules.applet.api.*;
import oracle.oats.scripting.modules.basic.api.internal.FuncLibraryWrapper;

public class CommonLibrary extends FuncLibraryWrapper
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

	public void shortWait() throws AbstractScriptException {
		checkInit();
		callFunction("shortWait");
	}

	public void Wait() throws AbstractScriptException {
		checkInit();
		callFunction("Wait");
	}

	public void longWait() throws AbstractScriptException {
		checkInit();
		callFunction("longWait");
	}

	public void llongWait() throws AbstractScriptException {
		checkInit();
		callFunction("llongWait");
	}

	public boolean objIsDisplayed(String objLocator)
			throws AbstractScriptException {
		checkInit();
		return (Boolean) callFunction("objIsDisplayed", objLocator);
	}

	public boolean objIsDisplayedByID(String objLocator)
			throws AbstractScriptException {
		checkInit();
		return (Boolean) callFunction("objIsDisplayedByID", objLocator);
	}

	public String getXpath(String v_OL, String v_Key)
			throws AbstractScriptException {
		checkInit();
		return (String) callFunction("getXpath", v_OL, v_Key);
	}

	public void fn_LaunchBrowser(String browserToLaunch)
			throws AbstractScriptException {
		checkInit();
		callFunction("fn_LaunchBrowser", browserToLaunch);
	}

	/**
	 * This will return the value of the passed in prop from the config file loaded in loadEnvironmentConfig
	 * @param configPath
	 */
	public void loadEnvironmentConfig(String configPath)
			throws AbstractScriptException {
		checkInit();
		callFunction("loadEnvironmentConfig", configPath);
	}

	public String getBaseFolderPath() throws AbstractScriptException {
		checkInit();
		return (String) callFunction("getBaseFolderPath");
	}

	public void loadEnvironmentConfigForUI() throws AbstractScriptException {
		checkInit();
		callFunction("loadEnvironmentConfigForUI");
	}

	public String getProperty(String propName) throws AbstractScriptException {
		checkInit();
		return (String) callFunction("getProperty", propName);
	}

	public void setProperty(String propName, String value)
			throws AbstractScriptException {
		checkInit();
		callFunction("setProperty", propName, value);
	}

	public void scriptInitialization() throws AbstractScriptException {
		checkInit();
		callFunction("scriptInitialization");
	}

	public void scriptTermination() throws AbstractScriptException {
		checkInit();
		callFunction("scriptTermination");
	}

	public void navigateToApp(String appURL) throws AbstractScriptException {
		checkInit();
		callFunction("navigateToApp", appURL);
	}

	public void signInToApplication(String username, String password,
			String appName) throws AbstractScriptException {
		checkInit();
		callFunction("signInToApplication", username, password, appName);
	}

	public void signOutFromApplication() throws AbstractScriptException {
		checkInit();
		callFunction("signOutFromApplication");
	}

	public void closeForm(String choice) throws AbstractScriptException {
		checkInit();
		callFunction("closeForm", choice);
	}

	public boolean objClick(String elePath, String eleLocator)
			throws AbstractScriptException {
		checkInit();
		return (Boolean) callFunction("objClick", elePath, eleLocator);
	}

	public String getDatetime() throws AbstractScriptException {
		checkInit();
		return (String) callFunction("getDatetime");
	}

	public String takeScreenshot() throws AbstractScriptException {
		checkInit();
		return (String) callFunction("takeScreenshot");
	}

	public void frameworkCleanUp(String cleanupDirectory, String pastDays)
			throws AbstractScriptException {
		checkInit();
		callFunction("frameworkCleanUp", cleanupDirectory, pastDays);
	}

	public void warnMsg(String message) throws AbstractScriptException {
		checkInit();
		callFunction("warnMsg", message);
	}

	public void infoMsg(String message) throws AbstractScriptException {
		checkInit();
		callFunction("infoMsg", message);
	}

}


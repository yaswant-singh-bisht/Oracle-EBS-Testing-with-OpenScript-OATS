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

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	@ScriptService oracle.oats.scripting.modules.applet.api.AppletService applet;
	@ScriptService oracle.oats.scripting.modules.formsFT.api.FormsService forms;

	public void initialize() throws Exception {	}
	public void run() throws Exception { }
	public void finish() throws Exception {	}
	
	private Properties prop = new Properties();
	public void shortWait() throws InterruptedException
	{
		Thread.sleep(2000);
	}
	
	public void Wait() throws InterruptedException
	{
		Thread.sleep(4000);
	}
	
	public void longWait() throws InterruptedException
	{
		Thread.sleep(6000);
	}
	
	public void llongWait() throws InterruptedException
	{
		Thread.sleep(10000);
	}
	
	public boolean objIsDisplayed(String objLocator) throws AbstractScriptException, Exception
	{
		try {
			if(web.findElementByXPath(objLocator).isDisplayed())
			{
				return true;
			}
		}  catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public boolean objIsDisplayedByID(String objLocator) throws AbstractScriptException, Exception
	{
		try {
			if(web.findElementById(objLocator).isDisplayed())
			{
				return true;
			}
		}  catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public String getXpath(String v_OL,String v_Key) {
		//Properties file instance
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(getScriptPackage().getRepositoryPath()+"\\ObjectLibrary\\"+v_OL+".properties");
			// load a properties file
			prop.load(input);
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if (input != null) {
				try {
					// Closing the file
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// Returns XPATH
		return prop.getProperty(v_Key);
	}

	public void fn_LaunchBrowser(String browserToLaunch) throws Exception {
		String browserSelection = browserToLaunch.toLowerCase();
		try {
			if (browserSelection.equalsIgnoreCase("firefox")) {
				browser.setBrowserType(BrowserType.Firefox);
			} else if (browserSelection.equalsIgnoreCase("chrome")) {
				browser.setBrowserType(BrowserType.Chrome);
			} else if (browserSelection.equalsIgnoreCase("IE") || browserSelection.equalsIgnoreCase("Internet Explorer")) {
				browser.setBrowserType(BrowserType.InternetExplorer);
			} else if (browserSelection.equalsIgnoreCase("edge")) {
				browser.setBrowserType(BrowserType.MicrosoftEdge);
			} else if (browserSelection.equalsIgnoreCase("safari")) {
				browser.setBrowserType(BrowserType.Safari);
			} else
			{
				browser.setBrowserType(BrowserType.Firefox);
				//Default
			}
			browser.launch();
			DOMBrowser currentExecutionBrowser = web.window("/web:window[@index='0' or @index='1']");
			currentExecutionBrowser.maximize();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This will return the value of the passed in prop from the config file
	 * loaded in loadEnvironmentConfig
	 * 
	 * @param configPath
	 */
	public void loadEnvironmentConfig(String configPath) {
		InputStream input = null;
		try {
			input = new FileInputStream(configPath + "qaConfig.properties");
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getBaseFolderPath() {
		String repositoryPath = getScriptPackage().getRepositoryPath();	
		return repositoryPath;
	}
	
	public void loadEnvironmentConfigForUI() {
		String suiteFolderpath = getBaseFolderPath();
		loadEnvironmentConfig(suiteFolderpath+"\\EnvConfig\\");
	}

	public String getProperty(String propName) {
		return prop.getProperty(propName);
	}
	
	public void setProperty(String propName, String value) {
		prop.setProperty(propName, value);
	}
	
	public void scriptInitialization() throws AbstractScriptException, Exception
	{		
		loadEnvironmentConfigForUI();
		try
		{
			fn_LaunchBrowser(getProperty("browserType"));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void scriptTermination() throws AbstractScriptException, Exception
	{
		prop.clear();
	}
	
	public void navigateToApp(String appURL) throws AbstractScriptException, Exception
	{
		String URL="";
		String finderPath="";
		if(appURL.equals("EBS"))
		{
			URL="ebsURL";
			DOMBrowser currentExecutionBrowser = web.window(getXpath("HomePage", "actWindow"));
			currentExecutionBrowser.navigate(getProperty(URL));
			llongWait();

		}
	
		web.window(getXpath("HomePage", "actWindow")).verifyTitle("Login page loaded", "Login");
	}
	
	public void signInToApplication(String username,String password, String appName) throws AbstractScriptException, Exception
	{	
		if(appName.equals("EBS"))
		{
			DOMText userNameField = (DOMText) web.findElementByXPath(getXpath("HomePage", "loginUserNameTxtBox"));
			userNameField.setText(username);
			DOMText passwordField = (DOMText) web.findElementByXPath(getXpath("HomePage", "loginPasswordTxtBox"));
			passwordField.setText(password);
			DOMButton loginButton = (DOMButton) web.findElementByXPath(getXpath("HomePage", "loginLoginBtn"));
			loginButton.click();
		}
		llongWait();	
		
		web.window(getXpath("HomePage", "actWindow")).verifyTitle("Login successful", "Home");
	}
	
	public void signOutFromApplication() throws AbstractScriptException, Exception
	{
		DOMImage logoutLink = (DOMImage) web.findElementByXPath(getXpath("HomePage", "homeLogoutLnk"));
		logoutLink.click();
		Wait();

		web.window(getXpath("HomePage", "actWindow")).verifyTitle("Logout successful", "Login");
		browser.close();
	}
	
	public void closeForm(String choice) throws AbstractScriptException, Exception
	{
		forms.close();
		if(choice != "")
		{
			forms.choiceBox(getXpath("HomePage", "frmCloseChoice")).clickButton(choice);
		}
	}
		
	public boolean objClick(String elePath,String eleLocator) throws AbstractScriptException, Exception
	{
		int selector = 0;
		if(eleLocator.equals("id"))
		{
			selector = 1;
		}
		else
		{
			selector = 2;
		}
		switch (selector) {
		case 1:
			for(int i=0;i<50;i++)
			{
				if(objIsDisplayedByID(elePath))
				{
					web.findElementById(elePath).click();
					return true;
				}
				llongWait();
			}
			break;
		case 2:
			for(int i=0;i<50;i++)
			{
				if(objIsDisplayed(elePath))
				{
					web.findElementByXPath(elePath).click();
					return true;
				}
				llongWait();
			}
			break;
		}
		return false;
	}
	
	public String getDatetime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		String founddate = dateFormat.format(date);
		String[] parts = founddate.split(" ");
		String[] appenderpart1 = parts[0].split("/");
		String[] appenderpart2 = parts[1].split(":");
		String appender = appenderpart1[0] + appenderpart1[1] + appenderpart1[2] + appenderpart2[0] + appenderpart2[1] + appenderpart2[2];
		return appender;
	}
	
	public String takeScreenshot() throws AbstractScriptException{
		String imgName = getScriptPackage().getRepositoryPath()+"\\Report\\capturedImg_"+getDatetime()+".jpg"; 
		File imgFile = new File (imgName); 

		if(imgFile.exists()){
			if(imgFile.setReadOnly()){
				imgFile.setWritable(true);
			}
			imgFile.delete();
		}
		ft.getScreenCapture(0, 0, 1920, 1080, imgName); 
		if(!imgFile.exists()){
			warn("getScreenCapture() failed.");
		}
		return imgName;
	}
	
	/*//Use this code in Scripts
	String scriptFullPath = getScriptPackage().getScriptPath();
	String[] scriptPath = scriptFullPath.split(".jwg");
	String cleanupPath = scriptPath[0]+"\\results\\";
	commonLibrary.frameworkCleanUp(cleanupPath);
	*/
	public void frameworkCleanUp(String cleanupDirectory, String pastDays) {
		File dir = new File(cleanupDirectory);
		loadEnvironmentConfigForUI();
		int days;
		if (pastDays != null){
			days = Integer.parseInt(pastDays);
		} else {
			days = 7;
		}
		long fromTime = System.currentTimeMillis() - (days * 24 * 60 * 60 * 1000) + (12 * 60 * 60 * 1000);
		long toTime = System.currentTimeMillis();
		long validRange = toTime - fromTime;
		File[] files = dir.listFiles();
		if (files != null) 
		{
			for (File file : files) {
				long fileModDate = file.lastModified();
				long fileRange = toTime - fileModDate;
				if (fileRange > validRange && file.isDirectory()) {
					while(file.exists()){
						recursiveDelete(file);
					}
				}
				else if (fileRange > validRange && file.isFile()) {
					file.delete();
				}
			}
		}
	}
	private void recursiveDelete(File file){
		if(file.list().length > 0){
			String[] list = file.list();
			for(String fl: list){
				File currentFile = new File(file.getPath(),fl);
				if(currentFile.isDirectory()){
					recursiveDelete(currentFile);
				}else{
					currentFile.delete();
				}
			}
		}
		else {
			file.delete();
		}
	}
	
	public void warnMsg(String message) throws Exception, AbstractScriptException {
	    saveLog("WARN - " + message);
	    warn(">> " + message);
	}
	
	public void infoMsg(String message) throws Exception, AbstractScriptException {
	    saveLog("INFO - " + message);
	    info(">> " + message);
	}
	
	private void saveLog(String message) throws Exception {
		DateFormat df = new SimpleDateFormat("[yyyy/MM/dd HH:mm:ss]");
		Date sysdate = new Date();
		String modifiedText = df.format(sysdate) + " " + message + "\n";

		String getFile = getScriptPackage().getRepositoryPath()+"\\Log\\resultLog.log";
		try {
			File logFile = new File(getFile);
			if(logFile.exists()){
				Files.write(Paths.get(getFile), modifiedText.getBytes(), StandardOpenOption.APPEND);
			} else {
				logFile.createNewFile();
				Files.write(Paths.get(getFile), modifiedText.getBytes(), StandardOpenOption.APPEND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
}

import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.webdom.api.*;
import oracle.oats.scripting.modules.formsFT.api.*;
import oracle.oats.scripting.modules.formsFT.common.api.elements.TextField;
import oracle.oats.scripting.modules.applet.api.*;
import lib.*;

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	@ScriptService oracle.oats.scripting.modules.applet.api.AppletService applet;
	@ScriptService oracle.oats.scripting.modules.formsFT.api.FormsService forms;
	@ScriptService oracle.oats.scripting.modules.datatable.api.DataTableService datatable;
	@FunctionLibrary("CommonLibrary") lib.myEBS.myEBS.CommonLibrary commonLibrary;
	@FunctionLibrary("EBSApplicationDeveloper") lib.myEBS.myApplicationDeveloper.EBSApplicationDeveloper eBSApplicationDeveloper;
	@FunctionLibrary("Report") lib.myEBS.myEBS.Report report;
	
	String userName;
	String password;
	String appName="EBS";
	String strScriptName;
	
	public void initialize() throws Exception {
		beginStep("Test initialization for report generation");
		{
			strScriptName = getScriptPackage().getScriptName();
			commonLibrary.infoMsg("<<<<<< START - Executing " + strScriptName + " script >>>>>>");
			commonLibrary.infoMsg("Test initialization for report generation");
			report.startTest(strScriptName, "Executing " + strScriptName + " script");
		}
		endStep();
		
		beginStep("Environment initialization");
		{
			commonLibrary.infoMsg("Environment initialization");
			commonLibrary.scriptInitialization();
			commonLibrary.navigateToApp(appName);
		}
		endStep();
		beginStep("Login application");
		{
			commonLibrary.infoMsg("Login application");
			String strRelativePath = getScriptPackage().getRepositoryPath();
			datatable.importExcel(strRelativePath+"\\TestData\\EBSApplication.xlsx", true);
			userName = ((String) datatable.getValue(0,"username"));
			password = ((String) datatable.getValue(0,"password"));
			commonLibrary.signInToApplication(userName, password, appName);
		}
		endStep();
	}

	public void run() throws Exception {
		beginStep("Working with left navigation menu");
		{
			commonLibrary.infoMsg("Working with left navigation menu");
			eBSApplicationDeveloper.navClick();
		}
		endStep();

		beginStep("Entering values on Profiles form");
		{
			commonLibrary.infoMsg("Entering values on Profiles form");
			TextField nameTxt = forms.textField(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesNameTxtBox"));
			boolean nameTxtVis = nameTxt.isVisible();
			if(nameTxtVis){
				nameTxt.setText("NAMETEST5");
				report.pass("Name textbox visible", "Verify that Name textbox is visible", true);
			} else {
				report.fail("Name textbox visible", "Verify that Name textbox is visible", true);
			}
			commonLibrary.Wait();
			forms.textField(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesApplicationTxtBox")).openDialog();
			commonLibrary.Wait();
			forms.window(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesApplicationChooser")).activate(true);
			commonLibrary.Wait();
			forms.listOfValues(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesApplicationList")).select("ADS Development");
			commonLibrary.Wait();
			TextField UserProfileNameTxt = forms.textField(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesUserProfileNameTxtBox"));
			boolean UserProfileNameTxtVis = UserProfileNameTxt.isVisible();
			if(UserProfileNameTxtVis){
				UserProfileNameTxt.setText("User profile name 5");
				report.pass("User Profile Name textbox visible", "Verify that User Profile Name textbox is visible", true);
			} else {
				report.fail("User Profile Name textbox visible", "Verify that User Profile Name textbox is visible", true);
			}
			commonLibrary.Wait();
			TextField ProfilesDescriptionTxt = forms.textField(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesDescriptionTxtBox"));
			boolean ProfilesDescriptionTxtVis = ProfilesDescriptionTxt.isVisible();
			if(ProfilesDescriptionTxtVis){
				ProfilesDescriptionTxt.setText("Description 5");
				report.pass("Description textbox visible", "Verify that Description textbox is visible", true);
			} else {
				report.fail("Description textbox visible", "Verify that Description textbox is visible", true);
			}
			commonLibrary.Wait();
			forms.list(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesHierarchyCmb")).selectItem("Server");
			commonLibrary.Wait();
			forms.checkBox(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesServerEnableChk")).check(false);
			commonLibrary.Wait();
			forms.checkBox(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesUserChangeableChk")).check(false);
			commonLibrary.Wait();
			forms.textField(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesEndDateActive")).openDialog();
			commonLibrary.Wait();
			forms.button(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesCalendarOkBtn")).setFocus();
			commonLibrary.Wait();
			forms.textField(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesCalendarCell")).click();
			commonLibrary.Wait();
			forms.calendar(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesCalendar")).enter("31-JAN-2023");
			commonLibrary.Wait();
			forms.textField(commonLibrary.getXpath("ApplicationDeveloper", "frmProfilesSqlValidationTxtBox")).setText("Sql validation text");
			commonLibrary.Wait();
		}
		endStep();
		
		beginStep("Closing form and discard changes");
		{
			commonLibrary.infoMsg("Closing form and discard changes");
			commonLibrary.closeForm("Discard");
		}
		endStep();
		
	}

	public void finish() throws Exception {
		beginStep("Tear down");
		{
			commonLibrary.infoMsg("Tear down");
			commonLibrary.scriptTermination();
			commonLibrary.signOutFromApplication();
			report.endTest();
			commonLibrary.infoMsg("<<<<<< END - Executing " + strScriptName + " script >>>>>> \n\n");
		}
	}
}

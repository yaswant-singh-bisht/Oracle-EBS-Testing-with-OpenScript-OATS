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
	@FunctionLibrary("EBSHumanResourcesVisionEnterprises") lib.myEBS.myEBS.EBSHumanResourcesVisionEnterprises eBSHumanResourcesVisionEnterprises;
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
			eBSHumanResourcesVisionEnterprises.navClick();
		}
		endStep();

		beginStep("Find Person");
		{
			commonLibrary.infoMsg("Find Person");
			forms.textField(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmFullNameTxtBox")).openDialog();
			commonLibrary.Wait();
			forms.listOfValues(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmValuesLst")).find("arn");
			commonLibrary.Wait();
			forms.listOfValues(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmValuesLst")).select("Arnold, Mr. James|2313|Ex-employee|Human Resources-West|HR008.HR Generalist|");
			commonLibrary.Wait();
			forms.button(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmEmpFindBtn")).click();
			commonLibrary.Wait();
		}
		endStep();

		beginStep("Exploring People form");
		{
			commonLibrary.infoMsg("Exploring People form");
			forms.textField(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmLastNameTxtBox")).setFocus();
			commonLibrary.Wait();
			forms.button(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmNav1Btn")).click();
			commonLibrary.Wait();
			forms.textField(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmAddLine1TxtBox")).setFocus();
			commonLibrary.Wait();
			forms.window(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmWin1")).close();
			commonLibrary.Wait();
			forms.window(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmWin1")).activate(true);
			commonLibrary.Wait();
			TextField LastNameTxt = forms.textField(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmLastNameTxtBox"));
			boolean LastNameTxtVis = LastNameTxt.isVisible();
			if(LastNameTxtVis){
				LastNameTxt.setFocus();
				report.pass("Last Name textbox visible", "Verify that Last Name textbox is visible", true);
			} else {
				report.fail("Last Name textbox visible", "Verify that Last Name textbox is visible", true);
			}
			commonLibrary.Wait();
			TextField CountryBirthTxt = forms.textField(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmCountryBirthTxtBox"));
			boolean CountryBirthTxtVis = CountryBirthTxt.isVisible();
			if(CountryBirthTxtVis){
				CountryBirthTxt.openDialog();
				report.pass("Country of Birth textbox visible", "Verify that Country of Birth textbox is visible", true);
			} else {
				report.fail("Country of Birth textbox visible", "Verify that Country of Birth textbox is visible", true);
			}
			
			commonLibrary.Wait();
			forms.listOfValues(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmValuesLst")).find("%united states");
			commonLibrary.Wait();
			forms.listOfValues(commonLibrary.getXpath("HumanResourcesVisionEnterprises", "frmValuesLst")).select("United States");
			commonLibrary.Wait();
		}
		endStep();

		beginStep("Closing form and discard changes");
		{
			commonLibrary.infoMsg("Exploring People form");
			commonLibrary.closeForm("Discard");
			commonLibrary.Wait();
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

import oracle.oats.scripting.modules.basic.api.FunctionLibrary;
import oracle.oats.scripting.modules.basic.api.IteratingVUserScript;
import oracle.oats.scripting.modules.basic.api.ScriptService;
import oracle.oats.scripting.modules.webdom.api.elements.DOMElement;
import org.dom4j.dom.DOMText;
import oracle.oats.scripting.modules.basic.api.*;
import oracle.oats.scripting.modules.basic.api.exceptions.AbstractScriptException;
import oracle.oats.scripting.modules.browser.api.*;
import oracle.oats.scripting.modules.functionalTest.api.*;
import oracle.oats.scripting.modules.adf.api.*;
import oracle.oats.scripting.modules.utilities.api.*;
import oracle.oats.scripting.modules.utilities.api.sql.*;
import oracle.oats.scripting.modules.utilities.api.xml.*;
import oracle.oats.scripting.modules.utilities.api.file.*;
import oracle.oats.scripting.modules.webdom.api.*;
import lib.*;

public class script extends IteratingVUserScript {
	@ScriptService oracle.oats.scripting.modules.browser.api.BrowserService browser;
	@ScriptService oracle.oats.scripting.modules.functionalTest.api.FunctionalTestService ft;
	@ScriptService oracle.oats.scripting.modules.webdom.api.WebDomService web;
	@ScriptService oracle.oats.scripting.modules.adf.api.ADFService adf;
	@ScriptService oracle.oats.scripting.modules.datatable.api.DataTableService datatable;	
	@ScriptService oracle.oats.scripting.modules.utilities.api.UtilitiesService utilities;
	@FunctionLibrary("CommonLibrary") lib.myEBS.myEBS.CommonLibrary commonLibrary;
	public void initialize() throws Exception {
	}	
	public void run() throws Exception {
	}	
	public void finish() throws Exception {
	}
	
	
}
	
	
   
echo >> Starting execution

cd\
cd OracleATS\openScript
runScript.bat "C:\OracleEBS_Automation\TestScripts\TS_ApplicationDeveloper_Profile\TS_ApplicationDeveloper_Profile.jwg" & runScript.bat "C:\OracleEBS_Automation\TestScripts\TS_HumanResourcesVisionEnterprises_People_EnterAndMaintain\TS_HumanResourcesVisionEnterprises_People_EnterAndMaintain.jwg"

echo >> Execution completed

package SunilFrameWork;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;

public class CheckAllgenericMethod {
	public static void main(String[] args) throws IOException {
		GenericMethod gn = new GenericMethod();
		ExtentReports ext=gn.genaretReports("TC001");
		gn.openBrowser("chrome");
	//	gn.maximize();
	//	gn.minimize();
		gn.navigateUrl("http://localhost:8888");
  //    gn.close("chrome");
		gn.inputTextValue("admin", "//input[@name='user_name']", "xpath", "userNameBox");
		gn.inputTextValue("admin", "//input[@name='user_password']", "xpath", "passwordBox");
		gn.click("//input[@name='Login']", "xpath", "loginButton");
		gn.getInnerText("//img[@title='vtiger CRM']", "xpath", "CRM");
		
		ext.flush();
	}

}

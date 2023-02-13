package com.eva.vtiger.texstscripts;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.eva.vtiger.genericMethod.WebDriverUtil;

public class LeadsTestScripts {
	public static void main(String[] args) throws IOException {
		WebDriverUtil rn=new  WebDriverUtil();
		ExtentReports ext=rn.genaretReports("TC001");
		rn.openBrowser("chrome");
		rn.navigateUrl("http://localhost:8888");
		rn.inputTextValue("admin", "//input[@name='user_name']", "xpath", "user name");
		rn.inputTextValue("admin", "//input[@name='user_password']", "xpath", "password");
		rn.click("//input[@name='Login']", "xpath", "Login button");
		
		
		
		
		ext.flush();
		
	}
	

}

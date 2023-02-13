package com.eva.vtiger.Common.reusableCode;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;

import SunilFrameWork.GenericMethod;

public class CommonCode {
	
	public  void login() throws IOException {
		GenericMethod rn = new GenericMethod();
		ExtentReports ext=rn.genaretReports("TC001");
		rn.openBrowser("chrome");
		rn.navigateUrl("http://localhost:8888");
		rn.inputTextValue("admin", "//input[@name='user_name']", "xpath", "userNameBox");
		rn.inputTextValue("admin", "//input[@name='user_password']", "xpath", "passwordBox");
		rn.click("//input[@name='Login']", "xpath", "loginButton");
		ext.flush();
	}
	
	

}

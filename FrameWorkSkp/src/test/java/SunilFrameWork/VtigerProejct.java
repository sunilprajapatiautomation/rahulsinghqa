package SunilFrameWork;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;

public class VtigerProejct {
	public static void main(String[] args) throws IOException {
		 tc001();
		// tc002();
		// tc003();
		
	}
	public static void tc001() throws IOException {
		GenericMethod gn = new GenericMethod();
		ExtentReports ext=gn.genaretReports("TC001");
		gn.openBrowser("chrome");
		gn.navigateUrl("http://localhost:8888");
		gn.validateHeight("//input[@name='user_name']", "xpath", 22, "userNameBox");
		gn.validateWidth("//input[@name='user_name']","xpath", 140, "userNameBox");
		gn.validateHeight("//input[@name='user_password']", "xpath", 23, "passwordBox");
		gn.validateWidth("//input[@name='user_password']","xpath", 140, "passwordBox");
		gn.validateHeight("//input[@name='Login']", "xpath", 40, "Login button ");
		gn.validateWidth("//input[@name='Login']","xpath", 138, "Login button ");
	    ext.flush();
	}
	public static void tc002() throws IOException {
		GenericMethod gn = new GenericMethod();
		ExtentReports ext=gn.genaretReports("TC001");
	gn.openBrowser("chrome");
	gn.navigateUrl("http://localhost:8888");
	gn.validateXcordinate("//input[@name='user_name']", "xpath", 632, "userNameBox");
	gn.validateYcordinate("//input[@name='user_name']","xpath", 230, "userNameBox");
	gn.validateXcordinate("//input[@name='user_password']", "xpath", 632, "passwordBox");
	gn.validateYcordinate("//input[@name='user_password']","xpath", 262, "passwordBox");
	gn.validateXcordinate("//input[@name='Login']", "xpath", 632, "Login button ");
	gn.validateYcordinate("//input[@name='Login']","xpath", 350, "Login button ");
    ext.flush();
		
	}
	public static void tc003() throws IOException {
		GenericMethod gn = new GenericMethod();
		ExtentReports ext=gn.genaretReports("TC001");
		gn.openBrowser("chrome");
		gn.navigateUrl("http://localhost:8888");
		gn.inputTextValue("admin", "//input[@name='user_name']", "xpath", "userNameBox");
		gn.inputTextValue("admin", "//input[@name='user_password']", "xpath", "passwordBox");
		gn.click("//input[@name='Login']", "xpath", "loginButton");
		gn.click("Marketing", "innerTest", "Marketing");
		gn.click("Leads", "innerTest", "Leads");
		gn.click("//img[@title='Create Lead...']","xpath", "create Lead");
		gn.inputTextValue("Sunil", "//input[@name='firstname']", "xpath", "firstName box");
		gn.inputTextValue("Prajapati", "//input[@name='lastname']", "xpath", "lastName box");
		gn.inputTextValue("EVS", "//input[@name='company']", "xpath", "companyName");
		gn.inputTextValue("Hi this is Sunil Prajapati task to create new lead", "//input[@id='designation']", "xpath", "TitleBox");
		gn.inputTextValue("7755884681", "phone", "id", "phone Number");
		gn.inputTextValue("sunilprajapati8928@gmail.com", "email", "id", "email id");
		gn.inputTextValue("Mondh", "lane", "name", "street");
		gn.inputTextValue("India", "country", "id", "country");
		gn.inputTextValue("Hello my name is sunil prajapati", "description", "name", "descriptionBox");
		gn.click("//*[@id=\"basicTab\"]/table/tbody/tr/td/table/tbody/tr[2]/td/table/tbody/tr[18]/td/div/input[1]", "xpath", "save button");
		ext.flush();
		
		
	}

}

package SunilFrameWork;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class RunClass {

	public static void main(String[] args) throws IOException {

		GenericMethod rn = new GenericMethod();
		ExtentReports ext=rn.genaretReports("TC001");
		rn.openBrowser("chrome");
		rn.navigateUrl("http://localhost:8888");
		rn.inputTextValue("admin", "//input[@name='user_name']", "xpath", "user name");
		rn.inputTextValue("admin", "//input[@name='user_password']", "xpath", "password");
		rn.click("//input[@name='Login']", "xpath", "Login button");
		rn.click("About Us", "innerTest", "about us");
		rn.clickByJavaScript("Feedback", "innerTest", "Feedback");
//		rn.click("Feedback", "innerTest", "feedback");
		rn.handleWindow("");
		rn.inputTextValue("Hello Sir", "description", "name", "description");
		ext.flush();

		
	}


}

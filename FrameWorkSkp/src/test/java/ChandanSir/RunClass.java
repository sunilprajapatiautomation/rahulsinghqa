package ChandanSir;

import java.io.IOException;

import org.openqa.selenium.Keys;

import com.aventstack.extentreports.ExtentReports;

public class RunClass {
	
	public static void main(String[] args) throws IOException {
		GnMethods rn=	new GnMethods();
		ExtentReports ext=rn.genaretReports("TC003");
		rn.openBrowser("chrome");
		rn.navigateUrl("http://localhost:8888");
		rn.actionSendKeys("user name", "admin").sendKeys(Keys.TAB,"admin",Keys.ENTER).perform();
		
	}

}

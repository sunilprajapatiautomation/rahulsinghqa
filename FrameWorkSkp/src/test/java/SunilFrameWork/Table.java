package SunilFrameWork;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;

public class Table {

	public static void main(String[] args) throws IOException {
		GenericMethod rn = new GenericMethod();
		ExtentReports ext=rn.genaretReports("TC001");
	WebDriver driver=	rn.openBrowser("chrome");
		rn.navigateUrl("http://localhost:8888");
		rn.inputTextValue("admin", "//input[@name='user_name']", "xpath", "user name");
		rn.inputTextValue("admin", "//input[@name='user_password']", "xpath", "password");
		rn.click("//input[@name='Login']", "xpath", "Login button");
		rn.click("//a[text()='Marketing']", "xpath", "Cam");
		whereElementPresent_Row_Colum(driver, "shivam");
		ext.flush();
		
	}

	public static void whereElementPresent_Row_Colum(WebDriver driver,String expected) {
		
		String test;
		List<WebElement> row = driver.findElements(By.xpath("//table[@class='lvt small']//tbody//tr"));
		List<WebElement> colum = driver.findElements(By.xpath("//table//tbody//tr//td[@class='lvtCol']"));
		for (int i = 1; i < row.size(); i++) {
		for (int j = 1; j <= colum.size(); j++) {
		String actual = driver.findElement(By.xpath("//table[@class='lvt small']//tbody//tr[" + i + "]//td[" + j + "]")).getText();
		if (actual.equalsIgnoreCase(expected)) {
		System.out.println(i+":"+j);
		
		}
		}}}
}

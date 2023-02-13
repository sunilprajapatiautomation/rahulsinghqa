package SunilFrameWork;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericMethod {
	private WebDriver driver;
	ExtentTest extTest;

	public WebDriver getDriver() {
		if (driver == null) {
			extTest.log(Status.FAIL, "");
		}

		return driver;
	}

	public WebDriver setDriver(WebDriver driver) {
		this.driver = driver;
		return driver;
	}

	
	/**
	 * @param browser
	 * @return WebDriver
	 */
	
	public WebDriver openBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			extTest.log(Status.INFO, "Browser launch successfully");
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			extTest.log(Status.INFO, "Browser launch successfully");
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			extTest.log(Status.INFO, "Browser launch successfully");
		}
		return driver;
	}


	/**
	 * @param browserPage
	 * @throws IOException
	 */
	public void close(String browserPage) throws IOException {
		try {
			driver.close();
			extTest.log(Status.INFO, "browser close successfully");
		} catch (Exception e) {
			getScreenShot(browserPage);
		}
	}

	// quit method
	public void quit(String browserPage) throws IOException {
		try {
			driver.quit();
			extTest.log(Status.INFO, "browser close successfully");
		} catch (Exception e) {
			getScreenShot(browserPage);
		}
	}

	/* maximize method */
	public void maximize() {
		driver.manage().window().maximize();
		extTest.log(Status.INFO, "broswer maximize successfully");
	}

	/* minimize method */
	public void minimize() {
		driver.manage().window().minimize();
		extTest.log(Status.INFO, "browser minimize successfully");
	}

	// generic method of navigate URL
	public void navigateUrl(String url) {
		driver.get(url);
		extTest.log(Status.INFO, "URL Navigate " + url + " successfully");
	}

	// method of getWebElement find the Element by locator
	public WebElement getWebElement(String locatorValue, String locatorType, String elementName) {
		WebElement we = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			we = driver.findElement(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("name")) {
			we = driver.findElement(By.name(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			we = driver.findElement(By.id(locatorValue));
		} else if (locatorType.equalsIgnoreCase("className")) {
			we = driver.findElement(By.className(locatorValue));
		} else if (locatorType.equalsIgnoreCase("tagName")) {
			we = driver.findElement(By.tagName(locatorValue));
		} else if (locatorType.equalsIgnoreCase("css")) {
			we = driver.findElement(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("innerTest")) {
			we = driver.findElement(By.linkText(locatorValue));
		} else {
			extTest.log(Status.FAIL, elementName + "wrong xpath");
		}
		return we;
	}

	// method of getList<WebElement> find the Element by locator return
	// List<WebElement>
	public List<WebElement> getList(String locatorValue, String locatorType, String elementName) {
		List<WebElement> listWe = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			listWe = driver.findElements(By.xpath(locatorValue));
		} else if (locatorType.equalsIgnoreCase("name")) {
			listWe = driver.findElements(By.name(locatorValue));
		} else if (locatorType.equalsIgnoreCase("id")) {
			listWe = driver.findElements(By.id(locatorValue));
		} else if (locatorType.equalsIgnoreCase("className")) {
			listWe = driver.findElements(By.className(locatorValue));
		} else if (locatorType.equalsIgnoreCase("tagName")) {
			listWe = driver.findElements(By.tagName(locatorValue));
		} else if (locatorType.equalsIgnoreCase("css")) {
			listWe = driver.findElements(By.cssSelector(locatorValue));
		} else if (locatorType.equalsIgnoreCase("innerTest")) {
			listWe = driver.findElements(By.linkText(locatorValue));
		} else {
			extTest.log(Status.FAIL, elementName + "wrong xpath");
		}
		return listWe;
	}

	// method of checkElement
	public boolean checkElement(String locatorValue, String locatorType, String elementName) {

		boolean status = false;
		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		if (we.isDisplayed()) {
			extTest.log(Status.PASS, elementName + " element is Displayed");
			if (we.isEnabled()) {
				extTest.log(Status.PASS, elementName + " element is Enabled");
				status = true;
			} else {
				extTest.log(Status.FAIL, elementName + " element is not Enabled");
			}
		} else {
			extTest.log(Status.FAIL, elementName + " element is not Displayed");
		}
		return status;
	}

	// method of inputTextValue
	public void inputTextValue(String value, String locatorValue, String locatorType, String elementName)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean status = checkElement(locatorValue, locatorType, elementName);
			if (status == true) {
				we.clear();
				we.sendKeys(value);
				extTest.log(Status.INFO, "entered value in " + elementName + "box is successfully");
			} else {
				extTest.log(Status.INFO, "not entered value in " + elementName + " box");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	// click method
	public void click(String locatorValue, String locatorType, String elementName) throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean status = checkElement(locatorValue, locatorType, elementName);
			if (status == true) {
				we.click();
				extTest.log(Status.INFO, "click " + elementName + " button is successfully");
			} else {
				extTest.log(Status.INFO, "not click " + elementName + "button is successfully");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* click multiple Element */
	public void clickMultipleElements(String locatorValue, String locatorType, String elementName) throws IOException {
		try {
			List<WebElement> listWe = getList(locatorValue, locatorType, elementName);
			for (int i = 0; i < listWe.size(); i++) {
				WebElement we = listWe.get(i);
				elementName = we.getText();
				boolean st = checkElement(locatorValue, locatorType, elementName);
				if (st == true) {
					we.click();
					extTest.log(Status.INFO, elementName + " click successfully");
				} else {
					extTest.log(Status.FAIL, elementName + " Not click ");
				}
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* getInnerText method return String */
	public String getInnerText(String locatorValue, String locatorType, String elementName) throws IOException {
		String innerText = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				innerText = we.getText();

				extTest.log(Status.INFO, elementName + " getText successfully that is = " + innerText);
			} else {
				extTest.log(Status.FAIL, elementName + " Not getText ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return innerText;
	}

	/**
	 * @method getInnerTextMultipleElements
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @return List<String>
	 * @throws IOException
	 */
	public List<String> getInnerTextMultipleElements(String locatorValue, String locatorType, String elementName)
			throws IOException {
//		String innerText = "";
		List<String> arrList = null;
		try {
			arrList = new ArrayList<String>();
			List<WebElement> lstWe = getList(locatorValue, locatorType, elementName);
			for (int i = 0; i < lstWe.size(); i++) {
				WebElement we = lstWe.get(i);
				boolean st = checkElement(locatorValue, locatorType, elementName);
				if (st == true) {
					String innerText = we.getText();
					arrList.add(innerText);
					extTest.log(Status.INFO, elementName + " getText successfully that is = " + innerText);
				} else {
					extTest.log(Status.FAIL, elementName + " Not getText");
				}
			}

		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return arrList;
	}

	/* getAttributeValue method */
	public String getAttributeValue(String locatorValue, String locatorType, String elementName, String attributeName)
			throws IOException {
		String attributeValue = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				attributeValue = we.getAttribute(attributeName);

				extTest.log(Status.INFO, elementName + " getAttribute Value successfully that is = " + attributeValue);
			} else {
				extTest.log(Status.FAIL, elementName + " Not get Attribute Value ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return attributeValue;
	}

	/* get CssValue method */
	public String getCssValue(String locatorValue, String locatorType, String elementName,
			String background_color_Ya_color, String colorHasValue) throws IOException {
		String colorText = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				String colorProperty = we.getCssValue(background_color_Ya_color);
				colorText = Color.fromString(colorProperty).asHex();
				if (colorText.equalsIgnoreCase(colorHasValue)) {
					extTest.log(Status.INFO, elementName + " of color is Right");
				} else {
					extTest.log(Status.FAIL, elementName + " of color is not Right");
				}
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return colorText;
	}

	/**
	 * @method getSize
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @throws IOException
	 */
	public Dimension getSize(String locatorValue, String locatorType, String elementName) throws IOException {
		Dimension dimsn = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				dimsn = we.getSize();
				int height = dimsn.getHeight();
				int width = dimsn.getWidth();
				extTest.log(Status.PASS, elementName + " actualHeight = " + height + " actualWidth = " + width);
			} else {
				extTest.log(Status.FAIL, elementName + " not get size");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return dimsn;
	}

	/**
	 * @method get Location
	 * @param locatorValue
	 * @param locatorType
	 * @param elementName
	 * @return Point class of object
	 * @throws IOException
	 */
	public Point getLocation(String locatorValue, String locatorType, String elementName) throws IOException {
		Point pnt = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				pnt = we.getLocation();
				int x = pnt.getY();
				int y = pnt.getY();
				extTest.log(Status.PASS, elementName + " actualXCordinate = " + x + " actualYCordinate = " + y);
			} else {
				extTest.log(Status.FAIL, elementName + " not get size");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return pnt;
	}
	// select class method

	/* selectByText */
	public void selectByVisibleText(String locatorValue, String locatorType, String elementName,
			String dropDownAttributeValue) throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.selectByVisibleText(dropDownAttributeValue);
				extTest.log(Status.INFO, elementName + " Select Value in dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not Select Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* selectByValue */
	public void selectByValue(String locatorValue, String locatorType, String elementName, String dropDownValue)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.selectByVisibleText(dropDownValue);
				extTest.log(Status.INFO, elementName + " Select Value in dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not Select Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* selectByIndex */
	public void selectByIndex(String locatorValue, String locatorType, String elementName, int dropDownValue)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.selectByIndex(dropDownValue);
				extTest.log(Status.INFO, elementName + " Select Value in dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not Select Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* deselectByValue */
	public void selectDeselectByValue(String locatorValue, String locatorType, String elementName, String dropDownValue)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.deselectByValue(dropDownValue);
				extTest.log(Status.INFO,
						elementName + " deselect Value in dropDown successfully that is = " + dropDownValue);
			} else {
				extTest.log(Status.INFO, " Not deselectBy Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* deselectByVisibleText */
	public void selectDeselectByVisibleText(String locatorValue, String locatorType, String elementName,
			String dropDownAttributeValue) throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.deselectByVisibleText(dropDownAttributeValue);
				extTest.log(Status.INFO,
						elementName + " deselectBy Value in dropDown successfully that is = " + dropDownAttributeValue);
			} else {
				extTest.log(Status.INFO, " Not deselectBy Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* deselectedByIndex */
	public void selecDeselecttByIndex(String locatorValue, String locatorType, String elementName, int dropDownValue)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.deselectByIndex(dropDownValue);
				extTest.log(Status.INFO,
						elementName + " deselect  Value in dropDown successfully that is = " + dropDownValue);
			} else {
				extTest.log(Status.INFO, " Not deselect  Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* deselectAll */
	public void selectDeselectAll(String locatorValue, String locatorType, String elementName) throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				sl.deselectAll();
				extTest.log(Status.INFO, elementName + " deselect All Value in multiple dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not deselect All Value in " + elementName + "multiple dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* getFirstSelectedOption */
	public void selectGetFirstSelectedOptions(String locatorValue, String locatorType, String elementName)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				String getFSO = sl.getFirstSelectedOption().getText();
				extTest.log(Status.INFO,
						" getFirstSelectedOption Value in " + elementName + " dropDown is = " + getFSO);
			} else {
				extTest.log(Status.INFO, " Not getFirstSelectOption Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* getAllSelectedOptions */
	public void selectGetAllSelectedOptions(String locatorValue, String locatorType, String elementName)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				List<WebElement> lst = sl.getAllSelectedOptions();
				for (int i = 0; i < lst.size(); i++) {
					String getOptions = lst.get(i).getText();
					i++;
					extTest.log(Status.INFO, "get one by one all selected options = " + i + " : " + getOptions);
				}
				extTest.log(Status.INFO, " getAllSelectedOptions Value in " + elementName + " dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not getFirstSelect Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* getOptions */
	public void selectGetOptions(String locatorValue, String locatorType, String elementName) throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				List<WebElement> lst = sl.getOptions();
				for (int i = 0; i < lst.size(); i++) {
					String getOptions = lst.get(i).getText();
					i++;
					extTest.log(Status.INFO, "get one by one all options in dropdown = " + i + " : " + getOptions);
				}
				extTest.log(Status.INFO, " getAllOptions Value in " + elementName + " dropDown successfully");
			} else {
				extTest.log(Status.INFO, " Not getFirstSelect Value in " + elementName + " dropDown ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/* count dropdown items */
	public void selectGetDropdownItemsCount(String locatorValue, String locatorType, String elementName,
			String dropDownAttributeValue) throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				Select sl = new Select(we);
				int NoItems = sl.getOptions().size();
				extTest.log(Status.INFO,
						elementName + " Count Items who Prasent in " + elementName + "DropDown = " + NoItems);
			} else {
				extTest.log(Status.INFO, " Not Count Items who Prasent in DropDown " + elementName);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	// Actions class //

	// actionMouseOver
	public Actions actionMouseOver(String locatorValue, String locatorType, String elementName) throws IOException {
		Actions ac = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.moveToElement(we).perform();
				;
				extTest.log(Status.PASS, elementName + " MouseOver successfully");
			} else {
				extTest.log(Status.FAIL, elementName + " Not MouseOver");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	// actionDragAndDrop
	public Actions actionDragAndDrop(WebElement drag, WebElement drop, String elementName) throws IOException {
		Actions ac = null;
		try {
			ac = new Actions(driver);
			ac.dragAndDrop(drag, drop).perform();
			extTest.log(Status.PASS, elementName + " Drag and Drop successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	// actionDragAndDrop
	public Actions actionDragAndDrop(String locatorValue, String locatorType, int xOffset, int yOffset,
			String elementName) throws IOException {
		Actions ac = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.dragAndDropBy(we, xOffset, yOffset).perform();
				extTest.log(Status.PASS, elementName + " Drag and Drop successfully");
			} else {
				extTest.log(Status.PASS, elementName + " Not Drag and Drop successfully");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/* actions Click with WebElement */
	public Actions actionClick(String locatorValue, String locatorType, String elementName) throws IOException {
		Actions ac = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.click(we).perform();
				;
				extTest.log(Status.PASS, elementName + " click successfully");
			} else {
				extTest.log(Status.FAIL, elementName + " Not click");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	// click without webelement
	public Actions actionClick(String elementName) throws IOException {
		Actions ac = null;
		try {
			ac = new Actions(driver);
			ac.click().perform();
			;
			extTest.log(Status.PASS, elementName + " click successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/* actions sendKeys with WebElement */
	public Actions actionSendKeys(String locatorValue, String locatorType, String elementName, String KeysValue)
			throws IOException {
		Actions ac = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.sendKeys(we, KeysValue).perform();
				;
				extTest.log(Status.PASS, elementName + " sendKeys " + KeysValue + "  successfully ");
			} else {
				extTest.log(Status.FAIL, elementName + " Not SendKeys ");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	/* actions sendKeys Without WebElement */
	public Actions actionSendKeys(String elementName, String KeysValue) throws IOException {
		Actions ac = null;
		try {
			ac = new Actions(driver);
			ac.sendKeys(KeysValue).perform();
			;
			extTest.log(Status.PASS, elementName + " click successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	// ContextClick
	public Actions actionContextClick(String elementName) throws IOException {
		Actions ac = null;
		try {
			ac = new Actions(driver);
			ac.contextClick().perform();
			extTest.log(Status.PASS, elementName + " Right click successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}

	// actionContextClick
	public Actions actionContextClick(String locatorValue, String locatorType, String elementName) throws IOException {
		Actions ac = null;
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				ac = new Actions(driver);
				ac.contextClick(we).perform();
				extTest.log(Status.PASS, elementName + " Right click successfully");
			} else {
				extTest.log(Status.PASS, elementName + "Not Right click successfully");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return ac;
	}
	// switch window

	/* switchWindow by Title */
	public void switchToWindowByTitle(String expectedTitle) {
		Set<String> set = driver.getWindowHandles();
		for (String handles : set) {
			driver.switchTo().window(handles);
			String actualTitle = driver.getTitle();
			String expTitle = expectedTitle;
			if (actualTitle.equalsIgnoreCase(expTitle)) {
				break;
			}
		}
	}

	/* switchWindow by URL */
	public void switchToWindowByUrl(String expectedUrl) {
		Set<String> set = driver.getWindowHandles();
		for (String handles : set) {
			driver.switchTo().window(handles);
			String actualUrl = driver.getCurrentUrl();
			String expUrl = expectedUrl;
			if (actualUrl.equalsIgnoreCase(expUrl)) {
				break;
			} else {
				extTest.log(Status.FAIL, "Not switch window");
			}
		}
	}

	// get Title of page return String
	public String getTitle() {
		String title = driver.getTitle();
		extTest.log(Status.INFO, "Get Title " + title + " successfully");
		return title;
	}

	// getCurrentUrl
	public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		extTest.log(Status.INFO, "Get Title " + url + " successfully");
		return url;
	}

	// method of uploadFile
	public void uploadFile(String locatorValue, String locatorType, String elementName, String filePath)
			throws IOException {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				we.sendKeys(filePath);
				extTest.log(Status.PASS, "file upload successfully in " + elementName);
			} else {
				extTest.log(Status.FAIL, "file not upload  in " + elementName);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	// switch frame
	public void switchToFrameByIndex(int indexValue, String elementName) throws IOException {
		try {
			driver.switchTo().frame(indexValue);
			extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	// switchToFrameByString method
	public void switchToFrameByString(String nameOrid, String elementName) throws IOException {
		try {
			driver.switchTo().frame(nameOrid);
			extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	// switchToFrameByWebElement
	public void switchToFrameByWebElement(String we, String elementName) throws IOException {
		try {
			driver.switchTo().frame(we);
			extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	// switchToParentFrame
	public void switchToParentFrame(String elementName) throws IOException {
		try {
			driver.switchTo().parentFrame();
			extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/// ----------------> Popup Handle <---------------- ///

	// method of getAlertText
	public String getAlertText(String elementName) {
		String popupValue = "";
		try {
			popupValue = driver.switchTo().alert().getText();
			extTest.log(Status.INFO, elementName + " getText of popup");
		} catch (Exception e) {
			extTest.log(Status.FAIL, elementName + " not getText of popup");
		}
		return popupValue;
	}

	// method of acceptAlert
	public void acceptAlert(String elementName) {
		try {
			driver.switchTo().alert().accept();
			extTest.log(Status.INFO, elementName + " click ok successfully");
		} catch (Exception e) {
			extTest.log(Status.INFO, elementName + " not click ok successfully");
		}
	}

	// method of dimissAlert
	public void dimissAlert(String elementName) throws IOException {
		try {
			driver.switchTo().alert().dismiss();
			extTest.log(Status.INFO, elementName + " click cancle button successfully");
		} catch (Exception e) {
			extTest.log(Status.INFO, elementName + " not click cancle button successfully");
			getScreenShot(elementName);
		}
	}

	/// ------------------> Wait <----------------- ///

	// method of implicitlyWait
	public void implicitlyWait(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	// method of exciplicitlyWaitVisibility
	public void exciplicitlyWaitVisibility(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(we));
	}

	// method of exciplicitlyWaitInvisibility
	public void exciplicitlyWaitInvisibility(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(we));
	}

	// method of exciplicitlyWaitElementToBeClickable
	public void exciplicitlyWaitElementToBeClickable(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(we));
	}

	/**
	 * @method exciplicitlyWait ElementToBeClickable
	 * @description it is wait for specific Element and specific condition of
	 *              elementToBeClickable
	 * @param time
	 * @param locatorValue
	 */
	public void exciplicitlyWaitElementToBeClickable(long time, String locatorValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
	}

	/**
	 * @method getCurrentDate
	 * @param pattern
	 * @param day
	 * @return String
	 */
	public String getCurrentDate(String pattern, int chooseDay) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, chooseDay);
		Date d = c.getTime();
		DateFormat df = new SimpleDateFormat(pattern);
		String dateStamp = df.format(d);
		return dateStamp;
	}
	// ---------> Validate Methods <--------- //

	/**
	 * @method validate Text
	 * @description It is used to verify text our expected with actual text
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedText
	 * @param elementName
	 * @return String
	 * @throws IOException
	 */
	public String validateText(String locatorValue, String locatorType, String expectedText, String elementName)
			throws IOException {
		String actualText = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			actualText = we.getText();
			if (actualText.equalsIgnoreCase(expectedText)) {
				extTest.log(Status.PASS, elementName + " is pass because actualText -" + actualText
						+ " and expectedText -" + expectedText + " matched");
			} else {
				extTest.log(Status.PASS, elementName + " is failed because actualText -" + actualText
						+ " and expectedText -" + expectedText + " not matched");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return actualText;
	}

	/**
	 * @method validateMultipleText
	 * @description It is used to matched the actualText with expectedText
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedText
	 * @param elementName
	 * @throws IOException
	 */
	public void validateMultipleText(String locatorValue, String locatorType, List<String> expectedText,
			String elementName) throws IOException {

		try {
			List<WebElement> list = getList(locatorValue, locatorType, elementName);
			for (int i = 0; i < list.size(); i++) {
				WebElement we = list.get(i);
				String actualText = we.getText();
				String exText = expectedText.get(i);
				if (actualText.equalsIgnoreCase(exText)) {
					extTest.log(Status.PASS, elementName + " is pass because actualText - " + actualText
							+ " and expectedText- " + exText + " matched");
				} else {
					extTest.log(Status.FAIL, elementName + " is failed because actualText - " + actualText
							+ " and expectedText- " + exText + " not matched");
				}
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}

	}

	/**
	 * @method validateAttributeValue
	 * @discription It is used to validate AttributeValue with our
	 *              ExpectedAttributeValue
	 * @param locatorValue
	 * @param locatorType
	 * @param attributeName
	 * @param expectedAttributeValue
	 * @return String
	 * @throws IOException
	 */
	public String validateAttributeValue(String locatorValue, String locatorType, String attributeName,
			String expectedAttributeValue) throws IOException {
		String actualAttributeValue = "";
		try {
			WebElement we = getWebElement(locatorValue, locatorType, expectedAttributeValue);
			actualAttributeValue = we.getAttribute(attributeName);
			if (actualAttributeValue.equalsIgnoreCase(expectedAttributeValue)) {
				extTest.log(Status.PASS, expectedAttributeValue + " is pass because actualText -" + actualAttributeValue
						+ " and expectedText -" + expectedAttributeValue + " matched");
			} else {
				extTest.log(Status.PASS, expectedAttributeValue + " is failed because actualText -"
						+ actualAttributeValue + " and expectedText -" + expectedAttributeValue + " not matched");
			}
		} catch (Exception e) {
			getScreenShot(expectedAttributeValue);
		}
		return actualAttributeValue;
	}

	/**
	 * @method validateTitle
	 * @discription It is used to validate Title with our Expected
	 * @param expectedTitle
	 * @param elementName
	 * @return String
	 * @throws IOException
	 */
	public String validateTitle(String expectedTitle, String elementName) throws IOException {
		String actualTitle = "";
		try {
			actualTitle = driver.getTitle();
			if (actualTitle.equalsIgnoreCase(actualTitle)) {
				extTest.log(Status.PASS, elementName + " is passed because actualTitle - " + actualTitle
						+ " and expectedTitle -" + expectedTitle + " is matched");
			} else {
				extTest.log(Status.PASS, elementName + " is failed because actualTitle - " + actualTitle
						+ " and expectedTitle -" + expectedTitle + " is not matched");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return actualTitle;
	}

	/**
	 * @method validateUrl
	 * @discription It is used to validate Url with our Expected Url
	 * @param expectedUrl
	 * @param elementName
	 * @return String
	 * @throws IOException
	 */
	public String validateUrl(String expectedUrl, String elementName) throws IOException {
		String actualUrl = "";
		try {
			actualUrl = driver.getCurrentUrl();
			if (actualUrl.equalsIgnoreCase(expectedUrl)) {
				extTest.log(Status.PASS, elementName + " is passed because actualTitle - " + actualUrl
						+ " and expectedTitle -" + expectedUrl + " is matched");
			} else {
				extTest.log(Status.PASS, elementName + " is failed because actualTitle - " + actualUrl
						+ " and expectedTitle -" + expectedUrl + " is not matched");
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
		return actualUrl;
	}

	/**
	 * @method ValidateHeight
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedHeight
	 * @param elementName
	 * @throws IOException
	 */
	public void validateHeight(String locatorValue, String locatorType, int expectedHeight, String elementName)
			throws IOException {
		try {
			Dimension dim = getSize(locatorValue, locatorType, elementName);
			int actualHeight = dim.getHeight();
			if (actualHeight == expectedHeight) {
				extTest.log(Status.PASS, elementName + " testing is pass because - actualHeight - " + actualHeight
						+ " expectedHeight =" + expectedHeight);
			} else {
				extTest.log(Status.FAIL, elementName = " testing is failed because : actualHeight - " + actualHeight
						+ " expectedHeight - " + expectedHeight);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/**
	 * @method validateWidth
	 * @discription It is used to validate the width size of element with our
	 *              expected
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedWidth
	 * @param elementName
	 * @throws IOException
	 */
	public void validateWidth(String locatorValue, String locatorType, int expectedWidth, String elementName)
			throws IOException {
		try {
			Dimension dim = getSize(locatorValue, locatorType, elementName);
			int actualWidth = dim.getWidth();
			if (actualWidth == expectedWidth) {
				extTest.log(Status.PASS, elementName + " testing is pass because - actualWidth - " + actualWidth
						+ " expectedWidth =" + expectedWidth);
			} else {
				extTest.log(Status.FAIL, elementName = " testing is failed because : actualWidth - " + actualWidth
						+ " expectedWidth - " + expectedWidth);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/**
	 * @method validate X cordinate
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedXcordinate
	 * @param elementName
	 * @throws IOException
	 */
	public void validateXcordinate(String locatorValue, String locatorType, int expectedXcordinate, String elementName)
			throws IOException {
		try {
			Point dim = getLocation(locatorValue, locatorType, elementName);
			int actualXcordinate = dim.getX();
			if (actualXcordinate == expectedXcordinate) {
				extTest.log(Status.PASS, elementName + " testing is pass because - actualXcordinate - "
						+ actualXcordinate + " expectedXcordinate =" + expectedXcordinate);
			} else {
				extTest.log(Status.FAIL, elementName = " testing is failed because : actualXcordinate - "
						+ actualXcordinate + " expectedXcordinate - " + expectedXcordinate);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	/**
	 * @method validate Y cordinate
	 * @param locatorValue
	 * @param locatorType
	 * @param expectedYcordinate
	 * @param elementName
	 * @throws IOException
	 */
	public void validateYcordinate(String locatorValue, String locatorType, int expectedYcordinate, String elementName)
			throws IOException {
		try {
			Point dim = getLocation(locatorValue, locatorType, elementName);
			int actualYcordinate = dim.getY();
			if (actualYcordinate == expectedYcordinate) {
				extTest.log(Status.PASS, elementName + " testing is pass because - actualYcordinate - "
						+ actualYcordinate + " expectedYcordinate =" + expectedYcordinate);
			} else {
				extTest.log(Status.FAIL, elementName = " testing is failed because : actualYcordinate - "
						+ actualYcordinate + " expectedYcordinate - " + expectedYcordinate);
			}
		} catch (Exception e) {
			getScreenShot(elementName);
		}
	}

	public void handleWindow(String expectedTitle) {
		try {
			Set<String> hValue = driver.getWindowHandles();
			for (String value : hValue) {
				driver.switchTo().window(value);
				String actualTitle = driver.getTitle();
				if (actualTitle.equalsIgnoreCase(expectedTitle)) {
					break;
				} else {

				}
			}
		} catch (Exception e) {

		}
	}

	public void clickByJavaScript(String locatorValue, String locatorType, String elementName) {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click()", we);
			}
		} catch (Exception e) {

		}

	}

	public void sendkeyByJavaScript(String locatorValue, String locatorType, String elementName, String dataValue) {
		try {
			WebElement we = getWebElement(locatorValue, locatorType, elementName);
			boolean st = checkElement(locatorValue, locatorType, elementName);
			if (st == true) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].value=" + dataValue + "", we);
			}
		} catch (Exception e) {

		}

	}

	public void scrollupByJavaScript(int xCordinate, int yCordinate) {
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(" + xCordinate, yCordinate + ")", "");

		} catch (Exception e) {

		}

	}

	// method of getScreenshot
	public void getScreenShot(String elementName) throws IOException {
		TakesScreenshot tc = (TakesScreenshot) driver;
		File from = tc.getScreenshotAs(OutputType.FILE);
		File to = new File(elementName + "//Screenshot.png");
		Files.copy(from, to);
		extTest.addScreenCaptureFromPath(to.getAbsolutePath());
	}

	// method of generateReports
	public ExtentReports genaretReports(String testCaseId) {
		DateFormat df = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
		String dateStamp = df.format(new Date());
		File filePath = new File("target" + dateStamp + ".html");
		ExtentSparkReporter esp = new ExtentSparkReporter(filePath);
		ExtentReports ext = new ExtentReports();
		ext.attachReporter(esp);
		extTest = ext.createTest(testCaseId);
		return ext;
	}
}

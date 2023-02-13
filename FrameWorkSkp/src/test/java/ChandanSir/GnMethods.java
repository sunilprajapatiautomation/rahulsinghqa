package ChandanSir;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
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

public class GnMethods {
	

		private WebDriver driver;
		ExtentTest extTest;
		/// -----------------> WebDriver Interface <------------------ ///
		 /*
		* we create generic method of openBrowser/ (launch browser) return WebDriver
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
		/* we create close method */
		public void close() {
		driver.close();
		extTest.log(Status.INFO, "browser close successfully");
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
		/*
		* we create generic method of navigate URL
		*/
		public void navigateUrl(String url) {
		driver.get(url);
		extTest.log(Status.INFO, "URL Navigate " + url + " successfully");
		}
		/// -------------> WebElement Interface <--------------- ///
		/*
		* we create generic method of getWebElement find the Element by locator return
		* WebElement
		*/
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
		/*
		* we create generic method of getList<WebElement> find the Element by locator
		* return List<WebElement>
		*/
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
		/*
		* we create generic method of checkElement and ( Verify Element) verify
		* isDisplayed verify is Enabled return boolean
		*/
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
		/*
		* we create generic method of inputTextValue () call the method of
		* getWebElement() call the method of checkElement() call the moethod of
		* sendKeys()
		*/
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
		getScreenShot();
		}
		}
		/*
		* we create the generic method of click call the method of getWebElement() call
		* the method of checkElement() call the method click of webElement
		*/
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
		getScreenShot();
		}
		}
		/* click multiple Element */
		public void clickMultipleElements(String locatorValue, String locatorType, String elementName)
		throws IOException {
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
		getScreenShot();
		}
		}
		/*
		* getInnerText method return String
		*/
		public String getInnerText(String locatorValue, String locatorType, String elementName) throws
		IOException {
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
		getScreenShot();
		}
		return innerText;
		}
		/*
		* getInnerTextMultipleElements method return String
		*/
		public String getInnerTextMultipleElements(String locatorValue, String locatorType, String
		elementName)
		throws IOException {
		String innerText = "";
		try {
		List<WebElement> lstWe = getList(locatorValue, locatorType, elementName);
		for (int i = 0; i < lstWe.size(); i++) {
		WebElement we = lstWe.get(i);
		boolean st = checkElement(locatorValue, locatorType, elementName);
		if (st == true) {
		innerText = we.getText();
		extTest.log(Status.INFO, elementName + " getText successfully that is = " +
		innerText);
		} else {
		extTest.log(Status.FAIL, elementName + " Not getText");
		}
		}
		} catch (Exception e) {
		getScreenShot();
		}
		return innerText;
		}
		/*
		* getAttributeValue method return String
		*/
		public String getAttributeValue(String locatorValue, String locatorType, String elementName, String
		attributeName)
		throws IOException {
		String attributeValue = "";
		try {
		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		boolean st = checkElement(locatorValue, locatorType, elementName);
		if (st == true) {
		attributeValue = we.getAttribute(attributeName);
		extTest.log(Status.INFO, elementName + " getAttribute Value successfully that is = " +
		attributeValue);
		} else {
		extTest.log(Status.FAIL, elementName + " Not get Attribute Value ");
		}
		} catch (Exception e) {
		getScreenShot();
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
		getScreenShot();
		}
		return colorText;
		}
		/// ----------------> Select class <----------------- ///
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
		getScreenShot();
		}
		}
		/* selectByValue */
		public void selectByValue(String locatorValue, String locatorType, String elementName, String
		dropDownValue)
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
		getScreenShot();
		}
		}
		/* selectByIndex */
		public void selectByIndex(String locatorValue, String locatorType, String elementName, int
		dropDownValue)
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
		getScreenShot();
		}
		}
		/* deselectByValue ---> who select already in dropDown */
		public void selectDeselectByValue(String locatorValue, String locatorType, String elementName, String
		dropDownValue)
		throws IOException {
		try {
		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		boolean st = checkElement(locatorValue, locatorType, elementName);
		if (st == true) {
		Select sl = new Select(we);
		sl.deselectByValue(dropDownValue);
		extTest.log(Status.INFO,
		elementName + " deselect Value in dropDown successfully that is = " +
		dropDownValue);
		} else {
		extTest.log(Status.INFO, " Not deselectBy Value in " + elementName + " dropDown ");
		}
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/* deselectByVisibleText --> who select already in dropDown */
		public void selectDeselectByVisibleText(String locatorValue, String locatorType, String elementName,
		String dropDownAttributeValue) throws IOException {
		try {
		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		boolean st = checkElement(locatorValue, locatorType, elementName);
		if (st == true) {
		Select sl = new Select(we);
		sl.deselectByVisibleText(dropDownAttributeValue);
		extTest.log(Status.INFO,
		elementName + " deselectBy Value in dropDown successfully that is = " +
		dropDownAttributeValue);
		} else {
		extTest.log(Status.INFO, " Not deselectBy Value in " + elementName + " dropDown ");
		}
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/* deselectedByIndex ----> who aleardy prasent in dropDown */
		public void selecDeselecttByIndex(String locatorValue, String locatorType, String elementName, int
		dropDownValue)
		throws IOException {
		try {
		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		boolean st = checkElement(locatorValue, locatorType, elementName);
		if (st == true) {
		Select sl = new Select(we);
		sl.deselectByIndex(dropDownValue);
		extTest.log(Status.INFO,
		elementName + " deselect Value in dropDown successfully that is = " +
		dropDownValue);
		} else {
		extTest.log(Status.INFO, " Not deselect Value in " + elementName + " dropDown ");
		}
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/* deselectAll ----> whenever select multiple options */
		public void selectDeselectAll(String locatorValue, String locatorType, String elementName) throws
		IOException {
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
		getScreenShot();
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
		getScreenShot();
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
		extTest.log(Status.INFO, "get one by one all selected options = " + i + " : " +
		getOptions);
		}
		extTest.log(Status.INFO, " getAllSelectedOptions Value in " + elementName + " dropDown successfully");
		} else {
		extTest.log(Status.INFO, " Not getFirstSelect Value in " + elementName + " dropDown ");
		}
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/* getOptions --> who prasent in dropDown */
		public void selectGetOptions(String locatorValue, String locatorType, String elementName) throws
		IOException {
		try {
		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		boolean st = checkElement(locatorValue, locatorType, elementName);
		if (st == true) {
		Select sl = new Select(we);
		List<WebElement> lst = sl.getOptions();
		for (int i = 0; i < lst.size(); i++) {
		String getOptions = lst.get(i).getText();
		i++;
		extTest.log(Status.INFO, "get one by one all options in dropdown = " + i + " : " +
		getOptions);
		}
		extTest.log(Status.INFO, " getAllOptions Value in " + elementName + " dropDown successfully");
		} else {
		extTest.log(Status.INFO, " Not getFirstSelect Value in " + elementName + " dropDown ");
		}
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/* count dropdown items */
		public void selectGetDropdownItemsCount(String locatorValue, String locatorType, String
		elementName,
		String dropDownAttributeValue) throws IOException {
		try {
		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		boolean st = checkElement(locatorValue, locatorType, elementName);
		if (st == true) {
		Select sl = new Select(we);
		int NoItems = sl.getOptions().size();
		extTest.log(Status.INFO,
		elementName + " Count Items who Prasent in " + elementName +
		"DropDown = " + NoItems);
		} else {
		extTest.log(Status.INFO, " Not Count Items who Prasent in DropDown " +
		elementName);
		}
		} catch (Exception e) {
		getScreenShot();
		}
		}
		//// ------------------> Actions class <------------------- ////
		/*
		* @method MouseOver
		*
		* @description it is use to MouseOver on the dropDown
		*
		* @param locatorValue
		*
		* @param locatorType
		*
		* @param elementName
		*
		* @return Actions class of object
		*
		* @throws IOException
		*/
		public Actions actionMouseOver(String locatorValue, String locatorType, String elementName) throws
		IOException {
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
		getScreenShot();
		}
		return ac;
		}
		/**
		* @method drag and drop
		* @description It is used to drag and drop Element one place to anthor place
		* @param drag
		* @param drop
		* @param elementName
		* @return Actions class of object
		* @throws IOException
		*/
		public Actions actionDragAndDrop(WebElement drag, WebElement drop, String elementName)
		throws IOException {
		Actions ac = null;
		try {
		ac = new Actions(driver);
		ac.dragAndDrop(drag, drop).perform();
		extTest.log(Status.PASS, elementName + " Drag and Drop successfully");
		} catch (Exception e) {
		getScreenShot();
		}
		return ac;
		}
		/**
		* @method drag and drop
		* @description It is used to drag and drop any Element one place to another plcae
		* @param locatorValue
		* @param locatorType
		* @param xOffset
		* @param yOffset
		* @param elementName
		* @return Actions class of object
		* @throws IOException
		*/
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
		getScreenShot();
		}
		return ac;
		}
		/* actions Click with WebElement */
		public Actions actionClick(String locatorValue, String locatorType, String elementName) throws
		IOException {
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
		getScreenShot();
		}
		return ac;
		}
		/**
		* method click without webelement
		*
		* @param elementName
		* @return
		* @throws IOException
		*/
		public Actions actionClick(String elementName) throws IOException {
		Actions ac = null;
		try {
		ac = new Actions(driver);
		ac.click().perform();
		;
		extTest.log(Status.PASS, elementName + " click successfully");
		} catch (Exception e) {
		getScreenShot();
		}
		return ac;
		}
		/* actions sendKeys with WebElement */
		public Actions actionSendKeys(String locatorValue, String locatorType, String elementName, String
		KeysValue)
		throws IOException {
		Actions ac = null;
		try {
		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		boolean st = checkElement(locatorValue, locatorType, elementName);
		if (st == true) {
		ac = new Actions(driver);
		ac.sendKeys(we, KeysValue).perform();
		;
		extTest.log(Status.PASS, elementName + " sendKeys " + KeysValue + " successfully ");
		} else {
		extTest.log(Status.FAIL, elementName + " Not SendKeys ");
		}
		} catch (Exception e) {
		getScreenShot();
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
		getScreenShot();
		}
		return ac;
		}
		/**
		* @method ContextClick
		* @description it is use to Right click on Element
		* @param elementName
		* @return Actions class of object
		* @throws IOException
		*/
		public Actions actionContextClick(String elementName) throws IOException {
		Actions ac = null;
		try {
		ac = new Actions(driver);
		ac.contextClick().perform();
		extTest.log(Status.PASS, elementName + " Right click successfully");
		} catch (Exception e) {
		getScreenShot();
		}
		return ac;
		}
		/**
		* @method ContextClick
		* @description it is use to Right click on Element
		* @param locatorValue
		* @param locatorType
		* @param elementName
		* @return Actions class of object
		* @throws IOException
		*/
		public Actions actionContextClick(String locatorValue, String locatorType, String elementName) throws
		IOException {
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
		getScreenShot();
		}
		return ac;
		}
		/// ---------------> switchWindow <----------------- ///
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
		/*
		* get Title of page return String
		*/
		public String getTitle() {
		String title = driver.getTitle();
		extTest.log(Status.INFO, "Get Title " + title + " successfully");
		return title;
		}
		/*
		* get CurrentUrl of page return String
		*
		* @return url
		*/
		public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		extTest.log(Status.INFO, "Get Title " + url + " successfully");
		return url;
		}
		/// -------------------> Upload File <------------------- ///
		/**
		* @param locatorValue //input[@type='file']
		* @param locatorType xpath
		* @param elemntName upload file
		* @param dataValue File Path
		* @throws IOException
		*/
		public void uploadFile(String locatorValue, String locatorType, String elementName, String filePath)
		throws IOException {
		try {
		WebElement we = getWebElement(locatorValue, locatorType, elementName);
		boolean st = checkElement(locatorValue, locatorType, elementName);
		if (st == true) {
		we.sendKeys(filePath);
		extTest.log(Status.PASS, "file upload successfully in " + elementName);
		} else {
		extTest.log(Status.FAIL, "file not upload in " + elementName);
		}
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/// ---------------------> switch To Frame <---------------------- ///
		/**
		* @method switchToFrameByIndex method
		* @param indexValue like as 0 ,1, 2, 3, 5, 7, 9
		* @throws IOException
		*/
		public void switchToFrameByIndex(int indexValue, String elementName) throws IOException {
		try {
		driver.switchTo().frame(indexValue);
		extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/**
		* @method switchToFrameByString method
		* @param name or id like as "hdd" or "120"
		* @throws IOException
		*/
		public void switchToFrameByString(String nameOrid, String elementName) throws IOException {
		try {
		driver.switchTo().frame(nameOrid);
		extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/**
		* @method switchToFrameByWebElement method
		* @param WebElement WebElement we=driver.findElement(By.xpath(" xpath "));
		* @throws IOException
		*/
		public void switchToFrameByWebElement(String we, String elementName) throws IOException {
		try {
		driver.switchTo().frame(we);
		extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/**
		* @method switchToParentFrame method
		* @param WebElement WebElement we=driver.findElement(By.xpath(" xpath "));
		* @throws IOException
		*/
		public void switchToParentFrame(String elementName) throws IOException {
		try {
		driver.switchTo().parentFrame();
		extTest.log(Status.INFO, elementName + " switch to frame successfully");
		} catch (Exception e) {
		getScreenShot();
		}
		}
		/// ----------------> Popup Handle <---------------- ///
		/**
		* @method getAlertText
		* @param String
		* @return String
		*/
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
		/**
		* @method acceptAlert
		* @param String
		*/
		public void acceptAlert(String elementName) {
		try {
		driver.switchTo().alert().accept();
		extTest.log(Status.INFO, elementName + " click ok successfully");
		} catch (Exception e) {
		extTest.log(Status.INFO, elementName + " not click ok successfully");
		}
		}
		/**
		* @method dimissAlert
		* @param String
		*/
		public void dimissAlert(String elementName) {
		try {
		driver.switchTo().alert().dismiss();
		extTest.log(Status.INFO, elementName + " click cencel button successfully");
		} catch (Exception e) {
		extTest.log(Status.INFO, elementName + " not click cencel button successfully");
		}
		}
		/// ------------------> Wait <----------------- ///
		/**
		* @method implicitlyWait
		* @description it is wait for all object
		* @param time
		*/
		public void implicitlyWait(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		}
		/**
		* @method exciplicitlyWaitVisibility
		* @description it is wait for specific Element and specific condition of
		* visibility
		* @param time
		* @param WebElement
		*/
		public void exciplicitlyWaitVisibility(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(we));
		}
		/**
		* @method exciplicitlyWait invisibility
		* @description it is wait for specific Element and specific condition of
		* invisibility
		* @param time
		* @param we
		*/
		public void exciplicitlyWaitInvisibility(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.invisibilityOf(we));
		}
		/**
		* @method exciplicitlyWait ElementToBeClickable
		* @description it is wait for specific Element and specific condition of
		* elementToBeClickable
		* @param time
		* @param we
		*/
		public void exciplicitlyWaitElementToBeClickable(long time, WebElement we) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(we));
		}
		/**
		* @method exciplicitlyWait ElementToBeClickable
		* @description it is wait for specific Element and specific condition of
		* elementToBeClickable
		* @param time
		* @param locatorValue
		*/
		public void exciplicitlyWaitElementToBeClickable(long time, String locatorValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
		}
		/*
		* we create the getScreenShot() method and add the screenShot with Report file
		*/
		public void getScreenShot() throws IOException {
		TakesScreenshot tc = (TakesScreenshot) driver;
		File from = tc.getScreenshotAs(OutputType.FILE);
		File to = new File("target//screenshot.png");
		Files.copy(from, to);
		extTest.addScreenCaptureFromPath(to.getAbsolutePath());
		}
		/**method geraretReports
		* @param testCaseId
		* @return ExtentTest class of object
		*/
		public ExtentReports genaretReports(String testCaseId) {
		DateFormat df = new SimpleDateFormat("dd-mm-yyyy-hh-mm-ss");
		String dateStamp = df.format(new Date());
		File filePath = new File("target" + dateStamp + ".html");
		ExtentSparkReporter esp = new ExtentSparkReporter(filePath);
		ExtentReports ext = new ExtentReports();
		ext.attachReporter(esp);
		extTest = ext.createTest(testCaseId);
		System.out.println("HelloReports");
		return ext;
		}
		}


package com.java;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	static WebDriver driver;

	// WebElements Methods
	public static void userInput(WebElement element, String value) {
		element.sendKeys(value);
	}

	public static void clickOnElement(WebElement element) {
		element.click();
	}

	public static void clearInput(WebElement element) {
		element.clear();
	}

	public static String printValue(WebElement element) {
		String text = element.getText();
		// System.out.println("Text is " +text);
		return text;
	}

	public static void isSelected(WebElement element) {
		boolean s = element.isSelected();
		System.out.println("check whether is selected or not " + s);
	}

	public static void isDisplayed(WebElement element) {
		boolean d = element.isDisplayed();
		System.out.println("check whether webelement is displayed" + d);

	}

	public static void isEnabled(WebElement element) {
		boolean e = element.isEnabled();
		System.out.println("check whether wenelement is enabled" + e);

	}

	public static void getAttributes(WebElement element, String text) {
		element.getAttribute(text);
	}

	// driver methods
	public static void closeBrowser() {
		driver.close();
	}

	public static void quitBrowser() {
     driver.quit();
	}
	
	public static void navigateTo(String url) {
  driver.navigate().to(url);
	}
	
	public static void navigatBack() {
driver.navigate().back();
	}
	
	public static void getUrlOfPage(String text) {
driver.get(text);
	}
	
	public static void getTitleOfPage(String text) {
driver.get(text);
	}
	
	public static void sleep() throws Throwable {
Thread.sleep(3000);
	}
	
	public static void implicitWait() {
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public static void browserLaunch(String browser) {

		if(browser.equalsIgnoreCase("chrome")) {
			List<String> l = new LinkedList<>();
			l.add("start-maximized");
			l.add("incognito");
			ChromeOptions options = new ChromeOptions();
			options.addArguments(l);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("geko")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}}
	
	public static  void popUpOk() {

		Alert a = driver.switchTo().alert();
		a.accept();
	}

	public static void popUpCancel() {

		Alert a = driver.switchTo().alert();
		a.dismiss(); // sendkeys getText
	}

	public static void frameSwitch(WebElement element) {
		driver.switchTo().frame(element); // index id parent defaultcontent
	}

	public static void mouseEvents(WebDriver driver, WebElement element, String options) {
      Actions a = new Actions(driver);
      if(options.equalsIgnoreCase("click")) {
    	  a.click(element).perform();
      }else if(options.equalsIgnoreCase("Right"))  {
    	  a.contextClick(element).perform();
      }else if(options.equalsIgnoreCase("move"))  {
    	  a.moveToElement(element).perform();
      }else if(options.equalsIgnoreCase("double click")) {
    	  a.doubleClick(element).perform();
      }
      
	}
public static void dradAndDrop(WebDriver driver, WebElement drag, WebElement drop ) {
Actions a = new Actions(driver);
a.dragAndDrop(drag, drop).perform();
}
public static String singleWindow() {
String windowHandle = driver.getWindowHandle();
return windowHandle;
}

public static Set<String> multipleWindow() {
	Set<String> windowHandles = driver.getWindowHandles();
	return windowHandles;

}
public static void selectValue(String options,WebElement element,String value) {
Select s = new Select(element);
if(options.equalsIgnoreCase("value")) {
s.selectByValue(value);	
}else if(options.equalsIgnoreCase("text")) {
	s.selectByVisibleText(value);
}else if(options.equalsIgnoreCase("index")) {
	int p = Integer.parseInt(value);
	s.selectByIndex(p);
}else {
	System.out.println("invalid select options");
}
}
public static List<WebElement> optionsFromDrop(WebElement element) {
	Select s = new Select(element);
	List<WebElement> options = s.getOptions();
	return options;
	

}
public static WebElement getFirstSelected(WebElement element) {
Select s  = new Select(element);
WebElement f = s.getFirstSelectedOption();
return f;
}
public static void capture(WebDriver driver, String path) throws IOException {
TakesScreenshot ts = (TakesScreenshot) driver;
File s = ts.getScreenshotAs(OutputType.FILE);
File d = new File(".\\Amazon_Cucumber\\capture\\"+path+"+.png");
FileHandler.copy(s, d);
}
}

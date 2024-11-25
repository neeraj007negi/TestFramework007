package com.utilities;

import static com.ui.constants.Environment.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.ui.constants.Browser;


public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() ;

  
	public BrowserUtility(Browser br) 
	{
		super();

		if(br==Browser.CHROME)
		{
			driver.set(new ChromeDriver());
		}
		else if(br==Browser.EDGE)
		{
			driver.set(new EdgeDriver());
		}
		else
		{
			System.out.println("Invalid Browser Name");
		}
		gotoURL(PropertiesUtility.ReadPropertyFile(QA,"URL"));
		maximizeScreen();
	}

	public BrowserUtility(Browser br, boolean isHeadless) 
	{
		super();

		if(br==Browser.CHROME)
		{
			if(isHeadless)
			{
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--headless=old");
				option.addArguments("disable-gpu");
				driver.set(new ChromeDriver(option));
			}
			else
			{
			driver.set(new ChromeDriver());
			}
		}
		else if(br==Browser.EDGE)
		{
			if(isHeadless)
			{
				EdgeOptions option = new EdgeOptions();
				option.addArguments("--headless=old");
				option.addArguments("disable-gpu");
				driver.set(new EdgeDriver(option));
			}
			else
			{
			driver.set(new EdgeDriver());
			}
		}
		else
		{
			System.out.println("Invalid Browser Name");
		}
			
		
		gotoURL(PropertiesUtility.ReadPropertyFile(QA,"URL"));
		maximizeScreen();
	}
	public BrowserUtility(WebDriver dr)
	{
		// TODO Auto-generated constructor stub
		this.driver.set(dr);
	}

	public WebDriver getDriver() 
	{
		return (driver.get());
	}

	public void CloseBrowser()
	{
		driver.get().close();
	}

	public void gotoURL(String url)
	{
		driver.get().get(url);
	}
	public void maximizeScreen()
	{
		driver.get().manage().window().maximize();
	}
	public void EnterText(By locator, String text)
	{
		driver.get().findElement(locator).sendKeys(text);
	}

	public String gettext(By locator)
	{
		WebElement element = driver.get().findElement(locator);
		return element.getText();
	}
	public void ClickElement(By locator)
	{
		driver.get().findElement(locator).click();
	}
	public String takescreenshot(String name)
	{
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		File screenshotsdata = screenshot.getScreenshotAs(OutputType.FILE);
		
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-ms-ss");
		String origdate = format.format(date);
		
		String path = System.getProperty("user.dir")+"//screenshots//"+name+"//"+name+"-"+origdate+".png";
		File screenshotfile = new File(path);
		try {
			FileUtils.copyFile(screenshotsdata, screenshotfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}
}

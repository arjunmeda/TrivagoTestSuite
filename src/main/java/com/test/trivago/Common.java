package com.test.trivago;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;



public class Common {

	private WebDriver driver;

	public Common(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement waitUntil(final WebElement element) {

		WebDriverWait expWait = new WebDriverWait(driver, 60);
		return expWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public WebElement waitUntilPresence(By by) {

		WebDriverWait expWait = new WebDriverWait(driver, 60);
		return expWait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public WebElement waitUntilBy(By by) {

		WebDriverWait expWait = new WebDriverWait(driver, 60);
		return expWait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void captureScreenshot(String prefix) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ssaa");
		String destDir = "./reports/screenshots/";
		new File(destDir).mkdirs();
		String destFile = prefix + "_" + dateFormat.format(new Date()) + ".png";
		try {
			Files.copy(scrFile.toPath(), new File(destDir + "/" + destFile).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.setEscapeHtml(false);
		String screenPath = "Saved <a href=screenshots/" + destFile + ">" + destFile + "</a></br>";

		Reporter.log(screenPath);
	}

	
}

package com.test.trivago;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = { "smoke" })
public class TrivagoTestSuite {

	WebDriver driver;

	HomePage homePage;
	LoginPage loginPage;
	BookingdotComPage bookingdotComPage;
	Common common;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "D:\\Office\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		common = new Common(driver);
		driver.get("https://www.trivago.com");
		driver.manage().window().maximize();
		homePage = new HomePage(driver, common);
		loginPage = new LoginPage(driver);
		bookingdotComPage = new BookingdotComPage(driver);
	}

	@Test(groups = { "smoke", "trivago" }, priority = 0)
	public void validateHomePage() {
		common.waitUntilBy(By.xpath("//button[@data-qa='header-login']")).isDisplayed();
	}

//	@Test(groups = { "smoke", "trivago" }, priority = 1)
//	public void Login() throws InterruptedException {
//		//loginPage.createAccount("arjunmeda@gmail.com", "Arjun*123456");
//		loginPage.register("arjunmeda2422@gmail.com");
//		loginPage.registerPassword("Arjun*123456");
//		Thread.sleep(5000);
//	}

	@Test(groups = { "smoke", "trivago" }, priority = 2)
	public void searchForPlace() throws InterruptedException {
		homePage.searchWithNeededCriteria("Goa", "Azaya Beach Resort Goa", "2021-02-12", "2021-02-14", 1);
		String winHandleBefore = driver.getWindowHandle();
		for (String winHandle : driver.getWindowHandles()) // Switch to new window opened.
		{
			driver.switchTo().window(winHandle);
		}

		bookingdotComPage.seeAvailabilityFor("Azaya Beach Resort Goa");
		Assert.assertTrue(driver.getTitle().contains("Azaya Beach Resort Goa"));
		bookingdotComPage.selectRooms("Essence Room King Bed", "1");
		bookingdotComPage.reserveRoom("Essence Room King Bed");
		Assert.assertTrue(driver.getTitle().contains("Your details"));
		bookingdotComPage.fillGuestDetails("Arjun", "M", "arjun@gmail.com");
		sleepFor(1000);
		Assert.assertTrue(driver.getTitle().contains("Final details"));
		driver.close();
		driver.switchTo().window(winHandleBefore);

	}

	private void sleepFor(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.close();
	}

}

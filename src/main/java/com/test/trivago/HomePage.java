package com.test.trivago;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	Common common;

	@FindBy(xpath = "//button[@data-qa='header-login']")
	WebElement loginButton;

	@FindBy(id = "querytext")
	WebElement searchTextBox;

	@FindBy(xpath = "//button[@key='checkInButton']")
	WebElement checkInDateButton;

	@FindBy(id = "adults-input")
	WebElement adultsCountInput;

	@FindBy(xpath = "//button[@data-qa='search-button']")
	WebElement searchBtn;

	@FindBy(xpath = "//button[@data-role='removeAdult']")
	WebElement removeAdultBtn;

	@FindBy(xpath = "//li[contains(@class,'toolbar-list__item--rating')]/button")
	WebElement guestRatingDropdown;

	@FindBy(xpath = "//div[@class='refinement-row__content']/ul/li[2]/button")
	WebElement ratingButton8star;

	@FindBy(xpath = "//span[text()='Azaya Beach Resort Goa']/ancestor::div[@class='item__details item__details--reduced']/following-sibling::section/..//button[@data-qa='champion-deal']")
	WebElement azayaViewDealBtn;

	public HomePage(WebDriver driver, Common common) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.common = common;
	}

	public boolean checkForLoginButton() {
		return common.waitUntil(loginButton).isDisplayed();
	}

	public void searchWithNeededCriteria(String place, String hotel, String checkinDate, String checkoutDate,
			int numOfAdults) {
		common.waitUntil(searchTextBox).sendKeys(place);
		common.sleepFor(2000);
		fillCheckInCheckOutDates(checkinDate, checkoutDate);
		common.waitUntil(removeAdultBtn).click();
		common.waitUntil(searchBtn).click();
		common.waitUntil(guestRatingDropdown).click();
		common.waitUntil(ratingButton8star).click();
		common.sleepFor(2000);
		viewDealFor(hotel);

	}

	private void fillCheckInCheckOutDates(String checkinDate, String checkoutDate) {
		common.waitUntil(checkInDateButton).click();
		String checkinDateXpath = "//table[@class='cal-month']/..//time[@datetime='" + checkinDate + "']";
		driver.findElement(By.xpath(checkinDateXpath)).click();
		String checkoutDateXpath = "//table[@class='cal-month']/..//time[@datetime='" + checkoutDate + "']";
		driver.findElement(By.xpath(checkoutDateXpath)).click();
	}

	public void viewDealFor(String hotelName) {
		String viewDealXpath = "//span[text()='" + hotelName
				+ "']/ancestor::div[@class='item__details item__details--reduced']/following-sibling::section/..//button[@data-qa='champion-deal']";
		common.waitUntilBy(By.xpath(viewDealXpath)).click();
	}

}

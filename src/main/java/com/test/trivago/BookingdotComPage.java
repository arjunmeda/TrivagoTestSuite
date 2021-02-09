package com.test.trivago;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;



public class BookingdotComPage {
	@FindBy(id = "firstname")
	WebElement fNameTextBox;

	@FindBy(id = "lastname")
	WebElement lNameTextBox;

	@FindBy(id = "email")
	WebElement emailTextBox;

	@FindBy(id = "email_confirm")
	WebElement emailConfirmTextBox;

	@FindBy(id = "notstayer_false")
	WebElement mainGuestRadioBtn;

	@FindBy(xpath = "//button/span[contains(text(),'Next: Final details')]")
	WebElement finalDetailsBtn;

	Common common;

	public BookingdotComPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.common = new Common(driver);
	}

	public void seeAvailabilityFor(String hotelName) {
		common.waitUntilBy(By.xpath("//span[contains(text(),'" + hotelName
				+ "')]/ancestor::div[@class='sr_property_block_main_row']/following-sibling::div/..//span[contains(text(),'See availability')]"))
				.click();
	}

	public void selectRooms(String type, String number) {
		Select roomDropDwn = new Select(common.waitUntilBy(By.xpath("//span[contains(text(),'" + type
				+ "')]/ancestor::td/following-sibling::td[contains(@class,'hprt-table-cell hprt-table-room-select')]/..//select")));
		roomDropDwn.selectByValue(number);
	}

	public void reserveRoom(String type) {
		common.waitUntilBy(By.xpath("//span[contains(text(),'" + type
				+ "')]/ancestor::div[@class='hprt-table-column']/following-sibling::div/..//button/span[contains(text(),'reserve')]"))
				.click();
	}

	public void fillGuestDetails(String fName, String lName, String email) {
		common.waitUntil(fNameTextBox).sendKeys(fName);
		common.waitUntil(lNameTextBox).sendKeys(lName);
		common.waitUntil(emailTextBox).sendKeys(email);
		common.waitUntil(emailConfirmTextBox).sendKeys(email);
		common.waitUntil(mainGuestRadioBtn).click();
		// common.waitUntilBy(By.xpath("//td[@class='guest-name-details
		// just-guest-name']/input")).sendKeys("Arjun M");
		common.waitUntil(finalDetailsBtn).click();
	}
}

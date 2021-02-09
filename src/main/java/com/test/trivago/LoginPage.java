package com.test.trivago;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(id = "check_email")
	WebElement emailTextBox;
	
	@FindBy(id = "register_email")
	WebElement registerEmailTextBox;

	@FindBy(id = "login_email_submit")
	WebElement loginEmailSubmitButton;

	@FindBy(id = "register_password")
	WebElement passwordTextBox;

	@FindBy(id = "register_email_submit")
	WebElement registerEmailSubmitBtn;

	@FindBy(id = "login_signup_link")
	WebElement createAccountBtn;

	Common common;

	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.common = new Common(driver);
	}

	

	public void register(String email) {
		common.waitUntil(emailTextBox).sendKeys(email);		
		common.waitUntil(loginEmailSubmitButton).click();
		common.waitUntil(loginEmailSubmitButton).click();

	}
	
	public void registerPassword(String password) {
		common.waitUntil(passwordTextBox).sendKeys(password);		
		common.waitUntil(registerEmailSubmitBtn).click();

	}
	
	public void createAccount(String email, String password) {
		common.waitUntil(createAccountBtn).click();
		common.waitUntil(registerEmailTextBox).sendKeys(email);
		common.waitUntil(passwordTextBox).sendKeys(password);		
		common.waitUntil(registerEmailSubmitBtn).click();

	}

	
}

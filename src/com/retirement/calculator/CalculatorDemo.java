package com.retirement.calculator;

import org.testng.annotations.Test;

import com.retirement.util.CalculatorConstants;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;	

import java.util.concurrent.TimeUnit;;

public class CalculatorDemo {
	WebDriver driver;

	@BeforeClass
	public void testSetup() {
		System.setProperty(CalculatorConstants.CHROME_DRIVER, CalculatorConstants.CHROME_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void openBrowser() {
		driver.get(CalculatorConstants.TEST_URL);
	}

	/**
	 * This method calculates without social benefits and default adjustment
	 */
	@Test(description="This method calculates without social benefits and no default adjustment")
	public void formSubmissionWithoutSocialBenefitsNoAdjustments() {
		sendElementKeys("current-age",CalculatorConstants.CURRENT_AGE); 
		sendElementKeys("retirement-age",CalculatorConstants.RETIREMENT_AGE);
		setLocatorValue("current-income",CalculatorConstants.CURRENT_INCOME);
		setLocatorValue("spouse-income",CalculatorConstants.SPOUSE_INCOME);
		setLocatorValue("current-total-savings",CalculatorConstants.CURRENT_TOTAL_SAVINGS);
		sendElementKeys("current-annual-savings",CalculatorConstants.PERCENTAGE_CURRENT_SAVINGS);
		sendElementKeys("savings-increase-rate",CalculatorConstants.PERCENTAGE_INCREASE_RATE);
		//No Social benefits selected
		setLocatorStatus("no-social-benefits", "true");
		//Calculate button clicked
		driver.findElement(By.xpath(CalculatorConstants.XPATH_CALCULATE_BUTTON)).click();
	}

	/**
	 * This method calculates with social benefits and no default adjustment
	 */
	@Test(description="This method calculates with social benefits and no default adjustment")
	public void formSubmissionWithSocialBenefitsNoAdjustments() {
		sendElementKeys("current-age",CalculatorConstants.CURRENT_AGE); 
		sendElementKeys("retirement-age",CalculatorConstants.RETIREMENT_AGE);
		setLocatorValue("current-income",CalculatorConstants.CURRENT_INCOME);
		setLocatorValue("spouse-income",CalculatorConstants.SPOUSE_INCOME);
		setLocatorValue("current-total-savings",CalculatorConstants.CURRENT_TOTAL_SAVINGS);
		sendElementKeys("current-annual-savings",CalculatorConstants.PERCENTAGE_CURRENT_SAVINGS);
		sendElementKeys("savings-increase-rate",CalculatorConstants.PERCENTAGE_INCREASE_RATE);

		//Selected Social benefits
		setLocatorStatus("yes-social-benefits", "true");
		setLocatorStatus("married", "true");
		setLocatorValue("social-security-override",CalculatorConstants.OVERRIDE_AMOUNT);

		//Clicked the calculate button without default adjustment
		driver.findElement(By.xpath(CalculatorConstants.XPATH_CALCULATE_BUTTON)).click();
	}

	/**
	 * This method calculates with social benefits and default adjustment
	 */
	@Test(description="This method calculates with social benefits and default adjustment")
	public void formSubmissionWithSocialBenefitsAndAdjustments() {
		sendElementKeys("current-age",CalculatorConstants.CURRENT_AGE); 
		sendElementKeys("retirement-age",CalculatorConstants.RETIREMENT_AGE);
		setLocatorValue("current-income",CalculatorConstants.CURRENT_INCOME);
		setLocatorValue("spouse-income",CalculatorConstants.SPOUSE_INCOME);
		setLocatorValue("current-total-savings",CalculatorConstants.CURRENT_TOTAL_SAVINGS);
		sendElementKeys("current-annual-savings",CalculatorConstants.PERCENTAGE_CURRENT_SAVINGS);
		sendElementKeys("savings-increase-rate",CalculatorConstants.PERCENTAGE_INCREASE_RATE);
		//Select Social Benefits
		setLocatorStatus("yes-social-benefits", "true");
		setLocatorStatus("married", "true");
		setLocatorValue("social-security-override",CalculatorConstants.OVERRIDE_AMOUNT);
		//Clicked on default adjustment
		driver.findElement(By.xpath(CalculatorConstants.XPATH_DEFAULT_ADJUSTMENT_LINK)).click();
		setLocatorValue("additional-income", CalculatorConstants.ADDITIONAL_INCOME);
		sendElementKeys("retirement-duration",CalculatorConstants.RETIREMENT_DURATION);
		setLocatorStatus("include-inflation", "true");
		setLocatorValue("retirement-annual-income", CalculatorConstants.RETIREMENT_ANNUAL_INCOME);
		setLocatorValue("pre-retirement-roi", CalculatorConstants.PRE_RETIREMENT_ROI);
		setLocatorValue("post-retirement-roi", CalculatorConstants.POST_RETIREMENT_ROI);
		driver.findElement(By.xpath(CalculatorConstants.XPATH_SAVE_CHANGES_BUTTON)).click();
		//calculate button clicked
		driver.findElement(By.xpath(CalculatorConstants.XPATH_CALCULATE_BUTTON)).click();
	}

	public void setLocatorValue(String locator,String value) {
		WebElement element = driver.findElement(By.id(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = " + value, element );
	}

	public void setLocatorStatus(String locator,String status) {
		WebElement element = driver.findElement(By.id(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].checked = " + status, element );
	}

	public void sendElementKeys(String locator,String value) {
		driver.findElement(By.id(locator)).sendKeys(value);
	}
	@AfterMethod
	public void postSubmission() {
		System.out.println("Form submission successful!");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();
	}
}

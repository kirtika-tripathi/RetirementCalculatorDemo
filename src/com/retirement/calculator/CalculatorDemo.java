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

	@Test(description="This method calculates without social benefits and default adjustment")
	public void formSubmissionWithoutSocialBenefits() {
		driver.findElement(By.id("current-age")).sendKeys(CalculatorConstants.CURRENT_AGE);

		driver.findElement(By.id("retirement-age")).sendKeys(CalculatorConstants.RETIREMENT_AGE);

		WebElement currentIncome = driver.findElement(By.id("current-income"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.CURRENT_INCOME, currentIncome);

		WebElement spouseIncome = driver.findElement(By.id("spouse-income"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.SPOUSE_INCOME, spouseIncome);

		WebElement currentTotalSavings = driver.findElement(By.id("current-total-savings"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.CURRENT_TOTAL_SAVINGS, currentTotalSavings);

		driver.findElement(By.id("current-annual-savings")).sendKeys(CalculatorConstants.PERCENTAGE_CURRENT_SAVINGS);

		driver.findElement(By.id("savings-increase-rate")).sendKeys(CalculatorConstants.PERCENTAGE_INCREASE_RATE);	

		//No Social benefits selected
		WebElement noSocialBenefits = driver.findElement(By.id("no-social-benefits"));
		((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", noSocialBenefits);

		//Calculate button clicked
		driver.findElement(By.xpath(CalculatorConstants.XPATH_CALCULATE_BUTTON)).click();
	}

	@Test(description="This method calculates with social benefits and no default adjustment")
	public void formSubmissionWithSocialBenefitsNoAdjustments() {
		driver.findElement(By.id("current-age")).sendKeys(CalculatorConstants.CURRENT_AGE);

		driver.findElement(By.id("retirement-age")).sendKeys(CalculatorConstants.RETIREMENT_AGE);

		WebElement currentIncome = driver.findElement(By.id("current-income"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.CURRENT_INCOME, currentIncome);

		WebElement spouseIncome = driver.findElement(By.id("spouse-income"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.SPOUSE_INCOME, spouseIncome);

		WebElement currentTotalSavings = driver.findElement(By.id("current-total-savings"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.CURRENT_TOTAL_SAVINGS, currentTotalSavings);

		driver.findElement(By.id("current-annual-savings")).sendKeys(CalculatorConstants.PERCENTAGE_CURRENT_SAVINGS);

		driver.findElement(By.id("savings-increase-rate")).sendKeys(CalculatorConstants.PERCENTAGE_INCREASE_RATE);	

		//Selected Social benefits
		WebElement socialBenefits = driver.findElement(By.id("yes-social-benefits"));
		((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", socialBenefits);

		WebElement maritalStatus = driver.findElement(By.id("married"));
		((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", maritalStatus);

		WebElement overRideAmount = driver.findElement(By.id("social-security-override"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.OVERRIDE_AMOUNT, overRideAmount);
		//Clicked the calculate button without default adjustment
		driver.findElement(By.xpath(CalculatorConstants.XPATH_CALCULATE_BUTTON)).click();
	}

	@Test(description="This method calculates with social benefits and default adjustment")
	public void formSubmissionWithSocialBenefitsAndAdjustments() {
		driver.findElement(By.id("current-age")).sendKeys(CalculatorConstants.CURRENT_AGE);

		driver.findElement(By.id("retirement-age")).sendKeys(CalculatorConstants.RETIREMENT_AGE);

		WebElement currentIncome = driver.findElement(By.id("current-income"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.CURRENT_INCOME, currentIncome);

		WebElement spouseIncome = driver.findElement(By.id("spouse-income"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.SPOUSE_INCOME, spouseIncome);

		WebElement currentTotalSavings = driver.findElement(By.id("current-total-savings"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value = "+ CalculatorConstants.CURRENT_TOTAL_SAVINGS, currentTotalSavings);

		driver.findElement(By.id("current-annual-savings")).sendKeys(CalculatorConstants.PERCENTAGE_CURRENT_SAVINGS);

		driver.findElement(By.id("savings-increase-rate")).sendKeys(CalculatorConstants.PERCENTAGE_INCREASE_RATE);	

		WebElement socialBenefits = driver.findElement(By.id("yes-social-benefits"));
		((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", socialBenefits);

		WebElement maritalStatus = driver.findElement(By.id("married"));
		((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", maritalStatus);

		WebElement overRideAmount = driver.findElement(By.id("social-security-override"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value =" + CalculatorConstants.OVERRIDE_AMOUNT, overRideAmount);
		//Clicked on default adjustment
		driver.findElement(By.xpath(CalculatorConstants.XPATH_DEFAULT_ADJUSTMENT_LINK)).click();

		WebElement additionalIncome = driver.findElement(By.id("additional-income"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value =" + CalculatorConstants.ADDITIONAL_INCOME, additionalIncome);

		driver.findElement(By.id("retirement-duration")).sendKeys(CalculatorConstants.RETIREMENT_DURATION);

		WebElement includeInflation = driver.findElement(By.id("include-inflation"));
		((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", includeInflation);

		WebElement annualIncome = driver.findElement(By.id("retirement-annual-income"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value =" + CalculatorConstants.RETIREMENT_ANNUAL_INCOME, annualIncome);

		WebElement rateOfInterest = driver.findElement(By.id("pre-retirement-roi"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value =" + CalculatorConstants.PRE_RETIREMENT_ROI, rateOfInterest);

		WebElement postrateOfInterest = driver.findElement(By.id("post-retirement-roi"));
		((JavascriptExecutor) driver).executeScript("arguments[0].value =" + CalculatorConstants.POST_RETIREMENT_ROI, postrateOfInterest );
		//Save changes clicked
		driver.findElement(By.xpath(CalculatorConstants.XPATH_SAVE_CHANGES_BUTTON)).click();
		//calculate button clicked
		driver.findElement(By.xpath(CalculatorConstants.XPATH_CALCULATE_BUTTON)).click();

	}

	@AfterMethod
	public void postSubmission() {
		System.out.println("Form submission successful!");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

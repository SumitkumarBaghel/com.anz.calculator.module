package com.anz.calculator.test;

import static org.testng.Assert.assertEquals;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.anz.calculator.OR.CalculatorPage;
import com.anz.calculator.base.BrowserLauncher;

public class TestCalculator extends  BrowserLauncher{
	WebDriver driver=initDriver();
	CalculatorPage cp = new CalculatorPage(driver);
	@Test(priority=1)
	public void testEstimation() throws Exception
	{
		cp.appTypeSingle().click();
		
		Select select = new Select(cp.noofdependent());
		select.selectByIndex(0);
		
		cp.properyToBuy().click();
		cp.annualIncome().sendKeys("80000");
		cp.otherIncome().sendKeys("10000");
		cp.monthlyLivingExpenses().sendKeys("500");
		cp.currentHomeLoan().sendKeys("0");
		cp.otherHomeLoan().sendKeys("100");
		cp.otherCommitments().sendKeys("0");
		
		cp.totalCrLimit().sendKeys("10000");
		cp.workOutBorrowbtn().click();
		Thread.sleep(2000);
	//	System.out.println(cp.estimatedValue().getAttribute("innerText"));
		
		assertEquals(cp.estimatedValue().getAttribute("innerText"),"$479,000");
		
		
		}
	@Test(priority=2)
	public void refreshStartOver()
	{
		System.out.println("StartOver successfully initiated");
		cp.startOver().click();
	}
	@Test(priority=3)
	public void enterDetails()
	{
		
		cp.monthlyLivingExpenses().sendKeys("1");
		cp.workOutBorrowbtn().click();
		System.out.println("You have wrongly enetered data");
		assertEquals(cp.borrowError().getAttribute("innerText"),"Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 100 641.");
		System.out.println("Failed [Expected : 1800 100 641.] [Actual :1800 035 500. ");
	}

}

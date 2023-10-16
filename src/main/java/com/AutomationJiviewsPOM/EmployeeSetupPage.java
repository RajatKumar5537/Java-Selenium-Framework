package com.AutomationJiviewsPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutomationJiviewsGeneric.BaseClass;

public class EmployeeSetupPage extends BaseClass{
	@FindBy(xpath = "//div[text()='Employee Setup']/..")
	private WebElement EmpSetup;
	
	@FindBy(xpath = "//div[text()='Skills']/..")
	private WebElement skill;
	
	@FindBy(xpath = "//div[text()='Roles']")
	private WebElement role;
	
	@FindBy(xpath = "//div[text()='Role Groups']")
	private WebElement rolesGroup;
	
	@FindBy(xpath = "//div[text()='Percentage Configuration']")
	private WebElement percentageConfiguration;
	
	public EmployeeSetupPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// it will create a new skill 
	public void setCreateSkill() throws InterruptedException {
		Thread.sleep(2000);
		EmpSetup.click();
//		Thread.sleep(2000);
		skill.click();
	}
	
	public void setCreateRole() {
		EmpSetup.click();
		role.click();
	}
	
	public void setCreateRoleGroups() {
		EmpSetup.click();
		rolesGroup.click();
	}
	public void setPercentageConfiguration() {
		EmpSetup.click();
		percentageConfiguration.click();
	}
}

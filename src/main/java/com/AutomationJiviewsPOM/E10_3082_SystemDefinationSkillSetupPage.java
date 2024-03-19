package com.AutomationJiviewsPOM;


import java.time.Duration;
import java.util.List;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.AutomationJiviewsGeneric.ExcelUtilities;
import com.AutomationJiviewsGeneric.FakeEmployee;
import com.AutomationJiviewsGeneric.ReusableComponent;
import com.AutomationJiviewsGeneric.WebUtilities;


public class E10_3082_SystemDefinationSkillSetupPage  {
	WebDriver driver;
	public ReusableComponent Rc;
	public ExcelUtilities excelUtility;
	public WebUtilities webUtility;
	String skillCodeData;


	@FindBy(xpath = "//button[@class='btn btn-secondary buttons-excel buttons-html5 btn-sm mr-1']")
	private WebElement btnExcel;
	@FindBy(xpath = "//button[@class='btn btn-secondary buttons-pdf buttons-html5 btn-sm mr-1']")
	private WebElement btnPDF;

	@FindBy(xpath = "//button[@id='btnAddSkill']")
	private WebElement addBtn;

	@FindBy(xpath = "//input[@id='txtSkillCode']")
	private WebElement skillCode;

	@FindBy(id="txtSkillCodeDescription")
	private WebElement skillDescription;

	@FindBy(id = "txtSequence")
	private WebElement sequence;

	@FindBy(id = "txtColourCode")
	private WebElement ColourCode;

	@FindBy(id = "txtFixedRanking")
	private WebElement wageLevelCode;

	@FindBy(xpath = "//span[text()='Is Active?']")
	private WebElement isActiveOption;

	@FindBy(id = "btnSaveSkill")
	private WebElement saveBtn;

	@FindBy(className = "toast-close-button")
	private WebElement notificationPopup;

	@FindBy(xpath = "//button[@class='btn btn-sm btn-outline-primary icon-btn mx-1']")
	private WebElement editBtn;

	@FindBy(xpath = "//table[@id='skill-list']/tbody/tr")
	private List<WebElement> rows;
	@FindBy(xpath = "//td/input[@type='checkbox']")
	private List<WebElement> checkboxes;
	@FindBy(xpath = "//li[@id='skill-list_next']")
	private WebElement nextPage;

	@FindBy(id = "btnDeleteSkill")
	private WebElement btnDeleteSkill;

	@FindBy(xpath = "//button[text()='Yes']")
	private WebElement clickYes;

	@FindBy(xpath =  "(//input[@type='checkbox'])[2]")
	private WebElement checkBox;

	@FindBy(xpath = "//input[@type='search']")
	private WebElement searchColumns;

	public E10_3082_SystemDefinationSkillSetupPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.Rc=new ReusableComponent(driver);
		this.webUtility= new WebUtilities(driver);
		this.excelUtility= new ExcelUtilities();
	}

	public void clickAddBtn() throws Exception {
		webUtility.visibilityOfElement(driver, addBtn);
		addBtn.click();
	}

	public void enterSkillCode(String skillCodeData) throws Exception {
		webUtility.visibilityOfElement(driver, skillCode);
		skillCode.clear();
		skillCode.sendKeys(skillCodeData);
	}

	public void enterSkillDescription(String skillDescriptionData) {
		skillDescription.clear();
		skillDescription.sendKeys(skillDescriptionData);
	}

	public void enterSequence(String sequenceData) {
		sequence.clear();
		sequence.sendKeys(sequenceData);
	}

	public void enterWageLevelCode(String wageLevelCodedata) {
		wageLevelCode.clear();
		wageLevelCode.sendKeys(wageLevelCodedata);
	}

	public void clickIsActiveOption() {
		webUtility.ElementClickable(driver, isActiveOption);
		isActiveOption.click();
	}

	public void clickSaveBtn() throws Exception {
		webUtility.visibilityOfElement(driver, saveBtn);
		saveBtn.click();
	}

	public void closeNotificationPopup() throws Exception {
		webUtility.ElementClickable(driver, notificationPopup);
		notificationPopup.click();
	}

	public void clickEditBtn() throws Exception {
		webUtility.visibilityOfElement(driver, editBtn);
		editBtn.click();
	}
	public void performDeleteAction() throws InterruptedException {
		for (int i = 0; i < 3; i++) {
			try {
				scrollAndClick(driver, btnDeleteSkill);
				break;
			} catch (ElementClickInterceptedException e) {
			}
		}
	}
	public void deleteRowsWithEnabledCheckbox() throws InterruptedException {
		boolean checkboxFound = false;

		// Iterate through rows
		for (int i = 0; i < rows.size(); i++) {
			WebElement checkbox = checkboxes.get(i);
			if (checkbox.isEnabled()) {
				//				scrollAndClick(driver, checkbox);
				checkbox.click();
				performDeleteAction();
				checkboxFound = true;
				break;
			}
		}
		// If no enabled checkbox found on the current page, go to the next page and try again
		if (!checkboxFound) {
			goToNextPageAndDelete();
		}
	}
	private void goToNextPageAndDelete() throws InterruptedException {
		try {
			scrollAndClick(driver, nextPage);
			scrollUp(driver);
			deleteRowsWithEnabledCheckbox(); // Recursive call to check for checkboxes on the next page

		} catch (ElementClickInterceptedException e) {
			// Handle the exception if necessary
		}
	}
	// Method to perform scroll-up action
	private void scrollUp(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -150)"); // Adjust the scroll distance as needed
	}
	public void scrollAndClick(WebDriver driver, WebElement element) {
		WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		// Scroll to the top of the page
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
		element.click();
	}


	public void clickYes() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(clickYes));
		clickYes.click();
	}

	public void enterSearchColumns(String searchColumnsData) throws Exception {
		webUtility.visibilityOfElement(driver, searchColumns);
		searchColumns.clear();
		searchColumns.sendKeys(searchColumnsData);
	}

	public void createNewSkill(FakeEmployee fakeEmployee) throws Exception {
		String sequenceData = excelUtility.readDataFromExcelFile("EmployeeTest", 3, 9);
		String wageLevelCodedata = excelUtility.readDataFromExcelFile("EmployeeTest", 3, 11);

		skillCodeData= fakeEmployee.getSkillCode();

		clickAddBtn();
		enterSkillCode(skillCodeData);
		enterSkillDescription(fakeEmployee.getSkillCodeDesc());
		enterSequence(sequenceData);
		enterWageLevelCode(wageLevelCodedata);
		clickSaveBtn();
		closeNotificationPopup();

		btnExcel.isEnabled();
		//		btnExcel.click();
		btnPDF.isSelected();
		//		btnPDF.click();
	}

	public void updateSkill(FakeEmployee fakeEmployee) throws Exception {
		//		String skillCodeData = excelUtility.readDataFromExcelFile("EmployeeTest", 4, 7);
		//		String skillDescriptionData = excelUtility.readDataFromExcelFile("EmployeeTest", 4, 8);
		Rc.explicitWait(searchColumns, "visibility");
		searchColumns.clear();
		searchColumns.sendKeys(skillCodeData);

		clickEditBtn();

		enterSkillCode(skillCodeData +" "+ System.currentTimeMillis());
		enterSkillDescription(fakeEmployee.getSkillCodeDesc());
		clickSaveBtn();
		closeNotificationPopup();
	}

	public void deactivateSkill() throws Exception {
		Rc.explicitWait(searchColumns, "visibility");
		searchColumns.clear();
		searchColumns.sendKeys(skillCodeData);

		deleteRowsWithEnabledCheckbox();
		clickYes();
		closeNotificationPopup();
	}

	public void activateDeactivateSkill() throws Exception {
		

		Rc.explicitWait(searchColumns, "visibility");
		searchColumns.clear();
		searchColumns.sendKeys(skillCodeData);
		clickEditBtn();
		clickIsActiveOption();
		clickSaveBtn();
		closeNotificationPopup();
	}

	public void setSearchColumns() throws Exception {
		enterSearchColumns(skillCodeData);
	}
}

package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.WaitHelper;

public class SearchCustomerPage {

	public WebDriver driver;
	WaitHelper helper;

	public SearchCustomerPage(WebDriver driver) {
		this.driver = driver;
		helper = new WaitHelper(this.driver);

	}

	By email = By.id("SearchEmail");
	By firstName = By.id("SearchFirstName");
	By lastName = By.id("SearchLastName");

	By search = By.xpath("//button[@id='search-customers']");

	By rows = By.xpath("//table[@id='customers-grid']//tbody/tr");
	By cols = By.xpath("//table[@id='customers-grid']//tbody/tr/td");

	// Actions methods

	public void enterEmail(String emailID) {
		WebElement ele = driver.findElement(email);
		helper.waitForElement(ele, 10);
		ele.clear();
		ele.sendKeys(emailID);
	}

	public void enterFname(String fName) {
		WebElement ele = driver.findElement(firstName);
		helper.waitForElement(ele, 10);
		ele.clear();
		ele.sendKeys(fName);
	}

	public void enterLname(String Lname) {
		WebElement ele = driver.findElement(lastName);
		helper.waitForElement(ele, 10);
		ele.clear();
		ele.sendKeys(Lname);
	}

	public void clickSearch() {
		driver.findElement(search).click();
	}

	public int getNoOfRows() {
		List<WebElement> list = driver.findElements(rows);
		return list.size();
	}

	public int getNoOfCols() {
		List<WebElement> list = driver.findElements(cols);
		return list.size();
	}

	public boolean searchForCustomer(String email) {
		boolean flag = false;
		for (int i = 0; i <= getNoOfRows(); i++) {
			String emailId = driver.findElement(By.xpath("//*[@id=\"customers-grid\"]/tbody/tr/td[2]")).getText();
			System.out.println(emailId);

			if (emailId.equalsIgnoreCase(email)) {
				flag = true;
			}
		}

		return flag;
	}

	public boolean searchForName(String firstName , String lastName) {
		boolean flag = false;
		for (int i = 0; i <= getNoOfRows(); i++) {
			String name = driver.findElement(By.xpath("//*[@id=\"customers-grid\"]/tbody/tr/td[3]")).getText();
			System.out.println(name);

			if (name.equalsIgnoreCase(firstName+" "+lastName)) {
				flag = true;
			}
		}

		return flag;
	}
	
}

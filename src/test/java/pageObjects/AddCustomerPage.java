package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.*;

public class AddCustomerPage {

	public WebDriver driver;

	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
	}

	By customers = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By customerMenu = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	By addNew = By.xpath("//a[@class='btn btn-primary']");
	By email = By.id("Email");
	By pass = By.id("Password");
	By firstName = By.name("FirstName");
	By lastName = By.name("LastName");
	By genderMale = By.xpath("//input[@id='Gender_Male']");
	By genderFemale = By.xpath("//input[@id='Gender_Female']");
	By dob = By.id("DateOfBirth");
	By company = By.id("Company");
	By tax = By.id("IsTaxExempt");
	By customerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	By Administartor = By.xpath("//li[contains(text(),'Administrators')]");
	By Registered = By.xpath("//li[contains(text(),'Registered')]");
	By Guest = By.xpath("//li[contains(text(),'Guest')]");
	By Vendors = By.xpath("//li[contains(text(),'Vendors')]");
	By vendor = By.xpath("//*[@id='VendorId']");
	By textArea = By.tagName("textarea");
	By submit = By.xpath("//button[@type='submit']");

	// ----------------------------------------------------------------------------------------//

	public String getPageTitle() {
		return driver.getTitle();
	}

	// Action methods

	public void clickOnCustomerMenu() {
		driver.findElement(customers).click();
	}

	public void clickOnCustomerMenuItem() {
		driver.findElement(customerMenu).click();
	}

	public void addNew() {
		driver.findElement(addNew).click();
	}

	public void enterEmail(String emailAddress) {
		driver.findElement(email).sendKeys(emailAddress);
	}

	public void enterPass(String password) {
		driver.findElement(pass).sendKeys(password);
	}

	public void enterFname(String fName) {
		driver.findElement(firstName).sendKeys(fName);
	}

	public void enterLname(String lName) {
		driver.findElement(lastName).sendKeys(lName);
	}

	public void selectVendor(String value) {
		Select drop = new Select(driver.findElement(vendor));
		drop.selectByVisibleText(value);
	}

	public void selectGender(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			driver.findElement(genderMale).click();
		}

		else if (gender.equalsIgnoreCase("female")) {
			driver.findElement(genderFemale).click();

		}

		else {
			driver.findElement(genderMale).click();
		}
	}

	public void setDOB(String date) {
		driver.findElement(dob).sendKeys(date);
	}

	public void company(String name) {
		driver.findElement(company).sendKeys(name);
	}

	public void isTax() {
		driver.findElement(tax).click();
	}

	public void comment(String text) {
		driver.findElement(textArea).sendKeys(text);
	}

	public void roles(String role) throws InterruptedException {

		driver.findElement(By.xpath("//li//span[@title='delete']")).click();
		Thread.sleep(1000);

		driver.findElement(customerRoles).click();
		WebElement listItem;
		Thread.sleep(2000);

		if (role.equals("Administrators")) {
			listItem = driver.findElement(Administartor);
		} else if (role.equals("Guest")) {
			listItem = driver.findElement(Guest);
		} else if (role.equals("Registered")) {
			listItem = driver.findElement(Registered);
		} else if (role.equals("Vendors")) {
			listItem = driver.findElement(Vendors);
		} else {
			listItem = driver.findElement(Guest);
		}

//		listItem.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", listItem);
		Thread.sleep(2000);
	}

	public void clickSave() {
		List<WebElement> button = driver.findElements(submit);
		button.get(1).click();
	}

}

package stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import junit.framework.Assert;
import pageObjects.AddCustomerPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {

	@Before
	public void setup() throws IOException {
		
		logger = Logger.getLogger("Project"); // Added Logger
		PropertyConfigurator.configure("log4j.properties"); // Added the path of Logger
		logger.info("*** Launching the Browser ***");

		prop = new Properties();
		FileInputStream configFile = new FileInputStream("config.properties");
		prop.load(configFile);
		
		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromepath"));
			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		} else {
			System.setProperty("webdriver.ie.driver", prop.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		}

		

		helperMethods();

	}

	@Given("User Launches Chrome Browser")
	public void user_Launches_Chrome_Browser() {
		lp = new LoginPage(driver);
	}

	@When("Users open the URL {string}")
	public void users_open_the_URL(String url) {
		logger.info("*** Opening the URL of the application ***");
		driver.get(url);
	}

	@When("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String user, String pass) {
		logger.info("**** Providing the USER Details ****");
		lp.setUserName(user);
		lp.setPassword(pass);
		lp.remember();
	}

	@When("User clicks on Login button")
	public void user_clicks_on_Login_button() {
		logger.info(" **** Clicking on Login Button ***");
		lp.clickLogin();
	}

	@SuppressWarnings("deprecation")
	@Then("Page title should be {string}")
	public void page_title_should_be(String expectedTitle) throws InterruptedException {
//		String currentTitle = driver.getTitle();	
//		assertTrue(currentTitle.equalsIgnoreCase(expectedTitle));

		if (driver.getPageSource().contains("Login was unsuccessful")) {
			driver.close();
			logger.info(" **** LOGIN Passed ****");
			Assert.assertTrue(false);
		} else {
			logger.info(" **** LOGIN Failed ****");
			Assert.assertEquals(expectedTitle, driver.getTitle());
		}
		Thread.sleep(2000);
	}

	@When("User Clicks on Log out Link")
	public void user_Clicks_on_Log_out_Link() throws InterruptedException {
		Thread.sleep(3000);
		logger.info(" **** LOGOUT Successful ****");
		hp = new HomePage(driver);
		hp.doLogout();
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String expectedTitle) {
		String currentTitle = driver.getTitle();
		assertTrue(currentTitle.equalsIgnoreCase(expectedTitle));

	}

	@Then("Close the Browser")
	public void close_the_Browser() {
		logger.info(" **** CLOSING The Browser ****");
		driver.close();
	}

	// Adding new Customer feature Steps Definitions

	@SuppressWarnings("deprecation")
	@Then("User can View Dashboard")
	public void user_can_View_Dashboard() {
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User clicks on Customer menu")
	public void user_clicks_on_Customer_menu() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickOnCustomerMenu();
	}

	@When("Clicks on Customer menu item")
	public void clicks_on_Customer_menu_item() throws InterruptedException {
		Thread.sleep(1000);
		addCust.clickOnCustomerMenuItem();
	}

	@When("Clicks on Add new Button")
	public void clicks_on_Add_new_Button() throws InterruptedException {
		Thread.sleep(2000);
		addCust.addNew();
	}

	@Then("User can view Add New customer page")
	public void user_can_view_Add_New_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enters the Customer details")
	public void user_enters_the_Customer_details() throws InterruptedException {
		logger.info("Adding a new Customer ... ");
		logger.info("**** PROVDIING Customer Details ****");
		Thread.sleep(1000);
		String email = randomStringGenerator() + "@gamil.com";
		addCust.enterEmail(email);
		Thread.sleep(500);
		addCust.enterPass("password123");
		Thread.sleep(500);
		addCust.enterFname("Marshal");
		Thread.sleep(500);
		addCust.enterLname("Mathers");
		Thread.sleep(500);
		addCust.selectGender("Male");
		Thread.sleep(500);
		addCust.setDOB("4/12/1997");
		Thread.sleep(500);
		addCust.company("LTI");
		Thread.sleep(500);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		addCust.roles("Guest");
		Thread.sleep(500);
		addCust.selectVendor("Vendor 1");
		Thread.sleep(500);
		addCust.comment("Adding our first Customer in DB ...");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,-750)");
	}

	@When("Clicks on Save button")
	public void clicks_on_Save_button() throws InterruptedException {
		logger.info("**** SAVING Customer Data ****");
		addCust.clickSave();
		Thread.sleep(2000);
	}

	@Then("User should be able to get confirmation message {string}")
	public void user_should_be_able_to_get_confirmation_message(String message) {
		assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully"));
	}

	@Then("Close the browser")
	public void close_the_browser() throws InterruptedException {
		Thread.sleep(2000);
		driver.manage().deleteAllCookies();
		driver.close();
	}

	// Steps for searching a Customer using Email ID
	String email = "brenda_lindgren@nopCommerce.com";

	@When("User enter the email of the Customer")
	public void user_enter_the_email_of_the_Customer() {
		logger.info("Searching a new Customer By using EmailID");
		searchCust = new SearchCustomerPage(driver);
		searchCust.enterEmail(email);

	}

	@When("Clicks on Search button")
	public void clicks_on_Search_button() throws InterruptedException {
		searchCust.clickSearch();
		Thread.sleep(2000);
	}

	@Then("User should be able to see the Email in the Table")
	public void user_should_be_able_to_see_the_Customer_details_in_the_Table() {
		boolean isAvailable = searchCust.searchForCustomer(email);
		assertTrue(isAvailable);
		assertTrue(searchCust.getNoOfRows() > 0);
	}

	// Searching the customer by First Name and Last name

	String firstName = "Brenda";
	String lastName = "Lindgren";

	@When("User enter the First Name of the Customer")
	public void user_enter_the_First_Name_of_the_Customer() throws InterruptedException {
		logger.info("Searching a new Customer By using Name");
		searchCust = new SearchCustomerPage(driver);
		searchCust.enterFname(firstName);
		Thread.sleep(1000);
	}

	@When("User enter the Last Name of the Customer")
	public void user_enter_the_Last_Name_of_the_Customer() throws InterruptedException {
		searchCust.enterLname(lastName);
		Thread.sleep(1000);
	}

	@Then("User should be able to see the Name in the Table")
	public void user_should_be_able_to_see_the_Name_in_the_Table() {
		boolean isAvailable = searchCust.searchForName(firstName, lastName);
		assertTrue(isAvailable);
		assertTrue(searchCust.getNoOfRows() > 0);
	}

}

package stepDefinitions;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObjects.AddCustomerPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {

	public static WebDriver driver;
	public LoginPage lp;
	public HomePage hp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCust;
	public static Logger logger;
	public Properties prop;
	
	// Generating random string
	public static String randomStringGenerator() {
		String str = RandomStringUtils.randomAlphabetic(5);
		return str;
	}
	
	public static void helperMethods() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
	}

	
}

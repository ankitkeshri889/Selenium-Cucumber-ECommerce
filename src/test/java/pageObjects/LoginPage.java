package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	public WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;

	}

	By email = By.xpath("//input[@id='Email']");
	By pass = By.xpath("//input[@id='Password']");
	By rememberMe = By.id("RememberMe");
	By login = By.xpath("//button");

	public void setUserName(String user) {
		driver.findElement(email).clear();
		driver.findElement(email).sendKeys(user);

	}

	public void setPassword(String user) {
		driver.findElement(pass).clear();
		driver.findElement(pass).sendKeys(user);

	}

	public void remember() {
		driver.findElement(rememberMe).click();
	}

	public void clickLogin() {
		driver.findElement(login).click();
	}

	public void doLogin(String user, String pass) {
		setUserName(user);
		setPassword(pass);
		clickLogin();
	}

}

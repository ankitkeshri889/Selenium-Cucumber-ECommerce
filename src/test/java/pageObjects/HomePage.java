package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	public WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;

	}
	
	By logout = By.linkText("Logout");
	
	public void doLogout() {
		driver.findElement(logout).click();
	}
}

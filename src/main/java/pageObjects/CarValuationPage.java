package main.java.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import main.java.utils.ScreenshotUtil; 

public class CarValuationPage {
    private WebDriver driver;
    private WebDriverWait wait;

   
    private By acceptCookiesButton = By.xpath("//*[@id='onetrust-accept-btn-handler']"); 

   
    private By registrationInput = By.id("vehicleReg"); 
    private By mileageInput = By.id("Mileage"); 
    private By submitButton = By.id("btn-go"); 
    
 
    private By manufacturerElement = By.xpath("//label[text()='Manufacturer:']/following-sibling::span"); 
    private By modelElement = By.xpath("//label[text()='Model:']/following-sibling::span"); 

    public CarValuationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); 
        acceptCookies(); 
    }

    private void acceptCookies() {
        try {
            
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
            if (acceptButton.isDisplayed()) {
                acceptButton.click();
                System.out.println("Cookie consent accepted.");
            }
        } catch (Exception e) {
            System.out.println("No cookie consent popup appeared or button was not clickable. Exception: " + e.getMessage());
            ScreenshotUtil.captureScreenshot(driver, "screenshots/cookie_consent_error.png"); 
        }
    }

    public void enterRegistration(String registration) {
        WebElement registrationInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationInput));
        registrationInputElement.sendKeys(registration);
    }

    public void enterMileage(String mileage) {
        WebElement mileageInputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(mileageInput));
        mileageInputElement.sendKeys(mileage);
    }

    public void submit() {
        WebElement submitButtonElement = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButtonElement.click();
    }

    public void printVehicleDetails() {
        String manufacturer = wait.until(ExpectedConditions.visibilityOfElementLocated(manufacturerElement)).getText();
        String model = wait.until(ExpectedConditions.visibilityOfElementLocated(modelElement)).getText();
        
       
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Model: " + model);
    }

	public String getResult() {
		
		return null;
	}
}

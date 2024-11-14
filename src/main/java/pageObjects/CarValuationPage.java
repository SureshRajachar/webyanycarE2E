package main.java.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CarValuationPage {
    private WebDriver driver;
    private WebDriverWait wait;

  
    private By acceptCookiesButton = By.xpath("//*[@id='onetrust-accept-btn-handler']");

  
    private By registrationInput = By.id("vehicleReg"); 
    private By mileageInput = By.id("Mileage"); 
    private By submitButton = By.id("btn-go"); 
    

    private By manufacturerElement = By.xpath("//label[text()='Manufacturer:']/following-sibling::span"); 
    private By modelElement = By.xpath("//label[text()='Model:']/following-sibling::span"); 
    private By yearElement = By.xpath("//label[text()='Year:']/following-sibling::span"); 
    private By colourElement = By.xpath("//label[text()='Colour:']/following-sibling::span"); 
    private By transmissionElement = By.xpath("//label[text()='Transmission:']/following-sibling::span"); 
    private By engineSizeElement = By.xpath("//label[text()='Engine Size:']/following-sibling::span"); 
    private By registeredElement = By.xpath("//label[text()='First Registered:']/following-sibling::span"); 

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
        String year = wait.until(ExpectedConditions.visibilityOfElementLocated(yearElement)).getText();
        String colour = wait.until(ExpectedConditions.visibilityOfElementLocated(colourElement)).getText();
        String transmission = wait.until(ExpectedConditions.visibilityOfElementLocated(transmissionElement)).getText();
        String engineSize = wait.until(ExpectedConditions.visibilityOfElementLocated(engineSizeElement)).getText();
        String firstRegistered = wait.until(ExpectedConditions.visibilityOfElementLocated(registeredElement)).getText();

     
        System.out.println("Manufacturer: " + manufacturer);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Colour: " + colour);
        System.out.println("Transmission: " + transmission);
        System.out.println("Engine Size: " + engineSize);
        System.out.println("First Registered: " + firstRegistered);
    }
}
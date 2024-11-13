package main.java.validations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import main.java.utils.FileReaderUtil;
import main.java.pageObjects.CarValuationPage;
import main.java.utils.ScreenshotUtil; 

import java.util.List;

public class CarValuationTest {

    public static void main(String[] args) {
        
        System.setProperty("webdriver.chrome.driver", "/usr/local/Caskroom/chromedriver/130.0.6723.116/chromedriver-mac-x64/chromedriver");
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        CarValuationPage valuationPage = new CarValuationPage(driver);
        List<String> registrations = FileReaderUtil.readCarRegistrations("/Users/sureshrajachar/eclipse-workspace/webyanycar/src/resources/car_input.txt");
        
        driver.get("https://www.webuyanycar.com/");

        for (String reg : registrations) {
            try {
              
                performCarValuation(driver, valuationPage, reg);
            } catch (Exception e) {
                ScreenshotUtil.captureScreenshot(driver, "screenshots/error_registration_" + reg + ".png");
                System.out.println("An error occurred for registration " + reg + ". Exception: " + e.getMessage());
            }
        }

        driver.quit();
    }
    
    private static void performCarValuation(WebDriver driver, CarValuationPage valuationPage, String registration) throws InterruptedException {
        valuationPage.enterRegistration(registration);
        valuationPage.enterMileage("20000"); 
        valuationPage.submit();

        Thread.sleep(1000); 

        String result = valuationPage.getResult();
        System.out.println("Result for registration " + registration + ": " + result);

        ScreenshotUtil.captureScreenshot(driver, "screenshots/success_registration_" + registration + ".png");
    }
}
package main.java.validations;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import main.java.utils.FileReaderUtil;
import main.java.pageObjects.CarValuationPage;
import main.java.utils.ScreenshotUtil; 

import java.util.ArrayList;
import java.util.List;

public class CarValuationTest {

    private static final String BASE_PATH = "/Users/sureshrajachar/eclipse-workspace/webyanycar/src/resources/";

    public static void main(String[] args) {
       
        System.setProperty("webdriver.chrome.driver", "/usr/local/Caskroom/chromedriver/130.0.6723.116/chromedriver-mac-x64/chromedriver");
        
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        CarValuationPage valuationPage = new CarValuationPage(driver);

       
        String[] inputFiles = {
            BASE_PATH + "car_input.txt",
           
        };

        List<String> allRegistrations = new ArrayList<>();
        for (String filePath : inputFiles) {
            List<String> registrations = FileReaderUtil.readCarRegistrations(filePath);
            allRegistrations.addAll(registrations);
        }

        driver.get("https://www.webuyanycar.com/");

        for (String reg : allRegistrations) {
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

   
        valuationPage.printVehicleDetails();


        ScreenshotUtil.captureScreenshot(driver, "screenshots/success_registration_" + registration + ".png");
    }
}
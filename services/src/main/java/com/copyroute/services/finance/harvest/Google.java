//package com.copyroute.services.finance.harvest;
//
//import com.copyroute.cdm.global.Statics;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import java.io.File;
//import java.io.IOException;
//
//import static junit.framework.Assert.assertEquals;
//
///**
// * Created by flatline on 6/23/15.
// */
//public class Google extends SeleniumDriver{
//
//    public void init() {
////        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
////        driver = new FirefoxDriver();
////        wait = new WebDriverWait(driver, 10);
//    }
//
//    public void searchGoogle(String url, String term) throws IOException {
//        driver.navigate().to(url);
//        assertEquals("The page title should equal Google at the start of the test.", "Google", driver.getTitle());
//
//        WebElement searchField = driver.findElement(By.name("q"));
//        searchField.sendKeys(term);
//        searchField.submit();
//
//        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("selenium")));
//
//        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(screenshot, new File("screenshot.png"));
//    }
//
//}

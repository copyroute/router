//package com.copyroute.services.finance.harvest;
//
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertTrue;
//
//import java.io.*;
//import java.util.List;
//
//import com.copyroute.cdm.global.Statics;
//import org.apache.commons.io.FileUtils;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.firefox.internal.ProfilesIni;
//import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//
//@Component
//public class SeleniumDriver {
//
//    protected WebDriver driver ;
//    // WebDriver driver = new ChromeDriver();
//
//    WebDriverWait wait;
//
////    @PostConstruct
//    public void init() {
//        Statics.Log("================= >>>>>> Initialized : " + this.getClass().toString());
//
//        FirefoxProfile firefoxProfile = new FirefoxProfile();
//        firefoxProfile.setPreference("browser.download.folderList",2);
//        firefoxProfile.setPreference("browser.download.manager.showWhenStarting",false);
//        firefoxProfile.setPreference("browser.download.dir","~/Downloads");
//        firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv");
////        driver = new FirefoxDriver(firefoxProfile);//new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
//
//        ProfilesIni allProfiles = new ProfilesIni();
//        FirefoxProfile desiredProfile = allProfiles.getProfile("default");
//        driver = new FirefoxDriver(desiredProfile);
//
////        driver = new FirefoxDriver();
//        wait = new WebDriverWait(driver, 10);
//    }
//
//    public void run() {
//
//        String csvFile = "/Users/mkyong/Downloads/GeoIPCountryWhois.csv";
//        BufferedReader br = null;
//        String line = "";
//        String cvsSplitBy = ",";
//
//        try {
//
//            br = new BufferedReader(new FileReader(csvFile));
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] country = line.split(cvsSplitBy);
//
//                System.out.println("Country [code= " + country[4]
//                        + " , name=" + country[5] + "]");
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        System.out.println("Done");
//    }
//
//    @PreDestroy
//    public void destroy(){
//        driver.close();
//        driver.quit();
//    }
//
//    public void sleep(){
//        try{Thread.sleep(5000L);}
//        catch(Exception ex){}
//    }
//
//    public WebElement clickById(String elementId){
//        WebElement element = driver.findElement(By.id(elementId));
//        element.click();
//        return element;
//    }
//
//    public WebElement setTextById(String elementId, String text){
//        WebElement element = driver.findElement(By.id(elementId));
//        element.sendKeys(text);
//        return element;
//    }
//
//
//    public void takeScreenShot(){
//
//        try{
//            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(screenshot, new File("screenshot.png"));
//        }
//        catch (Exception ex){Statics.Log(ex.getMessage());}
//
//    }
//
//
//}
//
//
//
//
////        driver.findElement(By.cssSelector(".account-types li::nth-child(1) [option='1']")).click();
////        driver.findElement(By.cssSelector(".account-types > ul li:nth-child(1) [option='1']")).click();
////        List<WebElement> dropDownElements = driver.findElements(ExpectedConditions.elementToBeClickable(By.ByCssSelector(".account-types")));
////        for(WebElement dropDownElement : dropDownElements)
////        {
////            dropDownElement.click();
////        }
////or your can access to element using index
////        dropDownElements.get(0).click();
////        WebElement dropDownListBox = driver.findElement(By.id("btnAccountType"));
////        Select clickThis = new Select(dropDownListBox);
////        clickThis.selectByVisibleText("Credit Cards");
//

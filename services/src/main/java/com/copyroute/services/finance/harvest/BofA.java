//package com.copyroute.services.finance.harvest;
//
//import com.copyroute.cdm.global.Statics;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * Created by flatline on 6/23/15.
// */
//@Component
//public class BofA extends SeleniumDriver {
//
//    public void searchBOFA(String url, String id, String pass) throws IOException {
//
//        driver.navigate().to(url);
//
//        // === Submit User Page ========================================
//        WebElement userBox = driver.findElement(By.name("id"));
//        userBox.sendKeys(id);
//        userBox.submit();
//
//        // === Submit Pass Page ========================================
//        //
//        try{
//            // Login
//            WebElement passBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("tlpvt-passcode-input")));
//            passBox.sendKeys(pass);
//            passBox.submit();
//
//            // Account
//            WebElement accountLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("BofA Core Checking - 3608")));
//            accountLink.click();
//
//            // Download
//            WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(By.name("download_transactions_top")));
//            downloadLink.click();
//
//            //
////            WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(By.name("select_filetype")));
//
//            Select periodSelect = new Select(driver.findElement(By.id("select_txnperiod")));
//            periodSelect.selectByIndex(2);
//
//            Select filetypeSelect = new Select(driver.findElement(By.id("select_filetype")));
//            filetypeSelect.selectByValue("csv");
//
//            WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("submit-download")));
//            downloadButton.click();
//
////            this.sleep();
////            this.sleep();
//
////            Alert alert = driver.switchTo().
////            alert.accept();
//
//
//        }
//        catch (Exception ex){
//            Statics.Log(ex.getMessage());}
//
//    }
//
//}

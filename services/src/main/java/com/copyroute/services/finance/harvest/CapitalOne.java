//package com.copyroute.services.finance.harvest;
//
//import com.copyroute.cdm.global.Statics;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * Created by flatline on 6/23/15.
// */
//@Component
//public class CapitalOne extends SeleniumDriver {
//
//
//
//    public void searchCapitalOne(String url, String id, String pass) throws IOException {
//        driver.navigate().to(url);
//
//        // Fill in User textbox
//        clickById("btnAccountType");
//
//        // Click on "Credit Card" list selection
//        WebElement creditCardSelectOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("rbCreditCards")));
//        creditCardSelectOption.click();
//        creditCardSelectOption.sendKeys("\n");
//
//        // Fill in User & Pass & Submit
//        setTextById("us-credit-cards-uid", id);
//        setTextById("us-credit-cards-pw", pass).submit();
//
//
//        // Click on "Transactions" link
//        WebElement transactionsLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("transactionsLink0")));
//        transactionsLink.click();
//        this.sleep();
//
//        // Click on "Download Transactions" link
//        WebElement downloadTransactionsLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("view_download_transactions_link_ID")));
//        downloadTransactionsLink.click();
//        this.sleep();
//        this.sleep();
//
//        // Set Start & End dates
//        WebElement startDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtFromDate_TextBox")));
//        startDate.click();
//        startDate.sendKeys("06/01/2015");
//        this.sleep();
//        WebElement endDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtToDate_TextBox")));
//        endDate.click();
//        endDate.sendKeys("06/01/2015");
//        this.sleep();
//
//
//        // Click on "Download" button
//        WebElement downloadButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnDownload")));
//        downloadButton.click();
//
//        Statics.Log("Made it...");
//
//    }
//
//}

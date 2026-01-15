package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import sharedData.SharedData;

import java.util.Arrays;
import java.util.List;

public class CreateCustomerAccountsTest extends SharedData {

    @Test
    public void automationTest() {
        WebElement bankManagerElement = getDriver().findElement(By.xpath("//button[text()='Bank Manager Login']"));
        bankManagerElement.click();

        WebElement addCustomerElement = getDriver().findElement(By.xpath("//button[@ng-click='addCust()']"));
        addCustomerElement.click();

        WebElement firstNameElement = getDriver().findElement(By.xpath("//input[@placeholder='First Name']"));
        String firstNameValue = "Alex";
        firstNameElement.sendKeys(firstNameValue);

        WebElement lastNameElement = getDriver().findElement(By.xpath("//input[@placeholder='Last Name']"));
        String lastNameValue = "Virlan";
        lastNameElement.sendKeys(lastNameValue);

        WebElement postCodeElement = getDriver().findElement(By.xpath("//input[@placeholder='Post Code']"));
        String postCodeValue = "E10AA";
        postCodeElement.sendKeys(postCodeValue);

        WebElement submitCustomerElement = getDriver().findElement(By.xpath("//button[@class='btn btn-default']"));
        submitCustomerElement.click();

        Alert customerAlert = getDriver().switchTo().alert();
        String customerAlertText = customerAlert.getText();
        System.out.println(customerAlertText);
        customerAlert.accept();

        WebElement openAccount = getDriver().findElement(By.xpath("//button[@ng-click='openAccount()']"));
        openAccount.click();

//        Pentru un customer vreau sa ii creez 3 conturi

        List<String> currencyValuesList = Arrays.asList("Dollar", "Pound", "Rupee");
        String fullName = firstNameValue + " " + lastNameValue;

        for (String s : currencyValuesList) {
            WebElement customerName = getDriver().findElement(By.id("userSelect"));
            Select customerSelect = new Select(customerName);
            customerSelect.selectByVisibleText(fullName);

            WebElement currency = getDriver().findElement(By.id("currency"));
            Select currencySelect = new Select(currency);
            currencySelect.selectByVisibleText(s);

            WebElement process = getDriver().findElement(By.xpath("//button[@type='submit']"));
            process.click();

            Alert accountAlert = getDriver().switchTo().alert();
            String accountAlertText = accountAlert.getText();
            System.out.println(accountAlertText);
            accountAlert.accept();
        }

        WebElement customers = getDriver().findElement(By.xpath("//button[@ng-click='showCust()']"));
        customers.click();

        WebElement searchCustomerElement = getDriver().findElement(By.xpath("//input[@placeholder='Search Customer']"));
        searchCustomerElement.sendKeys(fullName);


    }
}
package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CreateCustomerTest {
    public WebDriver driver;

    @Test
    public void automationTest() {
        driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement bankManagerElement = driver.findElement((By.xpath("//button[text()='Bank Manager Login']")));
        bankManagerElement.click();

        WebElement addCustomerElement = driver.findElement(By.xpath("//button[@ng-click='addCust()']"));
        addCustomerElement.click();

        WebElement firstNameElement = driver.findElement(By.xpath("//input[@placeholder='First Name']"));
        String firstNameValue = "Teodora";
        firstNameElement.sendKeys(firstNameValue);

        WebElement lastNameElement = driver.findElement(By.xpath("//input[@placeholder='Last Name']"));
        String lastNameValue = "Ardeleanu";
        lastNameElement.sendKeys(lastNameValue);

        WebElement postCodeElement = driver.findElement(By.xpath("//input[@placeholder='Post Code']"));
        String postCodeValue = "123456";
        postCodeElement.sendKeys(postCodeValue);

        WebElement submitCustomerElement = driver.findElement(By.xpath("//button[@type='submit']"));
        submitCustomerElement.click();

        Alert customerAlert = driver.switchTo().alert();
        String customerAlertText = customerAlert.getText();
        System.out.println(customerAlertText);
        customerAlert.accept();

        WebElement openAccount = driver.findElement(By.xpath("//button[@ng-click='openAccount()']"));
        openAccount.click();

        WebElement customerName = driver.findElement(By.id("userSelect"));
        Select customerSelect = new Select(customerName);
        customerSelect.selectByVisibleText(firstNameValue + " " + lastNameValue);

        WebElement currencyElement = driver.findElement(By.id("currency"));
        Select currencySelect = new Select(currencyElement);
        currencySelect.selectByVisibleText("Dollar");

        WebElement processElement = driver.findElement(By.xpath("//button[@type='submit']"));
        processElement.click();

        Alert accountAlert = driver.switchTo().alert();
        String accountAlertText = accountAlert.getText();
        System.out.println(accountAlertText);
        String[] accountsArray = accountAlertText.split(":");
        String accountNumber = accountsArray[1];
        System.out.println(accountNumber);
        accountAlert.accept();

        WebElement customersElement = driver.findElement(By.xpath("//button[@ng-click='showCust()']"));
        customersElement.click();

        WebElement searchCustomerElement = driver.findElement(By.xpath("//input[@placeholder='Search Customer']"));
        searchCustomerElement.sendKeys(firstNameValue);

        List<WebElement> tableRows = driver.findElements(By.xpath("//tbody/tr"));
        String customerTableRow = tableRows.get(0).getText();
        Assert.assertTrue(customerTableRow.contains(firstNameValue));
        Assert.assertTrue(customerTableRow.contains(lastNameValue));
        Assert.assertTrue(customerTableRow.contains(postCodeValue));
        Assert.assertTrue(customerTableRow.contains(accountNumber));

        WebElement deleteCustomerElement = driver.findElement(By.xpath("//button[@ng-click='deleteCust(cust)']"));
        deleteCustomerElement.click();

        //driver.quit();
    }
}

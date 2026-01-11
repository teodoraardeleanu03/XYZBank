package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import pages.CustomerPage;
import pages.LoginPage;
import pages.ManagerPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateCustomerAccountsTest {
    public WebDriver driver;

    @Test
    public void automationTest() {
        driver = new ChromeDriver();
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginBankManager();

        ManagerPage managerPage = new ManagerPage(driver);
        managerPage.createCustomer();

        List<String> firstNameValueList = Arrays.asList("Teodora1", "Teodora2", "Teodora3");
        List<String> lastNameValueList = Arrays.asList("Ardeleanu1", "Ardeleanu2", "Ardeleanu3");
        List<String> codPostalValueList = Arrays.asList("123456", "654321", "112233");
        String fullName = "";
        fullName = firstNameValueList.get(2) + " " + lastNameValueList.get(2);

        CustomerPage customerPage = new CustomerPage(driver);
        customerPage.createCustomersProcess(firstNameValueList, lastNameValueList, codPostalValueList);

        WebElement openAccount = driver.findElement(By.xpath("//button[@ng-click='openAccount()']"));
        openAccount.click();

        // Pentru un customer vreau sa creez 3 conturi
        List<String> currencyValuesList = Arrays.asList("Dollar", "Pound", "Rupee");
        for(int i = 0; i < currencyValuesList.size(); i++) {
            WebElement customerName = driver.findElement(By.id("userSelect"));
            Select customerSelect = new Select(customerName);
            customerSelect.selectByVisibleText(fullName);

            WebElement currencyElement = driver.findElement(By.id("currency"));
            Select currencySelect = new Select(currencyElement);
            currencySelect.selectByVisibleText(currencyValuesList.get(i));

            WebElement processElement = driver.findElement(By.xpath("//button[@type='submit']"));
            processElement.click();

            Alert accountAlert = driver.switchTo().alert();
            String accountAlertText = accountAlert.getText();
            System.out.println(accountAlertText);
            accountAlert.accept();
        }

        WebElement customersElement = driver.findElement(By.xpath("//button[@ng-click='showCust()']"));
        customersElement.click();

        WebElement searchCustomerElement = driver.findElement(By.xpath("//input[@placeholder='Search Customer']"));
        searchCustomerElement.sendKeys(fullName);

        // driver.quit();
    }
}

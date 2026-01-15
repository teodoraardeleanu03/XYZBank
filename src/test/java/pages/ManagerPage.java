package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage extends BasePage{

    @FindBy(xpath = "//button[@ng-click='addCust()']")
    private WebElement addCustomerElement;

    public ManagerPage(WebDriver driver) {
        super(driver);
    }

    public void createCustomer(){
        addCustomerElement.click();
    }
}
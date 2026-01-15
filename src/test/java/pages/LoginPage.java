package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//button[text()='Bank Manager Login']")
    private WebElement bankManagerElement;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginBankManager(){
        bankManagerElement.click();
    }
}
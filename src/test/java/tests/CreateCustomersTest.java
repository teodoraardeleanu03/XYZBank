package tests;

import models.CustomerModel;
import org.testng.annotations.Test;
import pages.CustomerPage;
import pages.LoginPage;
import pages.ManagerPage;
import sharedData.SharedData;
import java.util.Arrays;
import java.util.List;

public class CreateCustomersTest extends SharedData {

    @Test(groups = "@SmokeSuite")
    public void automationTest() {
        //CustomerModel testData = new CustomerModel("CustomersData.json");

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.loginBankManager();

        ManagerPage managerPage = new ManagerPage(getDriver());
        managerPage.createCustomer();

        List<String> firstNameValueList = Arrays.asList("Teodora 1","Teodora 2","Teodora 3");
        List<String> lastNameValueList = Arrays.asList("Ardeleanu 1","Ardeleanu 2","Ardeleanu 3");
        List<String> postCodeValueList = Arrays.asList("E10AA","E10AB","E10AC");

        CustomerPage customerPage = new CustomerPage(getDriver());
        customerPage.createCustomersProcess(firstNameValueList, lastNameValueList, postCodeValueList);
    }
}
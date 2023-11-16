package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Base_PO;
import pageObjects.Login_PO;

public class Login_Steps extends Base_PO {
    private Login_PO login_po;

    public Login_Steps(Login_PO login_po){
        this.login_po = login_po;
    }

    @Given("I access the web driver university login page")
    public void i_access_the_web_driver_university_login_page() {
        login_po.navigateTo_WebDriverUniversity_Login_Page();
    }

    @When("I enter a username {}")
    public void i_enter_a_username(String string) {
        login_po.setUsername(string);
    }

    @And("I enter a password {}")
    public void i_enter_a_password(String string) {
       login_po.setPassword(string);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        login_po.clickOn_Login_Button();
    }

    @Then("I should be presented with a successful login message")
    public void i_should_be_presented_with_a_successful_login_message() {
        login_po.validate_Successful_Login_Message();
    }

    @Then("I should be presented with a unsuccessful login message")
    public void i_should_be_presented_with_a_unsuccessful_login_message() {
        login_po.validate_Unsuccessful_Login_Message();
    }

    @Then("I should be presented with the following login validation message {}")
    public void i_should_be_presented_with_the_following_login_validation_message(String expectedMessage) {
        waitForAlert_And_ValidateText(expectedMessage);
    }
}

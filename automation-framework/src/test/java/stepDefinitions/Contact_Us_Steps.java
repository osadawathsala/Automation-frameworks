package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Base_PO;
import pageObjects.Contact_Us_PD;

/**
    * @author Osada
    * @Date
 */

public class Contact_Us_Steps extends Base_PO {

    private Contact_Us_PD contact_us_pd;

    public Contact_Us_Steps(Contact_Us_PD contact_us_pd) {
        this.contact_us_pd = contact_us_pd;
    }

    @Given("I access the web driver university contact us page")
    public void i_access_the_web_driver_university_contact_us_page() {
        contact_us_pd.navigateTo_WebDriverUniversity_Contact_Us_Page();
    }

    @When("I enter a unique first name")
    public void i_enter_a_unique_first_name() {
        contact_us_pd.setUnique_FirstName();
    }

    @And("I enter a unique last name")
    public void i_enter_a_unique_last_name() {
        contact_us_pd.setUnique_LastName();
    }

    @And("I enter a unique email address")
    public void i_enter_a_unique_email_address() {
        contact_us_pd.setUnique_EmailAddress();
    }
    @And("I enter a unique comment")
    public void i_enter_a_unique_comment() {
        contact_us_pd.setUnique_Comment();
    }

    @When("I enter a specific first name {word}")
    public void i_enter_a_specific_first_name(String firstName) {
        contact_us_pd.setSpecific_FirstName(firstName);
    }
    @When("I enter a specific last name {word}")
    public void i_enter_a_specific_last_name(String lastName) {
       contact_us_pd.setSpecific_LastName(lastName);
    }
    @When("I enter a specific email {word}")
    public void i_enter_a_specific_email(String email) {
        contact_us_pd.setSpecific_EmailAddress(email);
    }
    @When("I enter a specific comment {string}")
    public void i_enter_a_specific_comment(String string) {
        contact_us_pd.setSpecific_Comment(string);
    }

    @And("I click on the submit button")
    public void i_click_on_the_submit_button() {
       contact_us_pd.clickOn_Submit_Button();
    }
    @Then("I should be presented with a successful contact us submission message")
    public void i_should_be_presented_with_a_successful_contact_us_submission_message() {
        contact_us_pd.validate_Successful_Submission();
    }
}

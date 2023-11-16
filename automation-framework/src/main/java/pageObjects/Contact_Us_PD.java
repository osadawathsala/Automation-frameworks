package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import utils.Global_Vars;
/**
 * @author Osada
 * @Date
 */
public class Contact_Us_PD extends Base_PO{

    @FindBy(how = How.XPATH ,using = "//input[@name='first_name']")
    private WebElement firstName_TextField;
    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement lastName_TextField;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailAddress_TextField;
    @FindBy(xpath = "//textarea[@name='message']")
    private WebElement comment_TextField;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submit_Button;
    @FindBy(xpath = "//div[@id='contact_reply']/h1")
    WebElement successful_Submission_Message_text;

    public Contact_Us_PD() {
        super();
    }
    public void navigateTo_WebDriverUniversity_Contact_Us_Page(){
        navigateTo_URL(Global_Vars.WEB_DRIVER_UNIVERSITY_HOMEPAGE_URL + "/Contact-Us/contactus.html");
    }
    public void setUnique_FirstName(){
        sendKeys(firstName_TextField,"AutoFN" + generateRandomNumber(5));
    }
    public void setUnique_LastName(){
        sendKeys(lastName_TextField,"AutoLN" + generateRandomNumber(5));
    }
    public void setUnique_EmailAddress(){
        sendKeys(emailAddress_TextField,"AutoEmail"+ generateRandomNumber(5) + "@gmail.com");
    }
    public void setUnique_Comment(){
        sendKeys(comment_TextField,"Hello world  " + generateRandomString(20));
    }
    public void setSpecific_FirstName(String firstName){
        sendKeys(firstName_TextField,firstName);
    }
    public void setSpecific_LastName(String lastName){
        sendKeys(lastName_TextField,lastName);
    }
    public void setSpecific_EmailAddress(String emailAddress){
        sendKeys(emailAddress_TextField,emailAddress);
    }
    public void setSpecific_Comment(String comment){
        sendKeys(comment_TextField,comment);
    }
    public void clickOn_Submit_Button(){
        waitForWebElementAndClick(submit_Button);
    }
    public void validate_Successful_Submission(){
        waitFor(successful_Submission_Message_text);
        Assert.assertEquals(successful_Submission_Message_text.getText(),"Thank You for your Message!");
    }
}

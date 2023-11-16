package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Global_Vars;
/**
    * @author Osada
    * @Date
 */
public class Login_PO extends Base_PO{

    @FindBy(id = "text")
    private WebElement username_TextField;
    @FindBy(id = "password")
    private WebElement password_TextField;
    @FindBy(id="login-button")
    private WebElement login_button;

    public Login_PO() {
        super();
    }
    public void navigateTo_WebDriverUniversity_Login_Page(){
        navigateTo_URL(Global_Vars.WEB_DRIVER_UNIVERSITY_HOMEPAGE_URL + "/Login-Portal/index.html");
    }
    public void setUsername(String username){
        sendKeys(username_TextField,username);
    }
    public void setPassword(String password){
        sendKeys(password_TextField,password);
    }
    public void clickOn_Login_Button(){
        waitForWebElementAndClick(login_button);
    }
    public void validate_Successful_Login_Message(){
        waitForAlert_And_ValidateText("validation succeeded");
    }
    public void validate_Unsuccessful_Login_Message(){
       waitForAlert_And_ValidateText("validation failed");
    }
}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class EditUserPage extends BasePage {

    private By nameField = findByCss("html /deep/ #user_full_name");
    private By mailField = findByCss("html /deep/ #user_email");
    private By loginField = findByCss("html /deep/ #user_login");
    private By phoneField = findByCss("html /deep/ #user_phone");
    private By websiteField = findByCss("html /deep/ #user_website");
    private By externalField = findByCss("html /deep/ #user_external_user_id");
    private By facebookField = findByCss("html /deep/ #user_facebook_id");
    private By twitterField = findByCss("html /deep/ #user_twitter_id");
    private By tagField = findByCss("html /deep/ #tags_tagsinput .tag span");
    private By customDataField = findByCss("html /deep/ #user_custom_data");
    private By isAdminCheckboxChecked = findByCss("html /deep/ #user_is_admin_reader[checked]");


    public String getName(){
        return getValue(nameField);
    }

    public String getMail(){
        return getValue(mailField);
    }

    public String getLogin(){
        return getValue(loginField);
    }

    public String getPhone(){
        return getValue(phoneField);
    }

    public String getWebsite(){
        return getValue(websiteField);
    }

    public String getExternalId(){
        return getValue(externalField);
    }

    public String getFacebookId(){
        return getValue(facebookField);
    }

    public String getTwitterId(){
        return getValue(twitterField);
    }

    public String getCustomData(){
        return getValue(customDataField);
    }

    public boolean isTagPresented(String tag){
        return getText(tagField).contains(tag);
    }

    public boolean isAdminStatementCorrect(boolean statement){
        boolean state = false;
        if(isElementPresented(isAdminCheckboxChecked))
            state = true;

        return  state;
    }




    public EditUserPage(WebDriver driver){
        super(driver);
    }
}


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import java.util.regex.*;

public class AddUserPage extends BasePage {

    private By nameField = findByCss("html /deep/ #user_full_name");
    private By mailField = findByCss("html /deep/ #user_email");
    private By loginField = findByCss("html /deep/ #user_login");
    private By passwordField = findByCss("html /deep/ #user_password");
    private By confirmField = findByCss("html /deep/ #user_password_confirmation");
    private By phoneField = findByCss("html /deep/ #user_phone");
    private By websiteField = findByCss("html /deep/ #user_website");
    private By blobField = findByCss("html /deep/ #user_blob_id");
    private By externalField = findByCss("html /deep/ #user_external_user_id");
    private By facebookField = findByCss("html /deep/ #user_facebook_id");
    private By twitterField = findByCss("html /deep/ #user_twitter_id");
    private By tagsField = findByCss("html /deep/ #tags_tag");
    private By customDataField = findByCss("html /deep/ #user_custom_data");
    private By isAdminCheckbox = findByCss("html /deep/ #user_is_admin_reader");

    private By userCreatedMessage = findByCss("html /deep/ #form-messages .content");
    private By formErrorMessage = findByCss("html /deep/ #form-errors .content");

    private By mailError = findByCss("html /deep/ label[for='user_email'].error");
    private By loginError = findByCss("html /deep/ label[for='user_login'].error");
    private By passError = findByCss("html /deep/ label[for='user_password'].error");
    private By confrimPassError = findByCss("html /deep/ label[for='user_password_confirmation'].error");

    private By phoneError = findByCss("html /deep/ label[for='user_phone'].error");
    private By websiteError = findByCss("html /deep/ label[for='user_website'].error");
    private By externalError = findByCss("html /deep/ label[for='user_external_user_id'].error");
    private By facebookError = findByCss("html /deep/ label[for='user_facebook_id'].error");
    private By twitterError = findByCss("html /deep/ label[for='user_twitter_id'].error");
    private By tagError = findByCss("html /deep/ #tags-hint");

    private By addUserButton = findByCss("html /deep/ #user_submit");
    private By cancelButton = findByCss("html /deep/ a[onclick='gHistoryBack()']");

    public void fillTheFields(String name, String mail, String login,
                              String password, String confirm, String phone,
                              String website, String blob, String external,
                              String facebook, String twitter, String tag,
                              String customData, Boolean isAdmin){
        clearAndType(nameField, name);
        clearAndType(mailField, mail);
        clearAndType(loginField, login);
        clearAndType(passwordField, password);
        clearAndType(confirmField, confirm);
        clearAndType(phoneField, phone);
        clearAndType(websiteField, website);
        clearAndType(blobField, blob);
        clearAndType(externalField, external);
        clearAndType(facebookField, facebook);
        clearAndType(twitterField, twitter);
        type(tagsField, tag);
        clearAndType(customDataField, customData);
        if(isAdmin)
            click(isAdminCheckbox);
    }

    public void clickAddUserButton(){click(addUserButton);}

    public UsersPage clickCancelButton(){
        click(cancelButton);
        return  new UsersPage(driver);
    }

    public String getNewUserId(){
        pause(500);
        String parsedString;
        String unparsed = getText(userCreatedMessage);

        Pattern pattern = Pattern.compile("[^\\d]*");

        parsedString = pattern.matcher(unparsed).replaceAll("");

        return parsedString;
    }

    public boolean isSuccessMessagePresented(){
        //waitUntilElement(userCreatedMessage);
        return isElementPresented(userCreatedMessage);
    }

    public boolean isMailErrorPresented(){
        return isElementPresented(mailError);
    }

    public boolean isLoginErrorPresented(){
        return isElementPresented(loginError);
    }

    public boolean isPasswordErrorPresented(){
        return isElementPresented(passError);
    }

    public boolean isConfirmPasswordErrorPresented(){
        return isElementPresented(confrimPassError);
    }

    public String getMailErrorText(){
        return getText(mailError);
    }

    public String getLoginErrorText(){
        return getText(loginError);
    }

    public String getPasswordErrorText(){
        return getText(passError);
    }

    public String getConfirmPasswordErrorText(){
        return getText(confrimPassError);
    }

    public String getWebsiteError(){return getText(websiteError);}

    public String getPhoneError(){return getText(phoneError);}

    public String getExternalIdError(){return getText(externalError);}

    public String getFacebookIdError(){return getText(facebookError);}

    public String getTwitterIdError(){return getText(twitterError);}

    public String getTagError(){return getText(tagError);}

    public String getFormErrorText(){
        pause(300);
        return getText(formErrorMessage);
    }

    public void printResult(String data){
        clearAndType(nameField, data);
        pause(3000);
    }

    public AddUserPage(WebDriver driver){
        super(driver);
    }
}
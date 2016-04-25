import org.testng.Assert;
import org.testng.annotations.*;


public class MainTest extends BaseTest {

    @Parameters({"appLogin", "appPassword", "appName"})
    @BeforeMethod
    public void beforeMethod(String appLogin, String appPassword, String appName){
        openAddUserPage(appLogin, appPassword, appName);
    }

    public void openAddUserPage(String appLogin, String appPassword, String appName) {
        SignInPage signInPage = new SignInPage(driver);

        signInPage.visit();
        MainPage mainPage = signInPage.login(appLogin, appPassword);
        ApplicationPage applicationPage = mainPage.openYourApplication(appName);
        UsersPage usersPage =  applicationPage.openUsersPage();
        usersPage.clickAddNewUserButton();
    }

    @Test(dataProvider="GetValidNewUser", dataProviderClass = UserDataProvider.class)
    public void addNewUser(String name, String mail, String login,
    String password, String confirm, String phone,
    String website, String blobId, String externalId,
    String facebookId, String twitterId, String tag,
    String customData, boolean isAdmin) {

        String userId;

        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.fillTheFields(name, mail, login, password, confirm, phone, website, blobId, externalId, facebookId, twitterId, tag, customData, isAdmin);
        addUserPage.clickAddUserButton();
        Assert.assertTrue(addUserPage.isSuccessMessagePresented());

        userId = addUserPage.getNewUserId();
        UsersPage usersPage = addUserPage.openUsersPage();

        Assert.assertTrue(usersPage.isValuePresented(name));
        Assert.assertTrue(usersPage.isValuePresented(userId));
        Assert.assertTrue(usersPage.isValuePresented(login));
        Assert.assertTrue(usersPage.isValuePresented(externalId));
        Assert.assertTrue(usersPage.isValuePresented(facebookId));
        Assert.assertTrue(usersPage.isValuePresented(twitterId));
        Assert.assertTrue(usersPage.isValuePresented(tag));

        EditUserPage editUserPage = usersPage.openUserPage(userId);

        Assert.assertEquals(editUserPage.getName(), name);
        Assert.assertEquals(editUserPage.getLogin(), login);
        Assert.assertEquals(editUserPage.getMail(), mail);
        Assert.assertEquals(editUserPage.getPhone(), phone);
        Assert.assertEquals(editUserPage.getWebsite(), website);
        Assert.assertEquals(editUserPage.getExternalId(), externalId);
        Assert.assertEquals(editUserPage.getFacebookId(), facebookId);
        Assert.assertEquals(editUserPage.getTwitterId(), twitterId);
        Assert.assertEquals(editUserPage.getCustomData(), customData);
        Assert.assertTrue(editUserPage.isTagPresented(tag));
        Assert.assertTrue(editUserPage.isAdminStatementCorrect(isAdmin));

        //after test data cleaning
        usersPage = editUserPage.openUsersPage();
        usersPage.removeLastUser();
    }

    @Test
    public void checkRequiredFieldValidation(){
        String correctMail = getUniqueValue() + "@mail.com";
        String correctLogin = getUniqueValue();
        String correctPassword = "12345678";
        String incorrectMail = "incorrectmailcom";
        String emptyValue = "";
        String incorrectPassword = "1234567";

        String mailIsEmptyError = "This field required (or login)";
        String loginIsEmptyError = "This field required (or email)";
        String passwordIsEmptyError = "This field is required.";
        String passesDontMatchError = "Please enter the same value again.";
        String mailIsNotCorrectError = "Email expected";
        String passIsNotCorrectError = "Please enter at least 8 characters.";

        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.fillTheFields("", "", "", "", "", "", "", "", "", "", "", "", "", false);
        addUserPage.clickAddUserButton();
        Assert.assertTrue(addUserPage.isLoginErrorPresented());
        Assert.assertTrue(addUserPage.isMailErrorPresented());
        Assert.assertTrue(addUserPage.isPasswordErrorPresented());
        Assert.assertEquals(addUserPage.getLoginErrorText(), loginIsEmptyError);
        Assert.assertEquals(addUserPage.getMailErrorText(), mailIsEmptyError);
        Assert.assertEquals(addUserPage.getPasswordErrorText(), passwordIsEmptyError);

        addUserPage.fillTheFields("", emptyValue, correctLogin, correctPassword, incorrectPassword, "", "", "", "", "", "", "", "", false);
        addUserPage.clickAddUserButton();
        Assert.assertTrue(addUserPage.isConfirmPasswordErrorPresented());
        Assert.assertEquals(addUserPage.getConfirmPasswordErrorText(), passesDontMatchError);

        addUserPage.fillTheFields("", correctMail, emptyValue, incorrectPassword, incorrectPassword, "", "", "", "", "", "", "", "", false);
        addUserPage.clickAddUserButton();
        Assert.assertTrue(addUserPage.isPasswordErrorPresented());
        Assert.assertEquals(addUserPage.getPasswordErrorText(), passIsNotCorrectError);

        addUserPage.fillTheFields("", incorrectMail, emptyValue, correctPassword, correctPassword, "", "", "", "", "", "", "", "", false);
        addUserPage.clickAddUserButton();
        Assert.assertTrue(addUserPage.isMailErrorPresented());
        Assert.assertEquals(addUserPage.getMailErrorText(), mailIsNotCorrectError);
        }

    @Test(dataProvider="GetValidNewUser", dataProviderClass = UserDataProvider.class)
    public void checkCreationUsersWithSameCredentionals(String name, String mail, String login,
                           String password, String confirm, String phone,
                           String website, String blobId, String externalId,
                           String facebookId, String twitterId, String tag,
                           String customData, boolean isAdmin) {

        String mail2 = getUniqueValue() + "@mail.com";
        String login2 = getUniqueValue();
        String externalId2 = getUniqueId();
        String facebookId2 = getUniqueValue();
        String twitterId2 = getUniqueValue();

        String emailIsSameError = "Email has already been taken";
        String loginIsSameError = "Login has already been taken";
        String externalIdIsSameError = "External user id has already been taken";
        String facebookIdIsSameError = "Facebook has already been taken";
        String twitterIdIsSameError = "Twitter has already been taken";

        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.fillTheFields(name, mail, login, password, confirm, phone, website, blobId, externalId, facebookId, twitterId, tag, customData, isAdmin);
        addUserPage.clickAddUserButton();

        UsersPage usersPage = addUserPage.openUsersPage();
        addUserPage = usersPage.clickAddNewUserButton();
        addUserPage.fillTheFields(name, mail, login2, password, confirm, phone, website, blobId, externalId2, facebookId2, twitterId2, tag, customData, isAdmin);
        addUserPage.clickAddUserButton();
        Assert.assertEquals(addUserPage.getFormErrorText(), emailIsSameError);

        addUserPage.fillTheFields(name, mail2, login, password, confirm, phone, website, blobId, externalId2, facebookId2, twitterId2, tag, customData, isAdmin);
        addUserPage.clickAddUserButton();
        Assert.assertEquals(addUserPage.getFormErrorText(), loginIsSameError);

        addUserPage.fillTheFields(name, mail2, login2, password, confirm, phone, website, blobId, externalId, facebookId2, twitterId2, tag, customData, isAdmin);
        addUserPage.clickAddUserButton();
        Assert.assertEquals(addUserPage.getFormErrorText(), externalIdIsSameError);

        addUserPage.fillTheFields(name, mail2, login2, password, confirm, phone, website, blobId, externalId2, facebookId, twitterId2, tag, customData, isAdmin);
        addUserPage.clickAddUserButton();
        Assert.assertEquals(addUserPage.getFormErrorText(), facebookIdIsSameError);

        addUserPage.fillTheFields(name, mail2, login2, password, confirm, phone, website, blobId, externalId2, facebookId2, twitterId, tag, customData, isAdmin);
        addUserPage.clickAddUserButton();
        Assert.assertEquals(addUserPage.getFormErrorText(), twitterIdIsSameError);

        //after test data cleaning
        usersPage = addUserPage.openUsersPage();
        usersPage.removeLastUser();
    }

    @Test
    public void checkNonRequiredFieldsValidation(){

        String mail = getUniqueValue() + "@mail.com";
        String login = getUniqueValue();
        String password = "12345678";
        String confirm = "12345678";

        String shortPhone = "12345";
        String incorrectPhone = "incorrect";
        String incorrectWebsite = "incorrect";
        String incorrectExternalId = "incorrect";
        String incorrectFacebookId = "incorrect";
        String incorrectTwitterId = "incorrect";
        String incorrectTag = "123";

        String incorrectPhoneError = "Bad format.";
        String shortPhoneError = "Please enter at least 6 characters.";
        String incorrectSiteError = "Website address expected";
        String incorrectExternalIdError = "Please enter a valid number.";
        String incorrectFacebookIdError = "Facebook ID expected";
        String incorrectTwitterIdError = "Twitter ID expected";
        String incorrectTagError = "Parameter should contain alphanumeric characters and start with a letter";


        AddUserPage addUserPage = new AddUserPage(driver);
        addUserPage.fillTheFields("", mail, login, password, confirm, incorrectPhone, incorrectWebsite, "", incorrectExternalId, incorrectFacebookId, incorrectTwitterId, incorrectTag, "", false);
        addUserPage.clickAddUserButton();

        Assert.assertEquals(addUserPage.getPhoneError(), incorrectPhoneError);
        Assert.assertEquals(addUserPage.getWebsiteError(), incorrectSiteError);
        Assert.assertEquals(addUserPage.getExternalIdError(), incorrectExternalIdError);
        Assert.assertEquals(addUserPage.getFacebookIdError(), incorrectFacebookIdError);
        Assert.assertEquals(addUserPage.getTwitterIdError(), incorrectTwitterIdError);
        Assert.assertEquals(addUserPage.getTagError(), incorrectTagError);

        addUserPage.fillTheFields("", mail, login, password, confirm, shortPhone, "", "", "", "", "", "", "", false);
        addUserPage.clickAddUserButton();

        Assert.assertEquals(addUserPage.getPhoneError(), shortPhoneError);
    }

    @Test
    public void cancelAddUserForm() {

        AddUserPage addUserPage = new AddUserPage(driver);

        UsersPage usersPage = addUserPage.clickCancelButton();
        Assert.assertTrue(usersPage.isAddNewUserButtonPresented());


    }

}

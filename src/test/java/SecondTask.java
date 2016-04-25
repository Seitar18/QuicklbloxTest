import org.testng.annotations.*;
import org.apache.commons.lang3.StringUtils;


public class SecondTask extends BaseTest {

    @Parameters({"appLogin", "appPassword", "appName"})
    @BeforeMethod
    public void beforeMethod(String appLogin, String appPassword, String appName){
        SignInPage signInPage = new SignInPage(driver);

        signInPage.visit();
        MainPage mainPage = signInPage.login(appLogin, appPassword);
        ApplicationPage applicationPage = mainPage.openYourApplication(appName);
        applicationPage.openUsersPage();
    }

    @Test(dataProvider="GetLoginAndUserData", dataProviderClass = UserDataProvider.class)
    public void searchOrGetID(String searchingLogin, String name, String mail, String login,
                           String password, String confirm, String phone,
                           String website, String blobId, String externalId,
                           String facebookId, String twitterId, String tag,
                           String customData, boolean isAdmin) {

        String searchInfo;

        UsersPage usersPage = new UsersPage(driver);
        usersPage.performSearch(searchingLogin);
        searchInfo = usersPage.getSearchResult();


        if (StringUtils.containsIgnoreCase(searchInfo, "Showing 1 to 1 of 1 entries")) {
            usersPage.printResult("Our founded user id is " + usersPage.getFindedUserId());
        }

        else if(StringUtils.containsIgnoreCase(searchInfo, "Showing 0 to 0 of 0 entries")){
            AddUserPage addUserPage = usersPage.clickAddNewUserButton();
            addUserPage.fillTheFields(name, mail, login, password, confirm, phone, website, blobId, externalId, facebookId, twitterId, tag, customData, isAdmin);
            addUserPage.clickAddUserButton();
            addUserPage.printResult("Our new user id is " + addUserPage.getNewUserId());

            usersPage = addUserPage.openUsersPage();
            usersPage.removeLastUser();
        }
        else {
            usersPage.printResult("More then one user have been found.");
        }
    }

}

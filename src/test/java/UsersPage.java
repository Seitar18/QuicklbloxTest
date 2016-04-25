import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class UsersPage extends BasePage {

    private By addNewUserButton = findByCss("html /deep/ a[href$='users/new']");
    private By lastUserCheckboxLocator = findByCss("html /deep/ tbody tr:last-child input");
    private By removeLink = findByCss("html /deep/ .remove");
    private By searchBox = findByCss("html /deep/ input[type='search']");
    private By searchDataInfo = findByCss("html /deep/ .dataTables_info");
    private By findedUserId = findByCss("html /deep/ tr td a");

    public AddUserPage clickAddNewUserButton(){
        click(addNewUserButton);
        return new AddUserPage(driver);
    }

    public void removeLastUser(){
        click(lastUserCheckboxLocator);
        click(removeLink);
        acceptAlert();
    }

    public boolean isValuePresented(String title){
        return isElementPresented(findByCss("html /deep/ span[title^='" + title + "']"));
    }

    public boolean isAddNewUserButtonPresented(){
        return isElementPresented(addNewUserButton);
    }

    public EditUserPage openUserPage(String userId){
        click(findByCss("html /deep/ span[title^='" + userId + "']"));
        return new EditUserPage(driver);
    }

    public void performSearch(String searchData){
        clearAndType(searchBox, searchData);
        pause(1500);
    }

    public Boolean isPageButtonPresented(){
        return isElementPresented(findByCss("html /deep/  li[class='paginate_button active']"));
    }

    public void printResult(String data){
        clearAndType(searchBox, data);
        pause(3000);
    }


    public String getSearchResult(){return getText(searchDataInfo);}

    public String getFindedUserId(){return getText(findedUserId);}

    public UsersPage(WebDriver driver){
        super(driver);
    }
}
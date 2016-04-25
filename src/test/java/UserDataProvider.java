import org.testng.annotations.DataProvider;

public class UserDataProvider
{
    @DataProvider(name = "GetValidNewUser")
    public static Object[][] newUserDat()
    {
        //I don't know valid data for Blob Id field so this field is empty
        return new Object[][]{{"test Name", MainTest.getUniqueValue() + "@mail.com",
                MainTest.getUniqueValue(), "12345678", "12345678",
                "12345678", "http://www.google.com", "",
                MainTest.getUniqueId(), MainTest.getUniqueValue(), MainTest.getUniqueValue(),
                "www", "123", true}};
    }

    @DataProvider(name = "GetLoginAndUserData")
    public static Object[][] loginAndUserCreatingData()
    {
        return new Object[][]{
                {"QAForTest", "test Name", MainTest.getUniqueValue() + "@mail.com",
                MainTest.getUniqueValue(), "12345678", "12345678",
                "12345678", "http://www.google.com", "",
                MainTest.getUniqueId(), MainTest.getUniqueValue(), MainTest.getUniqueValue(),
                "www", "123", true},

                {MainTest.getUniqueValue(), "test Name", MainTest.getUniqueValue() + "@mail.com",
                        MainTest.getUniqueValue(), "12345678", "12345678",
                        "12345678", "http://www.google.com", "",
                        MainTest.getUniqueId(), MainTest.getUniqueValue(), MainTest.getUniqueValue(),
                        "www", "123", true},

                {"qa1234", "test Name", MainTest.getUniqueValue() + "@mail.com",
                        MainTest.getUniqueValue(), "12345678", "12345678",
                        "12345678", "http://www.google.com", "",
                        MainTest.getUniqueId(), MainTest.getUniqueValue(), MainTest.getUniqueValue(),
                        "www", "123", true}};
    }
}

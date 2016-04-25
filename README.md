# QuicklbloxTest
The 1 and 1a task for QuicklBlox

RUNNING TESTS
Download project and run "gradle init" comand from project root directory
After this run "gradle test" command
After first successful build run tests with "gradle clean test" command

PROJECT SOURCES STRUCTURE
TestSuite runner - TestNG.xml file in the root directory;
Configuration files - Wrappers(with all methods wrappers), BaseTest, 
BasePage(provides PageObjects), UserDataProvider( provides @DataProvider data);
Tests - for 1 task in MainTest file and for 1a task in SecondTask file;
All other file in test directory are Pages;

About 1a task - I printed output data to the fields on the web-page. This are the "search" field on the Users page
and "Full Name" field in the "Add New User" form page.

Environment:
Chrome - 50.0.2661.87 
Windows 7 SP1, 32-bit

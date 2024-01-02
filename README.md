# Android Application Testing

This repository contains test scripts and supporting files for Android application testing, including both UI and API tests. The tests are organized into different categories for efficient management and execution.

## Directory Structure

The test directory is organized as follows:

```test
├───tests
│ ├───android
│ │ ├───pageObject
│ │ │ ├─── ChromePage.java # Page object for Chrome interactions
│ │ │ └─── RegistrationPage.java # Page object for handling user registration
│ │ ├─── LoginTest.java # Test class for login functionality
│ │ └─── LoginTestSuite.java # Suite for executing all login-related tests
│ ├───api
│ │ └─── UserTests.java # API tests for user-related endpoints
│ └───base
│ ├─── BaseAndroidTest.java # Base setup for Android UI tests
│ ├─── BaseFactoryAndroidTest.java # Base setup for using Page Factory in tests
│ └─── UserData.java # Class for handling user data in tests
```

## Test Categories

- **Android Tests**: UI tests for Android using Appium, including page objects for Chrome interactions and user registration.
- **API Tests**: Tests that target the application's API layer, focusing on user-related endpoints.
- **Base Setup**: Base classes for setting up Appium tests with common functionalities like driver initialization.

## Running the Tests

To run the tests, ensure you have the necessary dependencies installed and your Android testing environment set up correctly. Use the following command to execute the tests:

```
mvn test
```
If you want to run on different devices (like IOS) 
```
mvn test -Ddevice=deviceName
```
## Azure DevOps Pipeline
 [Check here for Azure](README_AzureDevOpsPipeline.md)

##  Docker 
 [Check here for Docker](README_Docker.md)

# Project Title

Automated requirements for KhathaBook App (Android Version)

## Getting Started

Tools used: Intellij as IDE
Platform: Android
Version : 8.0
Language: JAVA
framework : TestNG
Build tool: Maven

### Prerequisites

Java 1.8+ version installed
Maven installed
Appium client
Android Studio - for emulator
adb installed

### APK used

khata-book-udhar-bahi-khata-credit-ledger-account.apk

## Running the tests

1. Import the pom.XML as a maven project
2. Use commands: mvn clean followed by mvn install to install the dependencies
3. Invoke the testNG.xml from IDE or
  via terminal: mvn test
4. Start the appium server (either through npm package or desktop client)

xml includes:
class: KhathaBook
Methods: verifyDebitCreditDetails(), verifyCustomerCreatedWithPhoneNumber(), verifyCreationOfExistingCustomer()

### Scenarios and exceptions handled

1.Khatha book supports login using mobilenumber and the otp received on the mobilenumber. (This is out of scope for automation, since have used an emulator, though tried to acheive this using free sms site: http://receivefreesms.com/)

2.Have automated considering the app is installed in the Mobile and user has an account created.

3.Better try-catch mechanism to handle, in case if there are exceptions and Explicitwait mechanism used instead of thread.sleep()

4.Proper asserts and reporting used to provide a detailed context, can be witnessed in testNG Report - have checked in the same.

5.Can be easily invoked via command line.

6.Reports can be found at: {folder}/appium/target/surefire-reports/emailable-report.html and failed screenshots at: appium/{screenshots}

## Authors

Vinayak kaladhar

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

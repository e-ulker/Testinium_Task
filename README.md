test-automation-TestiniumCodeCase,covering UI Testing

Prerequisites: Install Maven v4.0 or above

Concepts of Dependency injection Page Object Model pattern design Common web page interaction methods Commonly used test utility classes

This Project includes Selenium, JUnit , Log4J , RestAssured, Apache Poi libraries

Dependencies: In order to utilise this project you need to have following dependencies in pom.xml and you can find them https://mvnrepository.com/ Selenium-version 3.141.59  , JUnit-Version 4.13.2 , Log4J-version 1.2.17 ,RestAssured-version 5.2.0,  Apache Poi-Version 5.2.2

Prerequite

install maven version 4.0.0 or above.

PS: In case any problem happens while running the tests, please check the jdk version from project settings and choose the proper one or the one installed on your computer.

Reporting
After test finishes, test-output is created under project. Project report can be found under test-output file and can be opened with any browser. 
Reports for each module are written into their respective /target directories after a successful run. UI acceptance tests result in a HTML report for each tests in test-output/ExtentReport.html reports.


# RetirementCalculatorDemo
This application uses TestNG framework to automate the following Pre-retirement calculator application.
URL: https://www.securian.com/insights-tools/retirement-calculator.html

# Framework
TestNG: It is an open-source test automation framework for Java. It is developed on the same lines of JUnit and NUnit.
  
# Code Details
The application tests three different scenarios mentioned in CalculatorDemo.java class:
  - The test 'formSubmissionWithoutSocialBenefitsNoAdjustments' is happy path which tests the calculator without Social benefits and no default adjustments.
  - The test 'formSubmissionWithSocialBenefitsNoAdjustments' tests the calculator with Social benefits but no default adjustments.
  - The test 'formSubmissionWithSocialBenefitsAndAdjustments' tests the calculator with Social benefits and with default adjustments.
  
 The application uses CalculatorConstants.java to store the test data, config properties and other constants.

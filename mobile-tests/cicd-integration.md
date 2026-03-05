# CI/CD Pipeline Integration Report

## 1. Objective

The objective of this task was to integrate automated test execution into a Continuous Integration (CI) pipeline using GitHub Actions.

---

## 2. Pipeline Configuration

A GitHub Actions workflow file was created inside:

.github/workflows/qa.yml

The pipeline performs the following steps:

* Checkout repository
* Install dependencies
* Run Cypress tests
* Run Selenium TestNG tests
* Generate test results

The workflow is triggered on:

* Every push
* Every pull request

---

## 3. Automated Test Coverage in CI

The following test types are executed automatically:

* Web automation tests (Cypress)
* Selenium WebDriver tests (Java + TestNG)
* Mobile browser test flows

---

## 4. Benefits Achieved

* Automatic validation on every commit
* Early defect detection
* Improved code stability
* Continuous test reporting

---

## 5. Result

The CI pipeline successfully executes all automated tests without manual intervention.
The pipeline ensures continuous testing and quality validation for the project.

---

## 6. Conclusion

CI/CD integration has been successfully implemented as required in the QA Capstone Project.

---
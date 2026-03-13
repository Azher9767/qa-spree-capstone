# QA Capstone Project – Part 2

## Advanced Automation and Integration

This repository contains the **Part 2 implementation of the QA Capstone Project**, focusing on advanced test automation, mobile testing strategies, and CI/CD integration for an e-commerce platform.

The application under test is **Spree Commerce**, which was configured and manually tested in **Part 1**.
Part 2 extends the testing coverage by introducing **mobile testing workflows, Selenium automation, parallel execution strategies, CI/CD pipeline integration, security testing, and performance testing**.

---

# Project Overview

The goal of this phase is to extend the QA foundation established in Part 1 by implementing advanced testing techniques. This includes:

* Testing the application on mobile devices
* Automating complex workflows using Selenium WebDriver
* Executing tests across different environments
* Integrating automated tests into a CI/CD pipeline
* Validating application security through automated security tests
* Measuring application performance under load using JMeter

The project demonstrates how automated testing can improve product quality, reduce manual effort, and ensure reliability across multiple environments.

---

# Mobile Testing Strategy

Mobile testing is performed using the **mobile browser environment** rather than a native application.

The Spree storefront is tested in mobile view using **Chrome mobile emulation, Selenium WebDriver, and Appium**.

Key workflows validated:

* User authentication (login)
* Product browsing and search
* Product navigation
* Adding products to cart
* Checkout navigation

The goal is to ensure the storefront remains **functional and responsive across different mobile screen sizes and devices**.

Additional documentation:

```
device-matrix.md
network-testing.md
```

---

# Device Matrix

A device matrix defines the devices, operating systems, and browsers used during testing.

| Device             | OS         | Browser |
| ------------------ | ---------- | ------- |
| iPhone 13          | iOS 16     | Safari  |
| Samsung Galaxy S21 | Android 13 | Chrome  |
| Google Pixel 7     | Android 14 | Chrome  |
| iPad Air           | iPadOS     | Safari  |

Detailed coverage is available in:

```
device-matrix.md
```

---

# Network Testing

Mobile users often experience different network conditions.
To simulate real-world environments, tests are executed under different network speeds.

Network scenarios tested:

* WiFi
* 4G
* 3G
* Slow network simulation

These tests ensure the application behaves reliably even under slower connections.

Documentation:

```
network-testing.md
```

---

# Selenium Automation

Selenium WebDriver is used to automate complex browser workflows that require multiple steps and dynamic interactions.

Automation scenarios include:

* User login automation
* Product search
* Product navigation
* Add to cart workflow
* Checkout flow
* File upload

Tests are written in **Java using Selenium WebDriver and TestNG**.

Implementation details:

```
selenium-automation.md
```

---

# Parallel Test Execution

Parallel execution improves test execution speed and scalability.

Benefits include:

* Faster feedback cycles
* Increased test coverage
* Reduced CI pipeline time

Implementation documentation:

```
parallel-execution.md
```

---

# Security Testing

Security testing is performed using **Selenium WebDriver (Java)** to validate that the Spree application is protected against common web vulnerabilities.

Security tests are located in a dedicated `security-tests/` folder at the project root, separate from mobile tests.

### What is tested and why

| Test | Purpose |
| ---- | ------- |
| SQL Injection | Ensures login and search fields do not expose database errors or allow unauthorized access when malicious SQL is entered |
| XSS (Cross-Site Scripting) | Verifies that user input is properly escaped and malicious scripts cannot be executed in the browser |
| Authentication Security | Checks that admin and account pages are inaccessible without login, multiple failed logins are handled correctly, and sessions are properly invalidated after logout |
| Sensitive Data Exposure | Confirms that password fields are masked, error pages do not reveal stack traces, and sensitive keys are not exposed in page source |

### Running security tests

```bash
cd security-tests
mvn test
```

---

# Performance Testing

Performance testing is done using **Apache JMeter** to measure how the Spree application behaves under different levels of traffic.

Performance test configurations are located in the `performance-tests/` folder at the project root.

### What is tested and why

| Test | File | Purpose |
| ---- | ---- | ------- |
| Load Test | `spree-load-test.jmx` | Simulates normal expected traffic (10 users, 5 loops) to verify the application performs well under regular usage |
| Stress Test | `spree-stress-test.jmx` | Pushes the application beyond normal traffic (50 users, 10 loops) to find the breaking point and observe failure behavior |

### Pages covered

* Home page `/`
* Products page `/products`
* Product search `/products?keywords=shirt`
* Login page `/user/sign_in`
* Cart page `/cart`

### Running performance tests

**GUI Mode (recommended for viewing results):**

```bash
jmeter -t performance-tests/spree-load-test.jmx
jmeter -t performance-tests/spree-stress-test.jmx
```

**Command Line Mode (recommended for CI):**

```bash
jmeter -n -t performance-tests/spree-load-test.jmx -l load-test-results.jtl
jmeter -n -t performance-tests/spree-stress-test.jmx -l stress-test-results.jtl
```

**Generate HTML report from results:**

```bash
jmeter -g load-test-results.jtl -o load-test-report/
```

---

# CI/CD Integration

Automated tests are integrated into a **CI/CD pipeline using GitHub Actions**.

Each code commit triggers automated workflows that run the test suite and generate results, ensuring continuous validation of the application.

Pipeline configuration:

```
.github/workflows/qa.yml
```

Detailed documentation:

```
cicd-integration.md
```

---

# Project Structure

```
spree/
│
├── mobile-tests/
│   ├── device-matrix.md
│   ├── network-testing.md
│   ├── selenium-automation.md
│   ├── parallel-execution.md
│   ├── cicd-integration.md
│   ├── README.md
│   │
│   ├── selenium-tests/
│   │   ├── BaseTest.java
│   │   ├── LoginTest.java
│   │   ├── ProductSearchTest.java
│   │   ├── AddToCartTest.java
│   │   ├── CheckoutFormTest.java
│   │   └── FileUploadTest.java
│   │
│   └── test-config/
│       └── testng.xml
│
├── security-tests/
│   ├── pom.xml
│   ├── testng.xml
│   └── src/test/java/com/spree/security/
│       ├── BaseTest.java
│       ├── SQLInjectionTest.java
│       ├── XSSTest.java
│       ├── AuthSecurityTest.java
│       └── SensitiveDataTest.java
│
└── performance-tests/
    ├── spree-load-test.jmx
    ├── spree-stress-test.jmx
    └── README.md
```

---

# Prerequisites

Before running the automation tests, ensure the following tools are installed.

Required tools:

* Java JDK (11 or higher)
* Maven
* Node.js
* Android Studio
* Android Emulator
* Appium Server
* Selenium WebDriver
* Google Chrome Browser
* Apache JMeter 5.6+

---

# Installation Guide

## Linux Setup

### Install Java

```bash
sudo apt update
sudo apt install openjdk-11-jdk
java -version
```

### Install Maven

```bash
sudo apt install maven
mvn -version
```

### Install Node.js

```bash
sudo apt install nodejs npm
node -v
npm -v
```

### Install Appium

```bash
npm install -g appium
appium -v
```

### Optional: Appium Doctor

```bash
npm install -g appium-doctor
appium-doctor
```

### Install JMeter

```bash
# Download JMeter
wget https://downloads.apache.org/jmeter/binaries/apache-jmeter-5.6.3.tgz

# Extract
tar -xf apache-jmeter-5.6.3.tgz

# Add to PATH (add this line to ~/.bashrc or ~/.zshrc)
export PATH=$PATH:/path/to/apache-jmeter-5.6.3/bin

# Verify
jmeter --version
```

---

## Windows Setup

### Install Java

1. Download JDK from the official OpenJDK or Oracle website
2. Install and configure the `JAVA_HOME` environment variable

Verify:

```bash
java -version
```

---

### Install Maven

1. Download Maven
2. Extract and add the Maven `bin` folder to the system PATH

Verify:

```bash
mvn -version
```

---

### Install Node.js

Download Node.js from the official website.

Verify:

```bash
node -v
npm -v
```

---

### Install Appium

```bash
npm install -g appium
appium -v
```

---

### Install JMeter

1. Download JMeter from [https://jmeter.apache.org/download_jmeter.cgi](https://jmeter.apache.org/download_jmeter.cgi) - download the **Binaries** `.zip` file
2. Extract the zip file
3. Add the `bin` folder to your system PATH

Verify:

```bash
jmeter --version
```

---

# Android Emulator Setup

1. Install **Android Studio**
2. Open **Device Manager**
3. Create a new **Android Virtual Device (AVD)**
4. Select a device (e.g., Pixel 6)
5. Download an Android system image
6. Launch the emulator

Verify emulator connection:

```bash
adb devices
```

Expected output example:

```
List of devices attached
emulator-5554 device
```

---

# Running the Spree Application

Since the tests interact with the **Spree e-commerce application**, the Rails server must be running before executing automation tests.

Navigate to the Spree project directory:

```bash
cd spree
bundle install
bin/rails db:setup
bin/rails server
```

The application will run at:

```
http://localhost:3000
```

Verify the storefront is accessible before executing tests.

---

# Accessing Spree from Android Emulator

Android emulators cannot access `localhost` directly.

Instead, they use a special loopback address.

| Host Machine | Emulator Access |
| ------------ | --------------- |
| localhost    | 10.0.2.2        |
| local server | 10.0.2.2:3000   |

Therefore tests should access the application using:

```
http://10.0.2.2:3000
```

This allows the emulator to communicate with the Rails server running on the host machine.

---

# Starting Required Services

### Start Android Emulator

```bash
emulator -avd Pixel_6
```

Verify:

```bash
adb devices
```

---

### Start Appium Server

```bash
appium
```

Default server configuration:

| Setting | Value     |
| ------- | --------- |
| Host    | 127.0.0.1 |
| Port    | 4723      |

Server endpoint:

```
http://127.0.0.1:4723
```

---

# Running Selenium Tests

Navigate to the mobile tests directory:

```bash
cd mobile-tests
```

Run tests:

```bash
mvn clean test
```

Run TestNG suite:

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

---

# Running Security Tests

Navigate to the security tests directory:

```bash
cd security-tests
```

Run all security tests:

```bash
mvn test
```

---

# Running Performance Tests

Navigate to the performance tests directory:

```bash
cd performance-tests
```

Run load test:

```bash
jmeter -n -t spree-load-test.jmx -l load-test-results.jtl
```

Run stress test:

```bash
jmeter -n -t spree-stress-test.jmx -l stress-test-results.jtl
```

---

# Mobile Test Execution Workflow

1. Start the **Spree Rails server**
2. Start the **Android emulator**
3. Verify emulator connectivity (`adb devices`)
4. Start the **Appium server**
5. Execute Selenium tests using Maven

---

# Test Architecture

| Component          | Purpose                           |
| ------------------ | --------------------------------- |
| Spree Commerce     | E-commerce application under test |
| Selenium WebDriver | Browser automation                |
| Appium             | Mobile automation framework       |
| Android Emulator   | Simulated mobile device           |
| TestNG             | Test execution framework          |
| Maven              | Dependency management             |
| GitHub Actions     | CI/CD automation                  |
| Apache JMeter      | Performance testing               |

---

# QA Outcomes

This project demonstrates the implementation of:

* Mobile web testing strategy
* Selenium automation for critical workflows
* Parallel test execution
* CI/CD integration with GitHub Actions
* Security testing for common web vulnerabilities
* Performance testing under load and stress conditions
* Structured QA documentation

Together with **manual testing performed in Part 1**, this automation framework provides comprehensive validation of the Spree e-commerce application.

---

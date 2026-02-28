# QA Capstone – Planning & Foundation

## Application: Spree

---

# Project Overview

This repository contains the complete Quality Assurance implementation for **Capstone Part 1: Planning and Foundation**.

The selected application is **Spree (E-commerce platform)**.
The objective of this capstone is to establish a strong QA foundation across:

* Web environment
* Mobile responsiveness
* API validation
* Manual testing
* Automated testing
* Performance & accessibility validation

All required deliverables have been completed and documented in this repository.

---

# 🚀 Application Setup

## 🔹 Selected Application

Spree E-commerce Platform

## 🔹 Installation (Automated Method)

```bash
bash -c "$(curl -fsSL https://spreecommerce.org/install)"
```

## 🔹 Manual Installation (If Required)

Official Repository:
[https://github.com/spree/spree](https://github.com/spree/spree)

## 🔹 Environment Configuration

| Component | Configuration             |
| --------- | ------------------------- |
| Web       | Localhost (Rails Server)  |
| Mobile    | Browser Responsive Mode   |
| API       | Integrated within Web App |
| OS        | Linux                     |
| Browser   | Chrome, Firefox, Edge     |

Application was verified to be running successfully before initiating QA activities.

---

# 📊 QA Strategy

A detailed QA Strategy document is included:

📄 `QA_Strategy.md`

It covers:

* Test Objectives
* Test Scope
* Risk Analysis
* Resource Planning
* Test Environments
* Functional Testing Strategy
* Performance Testing Strategy
* Security Testing Considerations
* Automation Strategy

---

# 🧾 Test Plan

A structured test plan was created covering:

* Functional testing
* Performance testing
* Security considerations
* Risk assessment
* Test scope & exclusions
* Timelines

📄 `Manual_Test_Cases.md`

---

# 🧪 Manual Testing

## 🔹 Test Case Coverage

Core e-commerce workflows were tested:

* User Registration
* User Login
* Product Search
* Add to Cart
* Checkout Flow
* Negative Login Scenario

## 🔹 Execution Report

📄 `Manual_Test_Execution_Report.md`

Includes:

* Execution summary
* Detailed results
* Cross-browser validation
* Responsive validation
* Final conclusion

No functional or UI defects were identified during manual execution.

---

# 🌐 Cross-Browser Compatibility

Testing performed on:

* Google Chrome
* Mozilla Firefox
* Microsoft Edge

Results:

* UI rendering consistent
* Core workflows validated
* No compatibility issues observed

---

# 📱 Responsive Testing

Responsive validation performed using browser developer tools.

Validated for:

* Mobile viewport
* Tablet viewport

All layouts adjusted correctly and remained functional.

---

# 🤖 Automated Testing (Cypress)

End-to-End automation implemented using:

## 🛠 Cypress

### Covered Flows:

* Login
* Product Search
* Cart Management
* Checkout
* Admin Flow
* Mobile View Tests
* API Validation
* Accessibility Testing
* Performance Validation

Cypress test files:

```
cypress/e2e/
├── login.cy.js
├── productSearch.cy.js
├── cart.cy.js
├── checkout.cy.js
├── admin.cy.js
├── api.cy.js
├── accessibility.cy.js
├── mobile.cy.js
├── performance.cy.js
```

---

# 🔌 API Testing (Within Cypress)

Backend verification implemented using Cypress API testing.

* API response validation
* Status code checks
* Data integrity assertions

Ensures frontend & backend consistency.

---

# ♿ Accessibility Testing

Accessibility validation implemented using `cypress-axe`.

* Homepage accessibility scan
* WCAG violation logging
* No major accessibility violations detected

---

# ⚡ Performance Testing

Basic performance validation implemented using Cypress.

* Page load time measurement
* Threshold-based assertion (3 seconds max)
* Lighthouse-style performance scoring (if enabled)

Ensures acceptable frontend performance behavior.

---

# 🛡 Security Considerations

While deep penetration testing is outside the capstone scope, the following were reviewed:

* Input validation
* Error message handling
* Authentication behavior
* Session management behavior

No observable vulnerabilities during functional validation.

---

# 📂 Repository Structure

```
|── app
|    └── assets
|          └── images
|                 └── checkout.png
├── QA_Strategy.md
├── Manual_Test_Cases.md
├── Manual_Test_Execution_Result.md
├── cypress/
│   └── e2e/
│       ├── login.cy.js
│       ├── checkout.cy.js
│       ├── api.cy.js
│       ├── accessibility.cy.js
│       |── performance.cy.js
|       |── cart.cy.js
|       |── mobile.cy.js
|       |── admin.cy.js
|       |── productSearch.cy.js
└── QA_CAPSTONE_README.md
```

---

# ✅ Assignment Requirement Coverage Checklist

| Requirement                                 | Status                |
| ------------------------------------------- | --------------------- |
| Application Selection & Setup               | ✅ Completed           |
| Environment Configuration                   | ✅ Completed           |
| Detailed QA Strategy                        | ✅ Completed           |
| Test Plan (Functional/Performance/Security) | ✅ Completed           |
| Manual Test Cases                           | ✅ Completed           |
| Manual Execution                            | ✅ Completed           |
| Cypress E2E Automation                      | ✅ Completed           |
| API Integration Testing                     | ✅ Completed           |
| Cross-Browser Validation                    | ✅ Completed           |
| Responsive Testing                          | ✅ Completed           |
| Accessibility Testing                       | ✅ Completed           |
| Performance Testing                         | ✅ Completed           |
| GitHub Documentation                        | ✅ Completed           |

---

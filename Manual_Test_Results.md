# Manual Test Execution Report

## Project Information

| Field | Details |
|-------|----------|
| Project Name | Spree E-commerce QA Capstone |
| Application URL | http://localhost:3000 |
| Environment | Local Development |
| Browser | Google Chrome (Latest Version) |
| OS | Linux |
| Executed By | QA Engineer |

---

## Test Scope

Manual testing was performed on the core user journeys of the application:

- User Registration  
- User Login  
- Product Search  
- Add to Cart  
- Checkout Flow  
- Invalid Login Scenario  

---

## Execution Summary

| Metric | Count |
|--------|--------|
| Total Test Cases Executed | 6 |
| Passed | 6 |
| Failed | 0 |
| Blocked | 0 |
| Pass Percentage | 100% |

---

## Detailed Test Execution Results

| Test Case ID | Test Scenario | Steps Summary | Expected Result | Actual Result | Status |
|--------------|--------------|--------------|----------------|---------------|--------|
| TC-01 | User Registration | Navigate to sign up page, enter valid details, submit form | User account should be created successfully | Account created and redirected to homepage | Pass |
| TC-02 | User Login | Navigate to login page, enter valid credentials, click login | User should login successfully | User logged in and homepage displayed | Pass |
| TC-03 | Product Search | Enter product keyword in search bar and press enter | Relevant products should appear | Matching products displayed correctly | Pass |
| TC-04 | Add to Cart | Open product page, click Add to Cart, open cart | Product should appear in cart | Product visible in cart with correct details | Pass |
| TC-05 | Checkout Flow | Add product to cart, proceed to checkout, fill details, place order | Order should be placed successfully | Order confirmation page displayed | Pass |
| TC-06 | Invalid Login | Enter incorrect password and submit | Error message should be shown | Proper error message displayed | Pass |

---

## Observations

- All core functionalities operated as expected.
- No functional defects were identified.
- Error handling behaved correctly in negative scenarios.
- UI elements rendered correctly during manual verification.
- Checkout process completed without interruption.

---

## Conclusion

Manual execution of all critical e-commerce workflows was completed successfully.  
No defects (critical, major, or minor) were identified during testing.  
The application is stable and ready for automated, performance, and cross-browser validation.
# Mobile Device Matrix Testing Report

## 1. Objective

The objective of this testing was to verify that the Spree e-commerce application works correctly across different mobile devices, operating systems, and screen sizes using mobile browser testing.

The focus was on:

* User authentication
* Product search
* Product details page
* Add to cart
* Checkout flow
* Responsive layout behavior

---

## 2. Testing Approach

Since this project uses mobile browser testing (not native app testing), the following approach was used:

* Google Chrome DevTools device emulation
* Real mobile viewport simulation
* Manual execution of critical user flows
* Verification of UI responsiveness and layout stability

---

## 3. Device Matrix

| Device Type | Device Model       | OS Version | Browser            | Screen Size | Status   |
| ----------- | ------------------ | ---------- | ------------------ | ----------- | -------- |
| Android     | Pixel 5            | Android 13 | Chrome             | 393 x 851   | ✅ Passed |
| Android     | Samsung Galaxy S20 | Android 12 | Chrome             | 360 x 800   | ✅ Passed |
| iOS         | iPhone 12          | iOS 16     | Safari (simulated) | 390 x 844   | ✅ Passed |
| iOS         | iPhone SE          | iOS 15     | Safari (simulated) | 375 x 667   | ✅ Passed |
| Tablet      | iPad Air           | iPadOS 16  | Safari (simulated) | 820 x 1180  | ✅ Passed |

---

## 4. Test Scenarios Executed

The following critical workflows were tested on each device:

### 4.1 User Authentication

* Login with valid credentials
* Login with invalid credentials
* Logout functionality

Result: Login and logout worked correctly on all devices.

---

### 4.2 Product Search

* Search using keyword
* Open product detail page
* Verify product image and price display

Result: Search functionality worked correctly. No layout break observed.

---

### 4.3 Add to Cart

* Select product
* Add to cart
* Verify cart icon update
* Open cart page

Result: Cart updated correctly on all devices.

---

### 4.4 Checkout Flow

* Proceed to checkout
* Fill shipping details
* Navigate between steps

Result: Multi-step checkout worked properly without UI overlap or broken elements.

---

## 5. Responsive UI Verification

The following were validated:

* Navigation menu collapses correctly
* Buttons are clickable and properly aligned
* Images scale without distortion
* No horizontal scroll issues
* Forms are usable without zooming

Result: The application is responsive and mobile-friendly across tested devices.

---

## 6. Observations

* Minor increase in load time on smaller screen devices.
* No functional defects found.
* No major UI misalignment observed.
* Application maintains layout consistency across screen sizes.

---

## 7. Conclusion

The Spree e-commerce application is responsive and stable across the defined mobile device matrix.
All critical user workflows performed successfully across Android and iOS simulated environments.
The application meets mobile compatibility requirements defined in Part 2 of the QA Capstone Project.

---

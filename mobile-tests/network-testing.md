# Network Condition Testing Report

## 1. Objective

The objective of this testing was to validate the reliability and performance of the Spree e-commerce application under different network conditions such as 3G, 4G, and Wi-Fi.

---

## 2. Testing Approach

Network throttling was simulated using Google Chrome DevTools.

The following network profiles were tested:

* Slow 3G
* Fast 3G
* 4G
* No throttling (Wi-Fi simulation)

Critical user workflows were executed under each condition.

---

## 3. Test Scenarios Executed

The following flows were tested:

* Application homepage load
* User login
* Product search
* Product detail page load
* Add to cart
* Checkout page navigation

---

## 4. Observations

### Slow 3G

* Homepage load time increased significantly.
* Product images loaded slowly.
* No functional failure observed.
* UI remained stable.

### Fast 3G

* Slight delay in image rendering.
* Login and search worked correctly.
* No broken layout issues.

### 4G

* Smooth navigation.
* Minor loading delay.
* All workflows successful.

### Wi-Fi (No Throttling)

* Fastest performance.
* No delays.
* All pages loaded instantly.

---

## 5. Result

The application remained functional and stable under all simulated network conditions, but some sometimes issues may of our system machine.
No crashes, timeout errors, or broken UI elements were observed.
---

## 6. Conclusion

The Spree application demonstrates acceptable performance and reliability across varying network conditions including low bandwidth environments.
This satisfies the network simulation requirement defined in Part 2 of the QA Capstone Project.

---
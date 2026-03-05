# Test Cases

This document lists the functional test cases used to validate the core user flows of the Spree storefront application.

The goal of these test cases is to ensure that key shopping features work correctly across devices and environments.

---

## Test Case Table

| Test ID | Test Name | Steps | Expected Result | Priority |
|-------|-----------|------|----------------|---------|
| TC-01 | Verify storefront homepage loads | Open browser → Navigate to `http://localhost:3000` | Homepage loads successfully and products are visible | High |
| TC-02 | Verify product page navigation | Open homepage → Click any product | Product detail page opens with product information | High |
| TC-03 | Verify product search | Open homepage → Enter keyword in search bar → Press search | Relevant products appear in search results | Medium |
| TC-04 | Verify add to cart | Open product page → Click **Add to Cart** | Product is added to the shopping cart | High |
| TC-05 | Verify cart page | Add product to cart → Open cart page | Cart displays selected products with quantity and price | High |
| TC-06 | Verify continue shopping | Open cart page → Click **Continue Shopping** | User is redirected back to product listing page | Medium |

---

## Test Coverage Summary

The above test cases validate the most important user workflows of the Spree storefront application:

- Storefront homepage accessibility
- Product browsing and navigation
- Search functionality
- Add to cart functionality
- Cart validation

These tests ensure the core e-commerce shopping flow works correctly before automation tests are executed.
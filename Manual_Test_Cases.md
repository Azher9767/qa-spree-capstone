# Manual Test Cases - Spree Capstone Project
**Project:** Spree E-commerce  
**Student:** Rafiullah

---

## **1. User Management**

| Test Case ID | Title | Description | Steps | Expected Result | Platform |
|--------------|-------|------------|-------|----------------|----------|
| TC001 | User Registration | Verify user can register with valid email and password | 1. Navigate to `/signup` <br> 2. Fill out registration form (name, email, password, confirm password) <br> 3. Click "Sign Up" | User account is created, confirmation email sent (if enabled), user logged in and redirected to homepage | Web, Mobile |
| TC002 | User Login | Verify login with valid credentials | 1. Navigate to `/login` <br> 2. Enter registered email and password <br> 3. Click "Login" | User successfully logged in and redirected to homepage | Web, Mobile |
| TC003 | User Logout | Verify logout functionality | 1. Click "Logout" button/link | User session ends, redirected to homepage/login page | Web, Mobile |
| TC004 | Password Reset | Verify password reset via email | 1. Navigate to `/password/reset` <br> 2. Enter registered email <br> 3. Submit request <br> 4. Click reset link in email (mock if needed) | Password reset page opens, user can set new password and login | Web, Mobile |

---

## **2. Product Browsing & Search**

| Test Case ID | Title | Description | Steps | Expected Result | Platform |
|--------------|-------|------------|-------|----------------|----------|
| TC005 | Product Search | Verify search returns correct results | 1. Enter product keyword in search bar <br> 2. Click search icon | Products matching the keyword are displayed | Web, Mobile |
| TC006 | Product Filter | Verify filtering by category, price, or attributes | 1. Go to category page <br> 2. Apply filters (e.g., price range, brand) | Product list updates according to selected filters | Web, Mobile |
| TC007 | Product Details | Verify product details page | 1. Click on a product <br> 2. View product images, description, price, availability | Product details displayed correctly, images load, price matches | Web, Mobile |

---

## **3. Shopping Cart & Checkout**

| Test Case ID | Title | Description | Steps | Expected Result | Platform |
|--------------|-------|------------|-------|----------------|----------|
| TC008 | Add to Cart | Verify adding products to cart | 1. Select a product <br> 2. Click "Add to Cart" | Product added to cart, cart count updates | Web, Mobile |
| TC009 | Remove from Cart | Verify removing products from cart | 1. Go to cart page <br> 2. Click "Remove" on a product | Product removed, cart count updates | Web, Mobile |
| TC010 | Update Quantity | Verify changing quantity of products in cart | 1. Go to cart page <br> 2. Change quantity and click "Update" | Cart updated with new quantity and subtotal reflects changes | Web, Mobile |
| TC011 | Checkout | Verify complete checkout workflow | 1. Go to cart <br> 2. Click "Checkout" <br> 3. Fill shipping and payment info (mock) <br> 4. Submit order | Order confirmation page displayed, order saved in admin backend | Web, Mobile |

---

## **4. Admin Functionality**

| Test Case ID | Title | Description | Steps | Expected Result | Platform |
|--------------|-------|------------|-------|----------------|----------|
| TC012 | Admin Login | Verify admin login access | 1. Navigate to `/admin` <br> 2. Enter admin credentials <br> 3. Click "Login" | Admin dashboard displayed | Web |
| TC013 | Add Product | Verify admin can create new product | 1. Login as admin <br> 2. Go to `/admin/products/new` <br> 3. Fill product info (name, price, stock) <br> 4. Save product | Product appears in product list and store | Web |
| TC014 | Edit Product | Verify admin can edit existing product | 1. Go to `/admin/products` <br> 2. Select a product <br> 3. Update details and save | Product details updated in admin and store | Web |
| TC015 | Manage Orders | Verify admin can view & update orders | 1. Go to `/admin/orders` <br> 2. Select an order <br> 3. Update status (e.g., shipped) | Order status updated correctly | Web |

---

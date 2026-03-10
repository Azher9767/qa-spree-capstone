# Performance Testing - Spree Commerce

JMeter performance tests for the Spree e-commerce application.

---

## What is Performance Testing and Why

Performance testing checks how the application behaves under different levels of traffic.
It helps identify slow responses, bottlenecks, and breaking points before real users encounter them.

Two types of tests are included:

| Test | File | Purpose |
| ---- | ---- | ------- |
| Load Test | `spree-load-test.jmx` | Simulates normal expected traffic (10 users, 5 loops) to verify the app performs well under regular usage |
| Stress Test | `spree-stress-test.jmx` | Pushes the app beyond normal traffic (50 users, 10 loops) to find the breaking point and observe failure behavior |

---

## Pages Tested

Both test plans cover the core user flows:

- Home page `/`
- Products page `/products`
- Product search `/products?keywords=shirt`
- Login page `/user/sign_in`
- Cart page `/cart`

---

## Prerequisites

- [Apache JMeter 5.6+](https://jmeter.apache.org/download_jmeter.cgi)
- Spree server running at `http://localhost:3000`

---

## Running the Tests

### GUI Mode (recommended for first run)

```bash
jmeter -t spree-load-test.jmx
jmeter -t spree-stress-test.jmx
```

### Command Line Mode (recommended for CI)

```bash
jmeter -n -t spree-load-test.jmx -l load-test-results.jtl
jmeter -n -t spree-stress-test.jmx -l stress-test-results.jtl
```

---

## Test Configuration

| Setting | Load Test | Stress Test |
| ------- | --------- | ----------- |
| Users (Threads) | 10 | 50 |
| Ramp-up Time | 30s | 60s |
| Loops | 5 | 10 |
| Think Time | 1s between requests | None |

---

## Results

Results are saved as `.jtl` files and can be viewed in JMeter's GUI or converted to an HTML report:

```bash
jmeter -g load-test-results.jtl -o load-test-report/
```
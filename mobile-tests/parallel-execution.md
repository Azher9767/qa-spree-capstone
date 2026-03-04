# Parallel Test Execution Report

## 1. Objective

The objective of this task was to optimize test execution time by implementing parallel execution using TestNG.

---

## 2. Implementation

Parallel execution was configured in the `testng.xml` file using the following configuration:

* Parallel mode: tests
* Thread count: 2
* Multiple test classes executed simultaneously

---

## 3. Execution Comparison

### Before Parallel Execution

* Tests executed sequentially.
* Total execution time was higher.

### After Parallel Execution

* Multiple test cases executed simultaneously.
* Reduced overall execution time.
* Improved efficiency.

---

## 4. Observations

* Tests ran successfully in parallel.
* No test dependency conflicts observed.
* Execution logs confirmed concurrent thread usage.

---

## 5. Conclusion

Parallel execution was successfully implemented using TestNG.
This improved test efficiency and fulfills the parallel automation requirement

---
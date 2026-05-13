# Comparison Report: Human-Written vs. AI-Assisted Test Automation

**Author:** Tamara Stojanoska  
**Date:** May 2026  
**Environment:** Mac OS X (aarch64), Selenium 4.43.0, PrestaShop Demo  

---

## 1. Feature Comparison Table

| Feature | Automated AI (TC29, 30, 31) | Manual/Refactored (TC32) |
| :--- | :--- | :--- |
| **Logic Flow** | Procedural with repetitive frame/scroll patterns. Each test handles complex DOM interactions inline. | Streamlined and abstracted. Delegates to page object methods for state management. |
| **Stability** | **Moderate.** Requires inline `Thread.sleep()` and try-catch blocks for frame context handling. | **High.** Uses page object helper methods with built-in wait mechanisms. |
| **Maintainability** | **Low.** Hardcoded product selectors and element locators scattered throughout test logic. | **High.** Uses `ProductPage` helper methods (`clickIncrement()`, `getStockQuantity()`, etc.) for decoupling. |
| **Code Density** | High volume of code with verbose DOM manipulation and JavaScript execution. | Lean and readable. Focuses on business logic rather than implementation details. |
| **Resilience** | Brittle. Frame switches are followed by hard sleeps and context reset attempts. | Robust. Page object abstraction handles context switching internally. |

### Detailed Metric: Accuracy vs. Structure

| Metric | AI-Assisted (Automated) | Human-Written (Refactored) |
| :--- | :--- | :--- |
| **Logic Accuracy** | High for sequential workflows (navigation, submission). | High for state-driven validation (quantity changes, stock info). |
| **Locator Accuracy** | Excellent (CSS selectors well-identified). | Good (Selectors abstracted in `ProductPage` class). |
| **Code Structure** | Procedural (Direct Selenium calls mixed with business logic). | Modular (All DOM interactions wrapped in `ProductPage` methods). |
| **Scalability** | Poor (Adding new product tests requires duplicating navigation patterns). | Excellent (New tests reuse existing page object methods). |

---

## 2. Qualitative Analysis

### Efficiency & Speed
* **AI-Assisted:** Excellent for rapid script generation of complex workflows (e.g., TC31's login + review submission). Quickly identifies CSS selectors and generates traversal logic for multi-step interactions.
* **Human-Written:** Initially slower because the human designs abstraction layers. However, this foundation in TC32 makes writing subsequent product detail tests significantly faster and more maintainable.

### Stability (The Frame Context & Sleep Challenge)
* **AI-Assisted:** AI generates hardcoded `Thread.sleep()` calls (1500ms, 4000ms) followed by frame switching. While functional, this approach is fragile on Mac Silicon where timing expectations differ. Each test has independent frame management logic.
* **Human-Written:** Human-written test relies on page object methods that internally manage context. By encapsulating frame handling in `ProductPage.openProductDetails()` and similar methods, the manual test achieves better predictability and maintainability.

### Maintainability
* **AI-Assisted:** If product selectors change (e.g., the Hummingbird t-shirt image locator), all three tests (TC29, TC30, TC31) need updates since selectors are hardcoded inline.
* **Human-Written:** TC32 uses abstracted methods like `getStockQuantity()` and `clickIncrement()`. If the underlying DOM changes, only the `ProductPage` class needs a fix.

### Code Clarity
* **AI-Assisted:** Verbose with frequent `WebDriverWait`, `ExpectedConditions`, and `JavascriptExecutor` calls mixed into test logic.
* **Human-Written:** Clean and readable. The test method reads like a business scenario: "Open store frame → Click product → Verify stock → Increment quantity → Decrement quantity → Verify final state."

---

## 3. Time Estimation (Per Test Case)

| Phase | Human-Written (Manual) | AI-Assisted |
| :--- | :--- | :--- |
| **Initial Scripting** | 60 minutes | 15 minutes |
| **Debugging/Stabilization** | 10 minutes | 45 minutes (Iterative) |
| **Refactoring for Reuse** | 25 minutes | N/A |
| **Total Effort** | **~95 minutes** | **~60 minutes** |

**Analysis:** AI generates code 37% faster initially, but human intervention is necessary for handling Mac Silicon frame switching and building reusable page object abstractions.

---

## 4. Final Verdict: Hybrid Approach

To achieve optimal results in QA Automation for Product Details testing:

1. **Use AI for:** 
   - Generating initial locators and complex multi-step workflows (like TC31)
   - Rapidly scripting comprehensive validation logic

2. **Use Human for:**
   - Designing the `ProductPage` abstraction layer (critical for maintainability)
   - Refactoring repetitive frame/scroll patterns into reusable methods
   - Building constants and hardcoded values into centralized locations
   - Testing on Mac Silicon to ensure timing expectations are realistic

---


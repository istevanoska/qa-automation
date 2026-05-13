# Comparison Report: Human-Written vs. AI-Assisted Test Automation

**Author:** Tamara Stojanoska  
**Date:** May 2026  
**Environment:** Mac OS X (aarch64), Selenium 4.43.0, PrestaShop Demo

---

## 1. Feature Comparison Table

| Feature | Automated AI (TC24, 25, 26, 27) | Manual/Refactored (TC28) |
| :--- | :--- | :--- |
| **Logic Flow** | Isolated and procedural. Each test is a standalone script. | Modular and hierarchical. Focuses on reusable patterns. |
| **Stability** | **Moderate.** Prone to `TimeoutException` due to lack of frame context handling. | **High.** Uses `stabilizeFrameContext` to handle M1/M2 speed and redirects. |
| **Maintainability** | **Low.** Hardcoded credentials and duplicated login logic across classes. | **High.** Uses constants (`static final`) and centralized helper methods. |
| **Code Density** | High volume of code (good for complex validation logic). | Lean and clean (focused on readability and "DRY" principles). |
| **Resilience** | Brittle. Relies on standard waits which fail during iframe refreshes. | Robust. Uses JS injection and explicit context resetting. |

### Detailed Metric: Accuracy vs. Structure

| Metric | AI-Assisted (Automated) | Human-Written (Refactored) |
| :--- | :--- | :--- |
| **Logic Accuracy** | High for data-driven tasks (Password complexity). | High for state-driven tasks (Session/Iframe management). |
| **Locator Accuracy** | Excellent (fast identification). | Good (but slower manual verification needed). |
| **Code Structure** | Procedural (Code is duplicated for each test). | Modular (Logic is abstracted into helper methods). |
| **Scalability** | Poor (Becomes messy as project grows). | Excellent (Foundation for a large automation suite). |

---

## 2. Qualitative Analysis

### Efficiency & Speed
* **AI-Assisted:** Excellent for generating boilerplate code and complex validation checks (e.g., the real-time password strength logic in **TC24**). It saves significant time in writing locators.
* **Human-Written:** Initially slower because the human focuses on building a foundation. However, once the foundation (like `loginUser`) is built, writing subsequent tests becomes significantly faster.

### Stability (The "M1/M2 Performance" Challenge)
* **AI-Assisted:** AI struggle to predict environmental flakiness. The `TimeoutException` encountered on `.header-block__title` is a classic example where AI suggests longer waits instead of fixing the underlying **Frame Context Loss**.
* **Human-Written:** Human intuition identified that the PrestaShop iframe "dies" after login. The manual implementation of `driver.switchTo().defaultContent()` solved the persistent failures that AI couldn't fully automate away.

### Maintainability
* **AI-Assisted:** If the PrestaShop URL changes, you would have to update multiple test files.
* **Human-Written:** Using a **Refactored Pattern** (as seen in TC28) means updates happen in one place. If the login process changes, only the `loginUser` method needs a fix.

---

## 3. Time Estimation (Per Test Case)

| Phase | Human-Written (Manual) | AI-Assisted |
| :--- | :--- | :--- |
| **Initial Scripting** | 45 minutes | 10 minutes |
| **Debugging/Stabilization** | 15 minutes | 40 minutes (Iterative) |
| **Refactoring for Reuse** | 20 minutes | N/A |
| **Total Effort** | **~80 minutes** | **~50 minutes** |

**Conclusion:** AI is **37% faster** for the initial draft, but Human intervention is **100% necessary** for architectural stability and handling specific hardware/OS behaviors (like Mac Silicon).

---

## 4. Final Verdict: Hybrid Approach

To achieve the best results in QA Automation:
1.  **Use AI for:** Locators, complex logic blocks (like TC24), and generating test data.
2.  **Use Human for:** Designing the `BaseTest`, managing `Iframe` contexts, and refactoring code into reusable patterns to ensure the suite doesn't become a "maintenance nightmare."

---
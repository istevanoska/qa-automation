# Shopping Cart – Test Automation Summary

## 1. Implemented Tests

* Add product to cart (manual – without AI)
* Add multiple quantities (AI-assisted)
* Remove item from cart (AI-assisted)
* Update quantity in cart (AI-assisted)
* Continue shopping and add again (AI-assisted)

---

## 2. Time Spent

* Manual test: ~35 minutes
* AI-assisted tests (4): ~1.5 hours total

---

## 3. Comparison (Manual vs AI)

### Efficiency

* Manual: slower, written from scratch
* AI: faster generation, but required fixes

### Stability

* Manual: stable
* AI: required adjustments (waits, stale elements, modal handling)

### Maintainability

* Manual: simpler and clearer
* AI: improved after refactoring

### Accuracy

* Manual: followed exact flow
* AI: occasionally incorrect logic and required corrections

---

## 4. Key Notes

* Dynamic UI caused stale element issues
* Modal handling was critical for correct test execution
* Re-locating elements improved stability
* AI significantly sped up development but required validation

---

## 5. AI Prompting Approach

During the development of AI-assisted tests, the following prompting strategy was used:

* Test cases were provided as **screenshots from the Excel test case table**

* Initial prompt example:

    * *"As seen on the image, automate this test case"*

* Additional context was provided to align with project structure:

    * *"I have created other test cases, review these files and follow the same structure and classes"*

* When AI-generated code did not work correctly:

    * Screenshots from **browser Inspect (HTML elements)** were provided
    * Example:

        * *"Use this HTML structure for the button and fix the locator"*

---

## 6. Observations

* Providing visual input (test cases + UI elements) improved AI understanding
* AI required iterative prompting and refinement
* Generated code often needed manual corrections for stability and correctness
* Alignment with existing project structure improved final results

---

## 7. Conclusion

AI significantly accelerates test automation development, but it does not replace the need for developer understanding and control.

The best results were achieved by combining:

* AI-generated code
* Manual refinement and validation

This hybrid approach ensured better stability, accuracy, and maintainability of the automated tests.

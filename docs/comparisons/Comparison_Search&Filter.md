# Search & Filter – Test Automation Comparison Report

## 1. Implemented Tests

### Manual Test
- TC.05 – Search with Valid Product Keyword

### AI-Assisted Tests
- TC.06 – Search with No Results
- TC.07 – Price Range Filter Functionality
- TC.08 – Composition Filter on “All New Products”
- TC.09 – Color Filter Functionality
- TC.10 – Dimension Filter on “All New Products”
- TC.11 – Multiple Filters Applied Simultaneously
- TC.12 – Sorting Functionality on “All New Products” Page

---

## 2. Time Spent

| Type | Estimated Time |
|---|---|
| Manual implementation (TC.05) | ~30 minutes |
| AI-assisted implementation (TC.06–TC.12) | ~2 hours 30 minutes |
| AI debugging and refinement | ~1 hour 15 minutes |

**Total:**
- Manual: ~30 minutes
- AI-assisted: ~3 hours 45 minutes total

---

## 3. Comparison: Manual vs AI-Assisted Automation

| Metric | Manual Test (TC.05) | AI-Assisted Tests |
|---|---|---|
| Implementation Speed | Slower but precise | Faster initial generation |
| Code Stability | Stable and predictable | Required several fixes for filters and sorting |
| Maintainability | Clear and easy to understand | Needed refactoring because of repeated filter logic |
| Locator Reliability | Manually verified locators | Some generated locators were fragile |
| Assertion Quality | Focused on search result validation | Needed manual improvement for dynamic results |
| Scalability | Easy to extend for more keywords | More difficult because filters had different behavior |

---

## 4. Technical Analysis

### Manual Test – TC.05

The manually implemented test focused on searching with valid product keywords such as `mug`, `t-shirt`, `cushion`, `notebook`, and `poster`.

The manual implementation was more controlled because the search flow was simple but important to validate correctly. The test checked whether the search bar accepted valid input, submitted the query, and displayed relevant product results.

Since the test was written manually, the locators and assertions were easier to verify and align with the existing framework structure.

### AI-Assisted Tests – TC.06 to TC.12

The AI-assisted tests were useful for generating repetitive Search & Filter scenarios, especially for invalid searches, price filters, color filters, composition filters, dimension filters, multiple filters, and sorting.

However, these tests required more debugging because filter behavior on the PrestaShop demo page was dynamic. Some filter options appeared only on specific pages, some results changed after asynchronous loading, and sorting required careful waiting before verifying product order.

---

## 5. Key Challenges Encountered

### TC.06 – Search with No Results
- AI handled the basic invalid search flow well.
- The no-results message needed a reliable assertion.
- Random invalid keywords required consistent expected behavior.

### TC.07 – Price Range Filter Functionality
- Price slider behavior was difficult to automate reliably.
- Moving the slider required manual correction.
- Result validation needed to confirm that displayed prices were inside the selected range.

### TC.08 – Composition Filter
- Composition filters were only available for specific products or categories.
- AI-generated locators needed refinement.
- Product detail verification required additional waits.

### TC.09 – Color Filter Functionality
- Color filter options were visually styled elements.
- Normal click actions were sometimes unreliable.
- JavaScript click or explicit waits were needed.

### TC.10 – Dimension Filter
- Dimension filters were not always visible immediately.
- The test required scrolling and waiting for filter options.
- Product details had to be checked carefully after applying the filter.

### TC.11 – Multiple Filters Applied Simultaneously
- Combining filters caused dynamic result updates.
- AI initially produced repetitive logic.
- Assertions needed manual refinement to verify active filters and result consistency.

### TC.12 – Sorting Functionality
- Sorting required waiting for product order to update.
- AI-generated assertions were not always reliable.
- Price sorting and name sorting required different validation logic.

---

## 6. AI Prompting Strategy

During the development of the AI-assisted Search & Filter tests, the following prompting strategy was used:

- Test cases were provided from the Excel test case table.
- Existing Selenium test classes were shared to keep the same structure.
- AI was instructed to follow the existing Page Object Model and utility classes.
- Failed test outputs and browser behavior were used for refinement.
- Additional prompts were used to improve waits, locators, filtering logic, and assertions.

### Example Prompts

- "Automate this Search & Filter test case based on the screenshot."
- "Follow the same structure as my previous Selenium tests."
- "Use the existing HomePage methods where possible."
- "Fix the price range slider interaction."
- "Improve the locator for the color filter."
- "Add explicit waits after applying the filter."
- "Refactor repeated filter code into helper methods."
- "Verify that sorted products are displayed in the correct order."

---

## 7. Observations

- AI was effective for generating the initial structure of repetitive filter tests.
- Manual implementation was more stable for the basic search scenario.
- Search functionality was easier to automate than filter functionality.
- Filters required more manual debugging because of dynamic page updates.
- Price range and multiple filters were the most complex scenarios.
- Explicit waits improved test stability significantly.
- Some AI-generated assertions were too general and had to be replaced with stronger validations.
- Reusing helper methods improved readability and maintainability.

---

## 8. Conclusion

AI-assisted automation was useful for quickly generating Search & Filter test cases, especially where the test flow followed a repetitive pattern.

The main benefit of AI was faster creation of initial Selenium code for multiple filter and sorting scenarios.

However, because filters and sorting depended on dynamic UI behavior, reliable execution still required manual debugging, locator validation, explicit waits, and assertion improvements.

The best result was achieved through a hybrid approach: a manually written stable base test for valid search, combined with AI-assisted generation and manual refinement for more complex filter and sorting scenarios.
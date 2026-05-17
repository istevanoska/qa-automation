# Navigation & UI – Test Automation Comparison Report

## 1. Implemented Tests

### Manual Test
- TC.03 – Main Navigation Menu Categories

### AI-Assisted Tests
- TC.01 – Full Page Translation After Language Switch
- TC.02 – Logo Navigation to Homepage
- TC.04 – Footer Links Navigation

---

## 2. Time Spent

| Type | Estimated Time     |
|---|--------------------|
| Manual implementation (TC.03) | ~35 minutes        |
| AI-assisted implementation (TC.01, TC.02, TC.04) | ~1 hour 20 minutes |
| AI debugging and refinement | ~40 minutes        |

**Total:**
- Manual: ~35 minutes
- AI-assisted: ~2 hours total

---

## 3. Comparison: Manual vs AI-Assisted Automation

| Metric | Manual Test (TC.03) | AI-Assisted Tests |
|---|---|---|
| Implementation Speed | Slower but controlled | Faster initial generation |
| Code Stability | Stable from the beginning | Required synchronization fixes |
| Maintainability | Cleaner and easier to structure | Needed refactoring and locator cleanup |
| Readability | More modular and understandable | More repetitive Selenium logic |
| Locator Reliability | Carefully selected and verified manually | Some generated locators were unstable |
| Scalability | Easier to extend for future category tests | Required additional refinement for reuse |

---

## 4. Technical Analysis

### Manual Test – TC.03

The manually implemented navigation category test focused on validating the main menu category flow. It checked whether the user could navigate from the homepage to the main product categories and whether the correct category pages loaded successfully.

The manual implementation allowed better control over the test structure, assertions, and navigation logic. Because the code was written manually, it was easier to align it with the existing automation framework and reuse helper methods where needed.

### AI-Assisted Tests – TC.01, TC.02, TC.04

The AI-assisted tests were useful for quickly generating repetitive Selenium workflows such as language switching, logo navigation, and footer link validation.

However, the generated tests required manual refinement before becoming stable. The main issues were related to dynamic page loading, iframe handling, locator reliability, and missing explicit waits.

---

## 5. Key Challenges Encountered

### TC.01 – Full Page Translation After Language Switch
- Dynamic translation rendering caused occasional timing issues.
- Some language options required explicit waits before interaction.
- Page text verification needed to be adjusted to avoid unreliable assertions.

### TC.02 – Logo Navigation to Homepage
- Homepage loading required additional synchronization.
- Assertions had to be refined to confirm that the user was actually returned to the homepage.

### TC.03 – Main Navigation Menu Categories
- Manual implementation provided better control over category flow.
- The test was easier to debug because the navigation steps were written step by step.
- Reusable logic helped reduce duplication.

### TC.04 – Footer Links Navigation
- Footer elements were not immediately visible.
- Scrolling was required before interacting with footer links.
- Some footer links required additional waits because of dynamic content loading.

---

## 6. AI Prompting Strategy

During the development of the AI-assisted Navigation & UI tests, the following prompting strategy was used:

- Test cases were provided based on the Excel test case table.
- Existing project structure and previous Selenium tests were shared for consistency.
- AI was asked to follow the same Page Object Model structure.
- When generated code failed, additional prompts were used to fix synchronization, locators, and assertions.

### Example Prompts

- "Automate this Selenium test case based on the provided scenario."
- "Follow my existing project structure and naming conventions."
- "Use Page Object Model structure similar to previous tests."
- "Fix iframe synchronization."
- "Improve locator stability."
- "Replace hardcoded waits with explicit waits."
- "Refactor repetitive Selenium code into helper methods."
- "Use existing utility classes from the framework."

---

## 7. Observations

- AI significantly accelerated repetitive UI test generation.
- Most AI-generated tests required manual stabilization.
- Explicit waits greatly improved reliability.
- Reusing utility methods improved consistency.
- Manual implementation produced cleaner test structure.
- AI performed well for simple navigation workflows.
- Human intervention was necessary for synchronization, locator refinement, and maintainability.

---

## 8. Conclusion

AI-assisted automation was useful for rapidly generating Selenium test structures for Navigation & UI scenarios.

The main advantages of AI were faster initial implementation, quick locator generation, and easier creation of repetitive workflows.

However, reliable automation still required manual debugging, synchronization optimization, locator refinement, and framework-aware refactoring.

The best result was achieved through a hybrid approach: AI-generated Selenium code combined with manual refinement, reusable helper methods, and structured Page Object Model practices.
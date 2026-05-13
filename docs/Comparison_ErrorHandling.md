# Error Handling – Test Automation Summary

## 1. Implemented Tests

- Checkout with empty cart (manual – without AI)
- Invalid product / invalid route handling (AI-assisted)
- Quantity edge-case validation (AI-assisted)
- Session refresh / timeout handling (AI-assisted)
- Checkout interruption / network interruption simulation (AI-assisted)

---

## 2. Time Spent

- Manual test: ~15 minutes
- AI-assisted tests (4): ~30 minutes total

---

## 3. Comparison (Manual vs AI)

### Efficiency

- Manual: slower and written step-by-step
- AI: faster generation of initial test structure

### Stability

- Manual: stable and easier to debug
- AI: required fixes for waits, refresh handling, iframe switching, and dynamic UI behavior

### Maintainability

- Manual: cleaner and easier to understand
- AI: required refactoring and cleanup for readability

### Accuracy

- Manual: followed the expected business flow directly
- AI: sometimes generated incorrect assumptions that needed correction according to actual application behavior

---

## 4. Key Notes

- PrestaShop demo application uses iframe-based rendering
- Dynamic routing and SPA behavior affected error-handling validation
- Some expected behaviors (real 404 pages, 403/500 responses) could not be reproduced in the public demo environment
- Quantity validation behavior required adapting tests to the actual UI restrictions
- Session/cart persistence required refresh-based simulation instead of real timeout handling

---

## 5. AI Prompting Approach

During the development of AI-assisted tests, the following prompting strategy was used:

- Screenshots from the Excel test case table were provided as input

### Initial Prompt Examples

- "Automate this test case based on the image"
- "Create an automation test for this error handling scenario"

### Reusing Existing Project Structure

- "Review the other classes and use the same structure and logic"

### When Generated Code Failed

- Screenshots from browser Inspect/HTML were shared
- Specific UI elements and locators were highlighted

### Additional Refinement Prompts

- "This demo application behaves differently, adjust the logic accordingly"
- "Use the actual UI behavior instead of theoretical validation"

---

## 6. Observations

- AI accelerated repetitive Selenium setup and structure generation
- Real application behavior often differed from AI assumptions
- Dynamic UI and iframe handling required multiple iterations
- Error handling scenarios were harder to automate because the demo application lacked true backend/server responses
- Manual validation and debugging were necessary for stable final results

---

## 7. Conclusion

AI significantly improved development speed for error-handling automation scenarios, especially for generating Selenium structure and repetitive logic.

However:

- Generated tests required multiple corrections
- Real application behavior had to be validated manually
- Demo application limitations affected some scenarios

The best results were achieved through a hybrid workflow:

- AI-generated initial implementation
- Manual debugging and refinement
- Real UI behavior validation

This combination provided more reliable, maintainable, and realistic automated tests.
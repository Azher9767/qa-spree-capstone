// Accessibility Test

describe('Accessibility Test', () => {

  it('Homepage accessibility scan', () => {
    cy.visit('http://localhost:3000/')
    cy.injectAxe()

    cy.checkA11y(null, null, (violations) => {
      cy.task('log', violations.length)

      violations.forEach((violation) => {
        cy.log(violation.id)
        cy.log(violation.help)
      })
    }, true)
  })

})
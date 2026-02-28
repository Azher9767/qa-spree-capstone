// Mobile Responsiveness Test

describe('Mobile Responsiveness Test', () => {

  it('Homepage works on mobile viewport', () => {
    cy.viewport('iphone-6')
    cy.visit('http://localhost:3000/')

    cy.get('nav, .navbar')
      .should('be.visible')
  })

})
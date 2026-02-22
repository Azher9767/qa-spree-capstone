describe('Spree Signup & Login Flow', () => {
  const email = 'testuser@example.com'
  const password = 'password123'

  before(() => {
    // Open homepage
    cy.visit('/')
  })

  it('Sign Up using Turbo Frame', () => {
    // Intercept signup POST request
    cy.intercept('POST', '/user').as('signupRequest')

    // Open signup form (Turbo Frame)
    cy.contains('Sign Up').click()

    // Fill form inside turbo-frame
    cy.get('turbo-frame#login', { timeout: 15000 }).within(() => {
      cy.get('input[name="user[email]"]').type(email)
      cy.get('input[name="user[password]"]').type(password)
      cy.get('input[name="user[password_confirmation]"]').type(password)
      cy.get('input[type="submit"]').click()
    })

    // Wait for server request to fire
    cy.wait('@signupRequest')

    // Confirm user is logged in
    cy.contains('Logout', { timeout: 10000 }).should('be.visible')
  })

  it('Login with existing user', () => {
    cy.contains('Logout').click() // ensure logged out

    cy.intercept('POST', '/user/sign_in').as('loginRequest')

    cy.contains('Login').click()

    cy.get('turbo-frame#login', { timeout: 15000 }).within(() => {
      cy.get('input[name="user[email]"]').type(email)
      cy.get('input[name="user[password]"]').type(password)
      cy.get('input[type="submit"]').click()
    })

    cy.wait('@loginRequest')

    // Verify user is logged in again
    cy.contains('Logout', { timeout: 10000 }).should('be.visible')
  })
})
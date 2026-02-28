describe('Spree Signup & Login Flow', () => {
  const email = `test_account@example.com`
  const password = 'password123'

  beforeEach(() => {
    cy.viewport(1280, 800)
  })

  it('Sign Up', () => {
    cy.intercept('POST', '/user').as('signupRequest')

    cy.visit('/user/sign_up')

    cy.get('#user_email', { timeout: 10000 }).type(email)
    cy.get('#user_password').type(password)
    cy.get('#user_password_confirmation').type(password)
    cy.get('input[value="Sign Up"]').click()

    cy.wait('@signupRequest', { timeout: 15000 })

    cy.url({ timeout: 10000 }).should('include', 'localhost:3000')
  })

  it('Login and Logout', () => {
    cy.visit('/user/sign_in')

    cy.get('#user_email', { timeout: 10000 }).type(email)
    cy.get('#user_password').type(password)
    cy.get('input[value="Login"]').click()

    cy.url({ timeout: 10000 }).should('not.include', '/sign_in')

    cy.request({
      method: 'DELETE',
      url: '/user/sign_out',
      failOnStatusCode: false
    })
    cy.visit('/')
    cy.url().should('include', 'localhost:3000')
  })
})
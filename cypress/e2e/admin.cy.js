describe('Spree Admin Login', () => {
  const adminEmail = 'spree@example.com'
  const adminPassword = 'spree123'

  it('Admin can log in successfully', () => {
    cy.visit('/admin')

    cy.intercept('POST', '/admin_user/sign_in').as('adminLogin')

    cy.get('form#new_admin_user').within(() => {
      cy.get('input[name="admin_user[email]"]').type(adminEmail)
      cy.get('input[name="admin_user[password]"]').type(adminPassword)

      cy.get('button[type="submit"]')
        .should('not.be.disabled')
        .click()
    })

    cy.wait('@adminLogin')
    cy.url().should('include', '/admin')
    cy.contains('Dashboard').should('be.visible')
  })
})
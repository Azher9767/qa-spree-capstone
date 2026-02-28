describe('Product Search Flow', () => {

  beforeEach(() => {
    cy.viewport(1400, 900)
  })

  it('User can search for a product successfully', () => {
    cy.visit('http://localhost:3000/products?keywords=shirt')

    cy.url().should('include', 'keywords=shirt')

    cy.get('turbo-frame#products_list', { timeout: 10000 })
      .should('exist')

    cy.contains('No products found').should('not.exist')
  })

  it('Shows message when no product found', () => {
    cy.visit('http://localhost:3000/search?q=randomnonexistingproduct123xyz')

    cy.url().should('include', 'randomnonexistingproduct')

    cy.contains('No products found', { timeout: 10000 })
      .should('exist')
  })

})
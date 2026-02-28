describe('API Tests', () => {

  it('Fetch products API should return 200', () => {
    cy.request('http://localhost:3000/api/v2/storefront/products')
      .its('status')
      .should('eq', 200)
  })

})
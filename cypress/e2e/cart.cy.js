describe('Add to Cart Flow', () => {

  it('User can add product to cart', () => {
    cy.visit('/products/digital-download')

    // Click Add to cart 
    cy.intercept('POST', '/line_items').as('addToCart')
    cy.get('.add-to-cart-button').filter(':visible').first().click()
    cy.wait('@addToCart', { timeout: 10000 })

    // Visit cart
    cy.visit('/cart')

    // Verify present or not
    cy.url().should('include', '/cart')
    cy.get('body').should('not.contain', 'Your cart is empty')
  })

})
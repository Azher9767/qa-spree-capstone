describe('Checkout Flow', () => {
  const email = 'test_account@example.com'
  const password = 'password123'

  beforeEach(() => {
    // Set browser viewport size for consistent UI rendering
    cy.viewport(1400, 900)
  })

  it('User can complete checkout with Check payment', () => {

    // 1. Login to the application
    cy.visit('/user/sign_in')
    cy.get('#user_email').type(email)
    cy.get('#user_password').type(password)
    cy.get('input[value="Login"]').click()

    // Ensure user is redirected after successful login
    cy.url({ timeout: 10000 }).should('not.include', 'sign_in')

    // 2. Visit product page and add product to cart
    cy.visit('/products/digital-download')

    // Intercept add-to-cart request to wait for it
    cy.intercept('POST', '/line_items').as('addToCart')

    // Click first visible add-to-cart button
    cy.get('.add-to-cart-button').filter(':visible').first().click()

    // Wait until add-to-cart API call completes
    cy.wait('@addToCart', { timeout: 10000 })

    // 3. Visit cart page and extract checkout token from URL
    cy.visit('/cart')

    cy.get('a[href*="/checkout/"]', { timeout: 10000 })
      .first()
      .invoke('attr', 'href')
      .then((href) => {

        // Extract checkout token from the checkout URL
        const token = href.split('/checkout/')[1].split('/')[0]
        cy.log('Token: ' + token)

        // 4. Visit address step of checkout
        cy.visit(`/checkout/${token}/address`)
        cy.url({ timeout: 10000 }).should('include', '/address')

        // Extract CSRF authenticity token from address form
        cy.get('input[name="authenticity_token"]')
          .invoke('val')
          .then((authToken) => {

            // 5. Submit address form using cy.request
            cy.request({
              method: 'PATCH',
              url: `/checkout/${token}/update/address`,
              form: true,
              body: {
                '_method': 'patch',
                'authenticity_token': authToken,

                'order[bill_address_attributes][firstname]': 'John',
                'order[bill_address_attributes][lastname]': 'Doe',
                'order[bill_address_attributes][address1]': '123 Main Street',
                'order[bill_address_attributes][city]': 'Los Angeles',
                'order[bill_address_attributes][country_id]': '233',
                'order[bill_address_attributes][state_id]': '12',
                'order[bill_address_attributes][zipcode]': '90001',
                'order[bill_address_attributes][phone]': '3105551234',
              }
            }).then((addressResp) => {
              cy.log('Address response: ' + addressResp.status)
            })

            // 6. Visit payment step and pre-select Check payment method (id = 3)
            cy.visit(`/checkout/${token}/payment?payment_method_id=3`)
            cy.url({ timeout: 10000 }).should('include', 'payment')

            // Extract CSRF token from payment page
            cy.get('input[name="authenticity_token"]')
              .invoke('val')
              .then((paymentAuthToken) => {

                // Extract order state lock version (required for optimistic locking)
                cy.get('#order_state_lock_version')
                  .invoke('val')
                  .then((lockVersion) => {

                    // 7. Submit payment step using cy.request
                    cy.request({
                      method: 'PATCH',
                      url: `/checkout/${token}/update/payment`,
                      form: true,
                      body: {
                        '_method': 'patch',
                        'authenticity_token': paymentAuthToken,
                        'order[state_lock_version]': lockVersion,
                        'order[payments_attributes][][payment_method_id]': '3',

                        // Re-send USA billing address to ensure consistency
                        'order[bill_address_attributes][firstname]': 'John',
                        'order[bill_address_attributes][lastname]': 'Doe',
                        'order[bill_address_attributes][address1]': '123 Main Street',
                        'order[bill_address_attributes][city]': 'Los Angeles',
                        'order[bill_address_attributes][country_id]': '233',
                        'order[bill_address_attributes][state_id]': '12',
                        'order[bill_address_attributes][zipcode]': '90001',
                        'order[bill_address_attributes][phone]': '3105551234',
                      },
                      failOnStatusCode: false
                    }).then((payResp) => {
                      cy.log('Payment response: ' + payResp.status)
                    })

                    // 8. Visit order completion page and verify confirmation message
                    cy.visit(`/checkout/${token}/complete`)

                    // Verify success confirmation text appears
                    cy.contains('Your order is confirmed!', { timeout: 15000 })
                      .should('exist')

                    cy.contains('Thanks John for your order!', { timeout: 10000 })
                      .should('exist')
                  })
              })
          })
      })
  })
})
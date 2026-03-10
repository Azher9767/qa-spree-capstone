describe('Performance Tests', () => {

  it('Homepage should load within acceptable time', () => {
    const start = Date.now()

    cy.visit('http://localhost:3000/')
      .then(() => {
        const loadTime = Date.now() - start
        cy.log(`Page Load Time: ${loadTime}ms`)
        expect(loadTime).to.be.lessThan(3000)
      })
  })

  it('Homepage should have performance metrics within range', () => {
    cy.visit('http://localhost:3000/')

    // Page load performance check
    cy.window().then((win) => {
      const perfData = win.performance.timing
      const pageLoadTime = perfData.loadEventEnd - perfData.navigationStart
      const domReadyTime = perfData.domContentLoadedEventEnd - perfData.navigationStart

      cy.log(`Total Page Load: ${pageLoadTime}ms`)
      cy.log(`DOM Ready: ${domReadyTime}ms`)

      expect(pageLoadTime).to.be.lessThan(5000)
      expect(domReadyTime).to.be.lessThan(3000)
    })
  })

  it('Page resources should be optimized', () => {
    cy.visit('http://localhost:3000/')

    cy.window().then((win) => {
      const resources = win.performance.getEntriesByType('resource')
      cy.log(`Total Resources: ${resources.length}`)

      const slowResources = resources.filter(r => r.duration > 2000)
      cy.log(`Slow Resources: ${slowResources.length}`)

      expect(slowResources.length).to.be.lessThan(5)
    })
  })

})
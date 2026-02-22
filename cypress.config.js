module.exports = {
  allowCypressEnv: false,

 e2e: {
    baseUrl: 'http://localhost:3000',
    supportFile: 'cypress/support/index.js',
    specPattern: 'cypress/e2e/**/*.cy.js'
  }
};

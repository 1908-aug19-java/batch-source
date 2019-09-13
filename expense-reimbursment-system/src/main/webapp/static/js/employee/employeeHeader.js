(function () {
  document.querySelector("#home").addEventListener("click", ()=> { goToPage("employeeHome", "employeeHeader", "applicationFooter"); });
  document.querySelector("#employeeReimbursement").addEventListener("click", () => { goToPage("employeeReimbursement", "employeeHeader", "applicationFooter"); });
  document.querySelector("#employeeProfile").addEventListener("click", () => { goToPage("employeeProfile", "employeeHeader", "applicationFooter"); });
  document.querySelector("#logout").addEventListener("click", logout);

  function logout() {
    const resources = JSON.parse(window.localStorage.getItem("Resources"));
    sessionStorage.removeItem('jwt');
    loadResources(resources.login);
  }
})();




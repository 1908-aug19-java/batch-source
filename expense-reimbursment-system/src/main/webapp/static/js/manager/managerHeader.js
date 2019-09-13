(function () {

  document.querySelector("#home").addEventListener("click", () => { goToPage("managerHome", "managerHeader", "applicationFooter"); });
  document.querySelector("#all-employees").addEventListener("click", () => { goToPage("allEmployees", "managerHeader", "applicationFooter"); });
  document.querySelector("#register-employee").addEventListener("click", () => { goToPage("registerEmployee", "managerHeader", "applicationFooter"); });
  document.querySelector("#logout").addEventListener("click", logout);

  function logout() {
    const resources = JSON.parse(window.localStorage.getItem("Resources"));
    sessionStorage.removeItem('authorization');
    loadResources(resources.login);
  }

})();




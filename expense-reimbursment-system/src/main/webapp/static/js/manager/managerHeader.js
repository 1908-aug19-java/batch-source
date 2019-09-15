(function () {

  document.querySelector("#home").addEventListener("click", () => { goToPage("managerHome", "managerHeader", "applicationFooter"); });
  document.querySelector("#all-employees").addEventListener("click", () => { goToPage("managerAllEmployees", "managerHeader", "applicationFooter"); });
  document.querySelector("#register-employee").addEventListener("click", () => { goToPage("managerRegisterEmployee", "managerHeader", "applicationFooter"); });
  document.querySelector("#logout").addEventListener("click", logout);

})();




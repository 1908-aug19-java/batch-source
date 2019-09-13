(function () {
  document.querySelector("#submit-login").addEventListener("click", validateLogin);
  function validateLogin(e) {
    e.preventDefault();
    document.querySelector("#warning").textContent = "";
    let email = document.querySelector("#email").value;
    let password = document.querySelector("#password").value;
    let url = `http://localhost:8080/ers/login`;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onload = function () {
      if (xhr.status == 200) {
        let authorization = xhr.getResponseHeader("Authorization");
        let isAuthorized = authorization != null;
        if (isAuthorized) {
          var jwt = jwt_decode(authorization);
          sessionStorage.authorization = JSON.stringify(authorization);
          const resources = JSON.parse(window.localStorage.getItem("Resources"));
          let authority = jwt.authority;
          switch (authority.toUpperCase()) {
            case "MANAGER":
              loadResources(resources.managerHome, resources.managerHeader, resources.applicationFooter)
              break;
            case "EMPLOYEE":
              loadResources(resources.employeeHome, resources.employeeHeader, resources.applicationFooter)
              break;
          }
        } else {
          document.querySelector("#warning").textContent = "Invalid Credentitals";
        }
      } else {
        console.log("Recieved status code: " + xhr.status);
      }
    };
    xhr.send(`email=${email}&password=${password}`);
  }
})();

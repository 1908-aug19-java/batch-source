window.onload = function () {
  window.onpopstate = manageHistory;
  if (history.state != undefined) {
    dispatchBy("HISTORY");
  } else {
    bootstrap();
  }
};

function manageHistory(e) {
  if(e.state != undefined){
    loadResources(e.state.pageResource["main_content"], e.state.pageResource["header"], e.state.pageResource["footer"])
  }
  
}

function dispatchBy(type) {
  let ers_auth;
  let jwt;
  if ((ers_auth = sessionStorage.ers_auth) != undefined && (jwt = jwt_decode(ers_auth)).EXP > new Date().getTime()) {
    const resources = JSON.parse(window.localStorage.getItem("Resources"));
    let authority = jwt.authority;
    switch (type.toUpperCase()) {
      case "AUTHORITY": dispatchByAuthority(authority, resources);
        break;
      case "HISTORY": dispatchByHistory(authority, resources);
        break;
    }
  } else {
    goToPage("login");
  }
}

function dispatchByAuthority(authority, resources) {
  switch (authority.toUpperCase()) {
    case "MANAGER":
      addToHistory(resources.managerHome, resources.managerHeader, resources.applicationFooter);
      loadResources(resources.managerHome, resources.managerHeader, resources.applicationFooter);
      break;
    case "EMPLOYEE":
      addToHistory(resources.employeeHome, resources.employeeHeader, resources.applicationFooter);
      loadResources(resources.employeeHome, resources.employeeHeader, resources.applicationFooter);
      break;
  }
}

function dispatchByHistory(authority, resources) {
  let title = history.state.pageResource["main_content"].title.toUpperCase();
  switch (authority.toUpperCase()) {
    case "MANAGER":
      if (title.startsWith("MANAGER")) {
        loadResources(history.state.pageResource["main_content"], history.state.pageResource["header"], history.state.pageResource["footer"]);
      }
      else {
        history.go(-(history.state.pageResource.pageNumber));
        bootstrap();
      }
      break;
    case "EMPLOYEE":
      if (title.startsWith("EMPLOYEE")) {
        loadResources(history.state.pageResource["main_content"], history.state.pageResource["header"], history.state.pageResource["footer"]);
      }
      else {
        history.go(-(history.state.pageResource.pageNumber));
        bootstrap();
      }
      break;
  }
}

function replaceHistory(main_content, optionalHeader, optionalFooter) {
  history.replaceState({ pageResource: { "pageNumber": history.state == undefined ? 1 : history.state.pageResource.pageNumber + 1, "main_content": main_content, "header": optionalHeader, "footer": optionalFooter } }, main_content.title, `?page=${main_content.title}`);
}

function addToHistory(main_content, optionalHeader, optionalFooter) {
  if (main_content != undefined && main_content.title != "Login") {
    history.pushState({ pageResource: { "pageNumber": history.state == undefined ? 1 : history.state.pageResource.pageNumber + 1, "main_content": main_content, "header": optionalHeader, "footer": optionalFooter } }, main_content.title, `?page=${main_content.title}`);
  }
}

function loadResources(resource, optionalHeader, optionalFooter) {
  loadCSS(resource, optionalHeader, optionalFooter);
  loadHTML({ "#main_content": resource }, { "#header": optionalHeader }, { "#footer": optionalFooter });
  loadJS(resource, optionalHeader, optionalFooter);
}

function loadCSS() {
  let styleTag = document.getElementsByTagName("style")[0];
  styleTag.innerHTML = "";
  for (const resource of arguments) {
    let css = resource != undefined ? (resource.css != undefined ? resource.css : "") : "";
    styleTag.innerHTML += css + " ";
  }
}

function loadHTML() {
  for (const item of arguments) {
    for (const selector in item) {
      if (item.hasOwnProperty(selector)) {
        let resource = item[selector];
        if (selector == "#main_content") {
          let htmlTitle = resource != undefined ? (resource.title != undefined ? resource.title : "") : "";
          let titleTag = document.getElementsByTagName("title")[0];
          titleTag.innerHTML = `ERS - ${htmlTitle}`;
        }
        let htmlResource = resource != undefined ? (resource.html != undefined ? resource.html : "") : "";
        let htmlDiv = document.querySelector(selector);
        htmlDiv.innerHTML = htmlResource;
      }
    }
  }
}

function loadJS() {
  let oldScriptTag = document.querySelector("#scripts");
  oldScriptTag.parentNode.removeChild(oldScriptTag);
  let scriptTag = document.createElement("script");
  scriptTag.id = "scripts";
  scriptTag.type = "text/javascript";
  for (const resource of arguments) {
    let js = resource != undefined ? (resource.javascript != undefined ? resource.javascript : "") : "";
    scriptTag.innerHTML += js + " ";
  }
  document.body.appendChild(scriptTag);
}

function goToPage(main, header, footer) {
  const resources = JSON.parse(window.localStorage.getItem("Resources"));
  addToHistory(resources[main], resources[header], resources[footer]);
  loadResources(resources[main], resources[header], resources[footer]);
}

function bootstrap() {
  let url = `http://localhost:8080/ers/api/resources`;
  let xhr = new XMLHttpRequest();
  xhr.open("GET", url);
  xhr.responseType = "json";
  xhr.onload = function () {
    let status = xhr.status;
    if (status == 200) {
      window.localStorage.setItem("Resources", JSON.stringify(xhr.response));
      dispatchBy("AUTHORITY");
    } else {
      console.log("Recieved status code: " + xhr.status);
    }
  };
  xhr.send();
}

function logout() {
  sessionStorage.removeItem('ers_auth');
  window.location.href = `http://localhost:8080/ers/`;
}

function getUserAccounts(params, callback) {
  if(params == undefined){params = ""}
  let url = `http://localhost:8080/ers/api/user-accounts?${params}`;
  let xhr = new XMLHttpRequest();
  xhr.open("GET", url);
  xhr.responseType = "json";
  xhr.setRequestHeader("Authorization", JSON.parse(sessionStorage.ers_auth));
  xhr.onload = function () {
    let status = xhr.status;
    if (status == 200) {
      console.log("Retrieve Successful")
      if(callback != undefined){
        data = xhr.response;
        callback(data);
      }
    } else if(status == 401){
      console.log("Authorization required");
      goToPage("login")
    } {
      console.log("Recieved status code: " + xhr.status);
    }
  };
  xhr.send();
}

function setProfileImage(){
  if(sessionStorage.imageUrl != undefined){
    for (const selector of arguments) {
      document.querySelector(selector).style.visibility = "visible";
      document.querySelector(selector).src = sessionStorage.imageUrl;
    }
    document.querySelector("#user_circle").style.visibility = "hidden";
  }else{
    for (const selector of arguments) {
      document.querySelector(selector).style.visibility = "hidden";
    }
    document.querySelector("#user_circle").style.visibility = "visible";
  }
}

// function dispatchByHistory() {
//   let ers_auth;
//   let jwt;
//   if ((ers_auth = sessionStorage.ers_auth) != undefined && (jwt = jwt_decode(ers_auth)).EXP > new Date().getTime()) {
//     const resources = JSON.parse(window.localStorage.getItem("Resources"));
//     let authority = jwt.authority;
//     let title = history.state.pageResource["#main-content"].title.toUpperCase();
//     newFunction(authority, title, resources);
//   } else {
//     goToPage("login");
//   }
// }

// function dispatchByHistoryAndAuthority() {
//   const resources = JSON.parse(window.localStorage.getItem("Resources"));
//   let jwt = jwt_decode(sessionStorage.ers_auth);
//   let authority = jwt.authority;
//   switch (authority.toUpperCase()) {
//     case "MANAGER":
//       if (history.state.pageResource["#main-content"].title.toUpperCase().startsWith("MANAGER")) {
//         console.log("kklllaa")
//         loadResources(history.state.pageResource["#main-content"], history.state.pageResource["#header"], history.state.pageResource["#footer"])
//       } else {
//         loadResources(resources.managerHome, resources.managerHeader, resources.applicationFooter);
//       }
//       break;
//     case "EMPLOYEE":
//       if (history.state.pageResource["#main-content"].title.toUpperCase().startsWith("EMPLOYEE")) {
//         loadResources(history.state.pageResource["#main-content"], history.state.pageResource["#header"], history.state.pageResource["#footer"])
//       } else {
//         loadResources(resources.employeeHome, resources.employeeHeader, resources.applicationFooter);
//       }
//       break;
//   }
// }

// function dispatchByHistory() {
//   let ers_auth;
//   let jwt;
//   if ((ers_auth = sessionStorage.ers_auth) != undefined && (jwt = jwt_decode(ers_auth)).EXP > new Date().getTime()) {
//     const resources = JSON.parse(window.localStorage.getItem("Resources"));
//     let authority = jwt.authority;
//     switch (authority.toUpperCase()) {
//       case "MANAGER":
//         if (history.state.pageResource["#main-content"].title.toUpperCase().startsWith("MANAGER")) {
//           loadResources(history.state.pageResource["#main-content"], history.state.pageResource["#header"], history.state.pageResource["#footer"])
//         } else {
//           loadResources(resources.managerHome, resources.managerHeader, resources.applicationFooter);
//         }
//         break;
//       case "EMPLOYEE":
//         if (history.state.pageResource.title.toUpperCase().startsWith("EMPLOYEE")) {
//           loadResources(history.state.pageResource["#main-content"], history.state.pageResource["#header"], history.state.pageResource["#footer"])
//         } else {
//           loadResources(resources.employeeHome, resources.employeeHeader, resources.applicationFooter);
//         }
//         break;
//     }
//   } else {
//     goToPage("login");
//   }
// }



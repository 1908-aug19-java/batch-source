window.onload = function () {
  bootstrap();
};

function bootstrap() {
  let url = `http://localhost:8080/ers/api/resources`;
  let xhr = new XMLHttpRequest();
  xhr.open("GET", url);
  xhr.responseType = "json";
  xhr.onload = function () {
    let status = xhr.status;
    if (status == 200) {
      window.localStorage.setItem("Resources", JSON.stringify(xhr.response));
      let resource = xhr.response.login;
      loadResources(resource);
    } else {
      console.log("Recieved status code: " + xhr.status);
    }
  };
  xhr.send();
}

function goToPage(main, header, footer) {
  const resources = JSON.parse(window.localStorage.getItem("Resources"));
  loadResources(resources[main], resources[header], resources[footer]);
}

function loadResources(resource, optionalHeader, optionalFooter) {
  loadCSS(resource, optionalHeader, optionalFooter);
  loadHTML({ "#main-content": resource }, { "#header": optionalHeader }, { "#footer": optionalFooter });
  loadJS(resource, optionalHeader, optionalFooter);
}

function getResources(key){
  return JSON.parse(window.localStorage.getItem("Resources"))[key];
}

function loadCSS() {
  let styleTags = document.getElementsByTagName("style")[0];
  styleTags.innerHTML = "";
  for (const resource of arguments) {
    let css = resource != undefined ? (resource.css != undefined ? resource.css : "") : "";
    styleTags.innerHTML += css + " ";
  }
}

function loadHTML() {
  for (const item of arguments) {
    for (const selector in item) {
      if (item.hasOwnProperty(selector)) {
        let resource = item[selector];
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
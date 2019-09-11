console.log(this.XMLHttpRequest.HEADERS_RECEIVED)
function goToPage(page) {
    let userAccounts;
    let url = `http://localhost:8080/ers/${page}`;
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Authorization");
    xhr.send();
  }


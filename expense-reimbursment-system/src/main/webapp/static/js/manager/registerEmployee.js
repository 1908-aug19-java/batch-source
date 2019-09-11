window.onload = function(){
    let util = new Util();
    getUserAccounts();
    document.querySelector("#header").innerHTML = util.getHeader("MANAGER");
    document.querySelector("#footer").innerHTML = util.getFooter(); 
    document.querySelector("#firstName").addEventListener("blur", validateFirstname);
    document.querySelector("#lastName").addEventListener("blur", validateLastname);  
    document.querySelector("#password").addEventListener("blur", validatePassword);
    document.querySelector("#confirm_password").addEventListener("blur", validatePasswordEquality);
}

function validateFirstname() {
	let firstname = document.querySelector("#firstName").value;
	let re = /^[A-Za-z-']{2,32}$/;
	if (!re.test(firstname)) {
		document.querySelector('#firstname_warning').textContent = "Invalid first name, please use Letters, dashes, and apostrophes.";
		return false;
	} else {
		document.querySelector('#firstname_warning').textContent = "";
		return true;
	}
}

function validateLastname() {
	let lastname = document.querySelector("#lastName").value;
	let re = /^[A-Za-z-']{2,32}$/;
	if (!re.test(lastname)) {
		document.querySelector('#lastname_warning').textContent = "Invalid last name, please use Letters, dashes, and apostrophes.";
		return false;
	} else {
		document.querySelector('#lastname_warning').textContent = "";
		return true;
	}
}

function validateEmail(userAccounts) {
	let email = document.querySelector("#email").value;
    let re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if (!re.test(email)) {
		document.querySelector('#email_warning').textContent = "Invalid email address";
		return false;
	} 
	for ( let index in userAccounts) {
		if (document.querySelector("#email").value == userAccounts[index].email) {
			document.querySelector('#email_warning').textContent = "Email "
					+ userAccounts[index].email + " already exists";
			return false;
		}
	}
	document.querySelector('#email_warning').textContent = "";
	return true;
}

function validatePassword(){
	// at least one number, one lowercase and one uppercase letter
	// at least six characters
	let password = document.querySelector("#password").value;
	let re = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
	let newline = "\r\n";
	if (!re.test(password)) {
		document.querySelector('#password_warning').textContent = "Required:" + newline + "At least one number, one lowercase and one uppercase letter." 
			+ newline + "At least six characters.";
		password = "";
		return false;
	} else {
		document.querySelector('#password_warning').textContent = "";
		password = null;
		return true;
    }
}

function validatePasswordEquality(){
    let password = document.querySelector("#password").value;
    let confirmPassword = document.querySelector("#confirm_password").value;
	if (password !== confirmPassword) {
		document.querySelector('#confirm_password_warning').textContent = "Passwords do not match";
		password = null; confirmPassword = null;
		return false;
	} else {
		document.querySelector('#confirm_password_warning').textContent = "";
		password = null; confirmPassword = null;
		return true;
	}
}

//This is in place in case someone skips straight to the submit button which causes the on blur events to never occur
function validateForm(userAccounts){
	if(validateFirstname() && validateLastname() && validateEmail(userAccounts) && validatePassword() && validatePasswordEquality()){
		return true;
	}
	return false;
}

function getUserAccounts() {
    let userAccounts;
    let url = `http://localhost:8080/ers/user-accounts`;
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.responseType = "json";
    xhr.onload = function() {
      let status = xhr.status;
      if (status == 200) {
        userAccounts = xhr.response;
        setDependentListeners(userAccounts);
        return userAccounts;
      } else {
        console.log("Recieved status code: " +xhr.status);
      }
    };
    xhr.send();
  }
  
  //This sets the event listeners that are waiting for the ajax request to complete to the get the useraccount objects
  function setDependentListeners(userAccounts){
    document.querySelector("#email").addEventListener("blur", ()=>{validateEmail(userAccounts)});
    document.querySelector("#form").onsubmit = ()=>{return validateForm(userAccounts);}
  }
var baseURL = "http://localhost:8080/Project1/LoginServlet";

window.onload = () => {
	document.getElementById("submit-btn").addEventListener("click", login);
	console.log("in javascript")
}
const login = () => {
	let xhr = new XMLHttpRequest();
	let data= getCrediential();
	console.log(data);
	
	xhr.onreadystatechange = () => {
		if (xhr.status === 200 && xhr.readyState === 4) {
			let json = xhr.responseText;
			console.log(json);
		}
	}	
	if (data.password!=="" ||data.email!=="")// no empty field allowed
	{
		xhr.open("POST",  baseURL);
		xhr.send(JSON.stringify(data));
	    }
		else{
			console.log("data are empty");
		}
}

let getCrediential = () => {

	let emailText = document.getElementById("email").value;
	let passwordText = document.getElementById("password").value;
	let roleText = document.querySelector('.role').checked; //true/false

	return {	
		email: emailText,
		password: passwordText,
		isManager:roleText,
	}
}


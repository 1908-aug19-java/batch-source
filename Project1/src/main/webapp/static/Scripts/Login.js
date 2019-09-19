document.getElementById("login-btn").addEventListener("click", requestLogin);


function requestLogin(){
	console.log("Im in POST")
	let url = "http://localhost:8088/Project1/Login";
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status===200){
			// set authorization in our browser for future request
			let auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token", auth);
			let token = sessionStorage.getItem("token");
			let tokenArr = token.split("/");
			console.log(tokenArr[2]);
			if(tokenArr[2] == "1"){
				window.location.href="http://localhost:8088/Project1/EmployeeHome";
			}
			else{
				window.location.href="http://localhost:8088/Project1/ManagerHome";
			}
		}
		if(this.readyState === 4 ){
			console.log(this);
		}
	}
	let user = document.getElementById("username").value;
	let pass = document.getElementById("password").value;
	let position = document.getElementById("login-select").value;
	console.log(position);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
	
	let requestBody = `username=${user}&password=${pass}&position=${position}`;
	xhr.send(requestBody);
	
	
}
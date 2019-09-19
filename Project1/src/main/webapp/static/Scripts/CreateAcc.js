document.getElementById("create-btn").addEventListener("click", requestCreate);


function requestCreate(){
	let url = "http://localhost:8088/Project1/CreateAcc";
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status===200){
			window.location.href="http://localhost:8088/Project1/Login";
		}
		if(this.readyState === 4 ){
			console.log(xhr.status);
		}
	}
	let user = document.getElementById("username").value;
	let pass = document.getElementById("password").value;
	let firstname = document.getElementById("firstname").value;
	let lastname = document.getElementById("lastname").value;
	let phone = document.getElementById("phone").value;
	let address = document.getElementById("address").value;

	let requestBody = `username=${user}&password=${pass}&firstname=${firstname}&lastname=${lastname}&phone=${phone}&address=${address}`;
	console.log(requestBody);
	xhr.send(requestBody);
	
	
}
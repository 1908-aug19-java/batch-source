let token = sessionStorage.getItem("token");
if(!token){
	window.location.href="http://localhost:8088/Project1/Login";
}
document.getElementById("update-btn").addEventListener("click", requestReimbursement);
function requestReimbursement(){
	let url = "http://localhost:8088/Project1/UpdateInfo";
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status===200){
			window.location.href="http://localhost:8088/Project1/EmployeeHome";
		}
		if(this.readyState === 4 ){
			console.log(xhr.status);
		}
	}
	let firstname = document.getElementById("firstname").value;
	let lastname = document.getElementById("lastname").value;
	let phone = document.getElementById("phone").value;
	let address = document.getElementById("address").value;
	let requestBody = `username=${sessionStorage.username}&firstname=${firstname}&lastname=${lastname}&phone=${phone}&address=${address}`;
	console.log(requestBody);
	xhr.send(requestBody);
	
}
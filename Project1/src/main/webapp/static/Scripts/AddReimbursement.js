document.getElementById("reimbursement-btn").addEventListener("click", requestReimbursement);
function requestReimbursement(){
	let url = "http://localhost:8088/Project1/RequestReimbursement";
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status===200){
			window.location.href="http://localhost:8088/Project1/EmployeeHome";
		}
		if(this.readyState === 4 ){
			console.log("djsdkj")
			console.log(xhr.status);
		}
	}
	tokenArr = token.split("/");
	let amount = document.getElementById("Reimbursement").value;
	let username = tokenArr[0];
	let requestBody = `amount=${amount}&username=${username}`;
	console.log(requestBody);
	xhr.send(requestBody);
	
}
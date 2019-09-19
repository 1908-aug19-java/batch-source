let token = sessionStorage.getItem("token");
let tokenArr;
sessionStorage.username = ""
sessionStorage.position = ""
if(!token){
	window.location.href="http://localhost:8088/Project1/Login";
} else {
	tokenArr = token.split("/");
	console.log(tokenArr);
	if(tokenArr.length===3){
		let baseUrl = "http://localhost:8088/Project1/api/manager?username=";
		sessionStorage.username = tokenArr[0];
		sessionStorage.position = "Manager";
		sendAjaxGet(baseUrl+tokenArr[0], displayName);
	} else {
		window.location.href="http://localhost:8088/Project1/Login";
	} 
}

/*
 * if we are not redirected when checking for the token, a request will be made to 
 * the url for that particular user 
 */
document.getElementById("pending-btn").addEventListener("click", pending);
document.getElementById("resolved-btn").addEventListener("click", resolved);
document.getElementById("reimbursement-btn").addEventListener("click", aEmployee);
document.getElementById("employee-btn").addEventListener("click", allEmployee);
document.getElementById("logout-btn").addEventListener("click", logout);

sessionStorage.type = "";

function pending(){
	sessionStorage.type = document.getElementById("pending-btn").value;
	sessionStorage.hide = "false";
	window.location.href="http://localhost:8088/Project1/Reimbursement"
}

function resolved(){
	sessionStorage.type = document.getElementById("resolved-btn").value;
	sessionStorage.hide = "true";
	window.location.href="http://localhost:8088/Project1/Reimbursement"
}
function allEmployee(){
	window.location.href="http://localhost:8088/Project1/EmployeeInfo"
}
function aEmployee(){
	sessionStorage.hide = "true";
	sessionStorage.type = "all";
	sessionStorage.employeeid = document.getElementById("Employee").value
	window.location.href="http://localhost:8088/Project1/Reimbursement"
}
function logout(){
	sessionStorage.clear();
	window.location.href="http://localhost:8088/Project1/Login"
}


function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
			window.location.href="http://localhost:8088/Project1/Login";
		}
	}
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayName(xhr){
	let user = JSON.parse(xhr.response);
//	console.log(user);
	document.getElementById("user").innerHTML = user.username;
	
}

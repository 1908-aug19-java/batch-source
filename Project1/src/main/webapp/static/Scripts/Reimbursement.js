token = sessionStorage.getItem("token");
if(!token){
	window.location.href="http://localhost:8088/Project1/Login";
}

let requestUrl = "http://localhost:8088/Project1/api/Reimbursement" + `?type=${sessionStorage.type}&username=${sessionStorage.username}&position=${sessionStorage.position}&employeeid=${sessionStorage.employeeid}`;

if(sessionStorage.hide == "false"){
	let manager = document.getElementById("approve/deny");
	manager.hidden = false;
}
if(sessionStorage.hide == "true"){
	let manager = document.getElementById("approve/deny");
	manager.hidden = true;
}

sendAjaxGet(requestUrl, displayReimbursement);

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState===4 && xhr.status===200){
			callback(xhr.response);
		}
	}
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayReimbursement(reimbursementJSON){
//	console.log(employeeJSON);
	if(reimbursementJSON){
		let reimbursements = JSON.parse(reimbursementJSON);
		
		let table = document.getElementById("Reimbursement");
		table.hidden = false;
		
		for(let reimbursement of reimbursements){
			let newRow = document.createElement("tr");
			let resolvedby = reimbursement.resolvedBy;
			if(!resolvedby){
				resolvedby = "n/a";
			}
			let result = reimbursement.resultd;
			if(!result){
				result = "n/a";
			}
			newRow.innerHTML = `<td>${reimbursement.id}</td><td>${reimbursement.employeeId}</td><td>${reimbursement.employee}</td><td>${reimbursement.status}</td><td>${reimbursement.amount}</td><td>${resolvedby}</td><td>${result}</td>`;
			table.appendChild(newRow);
			
		}
	} else {
		console.log("issue getting reimbursement");
	}
}

let approveUrl = "http://localhost:8088/Project1/ReimbursementResult" + `?&username=${sessionStorage.username}&position=${sessionStorage.position}&result=approve`;
let denyUrl = "http://localhost:8088/Project1/ReimbursementResult" + `?&username=${sessionStorage.username}&position=${sessionStorage.position}&result=deny`;
document.getElementById("approve-btn").addEventListener("click", ()=>{sendAjaxGet2(approveUrl, displayReimbursement)});
document.getElementById("deny-btn").addEventListener("click", ()=>{sendAjaxGet2(denyUrl, displayReimbursement)});

function sendAjaxGet2(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded")
	xhr.onreadystatechange = function(){
		if(xhr.readyState===4 && xhr.status===200){
			let table = document.getElementById("Reimbursement");
			table.innerHTML = " ";
			let newRow = document.createElement("tr");
			newRow.innerHTML = `<th>Id</th><th>Employee Id</th><th>Employee Name</th><th>Status</th><th>Amount</th><th>Resolved By</th><th>result</th>`;
			table.appendChild(newRow);
			callback(xhr.response);
		}
	}
	let rid = document.getElementById("rid").value;
	let requestBody = `rid=${rid}`;
	xhr.send(requestBody);
}


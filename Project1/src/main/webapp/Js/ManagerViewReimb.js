let requestUrl = "http://localhost:8081/Project1/manager-view-reimb";

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	
	xhr.open("GET", url,true);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState===4 && xhr.status===200){
			
			callback(xhr.getResponseHeader("reimbursementJSON"));
		}
	}

	xhr.send();
}

function showEmployeeReimbursementsWithName(reimbursementJSON){
	let reimbursements = JSON.parse(reimbursementJSON);
	let table = document.getElementById("reimb-table-w-name");
	for(let reimbursement of reimbursements){
		let newRow = document.createElement("tr");
		newRow.innerHTML = `<td>${reimbursement.empName}</td><td>${reimbursement.reimbId}</td><td>${reimbursement.amount}</td><td>${reimbursement.description}</td>`;
		table.appendChild(newRow);
		let option = document.createElement("option");
		option.setAttribute("value",`${reimbursement.reimbId}`);
		option.innerHTML = `${reimbursement.reimbId}`;
		console.log(option);
		document.getElementById("dropdown").appendChild(option);
	}
}


sendAjaxGet(requestUrl,showEmployeeReimbursementsWithName);
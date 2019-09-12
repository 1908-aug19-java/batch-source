/**
 * 
 */

console.log("Hello from Directory.js")

let requestUrl = "http://localhost:8080/FrontControllerDemo/api/employees";

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState===4 && xhr.status===200){
			callback(xhr.response);
		}
	}
	
	xhr.send();
}

function displayEmployees(employeeJSON){
//	console.log(employeeJSON);
	if(employeeJSON){
		let employees = JSON.parse(employeeJSON);
		
		let table = document.getElementById("empl-table");
		table.hidden = false;
		
		for(let employee of employees){
			let newRow = document.createElement("tr");
			let deptName = employee.department.name;
			if(!deptName){
				deptName = "n/a";
			}
			newRow.innerHTML = `<td>${employee.name}</td><td>${deptName}</td>`;
			table.appendChild(newRow);
			
		}
	} else {
		console.log("issue getting employees");
	}
}


sendAjaxGet(requestUrl, displayEmployees);



let token = sessionStorage.getItem("token");
if(!token){
	window.location.href="http://localhost:8088/Project1/Login";
}


let requestUrl = "http://localhost:8088/Project1/api/employees" + `?username=${sessionStorage.username}&position=${sessionStorage.position}`;
sendAjaxGet(requestUrl, displayInfo);

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

function displayInfo(infoJSON){
//	console.log(employeeJSON);
	if(infoJSON){
		let employees = JSON.parse(infoJSON);
		
		let table = document.getElementById("Info");
		table.hidden = false;
		
		for(let employee of employees){
			let newRow = document.createElement("tr");
			newRow.innerHTML = `<td>${employee.id}</td><td>${employee.firstName}</td><td>${employee.lastName}</td><td>${employee.phone}</td><td>${employee.address}</td>`;
			table.appendChild(newRow);
			
		} 
	/*	let newRow = document.createElement("tr");
		newRow.innerHTML = `<td>${employee.id}</td><td>${employee.firstName}</td><td>${employee.lastName}</td><td>${employee.phone}</td><td>${employee.address}</td>`;
		table.appendChild(newRow); */
			
		}
}

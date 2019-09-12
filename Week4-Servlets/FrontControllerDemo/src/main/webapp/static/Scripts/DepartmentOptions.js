/**
 * 
 */
let requestUrl = "http://localhost:8080/FrontControllerDemo/api/departments";


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

function displayOptions(deptJSON){
	let departments = JSON.parse(deptJSON);
//	console.log(departments);
	
	let deptSelect = document.getElementById("dept-select");
	for(let dept of departments){
		let newOption = document.createElement("option");
		newOption.value = dept.id;
		newOption.innerHTML = dept.name;
		deptSelect.appendChild(newOption);
	}
	
}

sendAjaxGet(requestUrl, displayOptions);
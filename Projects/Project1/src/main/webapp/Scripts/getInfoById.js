var baseURL = "http://localhost:8080/Project1/GetEmployeeByIdServlet";
console.log("in javascript get request info by id ");

window.onload = function() {
	getinfo();
	
 }
let getinfo = function() {
	
	let xhr = new XMLHttpRequest();	

	xhr.onreadystatechange = function () {
		if (xhr.status === 200 && xhr.readyState === 4) {
			let resp= JSON.parse(sessionStorage.list);
			//console.log("resp",resp);
			addTbaleRows(resp);	
		}
	};
	xhr.open("GET", baseURL);
	xhr.send();
}
function addTbaleRows(resp) {   
	//for(let e in resp){	
		
		let row = document.createElement("tr");		
		let tdEmpId=resp.empId;
		let tdName=resp.name;
		let tdEmail=resp.email;
		let tdman =resp.managerId;

		if ( (resp.managerId)=="0") {    
	        	tdman = "Not A Manager";
		}
	        	else{
	        	tdman = "Manager";
	       }

	 row.innerHTML = `<td>${tdEmail}</td><td>${tdEmpId}</td><td>${tdman}</td><td>${tdName}</td>`;
     document.getElementById("Employee-table").appendChild(row);	
	//}
}
var baseURL = "http://localhost:8080/Project1/GetEmployeesServlets";
window.onload = function() {
	getEmployees();
 }
let getEmployees = function() {
	
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function () {
		if (xhr.status === 200 && xhr.readyState === 4) {
			const list = JSON.parse(xhr.responseText);
			console.log(list);	
		    addTbaleRows(list);
		}	
	};
	xhr.open("GET", baseURL);
	xhr.send();	
}
let addTbaleRows=function(list) {
	for(let e in list){
	    let row = document.createElement("tr");

        let tdId = list[e].empId;
        let tdNam = list[e].name;
		let tdEm = list[e].email;
		let tdMan = list[e].managerId;
    
	 row.innerHTML = `<td>${tdId}</td><td>${tdNam}</td><td>${tdEm}</td><td>${tdMan}</td>`;
     document.getElementById("Empl-table").appendChild(row);	
	}
}
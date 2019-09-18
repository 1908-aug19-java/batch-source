var baseURL = "http://localhost:8080/Project1/GetRequestByIdServlet";
console.log("in javascript get requestby id ");

window.onload = function() {
	getinfo();

}
let getinfo = function() {
	//console.log("Makes");
	let xhr = new XMLHttpRequest();	

	xhr.onreadystatechange = function () {
		if (xhr.status === 200 && xhr.readyState === 4) {
			let list= JSON.parse(sessionStorage.list);
			console.log(list);
			addTbaleRows(list);	
		}
	};
	xhr.open("get", baseURL);
	xhr.send();
}
function addTbaleRows(list) {   
	console.log(list);
	for(let r in list){
		let row = document.createElement("tr");
		
		let tdNum = list[r].reqNum;
		let tdId = list[r].emplId;
		let tdDesc = list[r].reason;
		let tdAmt = list[r].amount;
		let tdStat = list[r].status;
		let tdman = list[r].managerId;
	
		switch (list[r].status) {
	        case "0":
	        	tdStat = "pending";
	            break;
	        case "1":
	        	tdStat = "Approved";
	            break;
	        case "2":
	        	tdStat = "Rejected";
	            break;
	        }	
	 row.innerHTML = `<td>${tdNum}</td><td>${tdId}</td><td>${tdDesc}</td><td>${tdAmt}</td><td>${tdStat}</td><td>${tdman}</td`;
     document.getElementById("Requests-table").appendChild(row);	
	}
}
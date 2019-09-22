//var baseURL = "http://localhost:8080/Project1/NewReimbServlet";
//
//window.onload = () => {
//	document.getElementById("submit").addEventListener("click", submitReimb);
//	console.log("in javascript")
//}
//const submitReimb = () => {
//	let xhr = new XMLHttpRequest();
//	let data= getReimb();
//	console.log(data);	
//	xhr.onreadystatechange = () => {
//		if (xhr.status === 200 && xhr.readyState === 4) {
//			let json = xhr.responseText;
//			console.log(json);
//		}
//	}	
//	xhr.open("POST",  baseURL);
//	xhr.send(JSON.stringify(data));
//}
//let getReimb= () => {
//
//	let descripText = document.getElementById("description").value;
//	let amountText = document.getElementById("amount").value;
//	let idText = document.getElementById("id").value;
//	return {
//		emplId: idText,
//		reason: descripText,
//		amount: amountText,		
//	}
//}
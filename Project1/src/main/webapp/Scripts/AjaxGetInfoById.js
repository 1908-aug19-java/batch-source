/**
 * AjaxGetInfoById.js
 */
/**
 * 
 */
document.getElementById("submit").addEventListener("click", doSomething);
function doSomething(e){
e.preventDefault();
let id = document.getElementById("id").value;
let baseURL = "http://localhost:8080/Project1/GetEmployeeByIdServlet" + "?id=" + id;
let xhr = new XMLHttpRequest();	

	xhr.onreadystatechange = function () {
		if (xhr.status === 200 && xhr.readyState === 4) {
			sessionStorage.list =xhr.response;
			console.log(sessionStorage.list);
            window.location.href = "http://localhost:8080/Project1/GetEmployeeByIdServlet";
		}
	};
	xhr.open("post", baseURL);
	xhr.send();
}
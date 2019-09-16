(function(){
   const GLOBAL_LIMIT = 5;
	let tablePndingPage = 0;
	let tableApprovedPage = 0;

	populateTable("#all_employees_tbody", "ua_id ASC", GLOBAL_LIMIT, 0, `&authority=EMPLOYEE`);

	function populateTable(selector, ORDERBY, LIMIT, OFFSET, map) {
		if (map == undefined) { map = "" }
		let url = `http://localhost:8080/ers/api/user-accounts?ORDERBY=${ORDERBY}&LIMIT=${LIMIT}&OFFSET=${OFFSET}${map}`;
		let xhr = new XMLHttpRequest();
		xhr.open("GET", url);
		xhr.responseType = "json";
		xhr.setRequestHeader("Authorization", JSON.parse(sessionStorage.ers_auth));
		xhr.onload = function () {
			let status = xhr.status;
			if (status == 200) {
				console.log("Retrieve Successful")
				console.log(xhr.response)
				addRows(xhr.response, selector);
			} else if (status == 401) {
				console.log("Authorization required");
				goToPage("login")
			} else {
				console.log("Recieved status code: " + xhr.status);
			}
		};
		xhr.send();
	}

	function addRows(userAccounts, selector) {
		let tableBody = document.querySelector(selector);
		tableBody.innerHTML = "";
		for (let userAccount of userAccounts) {
			let row =
				`<tr>
			<td>${userAccount.email}</td>
			<td>${userAccount.firstName}</td>
			<td>${userAccount.lastName}</td>`;
			tableBody.innerHTML += row;
		}
	};
})();
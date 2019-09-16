(function () {

	const GLOBAL_LIMIT = 5;
	let tablePndingPage = 0;
	let tableApprovedPage = 0;

	document.querySelector("#submit_reimbursment").addEventListener("click", submitReiumbursement);
	let jwt = jwt_decode(sessionStorage.ers_auth);
	populateTable("#pending_tbody", "PENDING", "r_id ASC", GLOBAL_LIMIT, 0, `&email=${jwt.email}`);
	populateTable("#approved_tbody", "APPROVED", "r_id ASC", GLOBAL_LIMIT, 0, `&email=${jwt.email}`);

	function submitReiumbursement(e) {
		e.preventDefault();
		let reimbursment_amount = document.querySelector("#reimbursment_amount").value;
		let url = `http://localhost:8080/ers/api/reimbursements`;
		let xhr = new XMLHttpRequest();
		xhr.open("POST", url);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.setRequestHeader("Authorization", JSON.parse(sessionStorage.ers_auth));
		xhr.onload = function () {
			document.querySelector("#reimbursment_info").innerHTML = "";
			if (xhr.status == 200) {
				document.querySelector("#reimbursment_info").innerHTML = `Reimbursement Submitted Succesfully. Amount: $${reimbursment_amount} `;

			} else {
				console.log("Recieved status code: " + xhr.status + " " + xhr.statusText);
				if (xhr.getResponseHeader("Authorization") == null) {
					console.log("Failed to authenticate")
					sessionStorage.ers_auth = null;
					goToPage("login");
				}
			}
		};
		xhr.send(`reimbursment_amount=${reimbursment_amount}`);
	}

	function populateTable(selector, status, ORDERBY, LIMIT, OFFSET, map) {
		if (map == undefined) { map = "" }
		let url = `http://localhost:8080/ers/api/reimbursements?status=${status}&ORDERBY=${ORDERBY}&LIMIT=${LIMIT}&OFFSET=${OFFSET}${map}`;
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

	function addRows(reimbursements, selector) {
		let tableBody = document.querySelector(selector);
		tableBody.innerHTML = "";
		for (let reimbursement of reimbursements) {
			let row =
				`<tr>
			<td>$${reimbursement.amount}</td>
			<td>${reimbursement.status}</td>
			<td>${reimbursement.dateSubmitted}</td>`
			if (selector == "#approved_tbody") {
				row += `<td>${reimbursement.managerAccount.firstName + " " + reimbursement.managerAccount.lastName}</td></tr>`;
			}
			tableBody.innerHTML += row;
		}
	};
})();
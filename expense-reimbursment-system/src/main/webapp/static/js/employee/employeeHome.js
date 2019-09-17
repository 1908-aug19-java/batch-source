(function () {

	populateTables();

	document.querySelector("#submit_reimbursment").addEventListener("click", submitReiumbursement);

	function populateTables() {
		let jwt = jwt_decode(sessionStorage.ers_auth);
		const url = "http://localhost:8080/ers/api/reimbursements";
		const columnMapPending = `email=${jwt.email}&status=PENDING&`;
		const columnMapResolved = `email=${jwt.email}&status=RESOLVED&`;
		const orderBy = "r_id ASC";
		const limit = 5;
		const offset = 0;
		const tbselectorPending = "#pending_tbody";
		const tbSelctorResloved = "#resolved_tbody";
		populateTable(url, columnMapPending, orderBy, limit, offset, tbselectorPending, addRowToTable);
		populateTable(url, columnMapResolved, orderBy, limit, offset, tbSelctorResloved, addRowToTable);

	}

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
				populateTables();
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

	function addRowToTable(data, tableBodyselector) {
		let row = `<tr>
        <td>$${data.amount}</td>
		<td>${data.status}</td>`;
		if (tableBodyselector == "#resolved_tbody") {
			row += `<td>${data.state}</td>`;
		}
		row += `<td>${data.dateSubmitted}</td>`
		if (tableBodyselector == "#resolved_tbody") {
			row += `<td>${data.managerAccount.firstName + " " + data.managerAccount.lastName}</td></tr>`;
		}
		document.querySelector(tableBodyselector).innerHTML += row;
		return row;
	}

})();
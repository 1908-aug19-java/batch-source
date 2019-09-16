(function () {
	const GLOBAL_LIMIT = 5;
	let tablePndingPage = 0;
	let tableApprovedPage = 0;

	populateTable("#pending_tbody", "PENDING", "r_id ASC", GLOBAL_LIMIT, 0);
	populateTable("#approved_tbody", "APPROVED", "r_id ASC", GLOBAL_LIMIT, 0);

	function populateTable(selector, status, ORDERBY, LIMIT, OFFSET, emailMap) {
		if(emailMap == undefined){emailMap = ""}
		let url = `http://localhost:8080/ers/api/reimbursements?${emailMap}status=${status}&ORDERBY=${ORDERBY}&LIMIT=${LIMIT}&OFFSET=${OFFSET}`;
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
			<td>${reimbursement.dateSubmitted}</td>
			<td>${reimbursement.employeeAccount.firstName + " " + reimbursement.employeeAccount.lastName}</td>`;
			if (selector == "#pending_tbody") {
				row += `<td><button id="id_${reimbursement.id}" type="button" class="btn btn-primary btn-center">Approve Reimbursment</button></td></tr>`;
				tableBody.insertAdjacentHTML("beforeend", row);

				let jwt = jwt_decode(sessionStorage.ers_auth);
				let currentEmail = jwt.email;
				document.querySelector(`#id_${reimbursement.id}`).addEventListener("click", () => { approve(reimbursement.id, currentEmail); });
			} else if (selector == "#approved_tbody") {
				row += `<td>${reimbursement.managerAccount.firstName + " " + reimbursement.managerAccount.lastName}</td></tr>`;
				tableBody.innerHTML += row;
			}
		}
	};

	function approve(id, email) {
		let url = `http://localhost:8080/ers/api/reimbursements?id=${id}&email=${email}`;
		let xhr = new XMLHttpRequest();
		xhr.open("PUT", url);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.setRequestHeader("Authorization", JSON.parse(sessionStorage.ers_auth));
		xhr.onload = function () {
			let status = xhr.status;
			if (status == 200) {
				console.log("Reimbursement update Successful");
				populateTable("#pending_tbody", "PENDING", "r_id ASC", 5, 0);
				populateTable("#approved_tbody", "APPROVED", "r_id ASC", 5, 0);

			} else if (status == 401) {
				console.log("Authorization required");
				goToPage("login")
			} else {
				console.log("Recieved status code: " + xhr.status);
			}

		};
		xhr.send();
	}

	function updatePendingTable() {

	}

	function updateResolvedTable() {

	}

	// function previous(tablePage) {
	// 	let previous = "";
	// 	if (tablePage == 1) {
	// 		previous += "<li class='page-item disabled'>"
	// 			+ "<a class='page-link'>"
	// 			+ "Previous"
	// 			+ "</a>"
	// 			+ "</li>";
	// 	} else {
	// 		previous += "<li class='page-item'>"
	// 			+ "<a class='page-link' href=''>"
	// 			+ "Previous"
	// 			+ "</a>"
	// 			+ "</li>";
	// 	}
	// 	return previous;
	// }

	// function jumpPrevious(tablePage) {
	// 	let previous = "";
	// 	if (tablePage < 11) {
	// 		previous += "<li class='page-item disabled'>"
	// 			+ "<a class='page-link'>"
	// 			+ "<span aria-hidden='true'>"
	// 			+ "&laquo;"
	// 			+ "</span>"
	// 			+ "<span class='sr-only'>"
	// 			+ "Previous"
	// 			+ "</span>"
	// 			+ "</a>"
	// 			+ "</li>";
	// 	} else {
	// 		previous += "<li class='page-item'>"
	// 			+ "<a class='page-link' href=''/Search?Selection=jumpPrevious'>"
	// 			+ "<span aria-hidden='true'>"
	// 			+ "&laquo;"
	// 			+ "</span>"
	// 			+ "<span class='sr-only'>"
	// 			+ "Previous"
	// 			+ "</span>"
	// 			+ "</a>"
	// 			+ "</li>";
	// 	}
	// 	return previous;
	// }

	// function next(tablePage, pageCount) {
	// 	let next = "";
	// 	if (tablePage == pageCount) {
	// 		next += "<li class='page-item disabled'>"
	// 			+ "<a class='page-link'>"
	// 			+ "Next"
	// 			+ "</a>"
	// 			+ "</li>";
	// 	} else {
	// 		next += "<li class='page-item'>"
	// 			+ "<a class='page-link' href='/Search?Selection=next'>"
	// 			+ "Next"
	// 			+ "</a>"
	// 			+ "</li>";
	// 	}
	// 	return next;
	// }

	// function jumpNext(tablePage, pageCount) {
	// 	let next = "";
	// 	if (tablePage > pageCount - 10) {
	// 		next += "<li class='page-item disabled'>"
	// 			+ "<a class='page-link'>"
	// 			+ "<span aria-hidden='true'>"
	// 			+ "&raquo;"
	// 			+ "</span>"
	// 			+ "<span class='sr-only'>"
	// 			+ "Next"
	// 			+ "</span>"
	// 			+ "</a>"
	// 			+ "</li>";
	// 	} else {
	// 		next += "<li class='page-item'>"
	// 			+ "<a class='page-link' href='/Search?Selection=jumpNext'>"
	// 			+ "<span aria-hidden='true'>"
	// 			+ "&raquo;"
	// 			+ "</span>"
	// 			+ "<span class='sr-only'>"
	// 			+ "Next"
	// 			+ "</a>"
	// 			+ "</li>";
	// 	}
	// 	return next;
	// }

	// function goTo(tablePage, pageCount, increment) {
	// 	let count = getCount(pageCount, increment);
	// 	let goTo = "";
	// 	for (int i = 1 + inc; i <= count; i++) {
	// 		if (tablePage == i) {
	// 			goTo += "<li class='page-item active'>"
	// 				+ "<a class='page-link' href='#'>"
	// 				+ i
	// 				+ "</a>"
	// 				+ "</li>";
	// 		} else {
	// 			goTo += "<li class='page-item'>"
	// 				+ "<a class='page-link' href='/Search?Selection=goTo&Page=" + i + "'>"
	// 				+ i
	// 				+ "</a>"
	// 				+ "</li>";
	// 		}
	// 	}
	// 	return goTo;
	// }

	// function getCount(pageCount, increment) {
	// 	let i = 1 + increment;
	// 	let count = 0;
	// 	if (pageCount >= i + 10) {
	// 		count = i + 9;
	// 	} else {
	// 		count = pageCount;
	// 	}
	// 	return count;
	// }
})();
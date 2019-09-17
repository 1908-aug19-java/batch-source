(function () {

	populateTables();

	function populateTables() {
		const url = "http://localhost:8080/ers/api/reimbursements";
		const columnMapPending = "status=PENDING&";
		const columnMapResolved = "status=RESOLVED&";
		const orderBy = "r_id ASC";
		const limit = 5;
		const offset = 0;
		const tbselectorPending = "#pending_tbody";
		const tbSelctorResolved = "#resolved_tbody";
		populateTable(url, columnMapPending, orderBy, limit, offset, tbselectorPending, addRowToTable);
		populateTable(url, columnMapResolved, orderBy, limit, offset, tbSelctorResolved, addRowToTable);
	}

	function addRowToTable(data, tableBodyselector) {
		let row =
			`<tr><td>$${data.amount}</td>
			<td>${data.status}</td>`;
		if (tableBodyselector == "#resolved_tbody") {
			row += `<td>${data.state}</td>`;
		}
		row += `<td>${data.dateSubmitted}</td>
			<td>${data.employeeAccount.firstName + " " + data.employeeAccount.lastName}</td>`;
		if (tableBodyselector == "#pending_tbody") {
			row += `<td><button id="approve_${data.id}" type="button" class="btn btn-primary btn-center btn-size">Approve</button></td>
					<td><button id="deny_${data.id}" type="button" class="btn btn-primary btn-center btn-size">Deny</button></td></tr>`;
			document.querySelector(tableBodyselector).insertAdjacentHTML("beforeend", row);
			let jwt = jwt_decode(sessionStorage.ers_auth);
			let currentEmail = jwt.email;
			let approvedMap = `id=${data.id}&email=${currentEmail}&state=${"APPROVED"}`;
			let deniedMap = `id=${data.id}&email=${currentEmail}&state=${"DENIED"}`;
			document.querySelector(`#approve_${data.id}`).addEventListener("click", () => { approve(approvedMap, populateTables); });
			document.querySelector(`#deny_${data.id}`).addEventListener("click", () => { approve(deniedMap, populateTables); });
		} else if (tableBodyselector == "#resolved_tbody") {
			row += `<td>${data.managerAccount.firstName + " " + data.managerAccount.lastName}</td></tr>`;
			document.querySelector(tableBodyselector).innerHTML += row;
		}
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
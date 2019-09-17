(function () {

	populateTables();

	function populateTables() {
		const url = "http://localhost:8080/ers/api/user-accounts";
		const columnMap = "authority=EMPLOYEE&";
		const orderBy = "ua_id ASC";
		const limit = 5;
		const offset = 0;
		const tableBodyselector = "#all_employees_tbody";
		populateTable(url, columnMap, orderBy, limit, offset, tableBodyselector, addRowToTable);
	}

	function addRowToTable(data, tableBodyselector) {
		let row =
			`<tr><td><button id="id_${data.id}" type="button" class="btn btn-link">${data.email}</button></td>
			<td>${data.firstName} ${data.lastName}</td>`;
		document.querySelector(tableBodyselector).insertAdjacentHTML("beforeend", row);
		document.querySelector(`#id_${data.id}`).addEventListener("click", () => { sessionStorage.managerEmployeeId = data.id; goToPage("managerEmployee", "managerHeader", "applicationFooter"); });
	}

})();
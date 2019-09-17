(function () {

   populateTables();

   function populateTables() {
      const url = "http://localhost:8080/ers/api/reimbursements";
      const columnMap = `status=PENDING&e_id=${sessionStorage.managerEmployeeId}&`;
      const orderBy = "r_id ASC";
      const limit = 5;
      const offset = 0;
      const tableBodyselector = "#pending_tbody";
      const loopOptionalCallback = false;
      populateTable(url, columnMap, orderBy, limit, offset, tableBodyselector, addRowToTable, setTableTitle, loopOptionalCallback);
   }

   function addRowToTable(data, tableBodyselector) {
      let row =
         `<tr><td>$${data.amount}</td>
      <td>${data.status}</td>
      <td>${data.dateSubmitted}</td>
      <td><button id="approve_${data.id}" type="button" class="btn btn-primary btn-center btn-size">Approve</button></td>
		<td><button id="deny_${data.id}" type="button" class="btn btn-primary btn-center btn-size">Deny</button></td></tr>`;
      document.querySelector(tableBodyselector).insertAdjacentHTML("beforeend", row);

      let jwt = jwt_decode(sessionStorage.ers_auth);
      let currentEmail = jwt.email;
      let approvedMap = `id=${data.id}&email=${currentEmail}&state=${"APPROVED"}`;
      let deniedMap = `id=${data.id}&email=${currentEmail}&state=${"DENIED"}`;
      document.querySelector(`#approve_${data.id}`).addEventListener("click", () => { approve(approvedMap, populateTables); });
      document.querySelector(`#deny_${data.id}`).addEventListener("click", () => { approve(deniedMap, populateTables); });
   }

   function setTableTitle(data, tableBodyselector) {
      console.log(data)
      let tableTitle = document.querySelector(tableBodyselector).parentElement.parentElement.firstElementChild;
      tableTitle.innerHTML = data.employeeAccount.firstName + " " + data.employeeAccount.lastName;
   }

})();
(function(){
	document.querySelector("#submit_reimbursment").addEventListener("click", submitReiumbursement);
	function submitReiumbursement(e) {
		e.preventDefault(); 
		let reimbursment_amount = document.querySelector("#reimbursment_amount").value;
		let url = `http://localhost:8080/ers/api/reimbursements`;
		let xhr = new XMLHttpRequest();
		xhr.open("POST", url);
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		xhr.setRequestHeader("Authorization", JSON.parse(sessionStorage.ers_auth));
		xhr.onload = function () {
		  if (xhr.status == 200) {
			console.log("Reimbursement Submitted")
		  } else {
			console.log("Recieved status code: " + xhr.status + " " + xhr.statusText);
			if(xhr.getResponseHeader("Authorization") == null){
				console.log("Failed to authenticate")
				sessionStorage.ers_auth = null;
				goToPage("login");
			}
		  }
		};
		xhr.send(`reimbursment_amount=${reimbursment_amount}`);
	  }
})();
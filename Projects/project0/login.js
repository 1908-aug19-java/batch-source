document.getElementById("login-btn").addEventListener("click",RequestLogin);
//use the format she use in last code  and make username and password;button as ides)

function loginForm() {
	let username = $('#username').val();
	let password = $('#password').val();
	
	let data = {
		"username": username,
		"password": password
	};
	
	login(data, (data, status) => {
		if (data == "") {
			location.href = "/home";
		} else {
			modal("Failure", data);
		}
	});
}
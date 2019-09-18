let token = sessionStorage.getItem("token");
console.log(token);

if(!token){
	console.log("non authorized");
	window.location.href="http:/localhost:8080/RentEventsApp/login"
}else{
	console.log("is authorized");
	let tokenArr = token.split(":");
	console.log(tokenArr);
	if(tokenArr.length===2){
		
		let baseUrl = "http://localhost:8080/RentEventsApp/api/users?id=";
		sendAjaxGet(baseUrl+tokenArr[0],displayName)
	}else{
		
		windows.locations.href="http://localhost:8080/RentEventsApp/login";
	}
	
	
	
}
function sendAjaxGet(url,callback){
	
	let xhr = new XMLHttpRequest();
	xhr.open("GET",url);
	xhr.onreadystatechange = function(){
		
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState === 4){
			window.location.href="http://localhost:8080/Experiments/login";
			
		}
		
	}
	
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayName(xhr){
	console.log("home page");
//	let user= JSON.parse(xhr,response);
//	
//	document.getElementById("user").innerHTML = user.username;
	
}

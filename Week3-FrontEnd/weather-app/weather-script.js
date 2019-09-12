

document.getElementById("submit-btn").addEventListener("click", searchWeather);

let baseUrl = "http://api-cdn.apixu.com/v1/current.json?key=4f9095e1ed93462689a193305182003&q=";

// this is the method which is invoked when we click on our submit button 
function searchWeather(){

    removeError();

    let zipInput = document.getElementById("zip-input").value;

    // validate zipInput
    if(zipInput.length === 5){
        sendAjaxGet(baseUrl+zipInput, displayWeather, displayError);
    } else {
        displayError();
    }

}

// define the ajax workflow for a get request, including a callback function 
    // for a successful request and also an unsuccessful request
function sendAjaxGet(url, callback, errorCallback){
    let xhr = new XMLHttpRequest();

    xhr.open("GET", url);

    xhr.onreadystatechange = function(){
        // console.log(xhr);
        if(xhr.readyState===4 && xhr.status===200){
            let response = xhr.response;
            let returnedObject = JSON.parse(response);
            callback(returnedObject);
        } else if (xhr.readyState === 4) {
            errorCallback();
        }
    }

    xhr.send();

}

// this is the function we provide as a callback if the request is successful 
function displayWeather(weatherInfo){
    // console.log(weatherInfo);

    document.getElementById("result").hidden = false;
    document.getElementById("loc").innerHTML = `Weather for ${weatherInfo.location.name}, ${weatherInfo.location.region}`;
    document.getElementById("condition").innerHTML = weatherInfo.current.condition.text;
    document.getElementById("icon").src = `http:${weatherInfo.current.condition.icon}`
    document.getElementById("temp").innerHTML = `${weatherInfo.current.temp_f} °F (Feels like ${weatherInfo.current.feelslike_f} °F)`;

}

// this is the function we provide as a callback if the request is unsuccessful 
function displayError(){
    document.getElementById("error").innerHTML = "invalid input";
    document.getElementById("result").hidden = true;
}

function removeError(){
    document.getElementById("error").innerHTML = "";
}
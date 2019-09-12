let JSONCars = `[
    {
        "id": 286,
        "make": "Toyota",
        "model": "Camry",
        "year": 2008
    }, {
        "id": 437,
        "make": "Chevy",
        "model": "Silverado",
        "year": 2010
    }, {
        "id": 439,
        "make": "Mazda",
        "model": "FD3S",
        "year": 1993
    }, {
        "id": 504,
        "make": "Bugatti",
        "model": "Veyron",
        "year": 2019
    }
]`

window.onload = function(){
    console.log(JSONCars);
    let cars = JSON.parse(JSONCars);
    console.log(cars);
    for(let car of cars){
        this.addTableRow(car.make, car.model, car.year, car.id);
    }
}


document.getElementById("add-car-btn").addEventListener("click", addNew);

let counter = 1000;

function addNew(){
    let make = document.getElementById("make-input").value;
    let model = document.getElementById("model-input").value;
    let year = document.getElementById("year-input").value;
    console.log(`You have submitted a ${year} ${make} ${model}`);

    let errorSpan = document.getElementById("error-message");

    if(make && model && year){
        addTableRow(make, model, year);
        errorSpan.hidden = true;
        // send this information to our server to store in db
    } else {
        errorSpan.hidden = false;
        // let errorSpan = document.getElementById("error-message");
        // errorSpan.innerHTML = "Please fill out all form fields";
        // errorSpan.style = "color: red";
    }
}

function addTableRow(make, model, year, id){
    
    let row = document.createElement("tr");
    let carId;

    if(id){
        carId = id;
    } else {    
        carId = counter++;
    }

    row.innerHTML = `<td>${carId}</td><td>${make}</td><td>${model}</td><td>${year}</td>`;
   
    /*
    let cell1 = document.createElement("td");
    let cell2 = document.createElement("td");
    let cell3 = document.createElement("td");
    let cell4 = document.createElement("td");

    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(cell4);
    // console.log(row);

    cell1.innerHTML = counter++;
    cell2.innerHTML = make;
    cell3.innerHTML = model;
    cell4.innerHTML = year;
    */

    document.getElementById("car-table").appendChild(row);
}
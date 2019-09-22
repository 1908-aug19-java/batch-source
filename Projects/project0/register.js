let JSONinfos = `[
    {
        "name": "abdellatif",
        "email": "abdo@gmail.com",
        "username": "abdo",
        "password": "pass"
    }, 
]`

window.onload = function(){
    console.log(JSONinfos);
    let infos = JSON.parse(JSONinfos);
    console.log(infos);
    for(let info of infos){
        this.addTableRow(info.name, info.email, info.username, info.password);
    }
}


document.getElementById("register-btn").addEventListener("click", addNew);

let counter = 1000;

function addNew(){
    let name = document.getElementById("name-input").value;
    let email = document.getElementById("email-input").value;
    //let username = document.getElementById("username-input").value;
    let password = document.getElementById("password-input").value;

    console.log(`You have submitted a ${name} ${email} ${username}`);

    let errorSpan = document.getElementById("error-message");

    if(name && email && username && password){
        addTableRow(name,email,username,password);
        errorSpan.hidden = true;
        // send this information to our server to store in db###############
    } else {
        errorSpan.hidden = false;
        // let errorSpan = document.getElementById("error-message");
        // errorSpan.innerHTML = "Please fill out all form fields";
        // errorSpan.style = "color: red";
    }
}

function addTableRow(name, email, username, password){
    
    let row = document.createElement("tr");
    let infoUser;

    if(username){
        infoUser = username;
    } else {    
        infoUser = counter++;
    }
    row.innerHTML = `<td>${tdNum}</td><td>${tdId}</td><td>${tdDesc}</td><td>${tdAmt}</td><td>${tdStat}</td><td>${tdRes}</td>`;
   
    /* the row line above replace all bellewo comented lines
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

    document.getElementById("profile-table").appendChild(row);
}
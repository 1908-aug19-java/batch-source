
let person = {
    isAlive: true,
    walk: function(){
        console.log("person is walking");
    }
}

let employee = {
    isWorking: true,
    getPaid: function(){
        console.log("cha-ching");
    },
    walk: function(){
        console.log("employee is walking");
    }
}

employee.__proto__ = person;


let manager = {
    supervise: function(){
        console.log("supervising employees");
    }
}
manager.__proto__ = employee;


// prototypal inheritance is achieved through this __proto__ property
// it allows the object whose __proto__ we've set to have access to the properties
// and methods associated with the object we set it to
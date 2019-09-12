///// Objects, Arrays, Functions and Parameters

let myObj = {
    0: null,
    1: "hello",
    2: "world",
    3: 76
}

let myArr = [null, "hello", "world", 76];

/*
for(let i =0; i<myArr.length; i++){
    console.log(i+": "+myArr[i]);
}

for(let index in myArr){
    console.log(index+": "+myArr[index]);
}

for(let key in myObj){
    console.log(key+": "+myObj[key]);
}

for(let value of myArr){
    console.log(value);
}

for(let value of myObj){
    console.log(value);
}
*/

/* three different ways to define functions:

function printAll(){

}

let printAll = function(){

}

// arrow nation - another ES6 addition
let findSum = (num1, num2) => {return num1+num2};
let addFive = x => x+5;

*/

// let printAll = function(a, b, c){
//     console.log(a);
//     console.log(b);
//     console.log(c);
//     return "function completed";
// }

let printAll = function(){
    for(let argument of arguments){
        console.log(argument);
    }
}



// for(let i=0;i<1000;i++){
//     console.log(30);
// }
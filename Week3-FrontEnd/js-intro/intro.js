console.log("Hello World");

//////////// truthy/falsy values

// console.log(Boolean(0));
// console.log(Boolean());
// console.log(Boolean(""));
// console.log(Boolean("seven"*7));
// console.log(Boolean(false));
// console.log(Boolean(null));
// console.log(Boolean(9));

/////////// working with objects and variable declaration
// console.log(pizza) // this causes a reference error when pizza is defined with let but is just undefined when defined with var

let pizza = {
    toppings: ["mushrooms","onions","peppers"],
    crustType: "gluten free",
    sauce: "regular",
    bake: function(){
        console.log("pizza is in the oven");
    }
    
}

// let pizza = true;

// for/of loop also an addition in ES6
// for(let topping of pizza.toppings){
// 	console.log(topping);
// }

// var allows redeclaration and reassignment
// var x = 5;
// var x = 10;

// let allows for reassignment but not redeclaration 
// let y = 6;
// y = 10;

// const allows for neither reassignment nor redeclaration
// const z = 6;
// z = 10;

// var pass = false;
// var score = 80;
// if(score>75){
//     var pass = true;
// }
// console.log(pass);


// we see that using let will allow us to scope variables to a block

// let pass = false;
// let score = 80;
// if(score>75){
//     let pass = true;
//     console.log(pass + " in block")
// }
// console.log(pass + " outside of block");

console.log(myVar);
var myVar = 15;

console.log(myVar2);
let myVar2 = 16;
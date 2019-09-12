/*
 * closures take advantage of JavaScript's lexical scoping
 * to make variables accessible/inaccessible to other segments 
 * of code 
 * 
 * functions have access to global scope
 * functions have access to the scope they've been declared in
 * 
 * how we can accomplish encapsulation in JavaScript 
 */

// we want a counter which we can increment but which we can't 
  // access its value directly

/*
let counter = 0; 
function add(){
    counter += 1;
}
*/

/*
function add2(){
    let counter = 0;
    counter += 1;
    return counter;
}
*/

function add(){
    let counter = 0;
    return function(){counter +=1; return counter};
}

let addAgain = (function(){
    let counter = 0;
    return function(){counter +=1; return counter};
})();
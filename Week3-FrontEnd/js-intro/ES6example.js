/*
 * ES6 features
 * - let, const
 * - arrow notation
 * - for/of loop
 * - symbol
 * - template literals
 */

// template literals allow for multiline strings and 
  // for the ability to include variables in the string without
  // having to break it up - we use `` ${}

let y = "hello world";

let x = `here is a 
multiline string that we can 
work with using a template literal.
I can even inject values into it, like
this one: ${y}`;


// implicit declaration using var
myVariable = 40;
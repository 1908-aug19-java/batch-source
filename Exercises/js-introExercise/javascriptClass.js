console.log("Exercises");

// 1.      Longest String 
// Define function: maxLength(array)
// Write a JavaScript to find the longest string from a given array of strings and returns the string’s array index.
let array = ['banana', 'apple', 'dog'];
let maxLength=function (arr)
{ 
    let longest= arr[0];
    for( i=0; i < arr.length; i++)
    {
        if(arr[i].length > longest.length){
            longest = arr[i];
        }
    }    
       return arr.indexOf(longest);
}
//console.log(maxLength(array));

//2 reverse an array 
let reverseArray = function (array) {
    return array.reverse();
}

//3.     Count Vowels 
//Write a JavaScript function to count the number of vowels in a given string.
//let string="AAAbdelltif";
let vowelCount= function (string){
    let vowels = [ 'a', 'e', 'i', 'o', 'u' ];
     let count=0;
     for (i=0;i<string.length;i++)
     {   for(j=0;j<vowels.length;j++)
        {
         if (string[i].toLowerCase()==vowels[j]){
             count++;
         }
        }
     }
return count
}
//console.log(vowelCount(string));
//4Email Validation     
//Create a function that checks for a valid email format

let isValidEmail= function (string){
        var re = /\S+@\S+\.\S+/;
        return re.test(string);     
}


//5.  Remove Character
//Write a JavaScript function to remove a character at the specified position of a given string and return the new string.
function removeChar(string, index)
{
    let res="";
        res= string.slice(0,index)+string.slice(index+1);
   return res;
}
//6.Bubble Sort
//Use the bubble sort algorithm to sort the array. You may need to look up the algorithm if you’re not familiar with it
//Return the sorted array.
 function bubbleSort(numArray)
 {
     var res=[];
     for( let j=0;j<numArray.length;j++)
     {
        for(let i=0;i<(numArray.length-j)-1;i++ ) 
        {
            if (numArray[i]>numArray[i+1])
            {
                var temp=numArray[i];
                numArray[i]=numArray[i+1];
                numArray[i+1]=temp;
            }
        }
         res=numArray;
     }  
     return res;
 }
 //console.log(bubbleSort([9,0,5,3,4,2]));

 //7 Even Number
// Return true if even, false if odd.
// Challenge: Do not use % operator.
 function  isEven(someNum)
 {
     //check whether last bit is 1
     if((someNum & 1)==0)
     {
        console.log(  someNum,"is even No");
     }
     else
     {
        console.log(  someNum,"is Odd No");
     }
 }
 //console.log(isEven(92));
 //8.Palindrome
 //Return true if someStr is a palindrome, otherwise return false.
 //someStr="avid vida";
 function isPalindrome(someStr)
 {
     let str="";
     str=someStr.toLowerCase();
    let len = Math.trunc(str.length / 2);
    for (var i = 0; i < len; i++)
    {
        if (str[i]!== str[(str.length - i) - 1])
        {
            console.log( someStr ,"is Not palindrome");
            return false;
        }  
    }  
        console.log( someStr ,"is  palindrome");
        return true;
       
}
 //console.log(isPalindrome("MAN NAM"));
// 9. Find Leap Year
//Create a JavaScript function that takes a date parameter and returns true if the year is a leap year in the Gregorian calendar.
 function isLeapYear(date)
 {
    return (date % 100 === 0) ? (date % 400 === 0) : (date % 4 === 0);
 }
//console.log(isLeapYear("2020"));

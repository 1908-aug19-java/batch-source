function doSomething(){
    for(let i=0; i<5; i++){
        console.log(i);
    }
}

// doSomething();

let x: number;
x = 5;
// x = true;

let bool: boolean;
let str: string;
let obj: object;

function myVoidFunction(): void {
    console.log("this function doesn't return anything");
    // return 5;
}

let anotherFunction = function(value: any){
    if(typeof value === "string" && typeof value === "number"){
        return value;
    }
}

let foreverFunction = function(){
    while(true){
        console.log("hello");
    }
}

let y: any;
// console.log(typeof y); // undefined bc we're transpiling to js 

let myArr: any[]= [true, 4]; 
myArr[3] = "hello"; 
// console.log(myArr);
// console.log(myArr[2])
// let myStrArr: string[] = ["hello", 6];


let drawPoint = (x: number, y: number) =>{
    console.log(x+", "+y);
}

// drawPoint("cats", "cheese");
drawPoint(4,5);


let drawPoint2 = (point) => {
    console.log(point.x+", "+point.y);
}

drawPoint2({x:4, y:5});
drawPoint2({name:"Carl", email:"carlg@gmail.com"});

let drawPoint3 = (point: {x:number, y:number}) => {
    console.log(point.x+", "+point.y);
}

drawPoint3({x:4, y:5});
// drawPoint3({name:"Carl", email:"carlg@gmail.com"});

/*
 * Two ways to define object types: Class or Interface
 */
interface Point{
    x:number;
    y:number;
}

let drawPoint4 = (point: Point)=> {
    console.log(point.x+", "+point.y);
}

class MyPoint{
    x: number;
    y: number;
}

let drawPoint5 = (point: MyPoint)=> {
    console.log(point.x+", "+point.y);
}

class MyPoint2{
    x: number;
    y: number;

    constructor(_x:number = 0, _y:number = 0){ //? after variable declaration indicates optional param
        this.x = _x;
        this.y = _y;
    }

    drawPoint = ()=>{
        console.log(this.x+", "+this.y);
    }
}

let p: MyPoint2 = new MyPoint2();
p.drawPoint();
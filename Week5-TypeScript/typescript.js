function doSomething() {
    for (var i = 0; i < 5; i++) {
        console.log(i);
    }
}
// doSomething();
var x;
x = 5;
// x = true;
var bool;
var str;
var obj;
function myVoidFunction() {
    console.log("this function doesn't return anything");
    // return 5;
}
var anotherFunction = function (value) {
    if (typeof value === "string" && typeof value === "number") {
        return value;
    }
};
var foreverFunction = function () {
    while (true) {
        console.log("hello");
    }
};
var y;
// console.log(typeof y); // undefined bc we're transpiling to js 
var myArr = [true, 4];
myArr[3] = "hello";
// console.log(myArr);
// console.log(myArr[2])
// let myStrArr: string[] = ["hello", 6];
var drawPoint = function (x, y) {
    console.log(x + ", " + y);
};
// drawPoint("cats", "cheese");
drawPoint(4, 5);
var drawPoint2 = function (point) {
    console.log(point.x + ", " + point.y);
};
drawPoint2({ x: 4, y: 5 });
drawPoint2({ name: "Carl", email: "carlg@gmail.com" });
var drawPoint3 = function (point) {
    console.log(point.x + ", " + point.y);
};
drawPoint3({ x: 4, y: 5 });
var drawPoint4 = function (point) {
    console.log(point.x + ", " + point.y);
};
var MyPoint = /** @class */ (function () {
    function MyPoint() {
    }
    return MyPoint;
}());
var drawPoint5 = function (point) {
    console.log(point.x + ", " + point.y);
};
var MyPoint2 = /** @class */ (function () {
    function MyPoint2(_x, _y) {
        if (_x === void 0) { _x = 0; }
        if (_y === void 0) { _y = 0; }
        var _this = this;
        this.drawPoint = function () {
            console.log(_this.x + ", " + _this.y);
        };
        this.x = _x;
        this.y = _y;
    }
    return MyPoint2;
}());
var p = new MyPoint2();
p.drawPoint();

console.log("Hello from js file");

// we can change our image and its size with javascript 
let picElements = document.getElementsByTagName("img");
// console.log(picElements);
let otterPic = picElements[0];
otterPic.src = "https://www.rspcasa.org.au/wp-content/uploads/2019/01/Adopt-a-cat-or-kitten-from-RSPCA.jpg";
otterPic.width = 250;
otterPic.alt = "a cute cat";

// adding a caption to our picture
let newNode = document.createElement("figcaption");
newNode.innerHTML = "a very cute cat in Australia";

let figureElement = document.getElementById("fig-1");
figureElement.appendChild(newNode);

// clicking on our link
let link = document.getElementById("adoption-link");
// link.click();

let pic = figureElement.firstElementChild;
// console.log(pic);

let changePic = function(event){
    // console.log(event);
    let image = event.target;
    if(image.src == "https://www.rspcasa.org.au/wp-content/uploads/2019/01/Adopt-a-cat-or-kitten-from-RSPCA.jpg"){
        image.src = "https://seaotters.com/wp-content/uploads/2018/10/1080x720-cute-sea-otter-mom-newborn-pup-02.jpg";
        image.alt = "otter pic"
        image.width = 500;
        newNode.innerHTML = "some otters";
    } else {
        image.src = "https://www.rspcasa.org.au/wp-content/uploads/2019/01/Adopt-a-cat-or-kitten-from-RSPCA.jpg";
        image.width = 250;
        image.alt = "a cute cat";
        newNode.innerHTML = "a very cute cat in Australia";
    }
}

pic.addEventListener("click", changePic);


// function Animal(numOfLegs, isWarmBlooded){
//     this.numOfLegs = numOfLegs;
//     this.isWarmBlooded = isWarmBlooded;
//     this.move = function(){
//         console.log("animal is moving");
//     }
// }

function Animal(legsInput, bloodInput){
    let numOfLegs = legsInput;
    let isWarmBlooded = bloodInput;
    
    let move = function(){
        console.log("animal is moving");
    }
    
    this.getNumOfLegs = function(){
        return numOfLegs;
    }

    this.getIsWarmBlooded = function(){
        return isWarmBlooded;
    }

    this.setNumOfLegs = function(newNumOfLegs){
        if(newNumOfLegs>0){
            numOfLegs = newNumOfLegs;
        }
    }

    this.moveAnimal = function(){
        return move();
    }
}


function Rabbit(nameInput, hopsInput){
    let name = nameInput;
    let hops = hopsInput || true;

    this.getNumOfLegs = function(){
        return 4;
    }
    this.getIsWarmBlooded = function(){
        return true;
    }

    this.setName = function(nameInput){
        name = nameInput;
    }

    this.getName = function(){
        return name;
    }

    this.getHops = function(){
        return hops;
    }

    this.__proto__ = new Animal();
}

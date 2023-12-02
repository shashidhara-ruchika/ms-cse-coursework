class chaining{

    firstFunction(){
        console.log("first");
        return this;
    }

    secondFunction(){
        console.log("second");
        return this;
    }
    
    thirdFunction(){
        console.log("third");
        return this;
    }
}

const instance=new chaining();
instance.firstFunction().secondFunction().thirdFunction();


// function firstFunction(){
//     console.log("first classless");
//     return this;
// }

// function secondFunction(){
//     console.log("second classless");
//     return this;
// }

// function thirdFunction(){
//     console.log("third classless");
//     return this;
// }

// firstFunction().secondFunction().thirdFunction()
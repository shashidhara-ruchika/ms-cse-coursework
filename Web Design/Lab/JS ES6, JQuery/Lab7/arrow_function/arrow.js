var addFunc=(a,b)=>console.log("addFunc sum: "+(a+b));
addFunc(1,3);

var c=2;
var d=5;
var addFunc2=()=>{
     console.log(" addFunc2 sum: "+(c+d));
}
addFunc2();

var addFunc3=(a,b)=>{
     let c=a+b
     console.log(" addFunc3 sum: "+(c+d));
}
addFunc3(7, 8);
console.log(c);

//for return you need to add curly braces
var addFunc4=(a,b)=>{return(a+b)};
let z= addFunc4(11,3);
console.log(z)



const p1=new Promise((resolve,reject)=>{
    setTimeout(()=>{resolve("this is p1"),1000});
    // resolve("this is p1");
})

const p2=new Promise((resolve,reject)=>{
    setTimeout(()=>{resolve("this is p2"),1000});
    // resolve("this is p2");
})

const p3=new Promise((resolve,reject)=>{
    // setTimeout(()=>{resolve("this is p3"),1000});
    resolve("this is p3");
})

Promise.all([p1,p2,p3]).then((message)=>{
    alert(message);
})

// Promise.race([p1,p2,p3]).then((message)=>{
//     alert(message)
// })


 const p_1=new Promise((resolve, reject)=>{
     resolve("p5 is successful")
 })

 p_1.then(res=>{
     console.log(res)
 }).catch(err=>{
     console.log(err)
 })


const movies=[
    {title: 'Movie one', body:'Nice'},
    {title: 'Movie two', body:'Okay'}
]

function getMovies(){
    setTimeout(()=>{
        movies.forEach((movie,index )=> {
            console.log(movie.title)
        });
    },1000);
}

function createMovies(movie){
    return new Promise((resolve,reject)=>{
        setTimeout(()=>{
            movies.push(movie);
            const err =false; 
            if(!err){
                resolve(console.log("Added a new movie."))
            }
            else{
                reject("Something is worng!")
            }
        },2000)
    })
    
}

async function init(){
    console.log("Inside async function call")
    await createMovies({title: 'Movie three',body:'superb'});
    getMovies();
}

init();

/*
Async/await is a feature in modern JavaScript that simplifies asynchronous code and makes it look more like synchronous code. 
It's built on top of Promises, which are used to handle asynchronous operations.
 */

import React, {useState, useEffect} from 'react'

const Clock = () => {
    const [date, setDate] = useState(new Date()); // Intial value for your state
    // In react we make variable using useState  this is variable and other is function to update
    const [count, setCount] = useState(0); 

    //Usage: When you need to perform side effects in your component. It's often used for data fetching, setting up a subscription, and manually changing the DOM in React components.
    useEffect(()=>{
        setInterval(tick(),1000);
    }, [date]); // dependenciy can be empty or can be dependent on any variable, If it is empty it will be update on every referesh else on

    
    useEffect(()=>{   
        console.log("user update count Entered",count);      // This will display 2nd 
        return () => {
            console.log("re rendering before deleteing",count); //It displays First
            
                 }
    },[count])

   
    const tick = ()=>{
        setDate(new Date());
    }
    
  return (
    <div>
        <h1>Hello Boston</h1>
        <h2>{date.toLocaleTimeString()} </h2>
        <h3> {count}</h3>
        <button onClick={()=>setCount(count+1)}>Increment on Click</button>
    </div>
  )
}

export default Clock;

const reducer = (state=0, action) =>{
   switch(action.type) {
    case 'Increment' : return state+1;
    case 'Decrement' : return state-1;
    case 'Reset' : return 0;
    default : return state;
   }
}

// Reducer is a pure function which is to return a new state from the intial state. It reads the payload froms from the actions. The reducer then updates the store via state accordingly

export default reducer;  
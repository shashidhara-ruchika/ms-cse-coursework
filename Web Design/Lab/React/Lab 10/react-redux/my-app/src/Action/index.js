export const increment = () => {
    return {
        type: 'Increment'
    }
}
export const decrement = () => {
    return {
        type: 'Decrement'
    }
}
export const reset = () => {
    return {
        type: 'Reset'
    }
}


//Action is a pure object which is sent or dispatched from the view. It is created to store information about the user event such as info about ==type of action==, the time of occurence or they can have payload as well.
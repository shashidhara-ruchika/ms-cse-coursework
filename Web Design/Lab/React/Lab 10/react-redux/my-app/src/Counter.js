import React, { Component } from 'react';
import { connect } from 'react-redux';//This imports the connect function from the 'react-redux' package. The connect function is used to connect your React components to the Redux store.(serves as bridges)
//he primary roles of the connect function are:State Mapping, Dispatch Actions

import { increment, decrement, reset } from './Action';

class Counter extends Component {
    render () {  //render method of the Counter component defines the structure of the UI

        const { counter, increment, decrement, reset } = this.props;
        //this.props, which is an object containing all the props passed to the Counter component.        
//Actions to modify the state (increment, decrement, reset) are dispatched through button clicks, and the component reflects the current state of the counter.
        return (
            <div className='App'>
            <h1>Counter {counter}</h1>
            <div><button onClick={increment}>Increment</button></div>
            <div><button onClick={decrement}>Decrement</button></div>
            <div><button onClick={reset}>Reset</button></div>
            </div>   
        )
    }
}
//mapStateToProps: This function maps the state from the Redux store to the component's props. Here, it maps the state to a prop named counter
const mapStateToProps = (state) => { 
    return {
        counter: state
    }
}

//This function maps dispatch actions to props. It creates prop functions (increment, decrement, reset) that when called, dispatch the corresponding actions.
const mapDispatcherToProps = (dispatch) => {
    return {
        increment: () => dispatch(increment()),
        decrement: () => dispatch(decrement()),
        reset: () => dispatch(reset()),
    }
}

export default connect(mapStateToProps, mapDispatcherToProps)(Counter); //your component

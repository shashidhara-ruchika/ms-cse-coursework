import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import { legacy_createStore as createStore} from 'redux';  //
import { Provider } from 'react-redux';  //
import reducer from './Reducer';  //

const store = createStore(reducer);
ReactDOM.render(
 <Provider store={store}>
   <App />
 </Provider>,  //The Provider component from Redux makes the Redux store available to any nested components that need to access the Redux store.
  document.getElementById('root')

);

// store ,action, reducer, dispatch view
// Store stores all the values of the status of the react application,It is a global object or central repo
// So it could be accessed from anywhere in the application

//Action is a pure object which is sent or dispatched from the view. It is created to store information about the user event such as info about type of action, the time of occurence or they can have load as well

// Reducer is a pure function which is to return a new state from the intial state. It reads the payload froms from the actions. The reducer then updates the store via state accordingly

// The dispatch function is a method provided by the Redux store. It is used to dispatch actions to the store, triggering the state change. An action in Redux is a plain JavaScript object that describes an event or change in the application. It must have a type property indicating the type of action being performed.

// Dispatching actions are typically object with with type property that signfies the type of action being performed. Dispatch function is usedd to send action to reducer  to update the state.


//Purpose: This snippet is used in applications where Redux is implemented for state management. The Provider component from Redux makes the Redux store available to any nested components that need to access the Redux store.
//Usage: It's typical in applications that manage global state using Redux. The store prop passed to Provider is the Redux store.
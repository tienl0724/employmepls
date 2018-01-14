import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { FirebaseAuth } from 'react-firebaseui';
import firebase from 'firebase';

class App extends Component {

  render() {

    const config = {
      apiKey: "AIzaSyBhA0eDrGIGtHUkuK3-HD6wKQRm3cCYCa8",
      authDomain: "employmepls-a631c.firebaseapp.com",
      databaseURL: "https://employmepls-a631c.firebaseio.com",
      projectId: "employmepls-a631c",
      storageBucket: "employmepls-a631c.appspot.com",
      messagingSenderId: "682947596416"
    };
  
    firebase.initializeApp(config);

    const uiConfig = {
      // Popup signin flow rather than redirect flow.
      signInFlow: 'popup',
      // Redirect to /signedIn after sign in is successful. Alternatively you can provide a callbacks.signInSuccess function.
      signInSuccessUrl: '/signedIn',
      // We will display Google and Facebook as auth providers.
      signInOptions: [
        firebase.auth.EmailAuthProvider.PROVIDER_ID,
      ],
    };

    console.log(firebase.auth().currentUser)
    if (firebase.auth().currentUser){
      return (
        <h1>asd</h1>
      )
    }
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
        </p>
        <FirebaseAuth uiConfig={uiConfig} firebaseAuth={firebase.auth()}/>
      </div>
    );
  }
}

export default App;

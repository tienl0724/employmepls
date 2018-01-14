import firebase from 'firebase'

const config = {
  apiKey: "AIzaSyBhA0eDrGIGtHUkuK3-HD6wKQRm3cCYCa8",
    authDomain: "employmepls-a631c.firebaseapp.com",
    databaseURL: "https://employmepls-a631c.firebaseio.com",
    projectId: "employmepls-a631c",
    storageBucket: "employmepls-a631c.appspot.com",
    messagingSenderId: "682947596416"
}

firebase.initializeApp(config)

export const ref = firebase.database().ref()
export const database = firebase.database()
export const firebaseAuth = firebase.auth
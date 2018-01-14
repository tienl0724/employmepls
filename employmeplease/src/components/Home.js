import React, { Component } from 'react'
import {database, firebaseAuth, ref } from '../config/constants'

export default class Home extends Component {

  componentDidMount(){
    var refToData = ref.child('userData')
    refToData.on("value", (snapshot) => console.log(snapshot.val()))

  }

  writeUserData() {
    /* database.ref('userData').set({
      userId: firebaseAuth().currentUser.uid,
      email: "email",
      profile_picture : "imageUrl"
    }); */
  }
  render () {



    return (
      <div>
        Home. Not Protected. Anyone can see this.
      </div>
    )
  }
}
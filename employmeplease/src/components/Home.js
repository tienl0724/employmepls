import React, { Component } from 'react'
import {database, firebaseAuth, ref } from '../config/constants'
import { Redirect } from 'react-router-dom'
import ApplicantContainer from '../containers/ApplicantContainer'
import EmployerContainer from '../containers/EmployerContainer'
import Login from './Login'

export default class Home extends Component {

  state = {
    userType: ''
  }

  componentDidMount(){
    if (firebaseAuth().currentUser !== null){
      var refToData = ref.child('userData')
      refToData.on("value", (snapshot) => {
        for(let userData in snapshot.val()){ 
          if (userData === firebaseAuth().currentUser.uid){
            this.setState({
              userType: snapshot.val()[userData].userType
            })
            return
          }
        }
        this.setState({
          userType: 'none'
        })
      })
    }
  }

  render () {
    
    if (firebaseAuth().currentUser === null){
      this.setState
    }

    if (this.state.userType === ''){
      return (
        <h1>test</h1>
      )
    }

    if (this.state.userType === 'none'){
      return(
        <Redirect to='/login' />
      )
    }

    if (this.state.userType === 'Applicant'){
      return (
        <Redirect to='/applicant/' />
      )
    } else{
      return (
        <Redirect to='employer'/>
      )
    }
  }
}
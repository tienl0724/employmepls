import React, { Component } from 'react'
import { auth } from '../helpers/auth'
import { database, firebaseAuth } from '../config/constants'

function setErrorMsg(error) {
  return {
    registerError: error.message
  }
}

export default class Register extends Component {

  constructor(props){
    super(props)

    this.handleSubmit = this.handleSubmit.bind(this)
    this.handlePropertyValue = this.handlePropertyValue.bind(this)
  }

  state = { 
    registerError: null,
    userName: '',
    userType: 'Applicant',
    email: '',
    password: ''
  }

  handlePropertyValue(event){
    this.setState({
        [event.target.name] : event.target.value
    })
  }

  handleSubmit = (e) => {
    e.preventDefault()
      auth(this.state.email, this.state.password)
      .then(() => {
        database.ref('userData').child(firebaseAuth().currentUser.uid).set({
          userType: this.state.userType,
          userName : this.state.userName
        })
      })
      .catch(e => this.setState(setErrorMsg(e)))
        
  }

  render () {
    return (
      <div className="col-sm-6 col-sm-offset-3">
        <h1>Register</h1>
        <form onSubmit={this.handleSubmit}>
        <div className="form-group">
            <label>User Name:</label>
            <input type="text" className="form-control" placeholder="User Name" name="userName" value={this.state.userName} onChange={this.handlePropertyValue} />
          </div>
          <div className="form-group">
            <label>User Type:</label>
            <select className="form-control" value={this.state.userType} onChange={this.handlePropertyValue} name="userType">
              <option value="Applicant">Applicant</option>
              <option value="Employer">Employer</option>
            </select>
          </div>
          <div className="form-group">
            <label>Email</label>
            <input className="form-control" value={this.state.email} onChange={this.handlePropertyValue} placeholder="Email" name="email"/>
          </div>
          <div className="form-group">
            <label>Password</label>
            <input type="password" className="form-control" placeholder="Password" name="password" value={this.state.password} onChange={this.handlePropertyValue} />
          </div>
          {
            this.state.registerError &&
            <div className="alert alert-danger" role="alert">
              <span className="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
              <span className="sr-only">Error:</span>
              &nbsp;{this.state.registerError}
            </div>
          }
          <button type="submit" className="btn btn-primary">Register</button>
        </form>
      </div>
    )
  }
}

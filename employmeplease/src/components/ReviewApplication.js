import React, {Component} from 'react'
import resume from './resume.JPG'
import uuidv1 from 'uuid' 
import { database, firebaseAuth } from '../config/constants'
import { Link } from 'react-router-dom'
export default class ReviewApplication extends Component{

    constructor(props){
        super(props)
    
        this.handleSubmitSuccess = this.handleSubmitSuccess.bind(this)
        this.handleSubmitFailure = this.handleSubmitFailure.bind(this)
        this.handlePropertyValue = this.handlePropertyValue.bind(this)
      }

    state = {
        comment: '',
        additional: 'No effort on resume, Not enough industry experience'
    }

    handlePropertyValue(event){
        this.setState({
            [event.target.name] : event.target.value
        })
    }

    handleSubmitSuccess(){
        
        database.ref('feedback').child(uuidv1()).set({
            comment: 'Good Job. You are accepted'
          })  
          
        this.props.closeModal()
    }

    handleSubmitFailure(){
        database.ref('feedback').child(uuidv1()).set({
            comment: this.state.comment,
            additional: this.state.additional,
            companyName: 'emp2'
          }) 
          
          
          this.props.closeModal()
    }

    render(){
        console.log(this.props.clpseModalReview)
        return(
            <div class="row">
                <div class="col">
                <img src={resume}/>
                </div>
                <div class="col">
                <div class="row">
                    <button type="button" class="btn btn-success" onClick={this.handleSubmitSuccess}>Keep</button>
                </div>
                <div class="row reason">
                    <form>
                    <input type="checkbox" value="effort" />No effort on resume<br/>
                    <input type="checkbox" value="req"/>Basic requirement not met<br/>
                    <input type="checkbox" value="exp"/>Not enough industry experience<br/>
                    </form>
                </div>
                <div class="row">
                    <form class="comment">
                    Comment: <input type="text"  class="comment" name="comment" value={this.state.comment} onChange={this.handlePropertyValue} />
                    </form>
                </div>
                <div class="row reject">
                    <button type="button" class="btn btn-danger" onClick={this.handleSubmitFailure}>Reject</button>
                </div>
            </div>
        </div>
        )
    }
}
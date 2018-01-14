import React, {Component} from 'react'
import uuidv1 from 'uuid' 
import { database, firebaseAuth } from '../config/constants'

export default class AddJob extends Component{

    constructor(props){
        super(props)
    
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handlePropertyValue = this.handlePropertyValue.bind(this)
      }

    state = {
        title: '',
        description: '',
        requirements: '',
        salary: '',
        location: '',
        jobType: 'Full-time',
        startDate: ''
    }

    handlePropertyValue(event){
        this.setState({
            [event.target.name] : event.target.value
        })
    }

    handleSubmit(event){
        event.preventDefault()

        database.ref('jobs').child(uuidv1()).set({
            company: 'test',
            description: this.state.description,
            location: this.state.location,
            requirements: this.state.requirements,
            salary: this.state.salary,
            startDate: this.state.startDate,
            type: this.state.jobType,
            authorId: firebaseAuth().currentUser.uid,
            title: this.state.title
          })  
          
          this.props.closeModal()
    }

    render(){
        return (
            <div className="row">
                <form onSubmit={this.handleSubmit}>
                <div className="form-group">
                    <label >Title</label>
                    <input type="text" className="form-control" name="title" value={this.state.title} onChange={this.handlePropertyValue}/>
                </div>
                <div className="form-group">
                    <label >Description</label>
                    <textarea className="form-control" rows="3" name="description" value={this.state.description} onChange={this.handlePropertyValue}></textarea>
                </div>
                <div className="form-group">
                    <label>Requirements</label>
                    <input type="text" className="form-control" name="requirements" value={this.state.requirements} onChange={this.handlePropertyValue}/>
                </div>
                <div className="form-group">
                    <label>Location</label>
                    <input type="text" className="form-control" name="location" value={this.state.location} onChange={this.handlePropertyValue}/>
                </div>
                <div className="form-group">
                    <label>Salary</label>
                    <input type="text" className="form-control" name="salary" value={this.state.salary} onChange={this.handlePropertyValue}/>
                </div>
                <div className="form-group">
                    <label>Type</label>
                    <select className="form-control" name="jobType" value={this.state.jobType} onChange={this.handlePropertyValue}>
                        <option value="Applicant">Part-time</option>
                        <option value="Employer">Full-time</option>
                        <option value="Co-op">Co-op</option>
                        <option value="Internship">Internship</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Start Date</label>
                    <input type="text" className="form-control" name="startDate" value={this.state.startDate} onChange={this.handlePropertyValue}/>
                </div>
                <button type="submit">Add</button>
                </form>
            </div>
        )
    }
}
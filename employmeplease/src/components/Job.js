import React, { Component } from 'react'

export default class Job extends Component{

    render(){
        return (
            <div className="col-md-12 m-4">
                <div className="card">
                    <h5 className="card-header">{this.props.job.title}</h5>
                    <div className="card-body">
                        <h5 className="card-title">Requirements: {this.props.job.requirements}</h5>
                        <p className="card-text">{this.props.job.desciption}</p>
                        <a href="#" className="btn btn-primary">Company Name: {this.props.job.company}</a>
                    </div>
                    <span className="ml-4">Location: <strong>{this.props.job.location}</strong></span>
                    <span className="ml-4">Salary: <strong className="">{this.props.job.salary}</strong></span>
                    <span className="ml-4">Start date: <strong>{this.props.job.startDate}</strong></span>
                </div>
            </div>
        )
    }
}
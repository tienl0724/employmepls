import React, { Component } from 'react'

export default class Application extends Component{

    componentDidMount(){
        
    }

    render(){
        return (
            <div className="col-md-12 p-2">
                <div className="card">
                    <h5 className="card-header">{this.props.app.jobName}</h5>
                    <div className="card-body">
                        <h5 className="card-title">{this.props.app.email}</h5>
                    </div>
                </div>
            </div>
        )
    }
}
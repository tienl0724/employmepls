import React , {Component} from 'react'
import Modal from 'react-modal'
import AddJob from '../components/AddJob'
import {database, firebaseAuth, ref } from '../config/constants'
import Job from '../components/Job'
import Application from '../components/Application'
import ReviewApplication from '../components/ReviewApplication'

export default class EmployerContainer extends Component{
    constructor(props){
        super(props)

        Modal.setAppElement('body');
    }

    state = {
        isAddPostOpen: false,
        jobs: [],
        applications: [],
        isReviewModalOpen: false
    }

    addToApplications(app){
        let myApps = this.state.applications.slice()
        this.setState({
            applications: this.state.applications.concat(app)
        })
    }

    componentDidMount(){
        var refToData = ref.child('jobs')
        refToData.on("value", (snapshot) => {
            let jobs = snapshot.val()
            for(let job in jobs){ 
              if (jobs[job].authorId === firebaseAuth().currentUser.uid){
                  this.setState({
                      jobs: this.state.jobs.concat({
                          ...jobs[job],
                          jobID: job
                      })
                  })
              }
            }
          })
          var refToApplications = ref.child('applications')
          refToApplications.on("value", (snapshot) => {
            let applications = snapshot.val()
            for(let application in applications){
                console.log(this.state.jobs)
                console.log(applications[application].jobID) 
                let jobsTmp = this.state.jobs.slice()
                console.log(jobsTmp[0])
                for(let i = 0; i < jobsTmp.length; i++){
                    //console.log(this.state.jobs[i].jobID + " * - * " + applications[application].jobID)
                    if (jobsTmp[i] === null){
                        return
                    }
                    if (jobsTmp[i].jobID === applications[application].jobID){
                       this.addToApplications(applications[application])
                       
                       //this.state.applications.concat(applications[application])
                    }
                }
              }
          })
    }

    closeReviewModal = () => this.setState(() => ({ isReviewModalOpen: false}))
    closeAddPostModal = () => this.setState(() => ({ isAddPostOpen: false}))

    render(){
        const customStyles = {
            content : {
              top                   : '50%',
              left                  : '50%',
              right                 : 'auto',
              bottom                : 'auto',
              marginRight           : '-50%',
              transform             : 'translate(-50%, -50%)'
            }
        };
        console.log(this.state.applications + "appps")
        return (
            <div className="row">
                <div className="col-md-12">
                    <button onClick={() => this.setState({isAddPostOpen: true})}> 
                        Create new Job offer
                    </button>
                    <h1>Job Postings</h1>
                </div>
                <Modal
                isOpen={this.state.isAddPostOpen}
                onRequestClose={this.closeAddPostModal}
                contentLabel='Modal'
                style={customStyles}>
                    <AddJob closeModal={this.closeAddPostModal}/>
                </Modal>

                <Modal
                isOpen={this.state.isReviewModalOpen}
                onRequestClose={() => this.setState({isReviewModalOpen: false})}
                contentLabel='Modal'
                style={customStyles}>
                    <h1>asd</h1>
                    <ReviewApplication closeModal={this.closeReviewModal}/>
                </Modal>

                {this.state.jobs.length === 0 &&
                <h1>No jobs posted yet</h1>}

                {this.state.jobs.length !== 0 &&
                this.state.jobs.map(j => (
                    <Job job={j} key={j.title + j.salary}/>
                ))}

                <div className="col-md-12 p-4">
                    <h1>Applications Postings</h1>
                </div>

                {this.state.applications.length === 0 &&
                <h1>No applications received</h1>}

                {this.state.applications.length !== 0 &&
                this.state.applications.map(a => (
                    <div class="row">
                        <button onClick={() => this.setState({isReviewModalOpen: true})}>Review</button>
                        <Application app={a} onClickOpenModal={() => this.setState({isReviewModalOpen: false})} />
                    </div>
                ))}
            </div>
        )
    }
}
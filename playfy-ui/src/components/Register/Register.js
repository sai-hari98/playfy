import React, { Component } from 'react';

class Register extends Component {

    state = {
        firstName : {
            value : '',
            valid : false
        },
        lastName : {
            value : '',
            valid : false
        },
        userId : {
            value : '',
            valid : false
        },
        password : {
            value : '',
            valid : false
        },
        confirmPassword : {
            value : '',
            valid : false
        },
    }

    isFormValid() {
        return false;
    }

    render() {
        return (
            <div className="container-fluid">
                <div className="row d-flex justify-content-center mt-5">
                    <div className="col-6">
                        <div className="card mt-3">
                            <div className="card-body">
                                <h5 className="card-title">Register</h5>
                                <div className="row">
                                    <div className="col-6">
                                        <label htmlFor="first-name">First Name</label>
                                        <div className="input-group mb-3">
                                            <input type="text" className="form-control" id="first-name" />
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label htmlFor="last-name">Last Name</label>
                                        <div className="input-group mb-3">
                                            <input type="text" className="form-control" id="last-name" />
                                        </div>
                                    </div>
                                    <div className="col-12">
                                        <label htmlFor="user-id">User Id</label>
                                        <div className="input-group mb-3">
                                            <input type="text" className="form-control" id="user-id" />
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label htmlFor="pwd">Password</label>
                                        <div className="input-group mb-3">
                                            <input type="password" className="form-control" id="pwd" />
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label htmlFor="confirm-pwd">Confirm Password</label>
                                        <div className="input-group mb-3">
                                            <input type="password" className="form-control" id="confirm-pwd" />
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-12 text-center">
                                        <button className="btn btn-primary" disabled={this.isFormValid()}>Register</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default Register;
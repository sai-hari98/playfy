import React, { Component } from 'react';

class Login extends Component {

    state = {
        username: {
            value: '',
            valid: false
        },
        password: {
            value: '',
            valid: false
        }
    }

    isFormValid(){
        const formAttributes = Object.keys(state);
        for(let i = 0 ; i < formAttributes.length ; i++){
            if(!this.state[formAttributes[i]].valid)
                    return false;
        }
        return true;
    }

    render() {
        return (
            <div className="container-fluid">
                <div className="row d-flex justify-content-center mt-5">
                    <div className="col-4">
                        <div className="card mt-3">
                            <div className="card-body">
                                <h5 className="card-title">Login</h5>
                                <div className="row">
                                    <div className="col-12">
                                        <label htmlFor="user-id">User Id</label>
                                        <div className="input-group mb-3">
                                            <input type="text" className="form-control" id="user-id" />
                                        </div>
                                    </div>
                                    <div className="col-12">
                                        <label htmlFor="pwd">Password</label>
                                        <div className="input-group mb-3">
                                            <input type="password" className="form-control" id="pwd" />
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-12 text-center">
                                        <button className="btn btn-primary" disabled={!this.isFormValid()}>Login</button>
                                    </div>
                                </div>
                                <div className="row mt-3">
                                    <div className="col-12 d-flex justify-content-end">
                                        <a href="/register" className="card-link">Not Registered?</a>
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

export default Login;
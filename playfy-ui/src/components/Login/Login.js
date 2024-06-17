import React, { Component } from 'react';
import playfyAxios from '../../playfy-axios';
import { logIn } from '../../store/actions';
import { createInputObject } from '../../utils/object-utils';
import { validateField } from '../../utils/validation-utils';
import { connect } from 'react-redux';
import { isNullOrEmpty } from '../../utils/string-utils';
class Login extends Component {

    state = {
        username: createInputObject(),
        password: createInputObject()
    }

    isFormValid(){
        const formAttributes = Object.keys(this.state);
        for(let i = 0 ; i < formAttributes.length ; i++){
            if(!this.state[formAttributes[i]].valid)
                return false;
        }
        return true;
    }

    updateUsername(newUserId){
        let state = {...this.state};
        let username = {...state.username}
        username.value = newUserId;
        username.valid = validateField(username);
        state.username = username;
        this.setState(state);
    }

    updatePassword(newPassword){
        let state = {...this.state};
        let password = {...state.password}
        password.value = newPassword;
        password.valid = validateField(password);
        state.password = password;
        this.setState(state);
    }

    login(){
        playfyAxios.post("/login",{"userId" : this.state.username, "password" : this.state.password}).then(response => {
            this.props.login(response.data);
        }).catch(error => {
            console.log(error);
        });
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
                                            <input type="text" className="form-control" id="user-id" value={this.state.username.value} onChange={(event) => this.updatePassword(event.target.value)}/>
                                        </div>
                                    </div>
                                    <div className="col-12">
                                        <label htmlFor="pwd">Password</label>
                                        <div className="input-group mb-3">
                                            <input type="password" className="form-control" id="pwd" value={this.state.password.value} onChange={(event) => this.updatePassword(event.target.value)}/>
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-12 text-center">
                                        <button className="btn btn-primary" disabled={!this.isFormValid()} onClick={this.login()}>Login</button>
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

const mapStateToProps = state => {
    return {
        token: state.auth.idToken,
        userId: state.auth.userId
    }
}

const mapActionsToProps = dispatch => {
    return {
        login : (token) => dispatch(logIn(token))
    }
}

export default connect(mapStateToProps, mapActionsToProps)(Login);
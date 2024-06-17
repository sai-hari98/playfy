import React, { Component } from 'react';
import { createInputObject } from '../../utils/object-utils';
import { useNavigate } from 'react-router-dom';
import playfyAxios from '../../playfy-axios';
import { isNullOrEmpty } from '../../utils/string-utils';
import { validateField } from '../../utils/validation-utils';

class Register extends Component {

    state = {
        firstName : createInputObject({minChar : 1, maxChar: 100}),
        lastName : createInputObject({minChar : 1, maxChar: 100}),
        userId : createInputObject({minChar : 6, maxChar: 10}),
        email : createInputObject({email : true}),
        password : createInputObject({password: true}),
        confirmPassword : createInputObject({password: true})
    }

    isFormValid() {
        const formAttributes = Object.keys(this.state);
        for(let i = 0 ; i < formAttributes.length ; i++){
            console.log(formAttributes[i]);
            console.log(this.state[formAttributes[i]]);
            if(!this.state[formAttributes[i]].valid)
                return false;
        }
        return true;
    }

    updateField = (fieldName, value) => {
        let state = {...this.state};
        let field = {...state[fieldName]};
        field.value = value;
        field.valid = validateField(field);
        field.touched = !isNullOrEmpty(value);
        state[fieldName] = field;
        this.setState(state);
    }

    arePasswordsMatching(){
        if(!isNullOrEmpty(this.state.password.value) && !isNullOrEmpty(this.state.confirmPassword.value)){
            if(this.state.password.value !== this.state.confirmPassword.value)
                return false;
        }
        return true;
    }

    register() {
        let registerRequest = {
            userId : this.state.userId.value,
            password : this.state.password.value,
            email : this.state.email.value,
            firstName : this.state.firstName.value,
            lastName : this.state.lastName.value
        }
        playfyAxios.post("/users", registerRequest).then(response => {
            if(response.data){
                alert("You have been registered successfully. Redirecting to login page...");
                useNavigate("/login");
            }
        })

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
                                            <input type="text" className="form-control" id="first-name" value={this.state.firstName.value} onChange={(event) => this.updateField('firstName',event.target.value)} />
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label htmlFor="last-name">Last Name</label>
                                        <div className="input-group mb-3">
                                            <input type="text" className="form-control" id="last-name" value={this.state.lastName.value} onChange={(event) => this.updateField('lastName',event.target.value)}/>
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label htmlFor="email">E-mail</label>
                                        <div className="input-group mb-3">
                                            <input type="text" className="form-control" id="email" value={this.state.email.value} onChange={(event) => this.updateField('email',event.target.value)}/>
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label htmlFor="user-id">User Id</label>
                                        <div className="input-group mb-3">
                                            <input type="text" className="form-control" id="user-id" value={this.state.userId.value} onChange={(event) => this.updateField('userId',event.target.value)}/>
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label htmlFor="pwd">Password</label>
                                        <div className="input-group mb-3">
                                            <input type="password" className="form-control" id="pwd" value={this.state.password.value} onChange={(event) => this.updateField('password',event.target.value)}/>
                                        </div>
                                    </div>
                                    <div className="col-6">
                                        <label htmlFor="confirm-pwd">Confirm Password</label>
                                        <div className="input-group mb-3">
                                            <input type="password" className="form-control" id="confirm-pwd" value={this.state.confirmPassword.value} onChange={(event) => this.updateField('confirmPassword',event.target.value)}/>
                                        </div>
                                    </div>
                                </div>
                                <div className="row">
                                    <div className="col-12 text-center">
                                        <button className="btn btn-primary" disabled={!this.isFormValid() || !this.arePasswordsMatching()} onClick={() => this.register()}>Register</button>
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
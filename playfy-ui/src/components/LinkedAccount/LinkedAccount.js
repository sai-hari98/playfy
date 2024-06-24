import React, { Component } from "react";

class LinkedAccount extends Component {

    render() {
        return (
            <div className="row mb-5">
                <div className="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h6>
                                {this.props.header} {this.props.isLinked ? (
                                    <span class="material-symbols-outlined">
                                        verified
                                    </span>
                                ) : null}
                            </h6>
                        </div>
                        <div class="card-body">
                            <div className="row">
                                <div className="col-1">
                                    <label htmlFor={"user-id-" + this.props.provider}>User name</label>
                                </div>
                                <div className="col-4">
                                    <input type="text" className="form-control" value={this.props.userId} onChange={(event) => this.props.changeUserId(this.props.provider, event.target.value)} />
                                </div>
                                <div className="col-2">
                                    <button class="btn btn-primary" disabled={this.props.userId === ''} onClick={() => this.props.linkAccount(this.props.provider)}>Link Account</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default LinkedAccount;
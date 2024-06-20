import React, { Component } from "react";
import playfyAxios from "../../playfy-axios";
import Loader from "../Loader/Loader";
import { connect } from "react-redux";
import { Link } from "react-router-dom";

class Dashboard extends Component {

    state = {
        dashboardData: null
    }

    getDashboardInfo() {
        playfyAxios.get("/dashboard", {headers: {Authorization: `Bearer ${this.props.token}`}}).then(response => {
            this.setState({ dashboardData: response.data });
        }).catch(error => {
            console.log(error);
            alert("An error occurred while loading the page");
        });
    }

    getLinkAccountsBanner() {
        return (
            <div className="row">
                <div className="col-12">
                    <div className="card">
                        <div className="card-body">
                            <div className="row d-flex justify-content-between">
                                <div className="col-6">
                                    No accounts are linked currently. Want to link one?
                                </div>
                                <div className="col-2">
                                    <Link className="card-link" to="/linked-accounts">Link an account</Link>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }

    getRenderedPlaylists() {
        return (
            <div className="row">
                {this.state.dashboardData.playlists.map(playlist => {
                    return (
                        <div className="col-4">
                            <div className="card">
                                <img src="..." class="card-img-top" alt="..." />
                                <div className="card-body">
                                    <h5 class="card-title">{playlist.title}</h5>
                                    <a href="#" class="card-link material-symbols-outlined">sync</a>
                                    <a href="#" class="card-link material-symbols-outlined">download</a>
                                </div>
                            </div>
                        </div>
                    )
                })}
            </div>
        )
    }

    render() {
        if (this.state.dashboardData == null) {
            this.getDashboardInfo();
            return <Loader />;
        }
        return (
            <div className="container-fluid mt-5 pb-5">
                {this.state.dashboardData.linkedAccounts.length <= 0 ? this.getLinkAccountsBanner() : this.getRenderedPlaylists()}
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        token: state.token
    }
}

export default connect(mapStateToProps, null)(Dashboard);
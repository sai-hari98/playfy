import React, { Component } from "react";
import playfyAxios from "../../playfy-axios";

class Dashboard extends Component {

    state = {
        dashboardData: null
    }

    getDashboardInfo(){
        playfyAxios.get("/dashboard").then(response => {
            this.setState({ dashboardData: response.data });
        }).catch(error => {
            console.log(error);
            alert("An error occurred while loading the page");
        });
    }

    render() {
        if (this.state.dashboardData == null) {
            this.getDashboardInfo();
            return <Loader/>;
        }
        return (
            <div className="container">
                {this.state.dashboardData.length > 0 ? (
                    <>
                    </>
                ) : (
                    <>
                    </>
                )}
            </div>
        )
    }
}

export default Dashboard;
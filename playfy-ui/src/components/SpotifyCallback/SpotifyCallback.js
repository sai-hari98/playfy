import React, {Component} from 'react';

class SpotifyCallback extends Component{

    componentDidMount(){
        const urlParams = new URLSearchParams(window.location.search);
        const accessCode = urlParams.get('code');
        const state = urlParams.get('state');
        const error = urlParams.get('error');

        if(error === null){

        }
    }

    render(){
        if(error !== null){
            return (
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-12">
                            <h5 className="text-center">
                                Sorry, we are unable to link the spotify account. Please give a try after sometime.
                            </h5>
                        </div>
                    </div>
                </div>
            )
        }else{
            return (
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-12">
                            <h5 className="text-center">
                                Redirecting to dashboard page...
                            </h5>
                        </div>
                    </div>
                </div>
            )
        }
    }
}

export default SpotifyCallback;
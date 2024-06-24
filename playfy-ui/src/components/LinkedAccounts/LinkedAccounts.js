import React, { Component } from 'react';
import playfyAxios from '../../playfy-axios';
import Loader from '../Loader/Loader';
import LinkedAccount from '../LinkedAccount/LinkedAccount';
import { connect } from 'react-redux';

class LinkedAccounts extends Component {

    state = {
        linkedAccounts: null,
        spotify: '',
        primemusic: '',
        ytmusic: '',
        isLoading: true
    }

    getLinkedAccounts() {
        playfyAxios.get("/users/accounts", { headers: { Authorization: `Bearer ${this.props.token}` } }).then(response => {
            this.setUserNames(response.data);
            this.setState({ linkedAccounts: response.data, isLoading: false });
        }).catch(error => {
            console.log(error);
            this.setState({ isLoading: false });
            alert("Sorry, an error occurred while fetching linked accounts");
        })
    }

    setUserNames(linkedAccounts) {
        if (linkedAccounts.spotify) {
            this.setState({ spotify: linkedAccounts.spotify.userId });
        }
        if (linkedAccounts.primeMusic) {
            this.setState({ primemusic: linkedAccounts.primeMusic.userId });
        }
        if (linkedAccounts.ytMusic)
            this.setState({ ytmusic: linkedAccounts.ytMusic.userId });
    }

    changeUserId(provider, newUserId) {
        let stateCopy = { ...this.state };
        stateCopy[provider] = newUserId;
        this.setState({...stateCopy});
    }

    linkAccount(provider){
        let headers = {Authorization : `Bearer ${this.props.token}`}
        let body = {
            provider : provider,
            userId: this.state[provider]
        }
        playfyAxios.post("/users/accounts/link", body, {headers: headers}).then(response => {
            window.location.assign(response.data);
        }).catch(error => {
            console.log(error);
            alert('An error has occurred while linking your account');
        });
    }

    isAccountLinked(provider) {
        return this.state.linkedAccounts !== null && this.state.linkedAccounts[provider] !== null;
    }

    componentDidMount() {
        this.getLinkedAccounts();
    }

    render() {
        return (
            <>
                {this.state.linkedAccounts == null ? <Loader /> : (
                    <div className="container-fluid mt-5 pb-5">
                        <h5 className="mt-3 mb-5">Linked Music accounts</h5>
                        <LinkedAccount header="Spotify" provider="spotify" userId={this.state.spotify} changeUserId={(provider) => this.changeUserId(provider)} isLinked={this.isAccountLinked('spotify')} linkAccount={(provider) => this.linkAccount(provider)} />
                        <LinkedAccount header="Amazon Prime Music" provider="primemusic" userId={this.state.primemusic} changeUserId={(provider) => this.changeUserId(provider)} isLinked={this.isAccountLinked('primeMusic')} linkAccount={(provider) => this.linkAccount(provider)}/>
                        <LinkedAccount header="Youtube Music" provider="ytmusic" userId={this.state.ytmusic} changeUserId={(provider) => this.changeUserId(provider)} isLinked={this.isAccountLinked('ytMusic')} linkAccount={(provider) => this.linkAccount(provider)}/>
                    </div>
                )}
            </>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        token: state.token
    }
}

export default connect(mapStateToProps, null)(LinkedAccounts);
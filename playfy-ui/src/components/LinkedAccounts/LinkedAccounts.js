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
        playfyAxios.get("/users/linked-accounts", { headers: { Authorization: `Bearer ${this.props.token}` } }).then(response => {
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
        this.setState(stateCopy);
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
                        <LinkedAccount header="Spotify" provider="spotify" userId={this.state.spotify} changeUserId={this.changeUserId} isLinked={this.isAccountLinked('spotify')} />
                        <LinkedAccount header="Amazon Prime Music" provider="primemusic" userId={this.state.primemusic} changeUserId={this.changeUserId} isLinked={this.isAccountLinked('primeMusic')} />
                        <LinkedAccount header="Youtube Music" provider="ytmusic" userId={this.state.ytmusic} changeUserId={this.changeUserId} isLinked={this.isAccountLinked('ytMusic')} />
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
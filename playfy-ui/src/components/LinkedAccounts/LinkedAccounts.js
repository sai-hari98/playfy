import React, {Component} from 'react';
import playfyAxios from '../../playfy-axios';


export default class LinkedAccounts extends Component{

    state = {
        spotify : null,
        primeMusic : null,
        appleMusic : null
    }

    render() {
        if(spotify == null){
            playfyAxios.get("/users/accounts/linked").then(response => {
                
            }).catch(error => {
                console.log(error);
                alert("Sorry, an error occurred while fetching linked accounts");
            })
        }
    }
}
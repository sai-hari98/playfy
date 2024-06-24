import React, {Component} from 'react';
import Login from "../../components/Login/Login";
import Register from "../../components/Register/Register";
import {Route, Routes} from "react-router-dom";
import Dashboard from '../../components/Dashboard/Dashboard';
import LinkedAccounts from '../../components/LinkedAccounts/LinkedAccounts';

class PageLayout extends Component {

    componentDidMount(){
        console.log('Page Layout component mount');
    }
    render(){
        return (
            <>
                <Routes>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                    <Route path="/dashboard" element={<Dashboard/>}/>
                    <Route path="/linked-accounts" element={<LinkedAccounts/>}/>
                    <Route component={Login}/>
                </Routes>
            </>
        )
    }
}

export default PageLayout;
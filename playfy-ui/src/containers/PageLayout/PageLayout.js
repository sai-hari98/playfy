import React, {Component} from 'react';
import Login from "../../components/Login/Login";
import Register from "../../components/Register/Register";
import {Route, Routes} from "react-router-dom";

class PageLayout extends Component {

    render(){
        return (
            <>
                <Routes>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/register" element={<Register/>}/>
                    <Route component={Login}/>
                </Routes>
            </>
        )
    }
}

export default PageLayout;
import './LeftNav.css'
import React, {useState} from "react";
import {Link} from "react-router-dom";
import Account from "./Account";
import Login from "./Login";

const LeftNav = ({isLoggedIn}) => {

    return (
        <div className='left-nav'>
            {isLoggedIn ? (
                <Account/>
            ) : (
                <Login/>
            )}
        </div>
    )
}

export default LeftNav;

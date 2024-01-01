import React from "react";
import RightNav from "../components/RightNav/RightNav";
import Account from "../components/LeftNav/Account";
import {Outlet} from "react-router-dom";

const Main = () => {
    return (
        <>
            <div className='left-nav-container'>
                <Account/>
            </div>
            <div className='center-container'>
                <Outlet/>
            </div>
            <div className='right-nav-container'>
                <RightNav/>
            </div>
        </>
    )
}

export default Main

import React from "react";
import Feed from "../components/Center/Feed";
import RightNav from "../components/RightNav/RightNav";
import Login from "../components/LeftNav/Login";

const Guest = () => {
    return (
        <>
            <div className='left-nav-container'>
                <Login/>
            </div>
            <div className='center-container'>
                <Feed/>
            </div>
            <div className='right-nav-container'>
                <RightNav/>
            </div>
        </>
    )
}

export default Guest

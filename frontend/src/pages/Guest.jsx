import React from "react";
import LeftNav from "../components/LeftNav/LeftNav";
import Feed from "../components/Feed/Feed";
import RightNav from "../components/RightNav/RightNav";

const Guest = () => {
    return (
        <>
            <div className='left-nav-container'>
                <LeftNav isLoggedIn={false}/>
            </div>
            <div className='feed-nav-container'>
                <Feed/>
            </div>
            <div className='right-nav-container'>
                <RightNav/>
            </div>
        </>
    )
}

export default Guest

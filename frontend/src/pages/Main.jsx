import React from "react";
import LeftNav from "../components/LeftNav/LeftNav";
import RightNav from "../components/RightNav/RightNav";
import Feed from "../components/Feed/Feed";

const Main = () => {
    return (
        <>
            <div className='left-nav-container'>
                <LeftNav isLoggedIn={true}/>
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

export default Main

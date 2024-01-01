import React from "react";
import RightNav from "../components/RightNav/RightNav";
import Account from "../components/LeftNav/Account";
import AddPet from "../components/Center/AddPet";

const AddPetPage = () => {
    return (
        <>
            <div className='left-nav-container'>
                <Account/>
            </div>
            <div className='feed-nav-container'>
                <AddPet/>
            </div>
            <div className='right-nav-container'>
                <RightNav/>
            </div>
        </>
    )
}

export default AddPetPage

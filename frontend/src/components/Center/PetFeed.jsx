import React from "react";
import {useLocation} from "react-router-dom";

const PetFeed = () => {
    const location = useLocation();
    const petData = location.state;
    return (
        <div className='pet-feed'>
            {petData.name}
        </div>
    )
}

export default PetFeed;

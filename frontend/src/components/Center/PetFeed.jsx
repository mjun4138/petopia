import React from "react";
import {useLocation} from "react-router-dom";
import './PetFeed.css'

const PetFeed = () => {
    const location = useLocation();
    const pet = location.state.pet;
    console.log(pet)
    return (
        <div className='pet-feed'>
            <div className='pet-feed-form'>
                <div className='pet-feed-header'>
                    <div className='pet-feed-image'>
                        {pet.profileImage ? (
                            <img src={`${process.env.PUBLIC_URL}/pet-image/${pet.profileImage}`}/>
                        ) : (
                            <img src={`${process.env.PUBLIC_URL}/image/petImage.png`}/>
                        )}
                    </div>
                    <div className='pet-feed-info'>
                        <div className='pet-feed-info-name'>
                            <h3>{pet.name}</h3>
                        </div>
                        <div className='pet-feed-info-follow'>
                            게시물 <span>0</span>
                            팔로워 <span>{pet.follower}</span>
                            팔로우 <span>{pet.following}</span>
                        </div>
                        <div className='pet-feed-info-intro'>
                            <pre>{pet.intro}</pre>
                        </div>

                    </div>
                </div>
                <div className='pet-feed-body'>

                </div>
            </div>

        </div>
    )
}

export default PetFeed;

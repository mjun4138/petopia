import React from "react";
import './Feed.css'

const Feed = () => {

    const feedList = [
        {"pet" : "규니", "content" : "규니입니다.", "image" : "dog.jpg"},
        {"pet" : "쭈니", "content" : "쭈니예요.", "image" : "cat.jpg"},
        {"pet" : "뵤리", "content" : "I am 뵤리", "image" : "dog2.jpg"},
        {"pet" : "효기", "content" : "효기랑께", "image" : "cat2.jpg"}
    ]

    return (
        <div className='feed'>
            <div className='feed-list'>
                {feedList.map((feed, index) => (
                    <div className='feed-item' key={index}>
                        <div className='feed-header'>
                            <img src={`${process.env.PUBLIC_URL}/image/${feed.image}`} alt="feed image"/>
                            <span>{feed.pet}</span>
                        </div>
                        <div className='feed-image'>
                            <img src={`${process.env.PUBLIC_URL}/image/${feed.image}`} alt="feed image"/>
                        </div>
                        <div className='feed-footer'>
                            <span>{feed.content}</span>
                        </div>
                    </div>
                ))}

            </div>
        </div>
    )
}

export default Feed

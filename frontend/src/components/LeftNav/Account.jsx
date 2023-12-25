import React from "react";
import {Link} from "react-router-dom";

const Account = () => {

    const userName = "강형욱";
    const petList = [
        {"name" : "규니", "image" : "dog.jpg"},
        {"name" : "쭈니", "image" : "cat.jpg"},
        {"name" : "뵤리", "image" : "dog2.jpg"},
        {"name" : "효기", "image" : "cat2.jpg"}
    ]

    const handleLogout = () => {

    }

    return (
        <nav className='account'>
            <div className='logo'>
                펫토피아
            </div>

            <div className='user-profile'>
                <img src={`${process.env.PUBLIC_URL}/image/kang.jpg`} alt="user image"/>
                <div className='user-name'>
                    <h1>{userName}</h1>
                </div>
            </div>

            <div className='pet-list'>
                {petList.map((pet, index) => (
                    <div className='pet-item' key={index}>
                        <img src={`${process.env.PUBLIC_URL}/image/${pet.image}`} alt="pet image"/>
                        <div className='pet-name'>
                            <span>{pet.name}</span>
                        </div>
                    </div>
                ))}
                <div className='pet-add'>
                    <img src={`${process.env.PUBLIC_URL}/image/add.png`} alt="add image"/>
                    <div className='pet-name'>
                        <span>펫 등록</span>
                    </div>
                </div>
            </div>

            <div className='setting'>
                <button>설정</button>

                    <Link onClick={handleLogout} to={'/'} style={{textDecoration: 'none'}}>
                        <button>로그아웃</button>
                    </Link>

            </div>

        </nav>
    )
}

export default Account;

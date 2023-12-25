import logo from './logo.svg';
import './App.css';
import React, { useState } from "react";

const App = () => {
    const [isLoggedIn, setLoggedIn] = useState(false);
    const userName = "강형욱";
    const petList = [
        {"name" : "규니", "image" : "dog.jpg"},
        {"name" : "쭈니", "image" : "cat.jpg"},
        {"name" : "뵤리", "image" : "dog2.jpg"},
        {"name" : "효기", "image" : "cat2.jpg"}
    ]

    const handleLogout = () => {
        setLoggedIn(false);
    }

    const handleLogin = () => {
        setLoggedIn(true)
    }

    const MainComponent = ({ isLoggedIn }) => {
        console.log({isLoggedIn})
        return (
            <div>
                {isLoggedIn ? (
                    <FeedComponent/>
                ) : (
                    <LoginComponent/>
                )}
            </div>
        )
    }

    const FeedComponent = () => {
        return (
            <nav className='left-nav'>
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
                            <img src={`${process.env.PUBLIC_URL}/image/${pet.image}`} alt="user image"/>
                            <div className='pet-name'>
                                <span>{pet.name}</span>
                            </div>
                        </div>
                    ))}
                    <div className='pet-add'>
                        <img src={`${process.env.PUBLIC_URL}/image/add.png`} alt="user image"/>
                        <div className='pet-name'>
                            <span>펫 등록</span>
                        </div>
                    </div>
                </div>

                <div className='setting'>
                    <button>설정</button>
                    <button onClick={handleLogout}>로그아웃</button>
                </div>

            </nav>
        )
    }

    const LoginComponent = () => {
        return (
            <div className='left-nav'>

                <div className='login-form'>
                    <h1>펫토피아</h1>
                    <input type="text" id='id' placeholder='아이디'/>
                    <input type="password" id='pw' placeholder='비밀번호'/>
                    <button onClick={handleLogin}>로그인</button>
                </div>

                <div className='register-form'>
                    <span>계정이 없으시다면? </span><a href=''>회원가입</a>
                </div>
            </div>
        )
    }

    return (

        <div className='App'>
            <MainComponent isLoggedIn={isLoggedIn}/>
        </div>
    )

}




export default App;

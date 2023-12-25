import React, {useState} from "react";
import {Link} from "react-router-dom";

const Login = () => {
    const [showSignUpClick, setSignUpClick] = useState(false)

    const LoginForm = () => {
        return (
            <div className='login-form'>
                <div className='login'>
                    <h1>petopia</h1>
                    <input type="text" id='id' placeholder='아이디'/>
                    <input type="password" id='pw' placeholder='비밀번호'/>

                        <Link to={'/account'} style={{textDecoration: 'none'}}>
                            <button>로그인</button>
                        </Link>

                </div>

                <div className='register'>
                    <span>계정이 없으신가요? </span>
                    <span style={{cursor: 'pointer', color: 'blue'}} onClick={() => setSignUpClick(true)}>회원가입</span>
                </div>
            </div>
        )
    }

    const SignUpForm = () => {
        return (
            <div className='signup-form'>
                <div className='signup'>
                    <h1>petopia</h1>
                    <input type="email" id='email' placeholder='이메일'/>
                    <input type="text" id='name' placeholder='성명'/>
                    <input type="text" id='id' placeholder='아이디'/>
                    <input type="password" id='pw' placeholder='비밀번호'/>
                    <button onClick={() => setSignUpClick(false)}>가입</button>
                </div>

                <div className='register'>
                    <span>계정이 있으신가요? </span>
                    <span style={{cursor: 'pointer', color: 'blue'}} onClick={() => setSignUpClick(false)}>
                        로그인
                    </span>
                </div>
            </div>
        )
    }

    return (
        <>
            {showSignUpClick ? (
                <SignUpForm/>
            ) : (
                <LoginForm/>
            )}
        </>
    )
}

export default Login;

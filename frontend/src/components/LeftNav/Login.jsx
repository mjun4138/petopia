import React, {useState} from "react";
import {Link} from "react-router-dom";
import axios from "axios";

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
        const [createMemberRequest, setRequest] = useState({
            email: '',
            name: '',
            account: '',
            password: ''
        })

        const [valid, setValid] = useState({
            email: '',
            name: '',
            account: '',
            password: ''
        })

        const handleChange = (e) => {
            setRequest({
                ...createMemberRequest,
                [e.target.name]: e.target.value
            })
        }

        const createMember = async () => {
            try {
                await axios.post("/api/members", createMemberRequest)
                setSignUpClick(false)
                alert("가입이 완료되었습니다.")
            } catch (error) {
                setValid({
                    email: error.response.data.validation.email,
                    name: error.response.data.validation.name,
                    account: error.response.data.validation.account,
                    password: error.response.data.validation.password
                })
                console.log(error.response.data);
                console.log(error.response.data.validation.email)
                console.log({valid})
            }

        }

        return (
            <div className='signup-form'>
                <div className='signup'>
                    <h1>petopia</h1>
                    <input type="email" name="email" value={createMemberRequest.email} placeholder='이메일' onChange={handleChange}/>
                    <input type="text" name="name" value={createMemberRequest.name} placeholder='성명' onChange={handleChange}/>
                    <input type="text" name="account" value={createMemberRequest.account} placeholder='아이디' onChange={handleChange}/>
                    <input type="password" name="password" value={createMemberRequest.password} placeholder='비밀번호' onChange={handleChange}/>
                    <button onClick={createMember}>가입</button>
                    <span className="signup-error">
                        {
                            valid.email ?
                                <>{valid.email}</>
                                : valid.name ?
                                <>{valid.name}</>
                                : valid.account ?
                                    <>{valid.account}</>
                                    : (
                                        <>{valid.password}</>
                                        )
                        }
                    </span>
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

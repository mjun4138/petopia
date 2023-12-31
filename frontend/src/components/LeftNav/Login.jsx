import React, {useState} from "react";

import {createMemberAPI, loginAPI} from "../../customAxios";
import {useCookies} from "react-cookie";
import {useNavigate} from "react-router-dom";

const Login = () => {
    const [showSignUpClick, setSignUpClick] = useState(false)

    const [cookies, setCookie] = useCookies();
    const movePage = useNavigate();

    const LoginForm = () => {

        const [loginRequest, setLoginRequest] = useState({
            account: '',
            password: ''
        })

        const handleChange = (e) => {
            setLoginRequest({
                ...loginRequest,
                [e.target.name]: e.target.value
            })
        }

        const loginMember = async () => {
            try {
                const response = await loginAPI(loginRequest)

                localStorage.setItem('accessToken', response.data.accessToken)
                setCookie('refreshToken', response.data.refreshToken, {path: '/'})


                movePage('/account')

            } catch (error) {
                console.log(error)
            }
        }

        return (
            <div className='login-form'>
                <div className='login'>
                    <h1>petopia</h1>
                    <input type="text" name='account' placeholder='아이디' value={loginRequest.account} onChange={handleChange}/>
                    <input type="password" name='password' placeholder='비밀번호' value={loginRequest.password} onChange={handleChange}/>
                    <button onClick={loginMember}>
                        로그인
                    </button>


                </div>

                <div className='register'>
                    <span>계정이 없으신가요? </span>
                    <span style={{cursor: 'pointer', color: 'blue'}} onClick={() => setSignUpClick(true)}>회원가입</span>
                </div>
            </div>
        )
    }

    const SignUpForm = () => {
        const [createMemberRequest, setCreateMemberRequest] = useState({
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
            setCreateMemberRequest({
                ...createMemberRequest,
                [e.target.name]: e.target.value
            })
        }

        const createMember = async () => {
            try {
                const response = createMemberAPI(createMemberRequest)
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

import React, {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {logoutAPI, petsByMemberAPI} from "../../customAxios";

const Account = () => {

    const movePage = useNavigate();
    const memberId = JSON.parse(localStorage.getItem("memberData")).id
    const [pets, setPets] = useState([])


    const logoutMember = async () => {
        try {
            await logoutAPI(memberId);
            await localStorage.clear();

            movePage('/')
        } catch (error) {
            console.log(error);
        }

    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await petsByMemberAPI(memberId.toString())
                setPets(response.data)
            } catch (error) {
                console.log(error)
            }
        }
        fetchData()
    }, [])

    return (
        <nav className='account'>

            <div className='logo'>
                <Link to={'/account'} className='logo-cursor'>
                    <h1>petopia</h1>
                </Link>
            </div>

            <div className='user-profile'>
                <img src={`${process.env.PUBLIC_URL}/image/kang.jpg`} alt="user image"/>
                <div className='user-name'>
                    <h3>{JSON.parse(localStorage.getItem('memberData')).name}</h3>
                </div>
            </div>


            <div className='pet-list'>
                {!Array.isArray(pets) ? (
                    <></>
                ) : (
                    <>
                        {pets.map((pet, index) => (
                            <div className='pet-item' key={index}>
                                {pet.profileImage != null ? (
                                    <img src={`${process.env.PUBLIC_URL}/pet-image/${pet.profileImage}`} alt="pet image"/>
                                ) : (
                                    <img src={`${process.env.PUBLIC_URL}/image/petImage.png`} alt="pet image"/>
                                )}
                                <div className='pet-name'>
                                    <h4>{pet.name}</h4>
                                </div>
                            </div>
                        ))}
                    </>
                )}
                <div className='pet-add'>
                    <img src={`${process.env.PUBLIC_URL}/image/add.png`} alt="add image"/>
                    <div className='pet-name'>
                        <h4>펫 등록</h4>
                    </div>
                </div>
            </div>





            <div className='setting'>
                <button>설정</button>
                <button onClick={logoutMember}>로그아웃</button>


            </div>

        </nav>
    )
}

export default Account;

import React, {useEffect, useState} from "react";
import {Link, useLocation, useNavigate} from "react-router-dom";
import {logoutAPI, petsByMemberAPI} from "../../customAxios";
import "./Account.css";

const Account = () => {

    const movePage = useNavigate();
    const memberId = JSON.parse(localStorage.getItem("memberData")).id
    const location = useLocation();
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

    const handleAddPet = () => {
        movePage('add-pet')
    }

    const handlePetFeed = (pet) => {
        movePage(`${pet.id}`, {state: {pet}})

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
    }, [location.state?.isRender])

    return (
        <div className='account'>
            <nav className='account-form'>

                <div className='logo'>
                    <div className='logo-cursor' onClick={()=> movePage(`/account`)}>
                        <h1>petopia</h1>
                    </div>
                </div>

                <div className='user-profile'>
                    <div className='user-name'>
                        <h3>{JSON.parse(localStorage.getItem('memberData')).name}님, 반갑습니다.</h3>
                    </div>
                </div>


                <div className='pet-list'>
                    {!Array.isArray(pets) ? (
                        <></>
                    ) : (
                        <>
                            {pets.map((pet, index) => (
                                <div className='pet-item' key={index} onClick={() => handlePetFeed(pet)}>
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
                    <div className='pet-add' onClick={handleAddPet}>
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
        </div>

    )
}

export default Account;

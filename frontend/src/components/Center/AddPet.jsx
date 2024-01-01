import React, {useState} from "react";
import './AddPet.css';
import {useNavigate} from "react-router-dom";
import {createPetAPI} from "../../customAxios";

const AddPet = () => {
    const movePage = useNavigate();
    const memberId = JSON.parse(localStorage.getItem('memberData')).id;
    const [createPetRequest, setCreatePetRequest] = useState({
        memberId: memberId,
        name: '',
        intro: ''
    });
    const [fileRequest, setFileRequest] = useState(null);
    const [thumbnail, setThumbnail] = useState(null);
    const formData = new FormData();

    const handleChange = (e) => {
        setCreatePetRequest({
            ...createPetRequest,
            [e.target.name]: e.target.value
        })
    }

    const handleFileChange = (e) => {
        const file = e.target.files[0]
        const fileReader = new FileReader();
        fileReader.onload = () => {
            setFileRequest(file)
            setThumbnail(fileReader.result)

        }
        fileReader.readAsDataURL(file)
    }

    const createPet = async () => {
        try {
            const request = new Blob([JSON.stringify(createPetRequest)], {type: 'application/json'})
            const file = fileRequest
            formData.append('request', request)
            formData.append('file', file)
            await createPetAPI(formData)
            console.log(formData)

            movePage(-1)
        } catch (error) {
            console.log(error);
            console.log(createPetRequest)
            console.log(fileRequest)
            console.log(formData)
        }
    }

    return (
        <div className='add-pet'>
            <div className='add-pet-back' onClick={() =>movePage(-1)}>
                <img src={`${process.env.PUBLIC_URL}/image/back.png`}/>
            </div>
            <div className='add-pet-form'>
                <div className='add-pet-input'>
                    <label className='add-pet-file' htmlFor='file'>
                        {fileRequest ? (
                            <img src={thumbnail}/>
                        ) : (
                            <img src={`${process.env.PUBLIC_URL}/image/petImage.png`}/>
                        )}
                        <span>프로필 이미지 선택</span>
                        <input type='file' accept='image/*' id='file' onChange={handleFileChange}/>
                    </label>
                    <div className='add-pet-info'>
                        <input type="text" name='name' placeholder='펫 이름' value={createPetRequest.name} onChange={handleChange}/>
                        <textarea name='intro' placeholder='펫 소개' value={createPetRequest.intro} onChange={handleChange}/>
                    </div>
                </div>
                <div className='add-pet-register'>
                    <button onClick={createPet}>
                        등록
                    </button>
                </div>



            </div>
        </div>

    )
}

export default AddPet

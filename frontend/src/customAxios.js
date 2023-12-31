import axios from "axios";


const authInstance = axios.create({
    baseURL: "http://localhost:3000",
    headers: {
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`
    }
});

const instance = axios.create({
    baseURL: "http://localhost:3000",
})

authInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('accessToken');
        config.headers.Authorization = `Bearer ${token}`

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

authInstance.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export const createMemberAPI = async (createMemberRequest) => {
    return await instance.post("/api/members", createMemberRequest)
}

export const loginAPI = (loginRequest) => {
    return instance.post("/api/auth", loginRequest)
}

export const logoutAPI = (memberId) => {
    return authInstance.post("/api/auth/{memberId}", memberId)
}

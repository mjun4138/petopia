import './App.css';
import Main from "./pages/Main"
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import React from "react";
import Guest from "./pages/Guest";
import Feed from "./components/Center/Feed";
import AddPet from "./components/Center/AddPet";
import PetFeed from "./components/Center/PetFeed";

const App = () => {

    return (
        <div className='App'>
            <BrowserRouter>
                <Routes>
                    {/*게스트*/}
                    <Route path="/"  element={<Navigate to="/guest"/>}/>
                    <Route path="/guest" element={<Guest/>}/>
                    {/*회원*/}
                    <Route path="/account" element={<Main/>}>
                        <Route path="" element={<Feed/>}/>
                        <Route path=":petId" element={<PetFeed/>}/>
                        <Route path="add-pet" element={<AddPet/>}/>
                    </Route>

                </Routes>
            </BrowserRouter>
        </div>


    )
}

export default App;

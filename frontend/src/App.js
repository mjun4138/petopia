import './App.css';
import Main from "./pages/Main"
import {BrowserRouter, Route, Routes} from "react-router-dom";
import React from "react";
import Guest from "./pages/Guest";

const App = () => {

    return (
        <div className='App'>
            <BrowserRouter>
                <Routes>
                    <Route exact path="/"  element={<Guest/>}/>
                    <Route exact path="/account" element={<Main/>}/>
                </Routes>
            </BrowserRouter>
        </div>


    )
}

export default App;

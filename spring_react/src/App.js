import React from 'react';
import axios from 'axios';

import './App.css';
import {BrowserRouter} from "react-router-dom";
import Navbar from "./componnets/Navbar";
import Route from "react-router-dom/es/Route";
import Register from "./pages/Register";
import Login from "./pages/Login";
import Students from "./pages/Students";

class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            token : ""
        };
    }




    render() {
            return (
                <BrowserRouter>
                    <Navbar/>
                    <Route path='/login' component={Login} />
                    <Route path='/registration' component={Register} />
                    <Route path='/students' component={Students}/>
                    <div>  </div>
                </BrowserRouter>
            );
    }

}


export default App;

import React from "react";
import {NavLink} from "react-router-dom";

const Navbar = () => {
    return (
        <div>
            <div> <NavLink to="/login">login</NavLink> </div>
            <div> <NavLink to="/registration">registration</NavLink></div>
            <div> <NavLink to="/admin">admin</NavLink> </div>
            <div> <NavLink to="/students">students</NavLink> </div>
        </div>
    )
}

export default Navbar
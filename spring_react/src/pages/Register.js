import React from "react";
import axios from "axios";

class Register extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            username: "",
            password: "",
            passwordConfirm: ""
        }

        this.newUser = this.newUser.bind(this)
    }

    usernamehandler = (event) => {
        this.setState({
            username: event.target.value
        })
    };
    passwordhandler = (event) => {
        this.setState({
            password: event.target.value
        })
    };

    passwordConfirmhandler = (event) => {
        this.setState({
            passwordConfirm: event.target.value
        })
    };
    
    newUser = () => {
        if (this.state.password === this.state.passwordConfirm) {
            const newUserRegistration = {
                username: this.state.username,
                password: this.state.password
            };
            axios.post("http://localhost:8080/register", newUserRegistration)
                .then(res => {
                    console.log(res);
                })
                .catch(error => alert(error.response.data.message));
        }
        else alert("Пароли не совпадают!!!");
    };

    render() {
        return (
            <div>
                    <h1>User Registration</h1>
                    <label>Username :</label> <input type="text" value={this.state.username} onChange={this.usernamehandler} placeholder="Username..." /><br />
                    <label>Password :</label> <input type="password" value={this.state.password} onChange={this.passwordhandler} placeholder="Password..." /><br />
                    <label>Password confirm :</label> <input type="password" value={this.state.passwordConfirm} onChange={this.passwordConfirmhandler} placeholder="Password confirm..." /><br />
                    <div>
                    <button onClick={this.newUser}> Зарегистрироваться  </button>
                    </div>

            </div>
        )
    }
}

export default Register
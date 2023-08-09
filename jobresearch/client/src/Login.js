import React, {useState} from "react";
import "./Login.css";
import {Link} from "react-router-dom";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    function validateForm() {
        return username.length > 0 && password.length > 0;
    }

    async function handleSubmit(event) {
        event.preventDefault();
        const data = {username, password};
        const response = await fetch("http://localhost:8080/api/login", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        });

        console.log("Sending username:", username);
        console.log("Sending password:", password);

        if (response.ok) {
            const data = await response.json();
            localStorage.setItem('token', data.token);

            // alert(data.role);
            if (data.role === "admin") {
                window.location.href = '/admin-dashboard';
            } else {
                window.location.href = '/jobs';
            }
        } else {
            const errorData = await response.json();
            alert(errorData.message || "Login failed.");
        }
    }

    function handleUsernameChange(event) {
        setUsername(event.target.value);
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value);
    }

    return (
        <div className="container">
            <div className="form-wrapper">
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label className="label" htmlFor="username">Username</label>
                        <input
                            id="username"
                            className="input"
                            autoFocus
                            type="text"
                            value={username}
                            onChange={handleUsernameChange}
                        />
                    </div>
                    <div className="form-group">
                        <label className="label" htmlFor="password">Password</label>
                        <input
                            id="password"
                            className="input"
                            type="password"
                            value={password}
                            onChange={handlePasswordChange}
                        />
                    </div>
                    <button className="button loginButton" type="submit" disabled={!validateForm()}>
                        Login
                    </button>
                    <br/>
                    <Link to="/register" className="button registerButton">Register</Link>
                </form>
            </div>
        </div>
    );
}

export default Login;

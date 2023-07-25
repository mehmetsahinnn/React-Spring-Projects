import React, {useState} from 'react';
import {Link} from 'react-router-dom';
import './Login.css';

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        const response = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({username, password})
        });

        const data = await response.json();

        if (response.ok) {
            localStorage.setItem('username', username);
            localStorage.setItem('token', data.token);
            window.location.href = '/atm';  // navigate to the ATM page
        } else {
            alert('Login failed');
        }
    };

    return (
        <form onSubmit={handleSubmit} className="login-form">
            <label>
                Username : &nbsp;
                <input type="text" className="input-field" value={username} onChange={e => setUsername(e.target.value)}/>
            </label>
            <label>
                Password : &nbsp;
                <input type="password" className="input-field" value={password} onChange={e => setPassword(e.target.value)}/>
            </label>
            <input type="submit" className="submit-button" value="Login"/>
            <Link to="/register" className="register-link">Register</Link>
        </form>
    );
}

export default Login;

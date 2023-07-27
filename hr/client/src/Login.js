import React, {useState} from "react";
import {Link} from "react-router-dom";
import styles from './Login.module.css';

function Login() {
    const [name, setName] = useState("");  // changed from 'username' to 'name'
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(null);

        const response = await fetch('http://localhost:8080/api/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({name, password})  // changed 'username' to 'name'
        });

        if (response.ok) {
            const data = await response.json();
            localStorage.setItem('username', data.username); // Changed from username to data.username
            window.location.href = '/UserTable';
        }
        else {
            setError('Login failed');
        }
    }



    return (
        <div className={styles.container}>
            <h2 className={styles.title}>Login</h2>
            <form onSubmit={handleSubmit} className={styles.form}>
                <input
                    type="text"
                    placeholder="Name"  // changed placeholder from 'Username' to 'Name'
                    value={name}  // changed from 'username' to 'name'
                    onChange={(e) => setName(e.target.value)}  // changed 'setUsername' to 'setName'
                    className={styles.input}
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className={styles.input}
                />
                {error && <p className={styles.error}>{error}</p>}
                <button type="submit" className={styles.button}>Login</button>
                <Link to="/register" className={styles.link}>Register</Link>
            </form>
        </div>
    );
}

export default Login;

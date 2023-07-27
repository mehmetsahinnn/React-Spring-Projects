import React, {useState} from 'react';

function Register() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!username || !password) {
            setError('Username and Password are required.');
            return;
        }

        setLoading(true);
        setError(null);

        try {
            const response = await fetch('http://localhost:8080/api/register', {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({username, password, balance: 0})
            });


            const data = await response.json();

            if (response.ok) {
                localStorage.setItem('username', username);
                localStorage.setItem('token', data.token);
                window.location.href = '/';
            } else {
                window.location.href = '/';
            }
        } catch (error) {
            window.location.href = '/';
        }

        setLoading(false);
    };

    return (
        <form onSubmit={handleSubmit}
              style={{display: 'flex', flexDirection: 'column', width: '300px', margin: 'auto'}}>
            <fieldset>
                <legend style={{textAlign: 'center', color: 'blue'}}>Register</legend>
                <label>
                    Username:
                    <input type="text" value={username} onChange={e => setUsername(e.target.value)} required
                           style={{margin: '10px 0', padding: '5px'}}/>
                </label>
                <label>
                    Password:
                    <input type="password" value={password} onChange={e => setPassword(e.target.value)} required
                           style={{margin: '10px 0', padding: '5px'}}/>
                </label>
                {error && <p style={{color: 'red', textAlign: 'center'}}>{error}</p>}
                <input type="submit" value="Submit" disabled={loading}
                       style={{margin: '10px 0', padding: '10px', cursor: 'pointer'}}/>
            </fieldset>
        </form>
    );
}

export default Register;

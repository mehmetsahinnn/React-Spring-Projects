import React, {useState} from "react";

function Register() {
    const [name, setName] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [salary, setSalary] = useState("");
    const [message, setMessage] = useState(null);

    const handleRegister = async (e) => {
        e.preventDefault();
        setMessage(null);

        try {
            const response = await fetch("http://localhost:8080/api/register", {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({name, password, email, salary})
            });

            if (response.ok) {
                setMessage("Registration successful!");
                window.location.href='/'
            } else {
                if (response.headers.get("content-type") === "application/json") {
                    const data = await response.json();
                    setMessage(`Registration failed! ${data.message}`);
                } else {
                    setMessage(`Registration failed! Status: ${response.status}`);
                }
            }
        } catch (error) {
            setMessage(`Registration failed! ${error.toString()}`);
        }
    }


    return (
        <div>
            <h2>Register</h2>
            <form onSubmit={handleRegister}>
                <input
                    type="text"
                    placeholder="Name"
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />
                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                />
                <input
                    type="number"
                    placeholder="Salary"
                    value={salary}
                    onChange={e => setSalary(e.target.value)}
                />
                {message && <p>{message}</p>}
                <button type="submit">Register</button>
            </form>
        </div>
    );
}

export default Register;

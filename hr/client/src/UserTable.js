import React, {useState, useEffect} from "react";
import {Link} from "react-router-dom";

function UserTable() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/users')
            .then(response => response.json())
            .then(data => setUsers(data));
    }, []);

    const handleEdit = (e, id, field) => {
        const value = e.target.value;
        setUsers(users.map(user => user.id === id ? {...user, [field]: value} : user));
    }

    const handleSubmit = async (id) => {
        const userToUpdate = users.find(user => user.id === id);
        try {
            const response = await fetch(`http://localhost:8080/api/users/${id}`, {
                method: 'PUT',
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(userToUpdate)
            });
            if (!response.ok) {
                throw new Error(`Request failed: ${response.status}`);
            }
            alert('Changes saved successfully!');
        } catch (error) {
            alert(`Saving changes failed! ${error.toString()}`);
        }
    }

    const handleDelete = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/api/users/${id}`, {
                method: 'DELETE',
            });
            if (!response.ok) {
                throw new Error(`Request failed: ${response.status}`);
            }
            setUsers(users.filter(user => user.id !== id));  // Remove user from local state
            alert('User deleted successfully!');
        } catch (error) {
            alert(`Deleting user failed! ${error.toString()}`);
        }
    }

    return (
        <div>
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Password</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {users.map((user) => (
                    <tr key={user.id}>
                        <td>{user.id}</td>
                        <td><input value={user.name} onChange={e => handleEdit(e, user.id, 'name')}/></td>
                        <td><input type="password" value={user.password} onChange={e => handleEdit(e, user.id, 'password')}/></td>
                        <td>
                            <button onClick={() => handleSubmit(user.id)}>Submit</button>
                            &nbsp;
                            <button onClick={() => handleDelete(user.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <Link to="/">Exit</Link>
        </div>
    );
}

export default UserTable;

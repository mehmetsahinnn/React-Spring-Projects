import React, { useState, useEffect } from 'react';
import styles from './AdminDashboard.module.css';

function AdminDashboard() {
    const [users, setUsers] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('http://localhost:8080/api/users')
            .then((response) => {
                if (!response.ok) {
                    throw new Error("Failed to fetch data");
                }
                return response.json();
            })
            .then((data) => setUsers(data))
            .catch((error) => setError(error.message));
    }, []);

    if (error) {
        return <div>Error: {error}</div>;
    }

    return (
        <div className={styles.dashboardContainer}>
            <h2 className={styles.title}>Admin Dashboard - Kullanıcı Listesi</h2>
            <div className={styles.tableHeader}>
                <div className={styles.cell}>ID</div>
                <div className={styles.cell}>Username</div>
                <div className={styles.cell}>Password</div>
                <div className={styles.cell}>First Name</div>
                <div className={styles.cell}>Last Name</div>
                <div className={styles.cell}>Phone</div>
                <div className={styles.cell}>Job</div>
                <div className={styles.cell}>Military Status</div>
            </div>
            {users.map((user) => (
                <div key={user.id} className={styles.tableRow}>
                    <div className={styles.cell}>{user.id}</div>
                    <div className={styles.cell}>{user.username}</div>
                    <div className={styles.cell}>{user.password.slice(0,12) + " ..."}</div>
                    <div className={styles.cell}>{user.firstName}</div>
                    <div className={styles.cell}>{user.lastName}</div>
                    <div className={styles.cell}>{user.phoneNumber}</div>
                    <div className={styles.cell}>{user.jobExperience}</div>
                    <div className={styles.cell}>{user.militaryStatus ? "Done" : "No"}</div>
                </div>
            ))}
        </div>
    );
}

export default AdminDashboard;

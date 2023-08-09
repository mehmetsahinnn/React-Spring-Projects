import React, { useState } from 'react';
import styles from './Register.module.css';
import { useNavigate } from 'react-router-dom';

function Register() {
  const [formData, setFormData] = useState({
    username: '',
    password: '',
    firstName: '',
    lastName: '',
    phoneNumber: '',
    jobExperience: '', // Changed from "job"
    militaryStatus: false // Initialize with a boolean value
  });
  const [message, setMessage] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleCheckboxChange = (e) => { // A separate handler for the checkbox
    const { name, checked } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: checked
    }));
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    // Add frontend validation here if necessary

    try {
      const response = await fetch("http://localhost:8080/api/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData)
      });

      if (response.ok) {
        setMessage("Registration successful!");
        navigate('/');
      } else {
        const data = await response.json();
        setMessage(`Registration failed! ${data.message}`);
      }
    } catch (error) {
      setMessage("Network error. Please try again later.");
    }
  };

  return (
    <div className={styles.registerContainer}>
      <form onSubmit={handleRegister} className={styles.registerForm}>
        <input
          type="text"
          name="username"
          value={formData.username}
          onChange={handleChange}
          placeholder="Username"
          required
        />
        <input
          type="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          placeholder="Password"
          required
        />
        <input
          type="text"
          name="firstName"
          value={formData.firstName}
          onChange={handleChange}
          placeholder="First Name"
          required
        />
        <input
          type="text"
          name="lastName"
          value={formData.lastName}
          onChange={handleChange}
          placeholder="Last Name"
          required
        />
        <input
          type="text"
          name="phoneNumber"
          value={formData.phoneNumber}
          onChange={handleChange}
          placeholder="Phone"
          required
        />
        <input
          type="text"
          name="jobExperience"
          value={formData.jobExperience}
          onChange={handleChange}
          placeholder="Job Experience"
          required
        />

        <label>
          Completed Military Service:
          <input
            type="checkbox"
            name="militaryStatus"
            checked={formData.militaryStatus}
            onChange={handleCheckboxChange}
          />
        </label>

        <button type="submit" className={styles.submitButton}>Register</button>
      </form>
      {message && <div className={styles.message}>{message}</div>}
    </div>
  );
}

export default Register;




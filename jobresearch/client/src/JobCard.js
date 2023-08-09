import React from 'react';
import styles from './JobCard.module.css';

function JobCard({ title, company, location, description }) {
    return (
        <div className={styles.card}>
            <h3>{title}</h3>
            <p className={styles.companyName}>{company}</p>
            <p className={styles.location}>{location}</p>
            <p className={styles.description}>{description}</p>
            <button className={styles.applyButton}>Apply</button>
            &nbsp;
            <button className={styles.removeButton}>Remove</button>
        </div>
    );
}

export default JobCard;

import React from 'react';
import JobCard from './JobCard';
import styles from './Jobs.module.css';
import {Link} from "react-router-dom";

function Jobs() {
    const dummyJobs = [
        {
            title: 'Backend Developer',
            company: 'NextGen Solutions',
            location: 'Chicago',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Frontend Developer',
            company: 'Frontline Web Co.',
            location: 'San Diego',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Data Scientist',
            company: 'DataDriven',
            location: 'Boston',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'DevOps Engineer',
            company: 'Cloudscape Tech.',
            location: 'Miami',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Full Stack Developer',
            company: 'CodeCrafters',
            location: 'Austin',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'UI/UX Designer',
            company: 'Pixel Perfect Designs',
            location: 'Los Angeles',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'System Analyst',
            company: 'TechTree Corp.',
            location: 'Denver',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Database Administrator',
            company: 'DBMasters',
            location: 'Seattle',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Mobile App Developer',
            company: 'App Innovators',
            location: 'San Jose',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Security Specialist',
            company: 'GuardTech',
            location: 'Atlanta',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'QA Engineer',
            company: 'TestMasters Inc.',
            location: 'Orlando',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Site Reliability Engineer',
            company: 'Uptime Pros',
            location: 'Dallas',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Machine Learning Engineer',
            company: 'AI Pioneers',
            location: 'Palo Alto',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Blockchain Developer',
            company: 'CryptoCraft',
            location: 'San Francisco',
            description: 'Lorem ipsum dolor sit amet...'
        },
        {
            title: 'Cloud Architect',
            company: 'Nimbus Cloud Co.',
            location: 'Houston',
            description: 'Lorem ipsum dolor sit amet...'
        }
    ];


    return (
        <div className={styles.jobsContainer}>
            {dummyJobs.map((job, index) => (
                <JobCard key={index} {...job} />
            ))}
            <Link to={"/"} className={styles.linkBottom}>Home</Link>
        </div>
    );
}

export default Jobs;

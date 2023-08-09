import React, {useState} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

import Login from './Login';
import AdminDashboard from './admin-dashboard';
import Jobs from "./Jobs";
import Register from "./Register";

function App() {
    const [user, setUser] = useState(null);

    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login/>}/>
                <Route path={"/admin-dashboard"} element={<AdminDashboard/>}/>
                <Route path={"/jobs"} element={<Jobs/>}/>
                <Route path={"/register"} element={<Register/>}/>
            </Routes>
        </Router>
    );
}

export default App;
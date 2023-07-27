import React, {useState} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import UserContext from './UserContext';
import Login from './Login';
import UserTable from './UserTable';
import Register from './Register';

function App() {
    const [user, setUser] = useState(null);
    return (
        <UserContext.Provider value={{user, setUser}}>
            <Router>
                <Routes>
                    <Route path="/" element={<Login/>}/>
                    <Route path="/UserTable" element={<UserTable/>}/>
                    <Route path="/Register" element={<Register/>}/>
                </Routes>
            </Router>
        </UserContext.Provider>
    );
}

export default App;

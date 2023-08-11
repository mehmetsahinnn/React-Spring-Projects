import React, {useState} from 'react';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import UserContext from './UserContext';
import UserList  from "./UserList";

function App() {
    const [user, setUser] = useState(null);
    return (
        <UserContext.Provider value={{user, setUser}}>
            <Router>
                <Routes>
                    <Route path="/" element={<UserList/>}/>
                </Routes>
            </Router>
        </UserContext.Provider>
    );
}

export default App;

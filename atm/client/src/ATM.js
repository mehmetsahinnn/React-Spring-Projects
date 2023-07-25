import React, {useEffect, useState} from 'react';
import {Client} from '@stomp/stompjs';
import SockJS from 'sockjs-client';

function ATM() {
    const [amount, setAmount] = useState(0);
    const [username, setUsername] = useState(localStorage.getItem('username') || "");
    const [client, setClient] = useState(null);
    const [balance, setBalance] = useState(null);

    useEffect(() => {
        const newClient = new Client({
            webSocketFactory: () => new SockJS('http://localhost:8080/socket'),
            debug: (str) => {
                console.log(str);
            },
            reconnectDelay: 5000,
            heartbeatIncoming: 4000,
            heartbeatOutgoing: 4000,
        });

        newClient.onConnect = (frame) => {
            console.log('Connected: ' + frame);
            newClient.subscribe('/user/topic/balanceUpdate', message => {
                setBalance(JSON.parse(message.body));
            });
            newClient.publish({destination: '/app/getBalance', body: JSON.stringify({username: username})});
        };


        newClient.onStompError = (frame) => {
            console.log('Broker reported error: ' + frame.headers['message']);
            console.log('Additional details: ' + frame.body);
        };

        newClient.activate();
        setClient(newClient);

        return () => {
            if (newClient.connected) {
                newClient.deactivate();
            }
        }
    }, []);

    useEffect(() => {
        if (client && client.connected) {
            client.publish({destination: '/app/getBalance', body: JSON.stringify({username: username})});
        }
    }, [client, username]);


    const deposit = async () => {
        try {
            const response = await fetch(`http://localhost:8080/transactions/${username}/deposit/${amount}`, {method: 'POST'});
            const user = await response.json();
            setBalance(user.balance);
        } catch (error) {
            console.error('Error:', error);
        }
    };

    const withdraw = async () => {
        try {
            const response = await fetch(`http://localhost:8080/transactions/${username}/withdraw/${amount}`, {method: 'POST'});
            const user = await response.json();
            setBalance(user.balance);
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <div style={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}>
            <h1 style={{color: 'blue'}}>ATM</h1>
            <p>Your balance is: <span style={{fontWeight: 'bold'}}>{balance}</span></p>
            <input type="number" value={amount} onChange={e => setAmount(e.target.value)} placeholder="Enter Amount"
                   style={{padding: '10px', margin: '10px 0', width: '200px'}}/>
            <button onClick={deposit} style={{
                padding: '10px',
                margin: '10px 0',
                backgroundColor: 'green',
                color: 'white',
                cursor: 'pointer'
            }}>Deposit
            </button>
            <button onClick={withdraw} style={{
                padding: '10px',
                margin: '10px 0',
                backgroundColor: 'red',
                color: 'white',
                cursor: 'pointer'
            }}>Withdraw
            </button>
        </div>

    );
}

export default ATM;

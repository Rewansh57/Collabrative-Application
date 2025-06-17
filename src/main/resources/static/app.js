// app.js
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

document.addEventListener("DOMContentLoaded", () => {
    const response = document.getElementById("response");
    const messageInput = document.getElementById("message");
    const usernameInput = document.getElementById("username");
    const sendBtn = document.getElementById("send-button");
    const recipientInput = document.getElementById("recipient");
    const privateMessageInput = document.getElementById("private-message");
    const sendPrivateBtn = document.getElementById("send-private");
    const privateResponse = document.getElementById("private-response");

    const client = new Client({
        webSocketFactory: () => new SockJS('/ws'),
        reconnectDelay: 5000,
        debug: (str) => console.log(str),
    });

    client.onConnect = () => {
        console.log("Connected to broker");

        // Public messages
        client.subscribe('/topic/message', (message) => {
            const msg = JSON.parse(message.body);
            if (msg && response) {
                response.textContent = `[${msg.time}] ${msg.message.sender}: ${msg.message.content}`;
            }
        });

        // Private messages
        client.subscribe("/user/queue/messages", (message) => {
            const li = document.createElement('li');
            const body = JSON.parse(message.body);
            li.textContent = `${body.sender} -> ${body.recipient}`;
            privateResponse?.appendChild(li);
        });
    };

    client.onStompError = (frame) => {
        console.error('Broker error:', frame.headers['message']);
    };

    client.activate();

    sendBtn?.addEventListener('click', () => {
        const content = messageInput.value.trim();
        const sender = usernameInput.value.trim();
        if (!content || !sender) {
            alert("Enter both username and message to send.");
            return;
        }
        client.publish({
            destination: "/app/public-chat",
            body: JSON.stringify({ sender, content }),
        });
        messageInput.value = "";
    });

    sendPrivateBtn?.addEventListener("click", () => {
        const sender = usernameInput.value.trim();
        const recipient = recipientInput.value.trim();
        const content = messageInput.value.trim();
        if (!sender || !recipient || !content) {
            alert("Please enter all the details");
            return;
        }
        client.publish({
            destination: "/app/private-chat",
            body: JSON.stringify({ sender, recipient, content })
        });
        privateMessageInput.value = "";
    });
});

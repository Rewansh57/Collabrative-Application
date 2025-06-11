package org.example.projectsh.realtimechat.socket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class SocketEventListener {
    WebSocketConnectedRegistry webSocketConnectedRegistry;
    public SocketEventListener(WebSocketConnectedRegistry webSocketConnectedRegistry) {
        this.webSocketConnectedRegistry = webSocketConnectedRegistry;

    }

    @EventListener
    public void handleClientConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor accessor= StompHeaderAccessor.wrap(event.getMessage());
        String sessionId=accessor.getSessionId();
        String username=accessor.getFirstNativeHeader("username");


        System.out.println("User connected: "+sessionId);

        if (!(username==null)){
            webSocketConnectedRegistry.addClient(sessionId, username);
            System.out.println("Client connected: "+sessionId);


        }





    }
    public void handleClientDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor= StompHeaderAccessor.wrap(event.getMessage());
       String sesId= accessor.getSessionId();
       webSocketConnectedRegistry.removeClient(sesId);
       System.out.println("Client disconnected: "+sesId);



    }
}

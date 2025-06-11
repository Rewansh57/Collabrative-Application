package org.example.projectsh.realtimechat.socket;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class SocketEventListener {
    WebSocketConnectedRegistry webSocketConnectedRegistry;
    public SocketEventListener(WebSocketConnectedRegistry webSocketConnectedRegistry) {
        this.webSocketConnectedRegistry = webSocketConnectedRegistry;

    }

    @EventListener
    public void getHeaders(SessionConnectedEvent event) {
        StompHeaderAccessor accessor= StompHeaderAccessor.wrap()

    }
}

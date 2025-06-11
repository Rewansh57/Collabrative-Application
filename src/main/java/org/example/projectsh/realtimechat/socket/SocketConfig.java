package org.example.projectsh.realtimechat.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket

public class SocketConfig implements WebSocketMessageBrokerConfigurer {

    public void registerStompEndpoints (StompEndpointRegistry registry){
        registry.addEndpoint("/chat").withSockJS();
        registry.addEndpoint("/chat");
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();



    }
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");


    }
}

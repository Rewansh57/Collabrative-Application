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
        registry.addEndpoint("/chat").withSockJS();  //For a fallback condition
        registry.addEndpoint("/chat");
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
        //For development only as domain is yet to be given




    }
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableSimpleBroker("/topic", "/queue");
        /** Generalised endpoint for public and private chat
         relatively **/
        registry.setApplicationDestinationPrefixes("/app"); // For Default prefix addition in application
        registry.setUserDestinationPrefix("/user");  //For private Messages



    }
}

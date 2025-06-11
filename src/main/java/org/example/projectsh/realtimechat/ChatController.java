package org.example.projectsh.realtimechat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {
    @MessageMapping("/message")
    @SendTo("/topic/message")
    public OutputMessage getBroadcastedMessage(Message message){
        return new OutputMessage(message.getFrom,message.getBody(),time);


    }


}

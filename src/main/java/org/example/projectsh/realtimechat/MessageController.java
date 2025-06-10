package org.example.projectsh.realtimechat;

import org.example.projectsh.realtimechat.model.Message;
import org.example.projectsh.realtimechat.model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.text.SimpleDateFormat;
import java.util.Date;

@MessageMapping("/chat")
@SendTo("/topic/messages")

public class MessageController {
    public OutputMessage send(Message message){
        String time =new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(),message.getText(),time);


    }
}

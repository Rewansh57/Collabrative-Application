package org.example.projectsh.realtimechat;

import org.example.projectsh.realtimechat.model.Message;
import org.example.projectsh.realtimechat.model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Controller
public class ChatController {
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/public-chat")
    @SendTo("/topic/message")
    public OutputMessage getBroadcastedMessage(Message message){

        String time= LocalTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm")).
                toString();



        return new OutputMessage(message,time);



    }

    @MessageMapping("private-chat")
    public void privateChat(@Payload Message message /** Prepares the json object to a java object **/) {
        String time=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")).toString();

        OutputMessage msg=OutputMessage.builder()
                        .message(message)
                        .time(time)
                        .build();


        messagingTemplate.convertAndSendToUser(message.getRecipient(),"/queue/message",msg);


    }

    @MessageMapping("/chat")
    @SendToUser("/queue/reply")
    public String handleMessage(@Payload String message) {
        return "Hello " + message;
    }






}

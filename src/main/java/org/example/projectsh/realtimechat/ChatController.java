package org.example.projectsh.realtimechat;

import org.example.projectsh.realtimechat.model.Message;
import org.example.projectsh.realtimechat.model.OutputMessage;
import org.example.projectsh.realtimechat.model.Testing;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


@Controller
public class ChatController {
    @MessageMapping("/message")
    @SendTo("/topic/message")
    public OutputMessage getBroadcastedMessage(Message message){

        String time= LocalTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm")).
                toString();



        return new OutputMessage(message,time);



    }





}

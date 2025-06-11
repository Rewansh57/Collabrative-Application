package org.example.projectsh.realtimechat.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OutputMessage {
    private Message message;
    private String time;
    public OutputMessage(Message message,String time) {
        this.message = message;
        this.time = time;

    }


}

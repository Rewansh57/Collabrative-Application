package org.example.projectsh.realtimechat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class OutputMessage {
    private Message message;
    private String time;
    public OutputMessage(Message message,String time) {
        this.message = message;
        this.time = time;

    }


}

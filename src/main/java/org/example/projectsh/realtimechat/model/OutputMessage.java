package org.example.projectsh.realtimechat.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class OutputMessage {
    private String from;
    private String to;
    private String time;

    public OutputMessage(String from, String to, String time) {
        this.from = from;
        this.to = to;
        this.time = time;


    }




}

package org.example.projectsh.realtimechat.config;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class ShutDownManager {
    @PreDestroy
    public void onShutDown(){
        System.out.println("Server shutted down");
    }


}

package org.example.projectsh.realtimechat.socket;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component

public class WebSocketConnectedRegistry {

    private final Map<String,String > userSessionMap=new ConcurrentHashMap<>();

    public void addClient(String sessionId,String username){
        userSessionMap.put(sessionId,username);

    }
    public void removeClient(String sessionId){
        userSessionMap.forEach((key,value)->{
            if(value.equals(sessionId)){
                userSessionMap.remove(key);

            }

        });


    }
    public void removeOneClient(String sessionId){
        userSessionMap.remove(sessionId);

    }

    public String getClient(String sessionId){
        return "{"+sessionId+":"+userSessionMap.get(sessionId)+"}";



    }
    public Map<String,String> getAllClients(){
        return userSessionMap;


    }

}
